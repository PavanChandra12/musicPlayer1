

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name=request.getParameter("username");

		String psw=request.getParameter("psw");
		
		
		PrintWriter pw=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer","root","root");
			
			Statement stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from admin where name='"+name+"' and password='"+psw+"'");
			if(rs.next())
			{
			response.sendRedirect("upload.html");
			}
			else
			{
				pw.print("entered wrong details pls check");
				response.sendRedirect("adminlogin.html");
			}
			
			
		}
		catch(Exception e)
		{
			pw.print(e);
		}
		
		
	}

}
