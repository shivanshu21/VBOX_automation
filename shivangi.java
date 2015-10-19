import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import org.virtualbox_5_0.*;

class gui extends JFrame {
	JDesktopPane pane;
	JButton    create_button, exit_button, view_button, deploy_button, delete_button, ok_button, enter_button;
	JPanel     create_pane,   exit_pane,   view_pane,   deploy_pane,   delete_pane;
	JCheckBox  off_button,    sleep_button;
	JLabel statusLabel;
	
	
    public static boolean GLOBALDEBUG;
	public VirtualBoxManager mgr;
	
	public gui()
	{
	    setSize(1000, 600);
		pane = new JDesktopPane();
		pane.setBackground(Color.green);
        add(pane);

		// Connect to VirtualBox server
		vbox_automation obj = new vbox_automation();
		mgr = obj.init_connection();

        create_button  = new JButton("CREATE VIRTUAL MACHINES");
		view_button    = new JButton("VIEW VIRTUAL MACHINES");
		deploy_button  = new JButton("DEPLOY VIRTUAL MACHINES");
		delete_button  = new JButton("DELETE VIRTUAL MACHINES");
        exit_button    = new JButton("EXIT");

        create_pane    = new JPanel();
		view_pane      = new JPanel();
        deploy_pane    = new JPanel();
        delete_pane    = new JPanel();		
        exit_pane      = new JPanel();

        pane.add(create_pane);
		pane.add(view_pane);
		pane.add(deploy_pane);
		pane.add(delete_pane);
        pane.add(exit_pane);
		
        create_pane.add(create_button);
		view_pane.add(view_button);
		deploy_pane.add(deploy_button);
		delete_pane.add(delete_button);
        exit_pane.add(exit_button);
		
		
        create_pane.setBackground(Color.green);
		view_pane.setBackground(  Color.green);
		deploy_pane.setBackground(Color.green);
		delete_pane.setBackground(Color.green);
        exit_pane.setBackground(  Color.green);
		
        create_pane.setBounds(0, 0,   200, 50);
		view_pane.setBounds(  0, 50,  200, 50);
		deploy_pane.setBounds(0, 100, 200, 50);
		delete_pane.setBounds(0, 150, 200, 50);
        exit_pane.setBounds(  0, 200, 200, 50);
		
        create_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                create_dialogue();
			}
		});
		
		exit_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(0);
			}
		});
                                             
        deploy_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
		    {
                deploy_dialogue();
			}
		});

		delete_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				delete_dialogue();  
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// Creates the dialogue box which is shown on clicking "Create virtual machine"
	public void create_dialogue()
	{
		JInternalFrame jif = new JInternalFrame(
								 "CREATE MACHINE", 
								 true,             
								 true,              
					           	 true,             
								 true);            
							   
		jif.setSize(400, 400);
		jif.setLocation(400, 100);
		jif.setLayout(null);
		jif.setVisible(true);
		pane.add(jif);

		// Naming the labels
		JLabel name_label  = new JLabel(" Name");
		JLabel type_label  = new JLabel(" Type");
		JLabel num_label   = new JLabel(" Number");
		JLabel nw_label    = new JLabel(" Network");
		

		// Setting label positions
		name_label.setBounds(20, 20,  70, 50);
		type_label.setBounds(20, 70,  70, 50);
		num_label.setBounds( 20, 120, 70, 50);
		nw_label.setBounds(  20, 170, 70, 50);
		

		// Adding labels to internal frame
		jif.add(name_label);
		jif.add(type_label);
		jif.add(num_label);
		jif.add(nw_label);
		

		// Adding text fields
		final JTextField name_field = new JTextField();
		final JTextField type_field = new JTextField();
		final JTextField num_field  = new JTextField();
		final JTextField nw_field   = new JTextField();
		name_field.setBounds(110, 30,  100, 30);
		type_field.setBounds(110, 80,  100, 30);
		num_field.setBounds( 110, 130, 100, 30);
		nw_field.setBounds(  110, 180, 100, 30);
		jif.add(name_field);
		jif.add(type_field);
		jif.add(num_field);
		jif.add(nw_field);
		
		// Adding submit button
		JButton submit_button = new JButton("SUBMIT");
		jif.add(submit_button);
		submit_button.setBounds(300, 300, 80, 50);
		
		// What happens when you click Submit
		submit_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String name = name_field.getText();
				String type = type_field.getText();
				String num  = num_field.getText();
				String nw   = nw_field.getText();

				// Create Virtual machines
				vbox_automation createobj = new vbox_automation();
				if(createobj.create_machines(mgr, name,
											Integer.parseInt(num),
											type,
											Integer.parseInt(nw))) {
					JOptionPane.showMessageDialog(null, "Machines created");
				}
			}
		});
	}

    // Creates the dialogue box which is shown on clicking "Deploy virtual machine"
	public void deploy_dialogue()
	{
		JInternalFrame jif1 = new JInternalFrame(
								 "DEPLOY MACHINE", 
								 true,             
								 true,             
								 true,            
								 true);            
							   
		jif1.setSize(400, 400);
		jif1.setLocation(400, 100);
		jif1.setLayout(null);
		jif1.setVisible(true);
		pane.add(jif1);

		// Naming the labels
		JLabel name_label  = new JLabel("Group Name");
		
		// Setting label positions
		name_label.setBounds(30, 20 , 70, 50);
		
		// Adding labels to internal frame
		jif1.add(name_label);
		
		// Adding text fields
		final JTextField name_field = new JTextField();
		name_field.setBounds(110, 30, 100, 30);
		
		// Adding text field to internal frame
		jif1.add(name_field);
		
		// Adding enter button
		JButton enter_button = new JButton("ENTER");
		jif1.add(enter_button);
		enter_button.setBounds(300, 300, 80, 50);
		
		// What happens when you click enter
		enter_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String name;
				name = name_field.getText();
				System.out.println("VM group name is " + name);
								 
			}
		});
	}

	// Creates the dialogue box which is shown on clicking "Delete virtual machine"
	public void delete_dialogue()
	{
		JInternalFrame jif3 = new JInternalFrame(
								"DELETE MACHINE", 
								true,             
								true,             
								true,             
								true);            
							   
		jif3.setSize(400, 400);
		jif3.setLocation(400, 100);
		jif3.setLayout(null);
		jif3.setVisible(true);
		pane.add(jif3);
		
		//Declaring radio buttons                                 
		JRadioButton option1 = new radioButton("PowerOff");
		JRadioButton option2 = new JRadioButton("ShutDown"); 
		JRadioButton option3 = new JRadioButton("Cancel");
 
		ButtonGroup group = new ButtonGroup();
		//Grouping the buttons
		group.add(option1);
		group.add(option2);
		group.add(option3);                                                    
                                              
						// Setting the layout
                                                         setLayout(null);
                                                         option1.setBounds(50,100,120,30);  
                                                         option2.setBounds(50,150,120,30);                                                          
                                                         option3.setBounds(50,200,120,30);  
  
  
                                                  //adding to pane
                                                          jif3.add(option1);
                                                           jif3.add(option2);
                                                           jif3.add(option3);
                                               


		
		// Adding ok button
		JButton ok_button = new JButton("OK");
		jif3.add(ok_button);
		ok_button.setBounds(300, 300, 80, 50);
		
		
	}

	// Main method
	public static void main(String args[])
    {
		// Why so many brackets? One day you will learn what monstrosity LINT is.
		if (args[0].equals("-Dprint")) {
			GLOBALDEBUG = true;
		} else {
			GLOBALDEBUG = false;
		}
        new gui();
    }
}