

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name=request.getParameter("uname");
		
		String psw=request.getParameter("pass");
		
		String mail=request.getParameter("mail");
		
		PrintWriter pw=response.getWriter();
	
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer","root","root");
			
			Statement stmt=con.createStatement();
			int i=stmt.executeUpdate("insert into admin(name,mail,password) values('"+name+"','"+mail+"','"+psw+"')");
			if(i==1)
			{
				//response.sendRedirect("log.html");
				//response.sendRedirect("upload.html");
				pw.print("data registered successfully");
				}
			
			else
			{
				pw.println("you entered incoorect data");
			}
		}
		catch(Exception e)
		{
			pw.print(e);
		}
		
	
		
		
		
		
	}

}
