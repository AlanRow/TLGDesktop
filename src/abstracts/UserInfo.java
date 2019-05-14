package abstracts;

import org.javagram.response.object.User;

public class UserInfo {
	private String firstName;
	private String lastName;
	private String phone;
	
	public UserInfo(String first, String last, String userPhone){
		firstName = first;
		lastName = last;
		phone = userPhone;
	}
	
	public UserInfo(User user){
		firstName = user.getFirstName();
		lastName = user.getLastName();
		phone = user.getPhone();
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getFullName(){
		return firstName + " " + lastName;
	}
}