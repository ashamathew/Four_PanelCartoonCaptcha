   import java.io.*;
   import java.sql.*;
   import javax.servlet.*;
   import javax.servlet.http.*;
   public class VerifyOtp extends HttpServlet {
    String password="";
    String email="";
    String Limageset="";
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    RequestDispatcher rd=null;
    HttpSession sn=null;
    PrintWriter out=null;
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException 
    {
        res.setContentType("text/html");
        out = res.getWriter();
        HttpSession sn = req.getSession(true);
        String username=sn.getAttribute("eid").toString();
        String otpValue=req.getParameter("otpValue").toString();
        System.out.println("E="+username+"OTP"+otpValue);
	RequestDispatcher rd;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/captcha","root","password");
            st = con.createStatement();
            rs = st.executeQuery("select * from pass where pass='"+otpValue+"' && username='"+username+"'");
            if(rs.next())
            {
		System.out.println("ssss"+rs.getString(1));
                System.out.println("dddd"+rs.getString(2));
                System.out.println("delete from pass where username="+username);
                int s=st.executeUpdate("truncate table pass");                     
                String destination  ="/Multilevelsecurity/OtpSuccess.jsp";        
                res.sendRedirect(res.encodeRedirectURL(destination));
             }

       else {
	        String destination  ="/Multilevelsecurity/failure.jsp";        
                res.sendRedirect(res.encodeRedirectURL(destination)); 
     	    }
	
        } catch(Exception e2) 
        {
          out.println(e2);
        }
    }
}