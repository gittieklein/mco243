
public class Thread1 extends Thread
{
	public void run()
	{
		try
		{
			while(MutualExclusion.count < 10)
			{
				MutualExclusion.t1WantsToEnter = true;
				while (MutualExclusion.favoredThread == 2)
				{
					while (MutualExclusion.t2WantsToEnter)
					{
						Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
					}
					MutualExclusion.favoredThread = 1;			
				}
				Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
				// critical section code
				System.out.println("Thread 1 critical code step 1");
				Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
				System.out.println("Thread 1 critical code step 2");
				Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
				System.out.println("Thread 1 finished critical code");
				MutualExclusion.t1WantsToEnter = false;
					
				// non-critical code 
				System.out.println("Thread 1 non critical code");
				Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
				MutualExclusion.count++;
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}	
}
