import java.net.*;
import java.io.*;
class Server1 {
	public static void main(String args[]) throws IOException
    {
		//Create Server side socket
        ServerSocket ss = new ServerSocket(8080);
        //make this socket accept client connection
        Socket s = ss.accept ();
        System.out.println ("A connection established...");
        //attach OutputStream to socket
        OutputStream obj = s.getOutputStream();
        //to send data to Socket
        PrintStream ps = new PrintStream (obj);
        //now send the data
        String str = "Hello Client";
        ps.println (str);
        ps.println ("Bye");
        //close connection
        s.close ();
        ss.close ();
        ps.close ();
    }
}