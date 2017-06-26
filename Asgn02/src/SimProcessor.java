
public class SimProcessor implements IProcessor
{
	private IRandomValueGenerator random;
	private IProcess process;
	private int[] registers;
	private int currInstruction;
	
	public SimProcessor(IRandomValueGenerator random)
	{
		this.random = random;
		registers = new int[4];
		currInstruction = 1;
	}

	public IProcess getIProcess() 
	{
		return process;
	}

	public void setIProcess(IProcess p) 
	{
		process = p;
	}

	public int getCurrentInstruction() 
	{
		return currInstruction;
	}

	public void setCurrentInstruction(int i) 
	{
		currInstruction = i;
	}

	public ProcessState executeNextInstruction() 
	{
		return process.execute(currInstruction++);
	}

	public void setRegisterValue(int i, int val) 
	{
		registers[i] = val;
	}

	public int getRegisterValue(int i) 
	{
		return random.getNextInt();
	}
}
