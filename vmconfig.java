import java.util.ArrayList;

public class vmconfig
{
    public enum osversion {
        _linux, _windows
    }
	
	// Default constructor
	public vmconfig()
	{
		_name    = "vm1"; // papa :P
		_version = osversion._linux;
		_ram     = 0;
		_hdd     = 0;
	}
	
	// Ask me later about initializer lists in constructors
	public vmconfig(String name, osversion ver, int ram, int hdd)
	{
		_name    = name;
		_version = ver;
		_ram     = ram;
		_hdd     = hdd;
	}
	
	// This is a standard object oriented practice.
	// Keep class members private. Use get and set methods to access.
	// This is how you will write code in your job too.
	public String getName()
	{
		return _name;
	}
	public void setName(String name)
	{
		_name = name;
	}
	public osversion getOsversion()
	{
		return _version;
	}
	public void setOsversion(osversion version)
	{
		_version = version; 
	}
	public int getRam()
	{
		return _ram;
	}
	public void setRam(int ram)
	{
		_ram = ram;
	}
	public int getHdd()
	{
		return _hdd;
	}
	public void setHdd(int hdd)
	{
		_hdd = hdd;
	}
	
    private String _name; 
    private osversion _version;
    private int _ram;
    private int _hdd;
}