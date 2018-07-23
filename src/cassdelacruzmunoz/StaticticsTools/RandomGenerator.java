package cassdelacruzmunoz.StaticticsTools;

import java.util.ArrayList;

public class RandomGenerator {
	public RandomGenerator()
	{
	}
	/**
	 * Generates experimental groups. Currently doesn't support groups of unequal size.
	 * @param numSets How many treatments are being used
	 * @param experimentalUnits The array of experimental units casted to objects
	 * @return a two-dimensional array of the experimental units seperated into groups of numSets treatments; format is Object[numSets][experimentalUnits.length/4]
	 */
	public Object[][] randomExperimentalSets(int numSets,Object[] experimentalUnits)
	{
		Object[][] result;
		if(experimentalUnits.length%numSets!=0)
		{
			return null;
		}
		else
		{
			result=new Object[numSets][experimentalUnits.length/numSets];
			ArrayList<Object> temp=new ArrayList<Object>();
			for(int i=0;i<experimentalUnits.length;i++)
			{
				temp.add(experimentalUnits[i]);
			}
			int a=0;
			int b=0;
			while(temp.size()>0)
			{
				if(b<experimentalUnits.length/4)
				{
					result[a][b]=temp.get((int)(Math.random()*temp.size()));
					temp.remove(result[a][b]);
					b++;
				}
				else
				{
					a++;
					b=0;
					result[a][b]=temp.get((int)(Math.random()*temp.size()));
					temp.remove(result[a][b]);
				}
			}
		}
		return result;
	}
	/**
	 * Generates a Simple Random Sample using the given population and sample size.
	 * @param numSample The amount of people in the desired sample.
	 * @param population The population being sampled
	 * @return An array of objects containing each object selected for the sample.
	 */
	public Object[] simpleRandomSample(int numSample,Object[] population)
	{
		Object[] result;
		result=new Object[numSample][population.length/numSample];
		ArrayList<Object> temp=new ArrayList<Object>();
		for(int i=0;i<population.length;i++)
		{
			temp.add(population[i]);
		}
		for(int i=0;i<numSample;i++)
		{
			result[i]=temp.get((int)(Math.random()*temp.size()));
			temp.remove(result[i]);
		}
		return result;
	}
	/**
	 * Generates a table of random digits
	 * @param numDigits The amount of digits generated
	 * @return A string of digits, separated by spaces every 5 and by new lines every 150
	 */
	public String randomDigits(int numDigits)
	{
		String result="";
		for(int i=0;i<numDigits;i++)
		{
			result+=(int)(Math.random()*10);
			if(i%5==4)
			{
				result+=" ";
			}
			if(i%150==149)
			{
				result+="\n";
			}
		}
		return result;
	}
}