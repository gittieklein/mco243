
public class MemoryLoader 
{
	private Method method;
	private String[] ram = {"A", "A", "A", "A", "A", "1", 
							"1", "A", "A", "A", "2", "2", 
							"2", "2", "A", "A", "A", "3"};
	private int processNum;
	
	public MemoryLoader(Method m)
	{
		method = m;
		processNum = 3;	//set it to 3 because there are already 3 processes inside memory	
	}
	
	/**
	 * tries to insert into memory - if there is no room it returns false
	 */
	public boolean insert(int size)
	{
		if(method == Method.WORST)
		{
			int bestLocation = -1;
			int bestLocationLeft = -1;
			//find the first available place in ram 
			for(int i = 0; i < ram.length; i++)
			{
				//if the place is available
				 if(ram[i].equals("A"))
				 {
					 //count how many consecutative empty places
					 //continue counting from i
					 int places = 1;	//already have the first one, i
					 int location = i+1;
					 while(location < ram.length)
					 {
						 if(ram[location].equals("A"))
						 {
							 places++;
							 location++;
						 }
						 else
							 break;
					 }
					 
					 
					 if(places - size > bestLocationLeft)
					 {
						 bestLocationLeft = places - size;
						 bestLocation = i;
					 } 
				 }
			}
				
			if(bestLocation >= 0)
			{							
				 processNum++;
				 int filled = 0;
				 while(filled < size)
				 {
					 ram[bestLocation++] = Integer.toString(processNum); 
					 filled++;
				 }
				 return true;
			}
				
			return false;
		}
		else if(method == Method.FIRST)
		{
			//find the first available place in ram 
			for(int i = 0; i < ram.length; i++)
			{
				//if the place is available
				 if(ram[i].equals("A"))
				 {
					 //count how many consecutative empty places
					 //continue counting from i
					 int places = 1;	//already have the first one, i
					 int location = i+1;
					 while(location < ram.length)
					 {
						 if(ram[location].equals("A"))
						 {
							 places++;
							 location++;
						 }
						 else
							 break;
					 }
					 
					 //there is enough room here
					 if(places >= size)
					 {
						 processNum++;
						 int filled = 0;
						 while(i <= location && filled < size)
						 {
							 ram[i++] = Integer.toString(processNum); 
							 filled++;
						 }
						 return true;
					 }
				 }
			}
				
			return false;
		}
		else
		{
			int bestLocation = -1;
			int bestLocationLeft = -1;
			//find the first available place in ram 
			for(int i = 0; i < ram.length; i++)
			{
				//if the place is available
				 if(ram[i].equals("A"))
				 {
					 //count how many consecutative empty places
					 //continue counting from i
					 int places = 1;	//already have the first one, i
					 int location = i+1;
					 while(location < ram.length)
					 {
						 if(ram[location].equals("A"))
						 {
							 places++;
							 location++;
						 }
						 else
							 break;
					 }
					 
					 if(places - size >= 0)
						 if(bestLocationLeft < 0 || places - size < bestLocationLeft)
						 {
							 bestLocationLeft = places - size;
							 bestLocation = i;
						 } 
				 }
			}
				
			if(bestLocation >= 0)
			{							
				 processNum++;
				 int filled = 0;
				 while(filled < size)
				 {
					 ram[bestLocation++] = Integer.toString(processNum); 
					 filled++;
				 }
				 return true;
			}
				
			return false;
		}
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(String r : ram)
		{
			sb.append(r + " - ");
		}
		
		return sb.toString();
	}
	
}
