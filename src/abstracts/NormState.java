package abstracts;

import org.javagram.TelegramApiBridge;

public class NormState implements State {
	private Connection connect;
	
	public NormState(Connection bridge) {
		connect = bridge;
	}

	@Override
	public UserInfo getCurrentUser() {
		return new UserInfo(connect.getCurrentUser());
	}

	@Override
	public Dialogs getDialogs() {
		return new Dialogs(connect.getDialogs());
	}

}
