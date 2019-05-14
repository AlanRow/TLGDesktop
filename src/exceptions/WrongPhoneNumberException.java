package exceptions;

public class WrongPhoneNumberException extends Exception {
	public String phoneNumber;
	public WrongPhoneNumberException(String phone) {
		super("Wrong number: " + phone);
		phoneNumber = phone;
	}
}
