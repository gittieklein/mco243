
public interface IProcess 
{
	int getPid();
	String getProcName();
	ProcessState execute(int i);
}
