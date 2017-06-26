import java.util.Random;

public class MutualExclusion
{
	static int favoredThread = 1;
	static boolean t1WantsToEnter = false;
	static boolean t2WantsToEnter = false;
	static int count = 0;
	static Random rand = new Random();
	
	public static void main(String[] args)
	{
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		
		t1.start();
		t2.start();
		
		
	}
}
