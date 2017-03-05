import java.util.ArrayList;

public class AdjacencySolution extends Solution {

	public final String name = "MySolution -- Just Random";

	MySolution() {
		super();
	}

	public void doMove(ArrayList<Piece> choices) {
		final boolean[][] rawBoard = b.getBoard(); /* we're going to scan/traverse the board to count adjacencies. */

		Piece bestPiece = null;
		Coordinate bestMove = null;
		int mostCollisions = -1, tmp; /* tracks */
		for (Piece p : choices) {
			ArrayList<Coordinate> spots = b.getAvailableSpots(p);
			for (Coordinate c : spots) {
				tmp = countCollisions(p, c);
				if (tmp > mostCollisions) {
					bestPiece = p;
					bestMove = c;
					mostCollisions = tmp;
				}
			}
		}

		b.placePiece( choices.remove(bestPiece), bestMove );
	}

	private int countCollisions(Piece p, Coordinate atSpot) {
		int main_row_idx = atSpot.row - 1,
		    max_main_row = atSpot.row + p.raw.length;
		for (; main_row_idx < max_main_row; ++main_row_idx) {

		}
	}

};
