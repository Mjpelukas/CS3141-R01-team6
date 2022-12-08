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
		System.out.println(database.setInfo("maxim", "test1").get(0));
		
		
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
	public void updateFlashcardSet(String oldSetName, String setName, String username, String description, String isPublic) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try 
        {
            if (!(newSetName.equals(setName))) {
            // Create the SQL statement to delete the row matching the given parameters.
            String query = "UPDATE FROM FlashcardSets " + "SET setName = '";
            		+ setName + "',"
                    + "description = '" + description + "',"
                    + "isPublic = " + isPublic
                    + "WHERE setName = '" + setName + "' AND setOwner = '" + username + "';";

            // Use prepared statements to prevent SQL injection.
            //connection.createPreparedStatement().execute(query);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(query);
            return;
        } 
        catch (SQLException exception) 
        {
            System.out.println("SQLException: " + exception.getMessage());
            System.out.println("SQLState: " + exception.getSQLState());
            System.out.println("VendorError: " + exception.getErrorCode());
            
            return;
        }
    }
	
	public boolean createQuiz(String username, String quizName, String isPublic) {
		try {
			String insert =
					"INSERT INTO Quizzes values('" + quizName + "', '"
					 + username + "', null, " + isPublic + ");";
			
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
	
	
	public ArrayList<String> setInfo(String setName, String username){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// Determine the MySQL statement to obtain the rows matching the provided set
			// name and username.
			String query = "SELECT setName, description, isPublic from FlashcardSets where setName = ? and setOwner = ?";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, setName);
			preparedStatement.setString(2, username);
			resultSet = preparedStatement.executeQuery();
			
			// Iterate through each flashcard's term and definition to be entered in the
			// HTML table.
			ArrayList<String> items = new ArrayList<String>();
			
			/**while (resultSet.next()) {
				String item = resultSet.getString(1);
				items.add(item);
			}**/

			resultSet.next();
			items.add(setName);
			String description = resultSet.getString(2);
			items.add(description);
			String isPublic = resultSet.getString(3);
			items.add(isPublic);
			
			return items;
		} catch (SQLException exception) {
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
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
	
	/** How you view the flashcard **/
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
	
	public ArrayList<String> viewQuizzes(String username) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// Determine the MySQL statement to obtain the rows matching the provided set
			// name and username.
			String query = "SELECT quizName from Quizzes where quizOwner = ?";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			
			// Iterate through each flashcard set's name and add it to the arraylist
			ArrayList<String> quizNames = new ArrayList<String>();
			
			while (resultSet.next()) {
				String quizName = resultSet.getString(1);
				quizNames.add(quizName);
			}
			
			return quizNames;
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
/* !UNUSED! Too complicated because why would you search by username
 * maybe one day my sweet 
	// Search another user's list of public flashcard sets by their username.
	public ArrayList<String> searchUserFlashcards(String setname, String username)
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int setStringInt = 1;
		boolean doingSets = false;
		boolean doingUsername = false;
		try
		{
			// Create the SQL query to obtain another user's list of flashcard sets if it is public.
			String query = "SELECT setName FROM FlashcardSets WHERE isPublic = 1";
			if(setname != null){
				 query += "and setName = ?";
				 doingSets = true;
			}
			if(username != null){
				 query += "and setOwner = ?";
				 doingUsername = true;
				 
			}
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			if(doingSets) {
				preparedStatement.setString(setStringInt, setname);
				setStringInt++;
			}
			if(doingUsername) {
				preparedStatement.setString(setStringInt, username);
			}
			
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
*/ //@UNUSED! Too complicated because why would you search by username
/*	// Search another user's list of public quizzes by their username.
	public ArrayList<String> searchUserQuizzes(String username)
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try
		{
			// Create the SQL query to obtain another user's list of quizzes.
			String query = "SELECT quizName FROM Quizzes WHERE quizOwner = ? AND isPublic = 1";
			
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
*/
    // Search another user's public flashcard set by set name.
	public ArrayList<String[]> searchFlashcardSet(String setName) 
    {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			// Create the SQL query to obtain the searched flashcard set.
			String query = "SELECT setName, setOwner FROM FlashcardSets WHERE setName = ? AND isPublic = 1";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, setName);
			resultSet = preparedStatement.executeQuery();

			// Return a list of the flashcard set names that match the search query.
			ArrayList<String[]> flashcardSetsList = new ArrayList<String[]>();
			while(resultSet.next()) 
			{
				String[] flashcardSet = {resultSet.getString(1), resultSet.getString(2)};
				flashcardSetsList.add(flashcardSet);
			}
			
			return flashcardSetsList;
		} 
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
		}
	}

	// Search for another user's public quiz by quiz name.
	public ArrayList<String[]> searchQuiz(String quizName) 
    {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			// Create the SQL query to determine whether the searched quiz is public.
			String query = "SELECT quizName, quizOwner, quizID FROM Quizzes WHERE quizName = ? AND isPublic = 1";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, quizName);
			resultSet = preparedStatement.executeQuery();

			// Return a list of the quiz names that match the search query.
			ArrayList<String[]> quizzesList = new ArrayList<String[]>();
			while(resultSet.next()) 
			{
				String[] quiz = {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)};
				quizzesList.add(quiz);
			}
			
			return quizzesList;
		} 
		catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
		}
	}
	
	//temporary to prevent errors
	public void takeQuiz(String one, String two){}
	
	
/*	// Search for another user's flashcard set by its description.
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
	*/ 
	public ArrayList<int[]> masteryQuery(String Username, String quizName) 
    {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try 
		{
			// Create the SQL query to determine whether the searched quiz is public.
			String query = "SELECT question, correctGuesses, totalGuesses FROM Mastery WHERE"
				+ "quizOwner = ? and  quizName = ?";
			
			// Use prepared statements to prevent SQL injection.
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, quizName);
			resultSet = preparedStatement.executeQuery();

			ArrayList<int[]> masteryList = new ArrayList<int[]>();
			
			while (resultSet.next()) {
				int question = resultSet.getInt(1), correctGuesses = resultSet.getInt(2), totalGuesses= resultSet.getInt(3);
				int[] masteryQuestion = { question, correctGuesses, totalGuesses};
				masteryList.add(masteryQuestion);
			}
			
			return masteryList;
		}catch (SQLException exception) 
		{
			System.out.println("SQLException: " + exception.getMessage());
			System.out.println("SQLState: " + exception.getSQLState());
			System.out.println("VendorError: " + exception.getErrorCode());
			
			return null;
		}
	}

}
