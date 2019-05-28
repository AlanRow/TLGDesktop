package abstracts;

import java.io.IOException;
import java.util.List;

import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.InputPeer;
import org.javagram.response.object.MessagesDialog;
import org.javagram.response.object.User;

import exceptions.NotConnectException;
import exceptions.WrongPhoneNumberException;

public interface Connection {

	AuthSentCode sendCode(String replaceAll) throws WrongPhoneNumberException;

	AuthAuthorization logIn(String text) throws IOException;
	
	void logOut() throws NotConnectException;

	void sendMessage(InputPeer inputPeer, String text, long l) throws NotConnectException;

	User getCurrentUser();

	List<MessagesDialog> getDialogs();

}
