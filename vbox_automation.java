/*
    Virtualbox automatic deployments of VMs
	Project by Shivangi Goswami
*/
import org.virtualbox_5_0.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Arrays;

public class vbox_automation {

    public final String USERNAME;
	public final String PASSWORD;
	public final String URL;
	
	// Constructor
	public vbox_automation() {}
	{
	    // This is called an initializer list
        USERNAME = "Shivanshu";
		PASSWORD = "netapp1!";
		URL      = "http://localhost:18083";
	}
	
	// Initiates connection to vbox Web service
	public VirtualBoxManager init_connection()
	{
		VirtualBoxManager mgr = VirtualBoxManager.createInstance(null);
		try {
			mgr.connect(URL, USERNAME, PASSWORD);
			JOptionPane.showMessageDialog(null, "Connected to Virtualbox web service");
		} catch (Exception e) {
			e.printStackTrace();
			debugPrint("Cannot connect, start webserver first!");
			JOptionPane.showMessageDialog(null,
			"Cannot connect to Virtualbox web service. Make sure the service is started.");
		}
		return mgr;
	}

	// Creates Virtual machines
	public boolean create_machines(VirtualBoxManager mgr,
								   String groupname,
	                               int    num,
								   String type,
								   int    network)
	{
		int i;
		String mc_name;
		type = "Ubuntu";
		/*mc_name = "/" + groupname; <<<<<<
		List names = new ArrayList();
		names.add(mc_name);*/
		debugPrint("Creating " + groupname + " with " +
					Integer.toString(num) + " machines.");
		for (i = 0; i < num; i++) {
			mc_name = groupname + "_" + Integer.toString(i);
			try {
				IVirtualBox vbox = mgr.getVBox();
				IMachine nmac = vbox.createMachine(
								"",       /* Machine settings file. To be created. */
								mc_name,  /* Name */
								null,     /* Names of VM groups to be associated with */
								type,     /* OS type */
								"");      /* Additional settings */
				
				nmac.saveSettings();
				vbox.registerMachine(nmac);
				// Find groupname in the list of groups, then insert
				// this machine in that group object. <<<<<<
				debugPrint("\t Created machine: " + mc_name);

			} catch (Exception e) {
				System.out.println("Failed to create machine " + mc_name + ". Reason: ");
				e.printStackTrace();
				return false;
			}
		}
		debugPrint("Finished creating " + groupname);
		return true;
	}
	
	// Deploy virtual machines
	
	// Delete virtual machines
	
	// Prints debug logs when -DEBUG option is passed
	public void debugPrint(String arg)
	{
		if (gui.GLOBALDEBUG) {
			System.out.println(arg);
		}
	}
}