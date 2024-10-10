package codice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import codice.*;
import dao.*;
import model.*;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/plain; charset=UTF-8");
    	response.setHeader("Content-disposition","attachment; filename=acquisto.txt");

    	String id_ordines = request.getParameter("id_ordine");
    	int id_ordine = Integer.parseInt(id_ordines);

    	String dettagliAcquisto = generaFattura(id_ordine);
    	response.getWriter().println(dettagliAcquisto);
    }
    
    private String generaFattura(int id_ordine) {
        OrdineDaoImpl ordineDao = new OrdineDaoImpl();
        ArrayList<ProdottoCarrello> lista = ordineDao.getProdottiDaOrdine(id_ordine);

        StringBuilder fattura = new StringBuilder();
        fattura.append("3D World\n\n\n");
        fattura.append("Grazie per aver acquistato dal nostro negozio, qui troverà un riassunto del suo ordine.\n\n");
        int numero_articoli = 0;
        for(ProdottoCarrello p : lista) {
            numero_articoli += p.getQta();
        }
        fattura.append("Numero articoli: ").append(numero_articoli).append("\n");
        int i = 0;
        float prezzo_totale = 0;
        for(ProdottoCarrello p : lista) {
            String riga = String.format("Articolo %d:\t%-20s\t%.2f €\t x%d\tTotale: %.2f €\n", ++i, p.getNome(), p.getPrezzo(), p.getQta(), p.getPrezzo()*p.getQta());
            fattura.append(riga);
            prezzo_totale += p.getPrezzo()*p.getQta();
        }
        String riga = String.format("Totale ordine: %.2f €", prezzo_totale);
        fattura.append(riga);
        return fattura.toString();
    }

}




/*package codice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import dao.OrdineDaoImpl;
import model.ProdottoCarrello;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=acquisto.pdf");

        String id_ordines = request.getParameter("id_ordine");
        int id_ordine = Integer.parseInt(id_ordines);

        byte[] pdfBytes = generaFattura(id_ordine);
        response.getOutputStream().write(pdfBytes);
    }

    private byte[] generaFattura(int id_ordine) throws IOException {
        OrdineDaoImpl ordineDao = new OrdineDaoImpl();
        ArrayList<ProdottoCarrello> lista = ordineDao.getProdottiDaOrdine(id_ordine);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("3D World");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Grazie per aver acquistato dal nostro negozio, qui troverà un riassunto del suo ordine.");
                contentStream.newLine();
                contentStream.newLine();

                int numero_articoli = 0;
                for (ProdottoCarrello p : lista) {
                    numero_articoli += p.getQta();
                }
                contentStream.showText("Numero articoli: " + numero_articoli);
                contentStream.newLine();

                int i = 0;
                float prezzo_totale = 0;
                for (ProdottoCarrello p : lista) {
                    i++;
                    String riga = String.format("Articolo %d:\t%-20s\t%.2f €\t x%d\tTotale: %.2f €\n", i, p.getNome(),
                            p.getPrezzo(), p.getQta(), p.getPrezzo() * p.getQta());
                    contentStream.showText(riga);
                    contentStream.newLine();
                    prezzo_totale += p.getPrezzo() * p.getQta();
                }
                String riga = String.format("Totale ordine: %.2f €", prezzo_totale);
                contentStream.showText(riga);
                contentStream.endText();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);

            // Verifica se il documento PDF è stato salvato correttamente
            if (document.getNumberOfPages() == 0) {
                throw new IOException("Il documento PDF non contiene pagine");
            }

            return baos.toByteArray();
        }
    }
}*/
