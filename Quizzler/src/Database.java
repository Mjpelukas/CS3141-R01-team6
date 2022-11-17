import java.sql.*;
import java.util.*;

public class Database {
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	public static void main(String args[]) throws SQLException {
		Database database = new Database();
		
		database.connect();
		
		// TODO: Obtain the username and password from the POST method when the user
		// inputs login info.
		// database.authenticateLogin("[USERNAME]", "[PASSWORD]");
		
		database.disconnect();
	}
	
	// Connect to the database.
	public int connect() {
		try {
			// TODO: obtain the real URL, username, and password for the database
			
			connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com/0MkLwGAyw5", "0MkLwGAyw5",
					"wVY3yXffPP");
			// System.out.println("Connected to the database!");
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			exception.printStackTrace();
			return 1;
		}
		return 0;
	}

	// Disconnect from the database.
	public void disconnect() {
		try {
			connection.close();
			// System.out.println("Disconnected from the database!");
			
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
		}
	}
	
	// Authenticate a user's credentials when they attempt to log in.
	public boolean authenticateLogin(String username, String password) {
		PreparedStatement preparedStatement = null;
		// System.out.println(username + "|" + password);
		try {
			// Determine the MySQL statement to obtain the row matching the entered username
			// and password.
			String query = "SELECT * FROM Users where username = ? and user_password = sha2(?, 256)";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			return preparedStatement.executeQuery().next();
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}
	
	public boolean createAccount(String username, String password) {
		try {
			String insert =
					
					"INSERT INTO Users values('" + username + "', null, sha2('" + password + "', 256));";
			
			Statement statement = connection.prepareStatement(insert);
			// TODO: Create optional email functionality
			// if(email != null)
			// ps.setString(2, email);
			// else ps.setNull(2, 0);
			statement.execute(insert);
			
			return authenticateLogin(username, password);
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}

	public boolean createFlashcardSet(String username, String setName, String description, String isPublic) {
		try {
			String insert =
					"INSERT INTO FlashcardSets values('" + setName + "', '"
					 + username + "', '" + description + "', null, " + isPublic + ", null);";
			
			Statement statement = connection.prepareStatement(insert);
			statement.execute(insert);
			
			return true;
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}
	
	// Let a user view a flashcard set corresponding to a provided set name.
	public ArrayList<String[]> viewFlashcardSet(String setName, String username) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// Determine the MySQL statement to obtain the rows matching the provided set
			// name and username.
			String query = "SELECT term, term_definition from Flashcards where setName = ? and setOwner = ?";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, setName);
			preparedStatement.setString(2, username);
			resultSet = preparedStatement.executeQuery();
			
			// Iterate through each flashcard's term and definition to be entered in the
			// HTML table.
			ArrayList<String[]> flashcards = new ArrayList<String[]>();
			
			while (resultSet.next()) {
				String term = resultSet.getString(1), definition = resultSet.getString(2);
				String[] flashcard = { term, definition };
				flashcards.add(flashcard);
			}
			
			return flashcards;
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
		}
	}
	
	/** NEW STUFF **/
	public ArrayList<String> viewFlashcardSets(String username) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// Determine the MySQL statement to obtain the rows matching the provided set
			// name and username.
			String query = "SELECT setName from FlashcardSets where setOwner = ?";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			// Iterate through each flashcard set's name and add it to the arraylist
			ArrayList<String> sets = new ArrayList<String>();
			
			while (resultSet.next()) {
				String setName = resultSet.getString(1);
				sets.add(setName);
			}
			
			return sets;
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
		}
	}
	
	public boolean createFlashcard
	(String term, String term_definition, String setName, String setOwner) {
		try {
			
			// TODO: Make sure this isn't vulnerable to sql injection
			String insert = 
					"INSERT INTO Flashcards values('" + term + "', '" + term_definition + "', '" + setName + "', '" + setOwner + "');";
			
			// Execute the statement to insert the flashcard into the databse
			Statement statement = connection.prepareStatement(insert);
			statement.execute(insert);
			
			
			// TODO: ERROR Messages, shouldn't always work, should sometimes fail when the flashcard term is a repeat
			
			return true;
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}
	
	// Let a user view a flashcard set corresponding to a provided set name.
	//NOTE: there's no username for the set, so username isn't a parameter
	public ArrayList<String[]> viewFlashcards(String setName) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// Determine the MySQL statement to obtain the rows matching the provided set
			// name and username.
			String query = "SELECT term, term_definition from Flashcards where setName = ?";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, setName);
			resultSet = preparedStatement.executeQuery();
			
			// Iterate through each flashcard's term and definition to be entered in the
			// HTML table.
			ArrayList<String[]> flashcards = new ArrayList<String[]>();
			
			while (resultSet.next()) {
				String term = resultSet.getString(1), definition = resultSet.getString(2);
				String[] flashcard = {term, definition};
				flashcards.add(flashcard);
			}
			
			return flashcards;
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
		}
	}

	
	// Delete a flashcard set provided a flashcard set name.
	public boolean deleteFlashcardSet(String setName, String username) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			
			// Create the SQL statement to delete the row matching the given parameters.
			String query = "DELETE FROM FlashcardSets "
					+ "WHERE setName = '" + setName + "' AND setOwner = '" + username + "';";

			// Use prepared statements to prevent SQL injection.
			//connection.createPreparedStatement().execute(query);
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			return true;
		} 
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}

    // Delete a single flashcard provided a term.
	public boolean deleteFlashcard(String setName, String term) 
    {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			// Create the SQL statement to delete the row matching the given parameters.
			String query = "DELETE FROM Flashcards WHERE setName = ? AND term = ?";

			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, setName);
            preparedStatement.setString(2, term);
			resultSet = preparedStatement.executeQuery();
			
			return true;
		} 
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}

    // Delete a quiz provided a quiz name.
	public boolean deleteQuiz(String quizName, String username) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			// Create the SQL statement to delete the row matching the given parameters.
			String query = "DELETE FROM Quizzes WHERE quizName = ? AND setOwner = ?";

			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, quizName);
			preparedStatement.setString(2, username);
			resultSet = preparedStatement.executeQuery();
			
			return true;
		} 
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}

    // Delete a single quiz question provided a question number.
	public boolean deleteQuizQuestion(String quizName, int questionNumber) 
    {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			// Create the SQL statement to delete the row matching the given parameters.
			String query = "DELETE FROM QuizQuestions WHERE quizName = ? AND question = ?";

			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, quizName);
			preparedStatement.setInt(2, questionNumber);
			resultSet = preparedStatement.executeQuery();
			
			return true;
		} 
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}

	// Search another user's list of public flashcard sets by their username.
	public ArrayList<String> searchUserFlashcards(String username)
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try
		{
			// Create the SQL query to obtain another user's list of flashcard sets if it is public.
			String query = "SELECT setName FROM FlashcardSets WHERE setOwner = ? AND isPublic = 1";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			// Iterate through each set name to be shown on the website.
			ArrayList<String> sets = new ArrayList<String>();
			while (resultSet.next())
				sets.add(resultSet.getString(1));
			
			return sets;
		}
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
		}
	}

	// Search another user's list of public quizzes by their username.
	public ArrayList<String> searchUserQuizzes(String username)
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try
		{
			// Create the SQL query to obtain another user's list of quizzes.
			String query = "SELECT quizName FROM Quizzes WHERE setOwner = ? AND isPublic = 1";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			// Iterate through each quiz name to be shown on the website.
			ArrayList<String> quizzes = new ArrayList<String>();
			while (resultSet.next())
				quizzes.add(resultSet.getString(1));
			
			return quizzes;
		}
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
		}
	}

    // Search another user's public flashcard set by set name.
	public boolean searchFlashcardSet(String setName) 
    {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			// Create the SQL query to obtain the searched flashcard set.
			String query = "SELECT setName, username FROM FlashcardSets WHERE setName = ? AND isPublic = 1";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, setName);
			resultSet = preparedStatement.executeQuery();

			// View the flashcard set if it exists.
			if(resultSet != null)
				viewFlashcardSet(resultSet.getString(1), resultSet.getString(2));

			return true;
		} 
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}

	// Search for another user's public quiz by quiz name.
	public boolean searchQuiz(String quizName) 
    {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			// Create the SQL query to determine whether the searched quiz is public.
			String query = "SELECT quizName, username FROM Quizzes WHERE quizName = ? AND isPublic = 1";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, quizName);
			resultSet = preparedStatement.executeQuery();

			// Take the quiz if it exists.
			if(resultSet != null)
				takeQuiz(resultSet.getString(1), resultSet.getString(2));

			return true;
		} 
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return false;
		}
	}
	
	//temporary to prevent errors
	public void takeQuiz(String one, String two){}
	
	
	// Search for another user's flashcard set by its description.
	// Fuzzy Search: SELECT * FROM `TABLE` WHERE MATCH(`COLUMN`) AGAINST("SEARCH" IN NATURAL LANGUAGE MODE);
	public boolean searchFlashcardSetDescription(String description)
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
 	try 
		{
			// Create the SQL query to perform a fuzzy search of the flashcard set's description.
			String query = "SELECT setName, username FROM FlashcardSets WHERE MATCH('setName') AGAINST(? IN NATURAL LANGUAGE MODE) AND isPublic = 1";
		// 		// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, description);
			resultSet = preparedStatement.executeQuery();
 		// View the flashcard set if it exists.
 		if(resultSet != null)
 			viewFlashcardSet(resultSet.getString(1), resultSet.getString(2));
 		return true;
	} 
	catch (SQLException exception) 
 	{
 		System.out.println("SQLException: " + exception.getMessage());
 		System.out.println("SQLState: " + exception.getSQLState());
		System.out.println("VendorError: " + exception.getErrorCode());
 		return false;
 	}
 }

 // Search for another user's quiz by its description.
 // Fuzzy Search: SELECT * FROM `TABLE` WHERE MATCH(`COLUMN`) AGAINST("SEARCH" IN NATURAL LANGUAGE MODE);
	public boolean searchQuizDescription(String description)
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	 	try 
		{
	 		// Create the SQL query to perform a fuzzy search of the quiz's description.
	 		String query = "SELECT quizName, username FROM Quizzes WHERE MATCH('quizName') AGAINST(? IN NATURAL LANGUAGE MODE) AND isPublic = 1";
	
	 		// Use prepared statements to prevent SQL injection.
	 		preparedStatement = connection.prepareStatement(query);
	 		preparedStatement.setString(1, description);
	 		resultSet = preparedStatement.executeQuery();
	 		
	 		// Take the quiz if it exists.
	 		if(resultSet != null)
	 			takeQuiz(resultSet.getString(1), resultSet.getString(2));

	 		return true;
	 	} 
	 	catch (SQLException exception) 
	 	{
	 		System.out.println("SQLException: " + exception.getMessage());
	 		System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
	 		return false;
	 	}
	 }
	 
}
