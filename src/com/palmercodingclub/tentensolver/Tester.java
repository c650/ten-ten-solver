package com.palmercodingclub.tentensolver;
import java.util.*;
import com.palmercodingclub.tentensolver.solutions.*;
import cassdelacruzmunoz.StaticticsTools.SingleVariableStats;

public class Tester {
	static Solution[] sols;
	static double[][] score;
	static int trials;
	static String pFP;
	static SingleVariableStats[] stats;
	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("Incorrect # of args.");
			return;
		}
		pFP = args[0];
		trials=Integer.parseInt(args[1]);
		prepareSolutions();
		prepareScores();
		for(int i = 0; i < trials; i++) {
			runTrial(i);
		}
		calculateStats();
		printStats();
	}
	
	private static void prepareSolutions() {
		sols = new Solution[3];
		sols[0]=(Solution)(new AdjacencySolution());
		sols[1]=(Solution)(new ImporvedAdjacencySol());
		sols[2]=(Solution)(new MySolution());
	}
	
	private static void prepareScores() {
		score = new double[sols.length][];
		for (int i = 0; i < score.length; i++) {
			score[i] = new double[trials];
		}
	}
	
	private static void runTrial(int i) {
		long seed = System.currentTimeMillis();
		for(int a = 0; a < sols.length; a++) {
			Game g = new Game(sols[a], pFP, false, seed);
			g.play();
			score[a][i]=g.getScore();
		}
	}
	
	private static void calculateStats() {
		stats = new SingleVariableStats[sols.length];
		for(int i = 0; i < sols.length; i++) {
			stats[i] = new SingleVariableStats(score[i]);
			stats[i].calcAllStats();
		}
	}
	
	private static void printStats() {
		for (int a = 0; a < 5; a++) {
			for (int b=0; b < stats.length; b++) {
				System.out.print(stats[b].fiveNumSummary()[a]+"    ");
			}
			System.out.println("");
		}
	}
	
	public static double avg(ArrayList<Integer> arr) {
		double a = 0;
		for (int b : arr)
			a += b;

		return a / arr.size();
	}
}
