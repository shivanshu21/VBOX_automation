import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
class menuapp extends Frame 
{
MenuItem m1,m2,m3,m4;
MenuBar m;
Menu mn;
JFrame f;
menuapp()
{
setSize(400,400);
m=new MenuBar();
mn=new Menu("File");
m1=new MenuItem("Create");
m2=new MenuItem("Remove");
m3=new MenuItem("Start");
m4=new MenuItem("Pause");
mn.add(m1);
mn.add(m2);
mn.add(m3);
mn.add(m4);
m.add(mn);
setMenuBar(m);
m1.addActionListener(new ActionListener()  
{
public void actionPerformed(ActionEvent ae)
{
ProcessBuilder builder = new ProcessBuilder
(cmd.exe", "/c", "cd \"C:\\Program Files\\Oracle\\VirtualBox\" &&  VBoxManage createvm --name ims --register");
           builder.redirectErrorStream(true);
       Process p=builder.start();
     BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
       String line;
        while (true) { 
try
{
            line = r.readLine();

            if (line == null) { break; }
            System.out.println(line);
}
catch()
{
}
}
}
});

setVisible(true);
}
public static void main(String args[])
{
new menuapp();
}
}
