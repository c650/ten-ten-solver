package com.palmercodingclub.tentensolver;
import java.util.*;
import java.lang.Thread;
import com.palmercodingclub.tentensolver.solutions.*;

public class Tester {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Incorrect # of args.");
			return;
		}
		int n=1000;
		int[][] score = new int[3][];
		int[] scoreSum=new int[3];
		for (int i = 0; i < score.length; i++) {
			score[i] = new int[n];
			scoreSum[i]=0;
		}
		for(int i = 0; i < n; i++) {
			long seed = System.currentTimeMillis();
			Game g = new Game((Solution)(new AdjacencySolution()), args[0], false, seed);
			g.play();
			score[0][i]=g.getScore();
			scoreSum[0]+=g.getScore();
			g = new Game((Solution)(new ImporvedAdjacencySol()), args[0], false, seed);
			g.play();
			score[1][i]=g.getScore();
			scoreSum[1]+=g.getScore();
			g = new Game((Solution)(new MySolution()), args[0], false, seed);
			g.play();
			score[2][i]=g.getScore();
			scoreSum[2]+=g.getScore();
		}
		for(int i=0;i<scoreSum.length;i++) {
			System.out.println(scoreSum[i]);
		}
	}

	public static double avg(ArrayList<Integer> arr) {
		double a = 0;
		for (int b : arr)
			a += b;

		return a / arr.size();
	}
}
