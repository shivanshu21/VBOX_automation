import java.net.*;
import java.io.*;

class test_server
{
    public static void main(String args[]) throws InterruptedException
    {
        try {
            ServerSocket ss = new ServerSocket(8484);
            Socket s  = ss.accept();
            System.out.println("Server out of accept loop. Connection with client established.");

            // PrintStream to send and BufferedReader to receive
            PrintStream    ps = new PrintStream(s.getOutputStream ());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream ()));

            String read_str, send_str;
            int i = 0;
            while (true)
            {
                read_str = br.readLine();
                System.out.println("Message received from client: " + read_str);
                send_str = "Server reply number: " + Integer.toString(i++);
                ps.println(send_str);
                Thread.sleep(1000);
                if (i > 10000) {
                    break;
                }
            }
            s.close();
            ss.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
