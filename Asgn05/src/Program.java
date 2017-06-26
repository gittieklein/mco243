import java.util.Scanner;

public class Program 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter '1' for First Fit, '2' for Best Fit, '3' for Worst Fit ");
		int result = input.nextInt();
		Method method;
		if(result == 1)
			method = Method.FIRST;
		else if(result == 2)
			method = Method.BEST;
		else
			method = Method.WORST;
		
		MemoryLoader memory = new MemoryLoader(method);
		System.out.println(memory);
		
		int size = 0;
		boolean insert;
		
		System.out.println("What size page are you adding? (Enter -1 to exit) ");
		size = input.nextInt();
		
		while(size != -1)
		{
			insert = memory.insert(size);
			
			if(insert)
			{
				System.out.println(memory);
			}
			else
			{
				System.out.println("There is no room for this process");
			}
			
			System.out.println("What size page are you adding? (Enter -1 to exit) ");
			size = input.nextInt();
		}
		System.out.println("Good bye");
		System.exit(0);
	}
}
