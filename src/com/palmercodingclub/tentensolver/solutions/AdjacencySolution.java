package com.palmercodingclub.tentensolver.solutions;

import java.util.ArrayList;
import com.palmercodingclub.tentensolver.Solution;
import com.palmercodingclub.tentensolver.Piece;
import com.palmercodingclub.tentensolver.Coordinate;

public class AdjacencySolution extends Solution {

	public AdjacencySolution() {
		super("AdjacencySolution");
	}

	public int doMove(ArrayList<Piece> choices) {
		final boolean[][] rawBoard = b.getBoard(); /* we're going to scan/traverse the board to count adjacencies. */

		Piece bestPiece = null;
		Coordinate bestMove = null;
		int mostCollisions = -1, tmp; /* tracks */
		for (Piece p : choices) {
			ArrayList<Coordinate> spots = b.getAvailableSpots(p);
			for (Coordinate c : spots) {

				tmp = countCollisions(p, c, rawBoard);
				if (tmp > mostCollisions) {
					bestPiece = p;
					bestMove = c;
					mostCollisions = tmp;
				}

			}
		}
		choices.remove(bestPiece);
		b.placePiece( bestPiece , bestMove );
		return bestPiece.getScore();
	}

	private int countCollisions(Piece p, Coordinate atSpot, final boolean[][] rawBoard) {
		int count = 0;
		for (int i = 0, j, curr_row, curr_col; i < p.getRaw().length; ++i) {
			for (j = 0; j < p.getRaw()[i].length; ++j) {
				if (!p.getRaw()[i][j]) continue; /* skip over empty spots in the piece (just there for square-buffering)*/

				curr_row = atSpot.row + i;
				curr_col = atSpot.col + j;

				if (curr_row - 1 >= 0 && rawBoard[curr_row-1][curr_col])
					++count;
				if (curr_col - 1 >= 0 && rawBoard[curr_row][curr_col-1])
					++count;
				if (curr_row + 1 < rawBoard.length && rawBoard[curr_row+1][curr_col])
					++count;
				if (curr_col + 1 < rawBoard[curr_row].length && rawBoard[curr_row][curr_col+1])
					++count;
			}
		}

		if (atSpot.row == 0 || atSpot.row + p.getRaw().length >= rawBoard.length)
			++count;
		if (atSpot.col == 0 || atSpot.col + p.getRaw()[0].length >= rawBoard[0].length)
			++count;

		return count;
	}
};
