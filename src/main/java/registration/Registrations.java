package registration;
import java.io.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



/**
 * Servlet implementation class Registrations
 */
@WebServlet("/Registrations")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  // 1MB
	    maxFileSize = 1024 * 1024 * 5,     // 5MB
	    maxRequestSize = 1024 * 1024 * 10  // 10MB
	)
public class Registrations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrations() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  Part filePart = request.getPart("image"); // input name
	        String fileName = filePart.getSubmittedFileName();

	        // upload folder path
	        String uploadPath = getServletContext().getRealPath("") + "img";

	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }

	        // save file
	        filePart.write(uploadPath + File.separator + fileName);

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<h3>Image Uploaded Successfully!</h3>");
	        out.println("<img src='img/" + fileName + "' width='200'>");
		
		String username = request.getParameter("uname");
		String email =request.getParameter("email");
		String password = request.getParameter("password");
		// PrintWriter out=response.getWriter();
		 out.print(username+"|"+email+"|"+password);
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myBank","root","ravisaini");
			PreparedStatement ps = con.prepareStatement("insert into registionpage (username,email,password) values(?,?,?)");
			ps.setString(1, username);
			ps.setString(2,email);
			ps.setString(3, password);
			ps.executeUpdate();
			con.close();
			response.sendRedirect("login.html");
			
			
		 }
		 catch(Exception e){out.print(e.getMessage());}
         out.close();
	}
	

}
