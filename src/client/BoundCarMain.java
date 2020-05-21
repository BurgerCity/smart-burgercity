package client;

import java.io.IOException;

/**
 * 
 * @author Idriss Zerai
 *
 */

public class BoundCarMain {
	public static void main(String[] args) throws IOException {
		Client_socket c = new Client_socket();
		c.startConnection("127.0.0.1", 2015);
		BoundCarListner bcl= new BoundCarListner(c);
	}
}
