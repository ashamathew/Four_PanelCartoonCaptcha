   import java.io.*;
   import java.sql.*;
   import javax.servlet.*;
   import javax.servlet.http.*;
import javax.xml.ws.Response;
   public class createuseraccount1 extends HttpServlet {
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    RequestDispatcher rd=null;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
        HttpSession sn = req.getSession(true);
        String username= req.getParameter("username");
        String password= req.getParameter("password");
        String firstname= req.getParameter("firstname");
        String lastname= req.getParameter("lastname");
        String address1= req.getParameter("address1");
		String address2= req.getParameter("address2");
		String city= req.getParameter("city");
		String state= req.getParameter("state");
		String zipcode= req.getParameter("zipcode");
		String telephone= req.getParameter("telephone");
                String emailid= req.getParameter("email");
                String imageset=req.getParameter("Imageset");
        sn.setAttribute("username", username);
        sn.setAttribute("password", password);
        sn.setAttribute("firstname", firstname);
        sn.setAttribute("lastname", lastname);
        sn.setAttribute("address1", address1);
        sn.setAttribute("address2", address2);
        sn.setAttribute("city", city);
        sn.setAttribute("state", state);
        sn.setAttribute("zipcode", zipcode);
        sn.setAttribute("telephone", telephone);
        sn.setAttribute("emailid", emailid);
        
        
                
                
                System.out.println(telephone+zipcode+state+city+address2+address1+lastname+firstname+password+username);
        RequestDispatcher rd;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/captcha","root","password");
//            st = con.createStatement();
//            String qry="select username from profile where username='"+username+"'";
//            System.out.println(qry);
//           rs=st.executeQuery(qry);
//	if(rs.next())
//        {
//            res.sendRedirect("newjsp1.jsp");
//        }
//        else
//        {
//            
            
            if(imageset.equals("set1"))
            {
             String destination  ="/Multilevelsecurity/set1.jsp";        
             res.sendRedirect(res.encodeRedirectURL(destination));
            }
            else if(imageset.equals("set2"))
            {
             String destination  ="/Multilevelsecurity/set4.jsp";        
             res.sendRedirect(res.encodeRedirectURL(destination));
            }
       // }     
            
        } catch(Exception e2) {
             rd=req.getRequestDispatcher("failure.jsp");
        }
    }
}