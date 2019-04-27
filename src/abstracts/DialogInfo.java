package abstracts;

import org.javagram.response.object.InputPeer;
import org.javagram.response.object.MessagesDialog;

public class DialogInfo {
	private UserInfo user;
	private  String lastMessage;
	private int unreadCount;
	private InputPeer input;
	
	public DialogInfo(UserInfo friend, String lastMes, int unreads){
		user = friend;
		lastMessage = lastMes;
		unreadCount = unreads;
	}
	
	public DialogInfo(MessagesDialog tlgDialog){
		user = new UserInfo(tlgDialog.getPeerUser());
		lastMessage = tlgDialog.getTopMessage().getMessage();
		unreadCount = tlgDialog.getUnreadCount();
		input = tlgDialog.getPeerUser();
	}
	
	public UserInfo getUser(){
		return user;
	}
	
	public String getLastMessage(){
		return lastMessage;
	}
	
	public InputPeer getInputPeer(){
		return input;
	}
	
	public int getUnreadCount(){
		return unreadCount;
	}
}
