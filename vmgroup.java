import java.util.ArrayList;
import org.virtualbox_5_0.*;

public class vmgroup {
	public vmgroup()
	{
		_name     = "vmgroup1";
		_machines = new ArrayList<IMachine>();
	}
	public String getName()
	{
		return _name;
	}
	public void setName(String name)
	{
		_name = name;
	}
	public void addMachine(IMachine vm)
	{
		_machines.add(vm);
	}
	public int getNumMachines()
	{
		return _machines.size();
	}
	public IMachine getMachine(int i)
	{
		return _machines.get(i);
	}
	
    private String _name;
    private ArrayList<IMachine> _machines;
}