/*
    Virtualbox automatic deployments of VMs
	Project by Shivangi Goswami
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import org.virtualbox_5_0.*;
import java.util.List;
import java.util.Arrays;

class gui extends JFrame {
	JDesktopPane pane;
	JButton    create_button, exit_button, view_button, deploy_button, delete_button, ok_button, enter_button;
	JPanel     create_pane,   exit_pane,   view_pane,   deploy_pane,   delete_pane;
	JCheckBox  off_button,    sleep_button;
	JLabel statusLabel;
	
	// Should debug logs be printed on screen?
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
		
		// Make it blend in the background
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
		
		view_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				view_dialogue();
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// Creates the dialogue box which is shown on clicking "Create virtual machine"
	public void create_dialogue()
	{
		JInternalFrame jif = new JInternalFrame(
								 "CREATE MACHINE", /* Add single line comments on what these are*/
								 true,             /* Explain what this true means */
								 true,             /* Explain what this true means */
								 true,             /* Explain what this true means */
								 true);            /* Explain what this true means */
							   
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
		JLabel blank_label = new JLabel(); // Why is this neeeded?

		// Setting label positions
		name_label.setBounds(20, 20,  70, 50);
		type_label.setBounds(20, 70,  70, 50);
		num_label.setBounds( 20, 120, 70, 50);
		nw_label.setBounds(  20, 170, 70, 50);
		blank_label.setBounds(2, 2, 398, 398); // Why?

		// Adding labels to internal frame
		jif.add(name_label);
		jif.add(type_label);
		jif.add(num_label);
		jif.add(nw_label);
		ImageIcon q=new ImageIcon("vbox.jpg"); // Did you plan to add this on blank_label?
		jif.add(blank_label);

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
				try {
					jif.setClosed(true);					
				} catch (Exception e) {
				    e.printStackTrace();	
				}
			}
		});
	}

    // Creates the dialogue box which is shown on clicking "Deploy virtual machine"
	public void deploy_dialogue()
	{
		JInternalFrame jif = new JInternalFrame(
							"DEPLOY MACHINE", 
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
		JLabel name_label  = new JLabel("Group Name");
		
		// Setting label positions
		name_label.setBounds(30, 20 , 70, 50);
		
		// Adding labels to internal frame
		jif.add(name_label);
		
		// Adding text fields
		final JTextField name_field = new JTextField();
		name_field.setBounds(110, 30, 100, 30);
		
		// Adding text field to internal frame
		jif.add(name_field);
		
		// Adding enter button
		JButton enter_button = new JButton("ENTER");
		jif.add(enter_button);
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
								"DELETE MACHINE", /* Add single line comments on what these are*/
								true,             /* Explain what this true means */
								true,             /* Explain what this true means */
								true,             /* Explain what this true means */
								true);            /* Explain what this true means */
							   
		jif3.setSize(400, 400);
		jif3.setLocation(400, 100);
		jif3.setLayout(null);
		jif3.setVisible(true);
		pane.add(jif3);
		statusLabel = new JLabel("", JLabel.CENTER);    
		statusLabel.setSize(350,100);
                                                      
		// Naming the labels        
		final JCheckBox off_Button = new JCheckBox("Power off ");
		final JCheckBox sleep_Button = new JCheckBox("Shut Down");

		//adding to pane
		jif3.add(off_Button);
		jif3.add(sleep_Button);
                                      
		//adding to listener
		off_Button.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{         
				statusLabel.setText("Power off" + (e.getStateChange() == 1 ? "checked" : "unchecked"));
			}           
		});
		
		sleep_Button.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				statusLabel.setText("ShutDown" + (e.getStateChange() == 1 ? "checked" : "unchecked"));
			}        
		});
		
		// Adding ok button
		JButton ok_button = new JButton("OK");
		jif3.add(ok_button);
		ok_button.setBounds(300, 300, 80, 50);
		
		// What happens when you click ok
		ok_button.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent ie)
			{
				setDefaultCloseOperation(EXIT_ON_CLOSE);	
			}
		});
	}
	
	public void view_dialogue()
	{
		JInternalFrame jif = new JInternalFrame(
							"VIEW MACHINE",   /* Add single line comments on what these are*/
							true,             /* Explain what this true means */
							true,             /* Explain what this true means */
							true,             /* Explain what this true means */
							true);            /* Explain what this true means */
		
		jif.setSize(400, 400);
		jif.setLocation(400, 100);
		jif.setLayout(null);
		jif.setVisible(true);
		pane.add(jif);
		JLabel name_label  = new JLabel("Machine Name: ");
		name_label.setBounds(20, 20,  100, 50);
		jif.add(name_label);
		final JTextField name_field = new JTextField();
		name_field.setBounds(110, 30,  100, 30);
		jif.add(name_field);
		JButton display_button = new JButton("DISPLAY");
		jif.add(display_button);
		display_button.setBounds(200, 250, 100, 50);
		
		// What happens when you click Display
		display_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) {
				boolean macfound = false;
				String lookup = name_field.getText();
				String name;
				String osType;
				Long ram = 0L;
				boolean hwvirtEnabled         = false;
				boolean hwvirtNestedPaging    = false;
				boolean paeEnabled            = false;
				IVirtualBox vbox = mgr.getVBox();
				List<IMachine> machs = vbox.getMachines();
				for (IMachine m : machs) {
					try {
						name = m.getName();
						if (name.equals(lookup)) {
							macfound = true;
							ram                = m.getMemorySize();
							hwvirtEnabled      = m.getHWVirtExProperty(HWVirtExPropertyType.Enabled);
							hwvirtNestedPaging = m.getHWVirtExProperty(HWVirtExPropertyType.NestedPaging);
							paeEnabled         = m.getCPUProperty(CPUPropertyType.PAE);
							osType             = m.getOSTypeId();

							machine_view(name, osType, ram, hwvirtEnabled, hwvirtNestedPaging, paeEnabled);
						} else {
							continue;
						}
					} catch (VBoxException e) {
						JOptionPane.showMessageDialog(null, "An error occurred during machine lookup.");
					}
					if (!macfound) {
						JOptionPane.showMessageDialog(null, "Machine not found!");
					}
				}
				
				try {
					jif.setClosed(true);					
				} catch (Exception e) {
				    e.printStackTrace();	
				}
			}
		});
	}

	public void machine_view(String name, String osType,
	                         Long ram, boolean hwvirtEnabled,
							 boolean hwvirtNestedPaging, boolean paeEnabled)
	{
		JInternalFrame jif = new JInternalFrame(
					"MACHINE DETAILS", /* Add single line comments on what these are*/
					true,              /* Explain what this true means */
					true,              /* Explain what this true means */
					true,              /* Explain what this true means */
					true);             /* Explain what this true means */
		
		jif.setSize(400, 400);
		jif.setLocation(400, 100);
		jif.setLayout(null);
		jif.setVisible(true);
		pane.add(jif);
		JLabel name_label  = new JLabel("Machine Name: ");
		JLabel os_label    = new JLabel("OS type: ");
		JLabel ram_label   = new JLabel("RAM: ");
		JLabel virt_label  = new JLabel("Hardware Virtualization: ");
		JLabel page_label  = new JLabel("Nested Paging: ");
		JLabel pae_label   = new JLabel("Physical address extension: ");
		
		name_label.setBounds(20, 20, 150, 50);
		os_label.setBounds(  20, 40, 150, 50);
		ram_label.setBounds( 20, 60, 150, 50);
		virt_label.setBounds(20, 80, 150, 50);
		page_label.setBounds(20, 100, 150, 50);
		pae_label.setBounds( 20, 120, 150, 50);
		
		JLabel name_val  = new JLabel(name);
		JLabel os_val    = new JLabel(osType);
		JLabel ram_val   = new JLabel(Long.toString(ram));
		JLabel virt_val  = new JLabel(String.valueOf(hwvirtEnabled));
		JLabel page_val  = new JLabel(String.valueOf(hwvirtNestedPaging));
		JLabel pae_val   = new JLabel(String.valueOf(paeEnabled));
		
		name_val.setBounds(210, 20, 150, 50);
		os_val.setBounds(  210, 40, 150, 50);
		ram_val.setBounds( 210, 60, 150, 50);
		virt_val.setBounds(210, 80, 150, 50);
		page_val.setBounds(210, 100, 150, 50);
		pae_val.setBounds( 210, 120, 150, 50);
		
		jif.add(name_label);
		jif.add(os_label);
		jif.add(ram_label);
		jif.add(virt_label);
		jif.add(page_label);
		jif.add(pae_label);
		
		jif.add(name_val);
		jif.add(os_val);
		jif.add(ram_val);
		jif.add(virt_val);
		jif.add(page_val);
		jif.add(pae_val);
	}
	
	// Main method
	public static void main(String args[])
    {
		// Why so many brackets? One day you will learn what monstrosity LINT is.
		if (args[0].equals("-DEBUG")) {
			GLOBALDEBUG = true;
		} else if (args[0].equals("-NORM")) {
			GLOBALDEBUG = false;
		}
        new gui();
    }
}