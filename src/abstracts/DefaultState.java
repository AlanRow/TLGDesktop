package abstracts;

import java.util.ArrayList;
import java.util.List;

public class DefaultState implements State {

	@Override
	public UserInfo getCurrentUser() {
		return new UserInfo("Alan", "Ro", "888888888888");
	}

	@Override
	public Dialogs getDialogs() {
		List<DialogInfo> dgs = new ArrayList<DialogInfo>();
		dgs.add(new DialogInfo(new UserInfo("Alex", "Rog", "9999999999"), "Hello!", 1));
		
		return Dialogs.getDefault(dgs);
	}

}
