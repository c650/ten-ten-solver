import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {
	private final boolean DEBUG;
	private static Piece[] pieces;

	private Board b;
	private ArrayList<Piece> piecesInPlay;

	private int score = 0;

	public Game() {
		this("", false);
	}

	public Game(String pFP) {
		this(pFP, true);
	}

	public Game(String pFP, boolean debug) {

		DEBUG = debug;

		b = new Board();
		piecesInPlay = new ArrayList<Piece>();

		loadPieces(pFP);

		debug("Game initialized");

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
			System.out.println(b);
			if (gameOver()) break;

			int pieceIdx = pickPiece(); //assume pickPiece validates
			ArrayList<Coordinate> spots = b.getAvailableSpots(piecesInPlay.get(pieceIdx));

			// listArr(spots);

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
		// for (Piece p : piecesInPlay)
		// 	debug(p.toString());

		int rand = (int)(Math.random() * piecesInPlay.size());
		while(b.getAvailableSpots(piecesInPlay.get(rand)).isEmpty())
			rand = (int)(Math.random() * piecesInPlay.size());

		return rand;
	}

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

	private void debug(String s) {
		if (DEBUG)
			System.out.println(s);
	}

}
