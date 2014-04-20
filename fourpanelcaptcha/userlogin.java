   import java.io.*;
   import java.sql.*;
   import javax.servlet.*;
   import javax.servlet.http.*;
   public class userlogin extends HttpServlet {
    String username="";
	String email="";
	String eid="";
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
	RequestDispatcher rd=null;
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
        username = req.getParameter("username");
		email=req.getParameter("email");
		System.out.println(email);
       	HttpSession sn = req.getSession(true);
        sn.setAttribute("eid",username);
        
RequestDispatcher rd;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/captcha","root","password");
            st = con.createStatement();
            rs = st.executeQuery("select email from profile where username");
            if(rs.next()) {
				email=rs.getString(11);
                rd=req.getRequestDispatcher("mailAPI.jsp");
			//	sn.setAttribute("dpm",department);
            } else {

               rd=req.getRequestDispatcher("failure.jsp");
	        }
	        rd.forward(req,res);
        }
        catch(Exception e2)
         {
            System.out.println("Exception : "+e2.toString());
        }
    }
}