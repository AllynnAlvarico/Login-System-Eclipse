
public class User {

	private String userName;
	private String password;
	private String userType;
	
	public User(String user, String pass, String type){
		this.userName = user;
		this.password = pass;
		this.userType = type;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getUserPassword(){
		return this.password;
	}
	public String getUserType(){
		return this.userType;
	}
	public String toString(){
		return 
				"User Name: " + userName + 
				"Password: " + password + 
				"Type: " + userType;
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User u = (User) obj;
        return userName.equals(u.userName) && password.equals(u.password);
    }
}
