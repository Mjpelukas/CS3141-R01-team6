import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetQuizQuestionsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//TODO: WE EVENTUALLY NEED TO CHANGE username TO quizOwner.
		//SINCE WE ARE USING USERNAME, WE CAN CURRENTLY ONLY ACCESS PRIVATE QUIZZES
		String username = (String) getServletContext().getAttribute("username");
		String quizName = (String) getServletContext().getAttribute("quizName");
		
		Database database = new Database();
		database.connect();
		
		ArrayList<String[]> quizQuestions = database.viewQuizQuestions(username, quizName);
		
		request.setAttribute("quizQuestions", quizQuestions);
		
		//TODO: IDK WHAT THIS IS EVEN FOR????
		/*
		try {
			request.getRequestDispatcher("displaySets.jsp").forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
		database.disconnect();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		//TODO: HANDLE WHEN THE QUIZ IS SUBMITTED HERE
		
		//TODO: THIS IS THE IDEA BUT IT FOR SURE DOESN'T WORK
		//ArrayList<String[]> questionAnswers = new ArrayList<String[]>();
		
		/*
		int numCorrect = 0;
		//for i = 1 to number of question responses {		//note for this i starts at 1? might be changed
			if (questionAnswers.get(i).equals("correct answer here")) {
				numCorrect++;
			}
		//}
		*/
		
		ArrayList<String> stuff1 = (ArrayList<String>) request.getAttributeNames();
		for (int i = 0; i < stuff1.size(); i++) {
			System.out.println("stuff1 " + (i + 1) + ": " + stuff1.get(i));
		}
		System.out.println();
		ArrayList<String> stuff2 = (ArrayList<String>) request.getParameterNames();
		for (int i = 0; i < stuff2.size(); i++) {
			System.out.println("stuff2 " + (i + 1) + ": " + stuff2.get(i));
		}
		
		
	}

}
