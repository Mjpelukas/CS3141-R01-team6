import java.sql.*;
import java.util.ArrayList;

public class Database 
{
    Connection connection = null; 
	Statement statement = null; 
	ResultSet resultSet = null; 

    public static void main(String args[])
    { 
		Database database = new Database(); 

		database.connect();
        database.login(); 
		database.disconnect();
	} 

    // Connect to the database.
    public int connect()
    {   
		try 
        { 
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
    public void disconnect()
    { 
		try 
        { 
			connection.close(); 
			System.out.println("Disconnected from the database!"); 

		} 
		catch (SQLException exception)
        { 
			System.out.println("SQLException: " + exception.getMessage()); 
			System.out.println("SQLState: " + exception.getSQLState()); 
			System.out.println("VendorError: " + exception.getErrorCode()); 
		} 
	}

    // Authenticate a user's credentials when they attempt to log in.
    public boolean authenticateLogin(String username, String password)
    {
        try 
        { 
            // Set up the MySQL statement to obtain the row matching the entered username and password.
            statement = connection.createStatement(); 
            query = "SELECT count(*) FROM
                (SELECT username, user_password FROM User  
                where username = ? and user_password = sha2(?, 256) " ; 

            // Use prepared statements to prevent SQL injection.
			statement = connection.prepareStatement(query); 
			statement.setString(1, username); 
			statement.setString(2, password); 

            // Return whether we found the 1 row matching the user's username and password.
            return statement.executeQuery(statement) == 1;
		} 
		catch (SQLException exception)
        { 
			System.out.println("SQLException: " + exception.getMessage()); 
			System.out.println("SQLState: " + exception.getSQLState()); 
			System.out.println("VendorError: " + exception.getErrorCode()); 
            return false;
		} 
    }

    // Let a user view a flashcard set corresponding to a provided set name.
    public ArrayList<> viewFlashcardSet(String setName, String username)
    {
        try 
        {
            // Set up the MySQL statement to obtain the rows matching the provided set name and username.
            statement = connection.createStatement(); 
            query = "SELECT term, term_definition from Flashcards 
                        where setName = ? and username = ?";

            // Use prepared statements to prevent SQL injection.
            statement = connection.prepareStatement(query); 
			statement.setString(1, setName); 
			statement.setString(2, username); 
            resultSet = statement.execute();
            
            // Iterate through each flashcard's term and definition to be entered in the HTML table.
            ArrayList<String[2]> flashcards = new ArrayList<String[2]>();
            for(PgxResult result : resultSet) 
            {
                String term = result.getString(1);
                String definition = result.getString(2);
                flashcards.add({term, definition});
            }

            return flashcards;
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