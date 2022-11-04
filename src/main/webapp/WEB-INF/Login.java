import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Login {
	
	private String username, password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void createFile() {
		File file = new File("testing.txt");
	}
	
	String usernameEntered, passwordEntered;
	
	public boolean addAccount(String susername, String spassword) {
		Database database = new Database();
		database.connect();
		boolean authenticated = database.createAccount(susername, spassword);
		database.disconnect();
		username = susername;
		return authenticated;
	}
	
	private boolean checkIfNameExists(File file) {
		try {
			Scanner s = new Scanner(file);
			
			while (s.hasNextLine()) {
				String a = s.nextLine();
				if (a.equals(usernameEntered)) {
					s.close();
					return true;
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean loginTest(String susername, String spassword) {
		
		Database database = new Database();
		database.connect();
		boolean authenticated = database.authenticateLogin(susername, spassword);
		database.disconnect();
		
		username = susername;
		return authenticated;
	}

}