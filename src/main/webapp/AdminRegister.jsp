<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login Page</h2>
    <form action="./registeradmin" method="post">
        <label for="uname">Username:</label><br>
        <input type="text" id="uname" name="uname"><br>
        <label for="pass">Password:</label><br>
        <input type="password" id="pass" name="pass"><br><br>
        <input type="submit" value="Login">
    </form>
    <% 
    String uname=request.getParameter("uname");
    String pass=request.getParameter("pass");
    if((uname!=null && pass!=null)){
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer","root","root");
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("select * from admin where name='"+uname+"' and password='"+pass+"'");
    if(rs.next())
    {
    
    response.sendRedirect("upload.html");
    }
    else{
        out.println("Invalid username or password!");
    }
    }
    catch(Exception e)
    {
    out.println(e);
    }
    }
    %>
</body>
</html>
