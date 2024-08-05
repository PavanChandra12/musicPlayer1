import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("username");
		String mail=request.getParameter("mail");
		String psw=request.getParameter("psw");
		String cpsw=request.getParameter("cpsw");
		String dob=request.getParameter("dob");
		
		
		PrintWriter pw=response.getWriter();
		if(psw.equals(cpsw))
		{
			
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer","root","root");
			
			Statement stmt=con.createStatement();
			int i=stmt.executeUpdate("insert into listeners(username,mail,password,dob) values('"+name+"','"+mail+"','"+psw+"','"+dob+"')");
			if(i==1)
			{
				response.sendRedirect("login.html");
				
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
		else
		{
			pw.print("check the password correctly");
		}
		
		
		
		
		
	}

}