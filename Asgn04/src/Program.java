import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Program
{

	final static int NUM_PROCS = 6; // How many concurrent processes
	final static int TOTAL_RESOURCES = 30; // Total resources in the system
	final static int MAX_PROC_RESOURCES = 13; // Highest amount of resources any process could need
	final static int ITERATIONS = 30; // How long to run the program
	static int totalHeldResources = 0; //total number of resources held by any given process
	static Random rand = new Random();
	
	public static void main(String[] args)
	{
		
		// The list of processes:
		ArrayList<Proc> processes = new ArrayList<Proc>();
		for (int i = 0; i < NUM_PROCS; i++)
			processes.add(new Proc(MAX_PROC_RESOURCES - rand.nextInt(3))); // Initialize to a new Proc, with some small range for its max
		
		// Run the simulation:
		for (int i = 0; i < ITERATIONS; i++)
		{
			// loop through the processes and for each one get its request
			for (int j = 0; j < processes.size(); j++)
			{
				// Get the request
				int currRequest = processes.get(j).resourceRequest(TOTAL_RESOURCES - totalHeldResources);
				
				boolean safe = false;
				boolean granted = false;
				// just ignore processes that don't ask for resources
				if (currRequest == 0)
					continue;
				else if(currRequest < 0)
					totalHeldResources += currRequest;
				else
				{
					//create a deep copy of all the processes so you don't change the original
					ArrayList<Proc> copyProc = new ArrayList<Proc>();
					for(Proc proc: processes) copyProc.add(proc.clone());
					int total = TOTAL_RESOURCES - totalHeldResources;
					
					//assume you are granting the resources - see if it is still a safe state
					copyProc.get(j).addResources(currRequest);
					total -= currRequest;
					
					safe = isSafe(copyProc, total);
					
					if(safe)
					{
						//if it will be safe after granting requested resources, grant the resources					
						processes.get(j).addResources(currRequest);
						totalHeldResources += currRequest;
						granted = true;		
					}
				}

				// At the end of each iteration, give a summary of the current status:
				System.out.println("\n***** STATUS *****");
				System.out.println("Total Available: " + (TOTAL_RESOURCES - totalHeldResources));
				for (int k = 0; k < processes.size(); k++)
					System.out.println("Process " + k + " holds: " + processes.get(k).getHeldResources() + ", max: " +
							processes.get(k).getMaxResources() + ", claim: " + 
							(processes.get(k).getMaxResources() - processes.get(k).getHeldResources()));
				System.out.println("***** STATUS *****\n");
				
				if(currRequest < 0)
					System.out.println("Process " + j + " released " + -currRequest + " resources");
				else
					System.out.println("Process " + j + " requested " + currRequest + " resources, " + (granted ? "granted" : "denied"));
			}
		}
	}

	private static boolean isSafe(ArrayList<Proc> copyProc, int total) 
	{
		boolean found;
		while(!copyProc.isEmpty())
		{
			found = false;
			Iterator<Proc> iter = copyProc.iterator();
			while(iter.hasNext())
			{
				Proc p = iter.next();
				if(p.getMaxResources() - p.getHeldResources() <= total)
				{
					total += p.getHeldResources();
					iter.remove();
					found = true;
				}
			}
			if(!found) return false;
		}
		return true;
	}
}
