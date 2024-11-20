import java.util.ArrayList;

public class AdminSystem {
	private ArrayList<User> users;
	
	public AdminSystem(){
		users = new ArrayList<>();
	}
	public void addUser(String user, String password, String type){
		users.add(new User(user, password, type));
	}
	public boolean validateUser(String user, String password){
		for(User u : users)if(u.getUserName().equals(user) && u.getUserPassword().equals(password)) return true;
		return false;
	}
	
	public boolean listContain(User u){
		return users.contains(u);
	}
	
	public boolean isUsernameTaken(String username) {
	    for (User u : users) {
	        if (u.getUserName().equals(username)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public User getUser(String user, String password){
		for(User u : users){
			if(u.getUserName().equals(user) && u.getUserPassword().equals(password)) 
			return u;
		}
		return null;
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
//	User temp = new User(user, password, type);
//	users.add(temp);
//	if(users.contains(temp))System.out.println("Oh No!");
//	else users.add(temp);
}
