import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	public final static boolean[][][]
	pieces = {
		{{true}}, // the one-cell dot.
		{{true},{true}}, // the two-cell vertical line
		{{true,true}}, // the two-cell horizontal line
		{{true},{true},{true}}, // the three-cell vertical line
		{{true,true,true}}, // three-cell horizontal line
		{{false,true},{true,true}}, // BR three-cell corner
		{{true,false},{true,true}}, // BL three-cell corner
		{{true,true},{false,true}}, // TR three-cell corner
		{{true,true},{true,false}}, // TL three-cell corner
		/* TODO: rest of pieces. */

	};


	private Board b;
	private boolean[][][] piecesInPlay;

	private static final Scanner in = new Scanner(System.in);

	public Game() {
		System.out.println("Game initialized");
		b = new Board();
		piecesInPlay = new boolean[3][][];
	}

	public void play() {
		while(!gameOver()) {
			
		}
	}

	/* returns TRUE if game is over, if no pieces
		are playable. */
	private boolean gameOver() {
		for (boolean[][] p : piecesInPlay)
			if ( !b.getAvailableSpots(p).isEmpty() )
				return false;
		return true;
	}
}
