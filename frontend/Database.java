import java.sql.*;
import java.util.*;

public class Database {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public static void main(String args[]) {
        Database database = new Database();

        database.connect();

        // TODO: Obtain the username and password from the POST method when the user inputs login info.
        database.authenticateLogin("[USERNAME]", "[PASSWORD]");
        database.disconnect();
    }

    // Connect to the database.
    public int connect() {
        try {
            // TODO: obtain the real URL, username, and password for the database
            connection = DriverManager.getConnection(
                    "[URL]",
                    "[USERNAME]",
                    "[PASSWORD]");
            System.out.println("Connected to the database!");
        } 
        catch (SQLException exception) 
        {
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
            System.out.println("Disconnected from the database!");

        } catch (SQLException exception) {
            System.out.println("SQLException: " + exception.getMessage());
            System.out.println("SQLState: " + exception.getSQLState());
            System.out.println("VendorError: " + exception.getErrorCode());
        }
    }

    // Authenticate a user's credentials when they attempt to log in.
    public boolean authenticateLogin(String username, String password) 
    {
        PreparedStatement preparedStatement = null;

        try {
            // Determine the MySQL statement to obtain the row matching the entered username
            // and password.
            String query = "SELECT count(*) FROM (SELECT username, user_password FROM User where username = ? and user_password = sha2(?, 256) ";

            // Use prepared statements to prevent SQL injection.
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Return whether we found the 1 row matching the user's entered username and password.
            return preparedStatement.executeQuery().getFetchSize() == 1;
        } catch (SQLException exception) {
            System.out.println("SQLException: " + exception.getMessage());
            System.out.println("SQLState: " + exception.getSQLState());
            System.out.println("VendorError: " + exception.getErrorCode());
            return false;
        }
    }

    // Let a user view a flashcard set corresponding to a provided set name.
    public ArrayList<String[]> viewFlashcardSet(String setName, String username)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try 
        {
            // Determine the MySQL statement to obtain the rows matching the provided set name and username. 
            String query = "SELECT term, term_definition from Flashcards where setName = ? and username = ?";

            // Use prepared statements to prevent SQL injection.
            preparedStatement = connection.prepareStatement(query); 
			preparedStatement.setString(1, setName); 
			preparedStatement.setString(2, username); 
            resultSet = preparedStatement.executeQuery();
            
            // Iterate through each flashcard's term and definition to be entered in the HTML table.
            ArrayList<String[]> flashcards = new ArrayList<String[]>();

            while(resultSet.next())
            {
                String term = resultSet.getString(1),
                        definition = resultSet.getString(2);
                String[] flashcard = {term, definition};
                flashcards.add(flashcard);
            }

            return flashcards;
        }
        catch (SQLException exception)
        { 
			System.out.println("SQLException: " + exception.getMessage()); 
			System.out.println("SQLState: " + exception.getSQLState()); 
			System.out.println("VendorError: " + exception.getErrorCode()); 
            return null;
		} 
    }
}