import java.net.*;
import java.io.*;

class test_client
{
    public static void main(String args[]) throws IOException, InterruptedException
    {
        try{
            Socket s = new Socket (args[0], 8484); // Hardcoded port

            //DataOutputStream for sending data and BufferedReader to receive
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            BufferedReader br    = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //now communicate with Server
            String read_str, send_str;
            int i = 0;
            while (i < 1000)
            {
                send_str = "Client reply num: " + Integer.toString(i++);
                dos.writeBytes (send_str + "\n");
                read_str = br.readLine();
                System.out.println ("Message received from server: " + read_str);
                Thread.sleep(1000);
            }
            s.close ();
        } catch(Exception e) {
            System.out.println("Exception caught: \n");
            System.out.println(e);
        }
    }
}
