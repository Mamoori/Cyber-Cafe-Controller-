package networkproject.test;

import java.io.IOException;

import javax.swing.JWindow;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class SERVER_TEST {
	private Server server;

	public SERVER_TEST() throws IOException {

		server = new Server();
		server.start();
		server.getKryo().setRegistrationRequired(false);
		server.addListener(new Listener() {
			JWindow window = new JWindow();

			public void connected(Connection connection) {
				window.setSize(100, 100);
				super.connected(connection);
			}

			public void received(Connection connection, Object object) {
				if (object instanceof String) {
					try {
						String command = (String) object;
						//JSONObject cmd = (JSONObject) new JSONParser().parse(command);
						System.out.println(command);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		server.bind(1024, 1024);
		System.out.println("Server Started.....");

	}

	public static void main(String[] args) throws IOException {
		new SERVER_TEST();
	}

}
