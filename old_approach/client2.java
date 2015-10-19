import java.net.*;
import java.io.*;
class Client2 {
	public static void main(String args[]) throws IOException
    {
		//create a client socket
		Socket s = new Socket ("localhost", 888);
		//to send data to server
		DataOutputStream dos = new DataOutputStream (s.getOutputStream ());
		//to receive data from server
		BufferedReader br = new BufferedReader (new InputStreamReader
		(s.getInputStream ()));
		//to read data from keyboard to send to server
		BufferedReader kb = new BufferedReader (new InputStreamReader (System.in));
		//now communicate with Server
		String str, str1;
		while ( !(str = kb.readLine() ).equals("exit") ) {
			dos.writeBytes (str+ "\n");
            str1 = br.readLine ();
            System.out.println (str1);
        }
        s.close ();
    }
}