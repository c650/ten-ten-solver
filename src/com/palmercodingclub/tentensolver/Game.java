package com.palmercodingclub.tentensolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private final boolean DEBUG;
	private static Piece[] pieces;
	private Random rng;
	private Board b;
	private ArrayList<Piece> piecesInPlay;

	private Solution sol;

	private int score = 0;

	/**
	* This constructor assums no file for the pieces, and has debug turned off.
	* @param s which solution class to use
	*/
	public Game(Solution s) {
		this(s, "", false,-1);
	}

	/**
	* This constructor has debug turned off.
	* @param s   which solution class to use
	* @param pFP the file where the `Piece` info is stored
	*/
	public Game(Solution s, String pFP) {
		this(s, pFP, false,-1);
	}
	
	/**
	* This constructor has debug turned off.
	* @param s     which solution class to use
	* @param debug a boolean, dictates whether to print debugging messages or not.
	*/
	public Game(Solution s, boolean debug) {
		this(s, "", debug,-1);
	}
	
	public Game(Solution s, String pFP, boolean debug) {
		this(s, pFP, debug,-1);
	}
	
	/**
	* This constructor makes no assumptions and is called by the other constructors.
	* @param s     which solution class to use
	* @param pFP   the file where the `Piece` info is stored
	* @param debug a boolean, dictates whether to print debugging messages or not.
	*/
	public Game(Solution s, String pFP, boolean debug, long seed) {

		DEBUG = debug;

		b = new Board();
		piecesInPlay = new ArrayList<Piece>();

		loadPieces(pFP);

		sol = s;
		sol.setBoard(b);

		debug("Game initialized");
		
		if (seed==-1) {
			rng = new Random();
		}
		else {
			rng = new Random(seed);
		}

	}

	/**
	* Resets the game for trying again
	*/
	public void reset() {
		b.clear();
		piecesInPlay.clear();
		score = 0;
	}

	/**
	* Main logic for the game playing
	*/
	public int play() {
		if (pieces == null) {
			System.err.println("No pieces supplied.");
			System.exit(1);
		}

		while(true) {
			if (piecesInPlay.isEmpty()) {
				int i = 3;
				while(i-->0)
					piecesInPlay.add(pieces[(int)(rng.nextInt(pieces.length))]);
			}
			System.out.println(b);
			if (gameOver()) break;

			/*	The subclass of Solution will handle this!
				You'll implement your own .doMove(...) with
				your choice of logic and/or randomness to
				solve the puzzle!
			*/
			sol.doMove( piecesInPlay );

			score += b.scanAndClear();
		}
		System.out.println("Total Score: " + score);

		return score;
	}

	/**
	* Checks if the game is finished
	* @return true if game is over, if no pieces are playable, and false otherwise
	*/
	private boolean gameOver() {
		for (Piece p : piecesInPlay)
			if ( !b.getAvailableSpots(p).isEmpty() )
				return false;
		return !piecesInPlay.isEmpty();
	}

	// I'm not sure what this is for. It is currently unused.
	// Is it for debugging?
	private void listArr(ArrayList<Coordinate> spots) {
		for (int i = 0; i < spots.size(); i++) {
			System.out.print(spots.get(i) + "\t");
			if (i % 3 == 2) System.out.println();
		}
	}

	/**
	* Tries to load the pieces from the file
	* @param pieceFilePath the location of the piece file
	*/
	private void loadPieces(String pieceFilePath) {
		if (pieces != null) return;

		Scanner in = null;
		try {
			in = new Scanner(new File(pieceFilePath));
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't find file " + pieceFilePath);
			System.exit(1);
		}
		if (in == null)
			return;
		/*
			We will expect the file to start with one number.
			This number denotes the number of pieces to read in.
			Each piece will consist of two numbers r and c, how
			many rows and columns a piece needs, respectively.
			Each piece will consist of *'s and any other character
			set up as a matrix.
		*/
		int nRead = in.nextInt();
		debug("Reading in " + nRead + " pieces...");
		pieces = new Piece[nRead];
		boolean[][] tmpSpace;
		String tmpStr;

		for (int i = 0,r=0,c=0,j=0,k=0; i < nRead; i++) {

			r = in.nextInt();
			c = in.nextInt();

			tmpSpace = new boolean[r][c];


			for (j = 0; j < r; j++) {
				tmpStr = in.next();
				for (k = 0; k < c; k++)
					tmpSpace[j][k] = tmpStr.charAt(k) == '*';
			}

			pieces[i] = new Piece(tmpSpace);
			debug("Loaded: \n" + pieces[i]);
			tmpSpace = null;

		}
	}

	
	/**
	* Prints a string if debug is turned on
	* @param s The string to be printed
	*/
	private void debug(String s) {
		if (DEBUG)
			System.out.println(s);
	}
	
	public int getScore() {
		return score;
	}

}
