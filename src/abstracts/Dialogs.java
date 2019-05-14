package abstracts;

import java.util.ArrayList;
import java.util.List;

import org.javagram.response.object.MessagesDialog;

public class Dialogs {
	private List<DialogInfo> dialogs;
	
	public Dialogs(List<MessagesDialog> tlgDialogs) {
		 dialogs = new ArrayList<DialogInfo>();
		 
		 for (MessagesDialog dialog : tlgDialogs) {
			 dialogs.add(new DialogInfo(dialog));
		 }
	}
	
	public Dialogs() {
		 dialogs = new ArrayList<DialogInfo>();
	}
	
	public static Dialogs getDefault(List<DialogInfo> extDialogs) {
		 Dialogs def = new Dialogs();
		 def.dialogs = extDialogs;
		 
		 return def;
	}
	
	public List<DialogInfo> getDialogs(){
		return dialogs;
	}
}
