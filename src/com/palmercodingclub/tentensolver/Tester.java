package com.palmercodingclub.tentensolver;

import java.util.ArrayList;

import com.cassdelacruzmunoz.library.ConsoleIO;
import com.cassdelacruzmunoz.library.math.SingleVariableStats;
import com.palmercodingclub.tentensolver.solutions.AdjacencySolution;
import com.palmercodingclub.tentensolver.solutions.ImprovedAdjacencySol;
import com.palmercodingclub.tentensolver.solutions.MetaSolution;
import com.palmercodingclub.tentensolver.solutions.MySolution;

public class Tester {
	static Solution[] sols;
	static double[][] score;
	static int trials;
	static String pFP;
	static SingleVariableStats[] stats;
	static long startTime;
	
	public static void main(String[] args) {
		pFP = args[0];
		switch (ConsoleIO.generateMenu("Which mode would you like to use?", new String[] {"Comparison Test\n","Algorithm assisted play"} )) {
			case 0:
				trials=ConsoleIO.getNumFromUser("How many trials would you like?");
				comparisonTest();
				main(args);
				break;
			case 1:
				Game g = new Game(sols[0], pFP, false);
				g.playWithPlayer();
				main(args);
				break;
			case 2:
				break;
		}
	}
	
	private static void comparisonTest() {
		startTime=System.currentTimeMillis();
		prepareSolutions();
		prepareScores();
		for(int i = 0; i < trials; i++) {
			if(i%(trials/100.0)==0) {
				ConsoleIO.print((int)(((double)(i)/trials)*100)+"% done");
			}
			runTrial(i);
		}
		calculateStats();
		printFiveNumSum();
		ConsoleIO.print("Delta Time: "+(System.currentTimeMillis()-startTime)/1000.0+" seconds");
	}
	
	private static void prepareSolutions() {
		sols = new Solution[4];
		sols[0]=(Solution)(new AdjacencySolution());
		sols[1]=(Solution)(new ImprovedAdjacencySol());
		sols[2]=(Solution)(new MySolution());
		sols[3]=(Solution)(new MetaSolution());
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
	
	private static void printFiveNumSum() {
		for (int a = 0; a < 5; a++) {
			System.out.printf("%s\t",stats[0].getAllStatLabels()[4][a]);
			for (int b=0; b < stats.length; b++) {
				System.out.printf("%5d\t",(int)(stats[b].fiveNumSummary()[a]));
			}
			ConsoleIO.print("");
		}
	}
	
	public static double avg(ArrayList<Integer> arr) {
		double a = 0;
		for (int b : arr)
			a += b;

		return a / arr.size();
	}
}
