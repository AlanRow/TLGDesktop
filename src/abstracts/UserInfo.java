package abstracts;

import org.javagram.response.object.User;

public class UserInfo {
	private String firstName;
	private String lastName;
	
	public UserInfo(String first, String last){
		firstName = first;
		lastName = last;
	}
	
	public UserInfo(User user){
		firstName = user.getFirstName();
		lastName = user.getLastName();
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
}