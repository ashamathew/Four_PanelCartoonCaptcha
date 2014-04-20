   import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
   import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
   import javax.servlet.*;
   import javax.servlet.http.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
   public class emplogin extends HttpServlet {
    String eid="";
    String pwd="";
    String password="";
	String email="";
        String Limageset="";
        String decryptedValue;
    Connection con=null;
    Statement st=null;
    Statement st2=null;
    ResultSet rs=null;
    RequestDispatcher rd=null;
    HttpSession sn=null;
    PrintWriter out=null;
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
        eid = req.getParameter("eid");
        System.out.println(eid);
        try {
            password = encr(req.getParameter("password"));
        } catch (Exception ex) {
            Logger.getLogger(emplogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        Limageset=req.getParameter("Limageset");
	email=req.getParameter("email");
        res.setContentType("text/html");
        out = res.getWriter();
        HttpSession sn = req.getSession(true);
        sn.setAttribute("eid",eid);
        sn.setAttribute("password",password);
        
		RequestDispatcher rd;

                try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/captcha","root","password");
            st = con.createStatement();
            rs = st.executeQuery("select * from profile where username='"+eid+"' and password='"+password+"' ");
            if(rs.next())
            {
                
				email=rs.getString(11);
                                sn.setAttribute("email",email);
				System.out.println(email);
             
                                if(Limageset.equals("set1"))
                                {
                                 String destination  ="/Multilevelsecurity/Loginset1.jsp";        
                                 res.sendRedirect(res.encodeRedirectURL(destination));
                                 //rd=req.getRequestDispatcher("passGen");
                                }
                                else if(Limageset.equals("set2"))
                                {
                                String destination  ="/Multilevelsecurity/Loginset4.jsp";        
                                res.sendRedirect(res.encodeRedirectURL(destination)); 
          
                                }

			 //rd = getServletConfig().getServletContext().getRequestDispatcher("/run.html");
             // reqDispatcher.forward(req,res);
	     }

            else {
	             String destination  ="/Multilevelsecurity/failure.jsp";        
                                res.sendRedirect(res.encodeRedirectURL(destination)); 
          
	           // out.println("welcome");
	        }
	      //  rd.forward(req,res);
        } catch(Exception e2) {
            //System.out.println("Exception : "+e2.toString());
             System.out.println(e2.getMessage());
        }
    }
    public String encr(String ss) throws Exception 
           {
        
    System.out.println("function called.......");
            String ALGO = "AES";
            byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
            //String keyValue=tf2.getText();
            Key key = new SecretKeySpec(keyValue, ALGO);
            String secret = ss;

            // String passwordDec = testencrypt.decrypt(passwordEnc);

            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(secret.getBytes());
            String encryptedValue = new BASE64Encoder().encode(encVal);
            
            return encryptedValue;
           }
}