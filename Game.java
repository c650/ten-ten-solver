import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {

	private static Piece[] pieces;

	private Board b;
	private ArrayList<Piece> piecesInPlay;

	private int score = 0;

	public Game() {
		System.out.println("Game initialized");

		b = new Board();
		piecesInPlay = new ArrayList<Piece>();
	}

	public Game(String pFP) {
		this();
		loadPieces(pFP);
	}

	public void reset() {
		b.clear();
		piecesInPlay.clear();
		score = 0;
	}

	public int play() {
		if (pieces == null) {
			System.err.println("No pieces supplied.");
			System.exit(1);
		}
		for(;;) {
			if (piecesInPlay.isEmpty()) {
				int i = 3;
				while(i-->0)
					piecesInPlay.add(pieces[(int)(Math.random() * pieces.length)]);
			}
			if (gameOver()) break;

			int pieceIdx = pickPiece(); //assume pickPiece validates
			ArrayList<Coordinate> spots = b.getAvailableSpots(piecesInPlay.get(pieceIdx));
			//listArr(spots);

			b.placePiece(piecesInPlay.remove(pieceIdx),spots.get((int)(Math.random() * spots.size())));

			score += b.scanAndClear();
		}
		System.out.println("Total Score: " + score);

		return score;
	}

	/* returns TRUE if game is over, if no pieces
		are playable. */
	private boolean gameOver() {
		for (Piece p : piecesInPlay)
			if ( !b.getAvailableSpots(p).isEmpty() )
				return false;
		return !piecesInPlay.isEmpty();
	}

	private void listArr(ArrayList<Coordinate> spots) {
		for (int i = 0; i < spots.size(); i++) {
			System.out.print(spots.get(i) + "\t");
			if (i % 3 == 2) System.out.println();
		}
	}

	private int pickPiece() {
		/*for (Piece p : piecesInPlay)
			System.out.println(p);*/

		int rand = (int)(Math.random() * piecesInPlay.size());
		while(b.getAvailableSpots(piecesInPlay.get(rand)).isEmpty())
			rand = (int)(Math.random() * piecesInPlay.size());

		return rand;
	}

	private void loadPieces(String pieceFilePath) {
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
		System.out.println("Reading in " + nRead + " pieces...");
		pieces = new Piece[nRead];
		boolean[][] tmpSpace;
		String tmpStr;
		for (int i = 0,r,c; i < nRead; i++) {
			System.out.println(i);
			r = in.nextInt();
			c = in.nextInt();
			System.out.printf("r = %d and c = %d",r,c);
			tmpSpace = new boolean[r][c];

			for (int j = 0; j < tmpSpace.length; j++) {

				tmpStr = in.nextLine();
				for (int k = 0; k < tmpSpace[j].length; k++)
					tmpSpace[j][k] = k < tmpStr.length() && tmpStr.charAt(k) == '*';

			}

			pieces[i] = new Piece(tmpSpace);
			tmpSpace = null;

		}
	}

}
