package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ListIterator;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//Test123
public class HungryHungryServlet extends HttpServlet{
	 protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	 
	      
	          
	        String value = request.getParameter("param1");
	 
	 
	        response.getWriter().println("<html>");
	        response.getWriter().println("<input type=\"text\" name=\"username\">");
	        response.getWriter().println("<input type=\"text\" name=\"password\">");
	        response.getWriter().println(
	                "<input type=\"submit\" value=\"Log in\">Log in </input>");
	        response.getWriter().println("</form>");
	        response.getWriter().println("</html>");
	        doPost(request, response);
	 
	    }
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        // TODO Auto-generated method stub
	        System.out.println("JE::O");
	        URL url = new URL("http://www.cs.utexas.edu/calendar");
	          
	        File datafile = new File("HOLYFIREOFHELL.txt");
	        // jsoup test
	                try {
	                    Document doc = Jsoup.connect(url.toString()).get();
	                    Elements links = doc.select("td.single-day");
	                    ListIterator<Element> iter = links.listIterator();
	     
	                    FileOutputStream f = new FileOutputStream(datafile);
	                    PrintWriter pw = new PrintWriter(f);
	                    response.getWriter().write("<h2>" + "Current logs: " + "</h2>");
	                    while (iter.hasNext()) {
	                        Element e = (Element) iter.next();
	                        response.getWriter().println(e);
	                        pw.print(e);
	                        String linkUrl = e.absUrl("href");
	                        response.getWriter().println("<h3>" + linkUrl + "</h3>");
	     
	                    }
	                    pw.flush();
	                    pw.close();
	                     
	                } catch (Exception exp) {
	                    response.getWriter().println("<h3>" + "Link not valid. Please enter a valid URL." + "</h3>");
	                    exp.printStackTrace();
	                }
	     
	    }
}
