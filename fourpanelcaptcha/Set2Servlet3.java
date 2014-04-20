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

public class Set2Servlet3 extends HttpServlet{

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
			String str[] = request.getRequestURI().toString().split("/");
                        String id = str[3];
                        HttpSession sn = request.getSession(true);
                        sn.setAttribute("set1image3C",id);
			System.out.println("MySQL Connect Example."+id);
			String set1image1 = request.getParameter("set1image1A");
                        String set1image2 = request.getParameter("set1image2B");
                        String set1image3=id;
                         
		//	out.println(request.getRequestURI());
			System.out.println("Final1"+sn.getAttribute("set1image1"));
                        System.out.println("Final2"+sn.getAttribute("set1i112"));
                        System.out.println("Final3"+sn.getAttribute("set1image3C"));
                        String i1=sn.getAttribute("set1image1").toString()+".jpg";
                        String i2=sn.getAttribute("set1i112").toString()+".jpg";
                        String i3=sn.getAttribute("set1image3C").toString()+".jpg";
                           String usernam=sn.getAttribute("username").toString();  
                           String passw=sn.getAttribute("password").toString();
        String firstname=sn.getAttribute("firstname").toString();
        String lastname=sn.getAttribute("lastname").toString();
        String address1=sn.getAttribute("address1").toString();
        String address2=sn.getAttribute("address2").toString();
        String city=sn.getAttribute("city").toString();
        String state=sn.getAttribute("state").toString();
        String zipcode=sn.getAttribute("zipcode").toString();
        String telephone=sn.getAttribute("telephone").toString();
         String emailid=sn.getAttribute("emailid").toString();
        System.out.println("USER ID"+usernam+"E"+emailid);                              

        Statement stmt;
        ResultSet rs;
			try {
			
			
      Class.forName(driver).newInstance();
      conn = DriverManager.getConnection(url+dbName,userName,password);	
       stmt = conn.createStatement();
      System.out.println("Connected to the database");
      String qry="select * from profile where username='"+usernam+"'";
      System.out.println(qry);
      rs=stmt.executeQuery(qry);
      if(rs.next())
      {
          System.out.println("exists................");
          qry="update profile set password='"+passw+"',firstname='"+firstname+"',lastname='"+lastname+"',address1='"+address1+"',address2='"+address2+"',city='"+city+"',state='"+state+"',zipcode='"+zipcode+"',telephone='"+telephone+"',email='"+emailid+"',check1='"+i1+"',check2='"+i2+"',check3='"+i3+"' where username='"+usernam+"'";
          stmt.executeUpdate(qry);
          			//dispatcher.forward(request,response);
            String destination  ="/Multilevelsecurity/success1.jsp";        
             response.sendRedirect(response.encodeRedirectURL(destination));
      }
      else
      {
      String query = "insert into profile(username,password,firstname,lastname,address1,address2,city,state,zipcode,telephone,email,check1,check2,check3) values('"+usernam+"','"+passw+"','"+firstname+"','"+lastname+"','"+address1+"','"+address2+"','"+city+"','"+state+"','"+zipcode+"','"+telephone+"','"+emailid+"','"+i1+"','"+i2+"','"+i3+"')";

           

	    int i = stmt.executeUpdate(query);
             String destination  ="/Multilevelsecurity/success.jsp";        
             response.sendRedirect(response.encodeRedirectURL(destination));
      }		    
                                
          // getServletConfig().getServletContext().getRequestDispatcher("/set2.jsp").forward(request, response);

				System.out.println("Disconnected from database");
			} 
                        catch (Exception e) {
			e.printStackTrace();
			}
  }
}