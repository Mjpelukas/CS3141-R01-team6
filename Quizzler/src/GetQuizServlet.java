import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetQuizServlet extends HttpServlet {
	//    ."/QuizQuestions"
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String quizID = request.getParameter("createQuestion");
		request.setAttribute("QID", quizID);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		

	}

}
