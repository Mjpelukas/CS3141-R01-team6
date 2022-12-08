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
	
	/*public void createFile() {
		File file = new File("testing.txt");
	}*/
	
	String usernameEntered, passwordEntered;
	
	/*
	public int addAccountToFile(String usernameEntered, String passwordEntered) {
		this.usernameEntered = usernameEntered;
		this.passwordEntered = passwordEntered;
		
		File file = new File("testing.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			
			if (checkIfNameExists(file)) {
				//System.out.println("Sorry but the name you're trying to use already exists");
				p.close();
				
				return -1;
			}
			
			p.println(usernameEntered);
			p.println(passwordEntered);
			
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
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
		try {
			FileReader fr = new FileReader("testing.txt");
			Scanner s = new Scanner(fr);
			
			while (s.hasNextLine()) {
				if (s.nextLine().equals(susername)) {
					if (s.nextLine().equals(spassword)) {
						s.close();
						return true;
					} else {
						//System.out.println("Your login data is incorrect");
					}
				}
			}
			spassword = "";
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}*/
	
	// Creates account using database.java
	public boolean addAccount(String susername, String spassword) {
		Database database = new Database();
		database.connect();
		
		// TODO: Ideally, this will return an error string to an error error 
		// instead of a boolean which will need to be communicated on the front end
		boolean authenticated = database.createAccount(susername, spassword);
		database.disconnect();
		
		username = susername;
		
		return authenticated;
	}
	
	// This logs the user in
	public boolean loginTest(String susername, String spassword) {
		Database database = new Database();
		database.connect();
		
		boolean authenticated = database.authenticateLogin(susername, spassword);
		database.disconnect();
		
		username = susername;
		
		return authenticated;
	}

}
