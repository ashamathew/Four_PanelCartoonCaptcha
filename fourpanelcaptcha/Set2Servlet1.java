import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Set2Servlet1 extends HttpServlet{

   public void doGet(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
                        RequestDispatcher rd=null;
			String str[] = request.getRequestURI().toString().split("/");
                        String id = str[3];
                        HttpSession sn = request.getSession(true);
                        sn.setAttribute("set1image1",id);
			System.out.println("MySQL Connect Example."+id);
			
		//	out.println(request.getRequestURI());
			
			try {
				System.out.println("Connected to the database");
		               // rd=request.getRequestDispatcher("set2.jsp");
                               // rd.forward(request,response);
                                
                                //request.getRequestDispatcher(request.getServletPath()).forward(request, response);
                                
                             //    String nextJSP = "/set2.jsp";
				//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				//dispatcher.forward(request,response);
            String destination  ="/Multilevelsecurity/set5.jsp";        
             response.sendRedirect(response.encodeRedirectURL(destination));
          // getServletConfig().getServletContext().getRequestDispatcher("/set2.jsp").forward(request, response);

				System.out.println("Disconnected from database");
			} 
                        catch (Exception e) {
			e.printStackTrace();
			}
  }
}