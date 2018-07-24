package com.cassdelacruzmunoz.library.math;

public class SingleVariableStats {
	private double[] data;
	private double[] deviation;
	private double[] squaredDeviations;
	private double[] zScores;
	private double[] fiveNumSummary=new double[5];
	private double mean=0;
	private double standardDeviation=0;
	private double iqr;
	private double range;
	/**
	 * Main constructor
	 * @param d The data set
	 */
	public SingleVariableStats(double[] d)
	{
		this.data=d;
	}
	/**
	 * Calculates all the stats.
	 * 	Five number summary
	 * 	Mean and standard deviation
	 * 	IQR and range
	 * 	Z-scores
	 */
	public void calcAllStats()
	{
		calcMeanStandardDeviationZScores();
		data=mergesort(data);
		calcFiveNumSummary();
	}
	/**
	 * Gives the stats
	 * @return [0] is data
	 * 		   [1] is deviations
	 * 		   [2] is squared deviations
	 * 		   [3] is z-scores
	 * 		   [4] is five number summary
	 * 		   [5][0] is mean
	 * 		   [5][1] is standard deviation
	 * 		   [5][2] is iqr
	 * 		   [5][3] is range
	 */
	public double[][] getAllStats()
	{
		double[][] result=new double[6][];
		result[0]=data;
		result[1]=deviation;
		result[2]=squaredDeviations;
		result[3]=zScores;
		result[4]=fiveNumSummary;
		result[5]=new double[4];
		result[5][0]=mean;
		result[5][1]=standardDeviation;
		result[5][2]=iqr;
		result[5][3]=range;
		return result;
	}
	public String[][] getAllStatLabels() {
		String[][] result=new String[6][];
		result[0] = new String[data.length];
		for(int i=0;i<result[0].length;i++) {
			result[0][i]="Data point "+(i+1);
		}
		result[1] = new String[deviation.length];
		for(int i=0;i<result[1].length;i++) {
			result[1][i]="Deviation point "+(i+1);
		}
		result[2] = new String[squaredDeviations.length];
		for(int i=0;i<result[2].length;i++) {
			result[2][i]="Squred Deviation point "+(i+1);
		}
		result[3] = new String[zScores.length];
		for(int i=0;i<result[3].length;i++) {
			result[3][i]="Standardized Score point "+(i+1);
		}
		result[4]=new String[] {"min","Q1", "median","Q3", "max"};
		result[5]=new String[] {"mean", "standard deviation", "IQR", "range"};
		return result;
	}
	private void calcFiveNumSummary()
	{
		fiveNumSummary[0]=data[0];
		if(data.length%2==0)
		{
			fiveNumSummary[1]=(data[data.length/4]+data[data.length/4-1])/2;
		}
		else
		{
			fiveNumSummary[1]=(data[data.length/4]);
		}
		if(data.length%2==0)
		{
			fiveNumSummary[2]=(data[data.length/2]+data[data.length/2-1])/2;
		}
		else
		{
			fiveNumSummary[2]=(data[data.length/2]);
		}
		if(data.length%2==0)
		{
			fiveNumSummary[3]=(data[data.length/4+data.length/2]+data[data.length/4-1+data.length/2])/2;
		}
		else
		{
			fiveNumSummary[3]=(data[data.length/4+data.length/2]);
		}
		fiveNumSummary[4]=data[data.length-1];
		iqr=fiveNumSummary[3]-fiveNumSummary[1];
		range=fiveNumSummary[4]-fiveNumSummary[0];
	}
	private void calcMeanStandardDeviationZScores()
	{
		for(int i=0;i<data.length;i++)
		{
			mean+=data[i];
		}
		deviation=new double[data.length];
		squaredDeviations=new double[data.length];
		for(int i=0;i<data.length;i++)
		{
			deviation[i]=mean-data[i];
			squaredDeviations[i]=Math.pow(deviation[i],2);
			standardDeviation+=squaredDeviations[i];
		}
		zScores=new double[data.length];
		for(int i=0;i<data.length;i++)
		{
			zScores[i]=(data[i]-mean)/standardDeviation;
		}
	}
	private static double[] merge(double[] a, double[] b) {
        double[] c = new double[a.length + b.length];
        int i = 0, j = 0;
        for (int k = 0; k < c.length; k++) {
            if      (i >= a.length) c[k] = b[j++];
            else if (j >= b.length) c[k] = a[i++];
            else if (a[i] <= b[j])  c[k] = a[i++];
            else                    c[k] = b[j++];
        }
        return c;
    }

    public static double[] mergesort(double[] input) {
        int N = input.length;
        if (N <= 1) return input;
        double[] a = new double[N/2];
        double[] b = new double[N - N/2];
        for (int i = 0; i < a.length; i++)
            a[i] = input[i];
        for (int i = 0; i < b.length; i++)
            b[i] = input[i + N/2];
        return merge(mergesort(a), mergesort(b));
    }

	/**
	 * Accessor method for data; copies the array to prevent passing the actual array through
	 * @return a copy of the data array
	 */
	public double[] data()
	{
		double[] temp=new double[data.length];
		for(int i=0;i<temp.length;i++)
		{
			temp[i]=data[i];
		}
		return temp;
	}
	/**
	 * Accessor method for deviations; copies the array to prevent passing the actual array through
	 * @return a copy of the deviations array
	 */
	public double[] deviation()
	{
		double[] temp=new double[deviation.length];
		for(int i=0;i<temp.length;i++)
		{
			temp[i]=deviation[i];
		}
		return temp;
	}
	/**
	 * Accessor method for squared deviations; copies the array to prevent passing the actual array through
	 * @return a copy of the squared deviations array
	 */
	public double[] squaredDeviations()
	{
		double[] temp=new double[squaredDeviations.length];
		for(int i=0;i<temp.length;i++)
		{
			temp[i]=squaredDeviations[i];
		}
		return temp;
	}
	/**
	 * Accessor method for z-scores; copies the array to prevent passing the actual array through
	 * @return a copy of the z-scores array
	 */
	public double[] zScores()
	{
		double[] temp=new double[zScores.length];
		for(int i=0;i<temp.length;i++)
		{
			temp[i]=zScores[i];
		}
		return temp;
	}
	/**
	 * Accessor method for the five number summary; copies the array to prevent passing the actual array through
	 * @return a copy of the five number summary array
	 */
	public double[] fiveNumSummary()
	{
		double[] temp=new double[5];
		for(int i=0;i<5;i++)
		{
			temp[i]=fiveNumSummary[i];
		}
		return temp;
	}
	/**
	 * Accessor for the mean
	 * @return mean
	 */
	public double mean()
	{
		return mean;
	}
	/**
	 * Accessor for the standard deviation
	 * @return standard deviation
	 */
	public double standardDeviation()
	{
		return standardDeviation;
	}
	/**
	 * Accessor for the IQR
	 * @return IQR
	 */
	public double iqr()
	{
		return iqr;
	}
	/**
	 * Accessor for the range
	 * @return range
	 */
	public double range()
	{
		return range;
	}
}
