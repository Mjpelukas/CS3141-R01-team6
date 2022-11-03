import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSetsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = (String) getServletContext().getAttribute("username");
		Database database = new Database();
		database.connect();
		ArrayList<String> setNames = database.viewFlashcardSets(username);
		
		request.setAttribute("setNames", setNames);
		database.disconnect();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String table = "<table>\n"
				+ "<tr><th>Names</th><tr>"
				+ "<tbody>";
		for(String name : setNames) {
			table += "<tr><td>" + name + "</td></tr>";
		}
		table += "</tbody></table>";
		
		
		
		
		out.println("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" +
		         "<html>\n" +
		            "<head><title>Sets</title></head>\n" +
		            "<body>\n" +
		               "<h1 align = \"center\">Sets</h1>\n" +
		               "<ul>\n" +
		                  table +
		               "</ul>\n" +
		            "</body>" +
		         "</html>");
	}

}