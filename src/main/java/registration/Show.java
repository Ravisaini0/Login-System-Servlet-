package registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class Show
 */
@WebServlet("/Show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();  
		try{  
			 				
			Class.forName("com.mysql.jdbc.Driver");//jdbc driver   register
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/mybank","root","ravisaini");  
Statement stmt=con.createStatement();  
//ResultSet rs=stmt.executeQuery("select * from student");  
//		while(rs.next())  
//			out.println(rs.getString(2)+"|"+rs.getString(3)+"<br/>");  
		ResultSet rs=stmt.executeQuery("select * from registionpage where email='"+request.getParameter("email")+"' and password='"+request.getParameter("password")+"'");  
		if(rs.next())  
		response.sendRedirect("bank Dashbord.html"); 
		else
			
			response.sendRedirect( "login.html");
		con.close();  

//			con.close();  
//			response.setContentType("text/html");  
//			
//		//	String email=request.getParameter("email");//will return value  
//			//String password=request.getParameter("passwd");//will return value  
//			
//		
//			out.println("Welcome to first servlet");  
//			out.println("<a href='Welcome'>visit to welcome</a>");  
//			out.close();  
			  
			}catch(Exception e){out.println(e);}  
	
}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
