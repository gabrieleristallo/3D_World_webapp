package codice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

import dao.PhotoDaoImpl;
//import it.unisa.servlet.ServletOutputStream;

/**
 * Servlet implementation class GetPictureServlet
 */
@WebServlet("/getPicture")
public class GetPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ids = request.getParameter("id");
    	PhotoDaoImpl photoDao = new PhotoDaoImpl();
    	if(ids != null) {
    		int id = Integer.parseInt(ids);
    		byte[] bt =photoDao.getPhoto(id);
    		ServletOutputStream out = response.getOutputStream();
			if(bt != null)
			{
				out.write(bt);
				response.setContentType("image/jpeg");
			}
			out.close();
    	}
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
    

    
}
