
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
	
	String usernameEntered, passwordEntered;
	
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
