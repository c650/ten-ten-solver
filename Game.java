import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
/* press 'v' in Vim to highlight */
	public final static boolean[][][]
	piecesRaw = {
		{{true}}, // the one-cell dot.
		{{true},{true}}, // the two-cell vertical line
		{{true,true}}, // the two-cell horizontal line
		{{true},{true},{true}}, // the three-cell vertical line
		{{true,true,true}}, // three-cell horizontal line
		{{false,true},{true,true}}, // BR three-cell corner
		{{true,false},{true,true}}, // BL three-cell corner
		{{true,true},{false,true}}, // TR three-cell corner
		{{true,true},{true,false}}, // TL three-cell corner

	};


	public final static Piece[]
	pieces = {
		new Piece(piecesRaw[0]),
		new Piece(piecesRaw[1]),
		new Piece(piecesRaw[2]),
		new Piece(piecesRaw[3]),
		new Piece(piecesRaw[4]),
		new Piece(piecesRaw[5]),
		new Piece(piecesRaw[6]),
		new Piece(piecesRaw[7]),
		new Piece(piecesRaw[8]),
	};

	private Board b;
	private ArrayList<Piece> piecesInPlay;

	private static final Scanner in = new Scanner(System.in);

	private int score = 0;

	public Game() {
		System.out.println("Game initialized");
		b = new Board();
		piecesInPlay = new ArrayList<Piece>();
	}

	public void reset() {
		b.clear();
		piecesInPlay.clear();
		score = 0;
	}

	public int play() {
		do {
			if (piecesInPlay.isEmpty()) {
				int i = 3;
				while(i-->0)
					piecesInPlay.add(pieces[(int)(Math.random() * pieces.length)]);
			}

//			System.out.println(b);
			
			int pieceIdx = pickPiece(); //assume pickPiece validates
			ArrayList<Coordinate> spots = b.getAvailableSpots(piecesInPlay.get(pieceIdx));
			//listArr(spots);
			
			b.placePiece(piecesInPlay.remove(pieceIdx),spots.get((int)(Math.random() * spots.size())));
		
			score += b.scanAndClear();	
//			System.out.println(b);
			
		} while(!gameOver());
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

}
