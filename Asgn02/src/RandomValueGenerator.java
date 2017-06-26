import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator
{
	private static RandomValueGenerator singelton;
	private Random rand = new Random();
	
	private RandomValueGenerator(){}
	
	public static RandomValueGenerator getInstance()
	{
		if(singelton == null)
		{
			//protect from multithreading
			synchronized (RandomValueGenerator.class)
			{
				if(singelton == null)
				{
					singelton = new RandomValueGenerator();
				}
			}
		}		
		return singelton;
	}
	
	public int getNextInt()
	{
		return rand.nextInt();
	}

	public boolean getTrueWithProbability(double p) 
	{
		double num = rand.nextDouble();		
		return num <= p ? true : false;
	}
}
