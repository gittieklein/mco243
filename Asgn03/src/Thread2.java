
public class Thread2 extends Thread
{
	public void run()
	{
		try
		{
			while(MutualExclusion.count < 10)
			{
				MutualExclusion.t2WantsToEnter = true;
				while (MutualExclusion.favoredThread == 1)
				{
					while (MutualExclusion.t1WantsToEnter)
					{
						Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
					}
					Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
					MutualExclusion.favoredThread = 2;			
				}
				// critical section code
				System.out.println("Thread 2 critical code step 1");
				Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
				System.out.println("Thread 2 critical code step 2");
				Thread.sleep(MutualExclusion.rand.nextInt(3000) + 3000);
				System.out.println("Thread 2 finished critical code");
				MutualExclusion.t2WantsToEnter = false;
					
				// non-critical code 
				System.out.println("Thread 2 non critical code");
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
