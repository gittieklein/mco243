
public class ProcessControlBlock 
{
	private IProcess process;
	private int currInstruction;
	private int register1;
	private int register2;
	private int register3;
	private int register4;
	
	public ProcessControlBlock(IProcess process)
	{
		this.process = process;
		currInstruction = 1;
	}
	
	public IProcess getProcess()
	{
		return process;
	}
	
	public int getCurrentInstruction()
	{
		return currInstruction;
	}
	
	public void setCurrentInstruction(int i)
	{
		currInstruction = i;
	}
	
	public int getRegister1()
	{
		return register1;
	}

	public void setRegister1(int i)
	{
		register1 = i;
	}
	
	public int getRegister2()
	{
		return register2;
	}

	public void setRegister2(int i)
	{
		register2 = i;
	}
	
	public int getRegister3()
	{
		return register3;
	}

	public void setRegister3(int i)
	{
		register3 = i;
	}
	
	public int getRegister4()
	{
		return register4;
	}

	public void setRegister4(int i)
	{
		register4 = i;
	}
}
