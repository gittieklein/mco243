import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Simulation 
{
	public static void main(String[] args)
	{
		Random rand = new Random();	//used to generate number of instructions per process
		
		IRandomValueGenerator random = RandomValueGenerator.getInstance();
		IProcessor processor = new SimProcessor(random);
		ProcessControlBlock[] pcb = new ProcessControlBlock[10];
		
		for(int i = 0; i < pcb.length; i++)
		{
			pcb[i] = new ProcessControlBlock(new SimProcess(random, i + 1, "Proc" + (i + 1), rand.nextInt(300) + 101));
		}
		
		final int QUANTUM = 5;
		
		Queue<ProcessControlBlock> processReady = new LinkedList<ProcessControlBlock>();
		Queue<ProcessControlBlock> processBlocked = new LinkedList<ProcessControlBlock>();
		
		for(int i = 0; i < pcb.length; i++)
		{
			processReady.add(pcb[i]);
		}
		
		//get the first process ready before it executes
		ProcessControlBlock tempPCB = processReady.poll();
		processor.setIProcess(tempPCB.getProcess());
		processor.setCurrentInstruction(tempPCB.getCurrentInstruction());
		processor.setRegisterValue(0, tempPCB.getRegister1());
		processor.setRegisterValue(1, tempPCB.getRegister2());
		processor.setRegisterValue(2, tempPCB.getRegister3());
		processor.setRegisterValue(3, tempPCB.getRegister4());
		ProcessState state = ProcessState.READY;	//assume that the process starts ready
		int count = 0;	//make sure a process only executes less that the quantum
		for(int i = 0; i < 3000; i++)
		{
			System.out.print("Step " + i + ": ");
			//each iteration executes an instruction or performs a context switch
			if(count < QUANTUM && state.equals(ProcessState.READY))
			{
				state = processor.executeNextInstruction();
				count++;
			}
			else
			{
				if(!state.equals(ProcessState.FINISHED))
				{
					//save the state of the current process if the process isn't finished
					tempPCB.setCurrentInstruction(processor.getCurrentInstruction());
					tempPCB.setRegister1(processor.getRegisterValue(0));
					tempPCB.setRegister2(processor.getRegisterValue(1));
					tempPCB.setRegister3(processor.getRegisterValue(2));
					tempPCB.setRegister4(processor.getRegisterValue(3));	
					System.out.println("Context Switch");
					System.out.println("\tSaving Process: " + tempPCB.getProcess().getPid());
					System.out.println("\t\tInstruction: " + tempPCB.getCurrentInstruction() + " - R1: " + tempPCB.getRegister1() +
							", R2: " + tempPCB.getRegister2() + ", R3: " + tempPCB.getRegister3() + ", R4: " + tempPCB.getRegister4());
					//if it reached its quantum put it in the back of the ready queue
					if(count == QUANTUM)
					{
						System.out.println("***Quantum Expired***");
						processReady.add(tempPCB);
					}
					//if a process is blocked, move it to the blocked queue
					else
					{
						System.out.println("***Process Blocked***");
						processBlocked.add(tempPCB);
					}
					count = 0;	//reset count to 0 so the next process has the number of quantum left
				}
				else
				{
					System.out.println("Context Switch");
					System.out.println("***Process Completed***");
				}
				
				//if there are more ready processes, take off the next process
				if(!processReady.isEmpty())
				{
					tempPCB = processReady.poll();
					//restoring state of process onto processor
					processor.setIProcess(tempPCB.getProcess());
					processor.setCurrentInstruction(tempPCB.getCurrentInstruction());
					processor.setRegisterValue(0, tempPCB.getRegister1());
					processor.setRegisterValue(1, tempPCB.getRegister2());
					processor.setRegisterValue(2, tempPCB.getRegister3());
					processor.setRegisterValue(3, tempPCB.getRegister4());
					state = ProcessState.READY;
					System.out.println("\tRestoring Process: " + tempPCB.getProcess().getPid());
					System.out.println("\t\tInstruction: " + tempPCB.getCurrentInstruction() + " - R1: " + tempPCB.getRegister1() +
							", R2: " + tempPCB.getRegister2() + ", R3: " + tempPCB.getRegister3() + ", R4: " + tempPCB.getRegister4());
				}
			}	
			//add a null value to the queue so you have a placeholder so you don't have an infinite loop
			//check if each element should be re-added to the blocked queue or be moved to the ready queue
			//if the element dequeued is null, you checked the whole queue and should move on
			processBlocked.add(null);
			ProcessControlBlock checkPCB = processBlocked.poll();
			while(checkPCB != null)
			{
				//test for a random probability
				//if it's true it's ready
				//if not, add it to the back of the blocked queue
				if(random.getTrueWithProbability(.3))
					processReady.add(checkPCB);
				else
					processBlocked.add(checkPCB);
				checkPCB = processBlocked.poll();
			}
		}
	}	
}
