package codice;

import java.io.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.*;
import model.*;
import dao.*;
import codice.*;

@WebServlet("/UploadPhotoServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
				 maxFileSize=1024*1024*10,      // 10MB
				 maxRequestSize=1024*1024*50)   // 50MB
public class UploadPhotoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static String SAVE_DIR ="/uploadTemp";
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ids = request.getParameter("id");
    	System.out.println(ids);
    	int id = Integer.parseInt(ids);
    	PhotoDaoImpl photoDao = new PhotoDaoImpl();
    	String appPath = request.getServletContext().getRealPath("");
	    String savePath = appPath + File.separator + SAVE_DIR;
    	System.out.println(savePath);

	    
	    File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		System.out.println(request.getParts());
		
		for (Part part : request.getParts()) {
			String fileName = extractFileName(part);
			System.out.println(fileName);
			if (fileName != null && !fileName.equals("")) {
				part.write(savePath + File.separator + fileName);
				System.out.println(savePath + File.separator + fileName);
				photoDao.loadPhoto(id, savePath + File.separator + fileName);
			}
		}
		System.out.println("dopo il for");

		/*RequestDispatcher view = request.getRequestDispatcher("/protected/prodottiAdmin.jsp");
		view.forward(request, response);*/
		response.sendRedirect(request.getContextPath() + "/protected/prodottiAdmin.jsp");
    	
    }
    
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }		
   
    
}
