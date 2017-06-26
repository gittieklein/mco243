
public class SimProcess implements IProcess
{
	private IRandomValueGenerator random;
	private int pid;
	private String procName;
	private int totalInstructions;
	
	public SimProcess(IRandomValueGenerator random, int pid, String procName, int totalInstructions)
	{
		this.random = random;
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
	}
	
	public int getPid() 
	{
		return pid;
	}

	public String getProcName() 
	{
		return procName;
	}

	public ProcessState execute(int instruction) 
	{
		System.out.printf("Proc: %s, PID: %d executing instruction %d%n", procName, pid, instruction);
		if(instruction >= totalInstructions)
			return ProcessState.FINISHED;
		else 
		{
			boolean block = random.getTrueWithProbability(.15);
			if(block)
				return ProcessState.BLOCKED;
			else
				return ProcessState.READY;
		}			
	}	
}
