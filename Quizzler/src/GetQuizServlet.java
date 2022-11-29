import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetQuizServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		Database database = new Database();
		database.connect();
		
		//TODO:
		
		database.disconnect();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String quizName = request.getParameter("quizNameText");
		String username = (String) getServletContext().getAttribute("username");
		String courseText = request.getParameter("courseText");
		
		String publicChoice = request.getParameter("visibility");	// can be public or private
		boolean isPublic = false;
		if (publicChoice.equals("public")) {
		    // public selected
			isPublic = true;
		} else if (publicChoice.equals("private")) {
		    // private selected
			isPublic = false;
		} else {
			System.out.println("ERROR: public choice failed");
		}
		
		Database database = new Database();
		database.connect();
		
		String temporary = "false";
		//database.createQuiz(quizName, username, courseText, isPublic);
		database.createQuiz(quizName, username, courseText, temporary);
		
		database.disconnect();
	}

}
