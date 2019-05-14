package abstracts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;

import exceptions.SessionInterruptedException;

public class AccountInfo {
	private TelegramApiBridge connect;
	private AuthAuthorization profile;
	private UserInfo user;
	private Dialogs dialogs;
	
	private AccountInfo(UserInfo userInfo, Dialogs extDialogs) {
		user = userInfo;
		dialogs = extDialogs;
	}
	
	public Dialogs getDialogs() throws SessionInterruptedException {
		if (connect == null)
			return dialogs;
		
		try {
			return new Dialogs(connect.messagesGetDialogs());
		} catch (IOException e) {
			throw new SessionInterruptedException();
		}
	}
	
	//при неполадках с соединением
	public Dialogs getLastDialogs() {
		return dialogs;
	}
	
	public UserInfo getUser() {
		return user;
	}
	
	public AccountInfo(AuthAuthorization userProfile, TelegramApiBridge bridge) throws SessionInterruptedException {
		profile = userProfile;
		connect = bridge;
		user = new UserInfo(profile.getUser());
		try {
			dialogs = new Dialogs(connect.messagesGetDialogs());
		} catch (IOException e) {
			throw new SessionInterruptedException();
		}
	}
	
	//используется при отсутствии соединения
	public static AccountInfo getDefault() {
		UserInfo defaultUser = new UserInfo("Name", "Surname", "8(9**) *** **-**");
		List<DialogInfo> defDialogs = new ArrayList<DialogInfo>(); 
		defDialogs.add(new DialogInfo(defaultUser, "Hello!", 1));
		
		return new AccountInfo(defaultUser, Dialogs.getDefault(defDialogs));
	}
	
}
