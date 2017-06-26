
public interface IProcessor 
{
	IProcess getIProcess();
	void setIProcess(IProcess p);
	int getCurrentInstruction();
	void setCurrentInstruction(int i);
	ProcessState executeNextInstruction();
	void setRegisterValue(int i, int val);
	int getRegisterValue(int i);
}
