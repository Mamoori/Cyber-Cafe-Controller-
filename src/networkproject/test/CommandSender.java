package networkproject.test;

import org.json.simple.JSONObject;

public class CommandSender {

	public static CommandSender instance = new CommandSender();

	private CommandSender() {}

	public String send(String command, Object data) {
		JSONObject object = new JSONObject();
		object.put("command", command);
		object.put("data", data);
		return object.toJSONString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(instance.send("start", "01:00"));
	}

}
