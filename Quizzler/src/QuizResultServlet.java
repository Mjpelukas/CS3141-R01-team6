import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizResultServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = (String) getServletContext().getAttribute("username");
		String quizName = (String) getServletContext().getAttribute("quizName");
		
		Enumeration questionResults = request.getParameterNames();
		while (questionResults.hasMoreElements()) {
			//go through each one
			//if the enum equals just an int
				//
			Object obj = questionResults.nextElement();
			System.out.println(obj.toString());			//this might not work out the way I think it will
			//if ()
		}
		
		/*
		ArrayList<String[]> question = request.getParameter("quizQuestions");
		for (int i = 0; i < length; i++) {
			String question = request.getParameter(question TODO: THIS SHOULD BE A CHANGING STRING);
			if (question == quizQuestions.get(i)[5]) {			//checking off the correct answer
				send back "question " + (i + 1) + " correct";
			} else {
				send back "question " + (i + 1) + " incorrect";
			}
		}
		*/
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}

}
