package de.moldawski.byz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ByzantineFault {

	// By changing the ammount of lieutenants and traitors among them, 
	// you can demonstrate the OM algorithm from http://lamport.azurewebsites.net/pubs/byz.pdf
	// In order the consens is reached, the nTraitors < nLieutenants/3 must be met
	private final static int nLieutenants = 12;
	private final static int nTraitors = 5;

	private final static String attac = "ATTAC";
	private final static String retreat = "RETREAT";

	private final static String command = attac;
	private final static String traitorSays = retreat;
	
	static int nMessages;

	Map<String, String> messages = new HashMap<>() {

		private static final long serialVersionUID = 1L;

		@Override
		public String toString() {
			StringBuffer res = new StringBuffer();
			for (String g : keySet()) {
				res.append(g);
				res.append("{");
				res.append((traitors.contains(g) ? "traitor" : "loyal") + ", ");
				res.append(get(g));
				res.append("} ");
			}
			return res.toString();
		}

	};
	private static Set<String> lieutnants = new HashSet<>();
	private static Set<String> traitors = new HashSet<>();

	void om(int m, Set<String> currentLieutenants, String commander, String message) {
		for (String receiver : currentLieutenants) {
			
			//a traitor changes the message now
			String newMessage = traitors.contains(receiver) ? traitorSays : message;
			
			nMessages++;

			if (m == 0) {
				// end of recursion
				messages.put(receiver, newMessage);
			} else {
				var minusMe = new HashSet<String>(currentLieutenants);
				minusMe.remove(receiver);
				var byz = new ByzantineFault();
				// recursion
				byz.om(m - 1, minusMe, receiver, newMessage);
				//
				var nCommands = byz.messages.entrySet().stream().filter(t -> {
					return t.getValue().equals(command);
				}).count() + (newMessage.equals(command) ? 1 : 0);
				messages.put(receiver, (nCommands > (minusMe.size() + 1) / 2) ? command : "no consens");
			}
		}

	}

// Inititialization	
	static {
		for (int i = 1; i <= nLieutenants; i++) {
			lieutnants.add("l" + i);
		}
		for (int i = 1; i <= nTraitors; i++) {
			traitors.add("l" + (nLieutenants - i + 1));
		}
	}

	public static void main(String[] args) {
		var b = new ByzantineFault();
		b.om((lieutnants.size())/3, lieutnants, "c1", command);
		System.out.println(b.messages);
	}

}
