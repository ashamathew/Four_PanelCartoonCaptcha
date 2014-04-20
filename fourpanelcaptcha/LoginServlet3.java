import java.sql.*;
import javax.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet3 extends HttpServlet{

   public void doGet(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
       			Connection conn = null;
			String url = "jdbc:mysql://localhost:3306/";
			String dbName = "captcha";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "root";
			String password = "password";
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
                        RequestDispatcher rd=null;
                        Statement stmt;
                        ResultSet rs;
                        String str[] = request.getRequestURI().toString().split("/");
                        String id = str[3];
                        System.out.println("id is"+id);
                        HttpSession sn = request.getSession(true);
                        sn.setAttribute("lset1image3C",id);
			System.out.println("MySQL Connect Example."+id);
			String set1image1 = request.getParameter("lset1image1A");
                        String set1image2 = request.getParameter("lset1image2B");
                        String set1image3=id;
                         
		//	out.println(request.getRequestURI());
			System.out.println("Final1"+sn.getAttribute("lset1image1"));
                        System.out.println("Final2"+sn.getAttribute("lset1i112"));
                        System.out.println("Final3"+sn.getAttribute("lset1image3C"));
                        String i1=sn.getAttribute("lset1image1").toString()+".jpg";
                        String i2=sn.getAttribute("lset1i112").toString()+".jpg";
                        String i3=sn.getAttribute("lset1image3C").toString()+".jpg";
                           
                  
                    String usernam=sn.getAttribute("eid").toString();  
                        String  email=sn.getAttribute("email").toString();
                        System.out.println("----------");
                        System.out.println(i1+i2+i3+usernam+email);
                        System.out.println("----------");
	try {
             Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/captcha",userName,password);
             stmt = conn.createStatement();
             rs = stmt.executeQuery("select * from profile where username='"+usernam+"' && check1='"+i1+"'&& check2='"+i2+"'&& check3='"+i3+"'");
             if(rs.next())
                {
                
		email=rs.getString(11);
                sn.setAttribute("email",email);
		System.out.println(email);
                    //rd=request.getRequestDispatcher("passGen");
                   
			 //rd = getServletConfig().getServletContext().getRequestDispatcher("/run.html");
            //  rd.forward(request,response);
              
              String destination  ="/Multilevelsecurity/passGen";        
                    response.sendRedirect(response.encodeRedirectURL(destination)); 
	
                }
            else 
                {
	            String destination  ="/Multilevelsecurity/failure.jsp";        
                    response.sendRedirect(response.encodeRedirectURL(destination)); 
                }
           
			
} 
                        
                        
                        catch (Exception e) 
                        {
			e.printStackTrace();
			}
  }
}