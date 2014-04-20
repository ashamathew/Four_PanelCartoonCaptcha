import java.util.Random;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.smtp.*;
import java.util.Properties;
public class passGen extends HttpServlet{
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
	String email="";
    RequestDispatcher rd=null;
    String password="java@987";
       String from="datamaincent@gmail.com";
 static   Properties properties = new Properties();
   static{
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.socketFactory.port", "465");
     properties.put("mail.smtp.socketFactory.class",
                     "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", "465");
   }
	private static final String dCase = "abcdefghijklmnopqrstuvwxyz";
    private static final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String sChar = "!@#$%^&*";
    private static final String intChar = "0123456789";

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException,ServletException {
	HttpSession sn=req.getSession(true);
	String e=(String)sn.getAttribute("email");
        
      Random r = new Random();
      System.out.println("password sending in progress..."+e);
        String pass = "";
		 while (pass.length () != 6){
            int rPick = r.nextInt(4);
            if (rPick == 0){
                int spot = r.nextInt(26);
                pass += dCase.charAt(spot);
            } else if (rPick == 1) {
                int spot = r.nextInt (26);
                pass += uCase.charAt(spot);
            } else if (rPick == 2) {
                int spot = r.nextInt (9);
                pass += sChar.charAt(spot);
            } else if (rPick == 3){
                int spot = r.nextInt (10);
                pass += intChar.charAt (spot);
            }
        }
        System.out.println ("Generated Pass: " + pass);
		//String pass=req.getParameter("pass");
       String newpass="OTP for this session is : "+pass;
        sn.setAttribute("otpValue",pass);
               String username=sn.getAttribute("eid").toString();
                 RequestDispatcher rd;
       

            try
      {
         
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/captcha","root","password");
            st = con.createStatement();
            String qrry="insert into pass values('"+pass+"','"+username+"')";
            System.out.println(qrry);
            int add=st.executeUpdate(qrry);
            
            Session session123 = Session.getInstance(properties,new javax.mail.Authenticator() {
                
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
            }});
Message message = new MimeMessage(session123);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO, 
            InternetAddress.parse(e));
         message.setSubject("OTP FOR YOU");
         message.setText("otp for this session is :"+pass);
            Transport.send(message);  
          
         System.out.println("email sent successfully");
//          from="thamaskumar.s@pantechmail.com";
//        password="thamas@pantech";
//         Session session = Session.getDefaultInstance(properties,  
//            new javax.mail.Authenticator() {
//            protected PasswordAuthentication 
//            getPasswordAuthentication() {
//            return new 
//            PasswordAuthentication(from, password);
//            }});
//
//         Message message = new MimeMessage(session);
//         message.setFrom(new InternetAddress(from));
//         message.setRecipients(Message.RecipientType.TO, 
//            InternetAddress.parse(e));
//         message.setSubject("OTP FOR YOU");
//         message.setText("otp for this session is :"+pass);
//         Transport.send(message);
    
            
            
            //transport.sendMessage(m,m.getAllRecipients());
           
			//int i=st.executeUpdate("update log set username='"+username+"'");
            rd=req.getRequestDispatcher("GetOtp.jsp");
            con.close();
            st.close();
            rd.forward(req,res);
      
        } catch(Exception e2) {
             rd=req.getRequestDispatcher("failure.jsp");
        }
    }
}
  
   
    
