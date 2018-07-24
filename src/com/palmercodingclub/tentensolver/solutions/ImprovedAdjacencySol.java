package com.palmercodingclub.tentensolver.solutions;

import java.util.ArrayList;
import com.palmercodingclub.tentensolver.Solution;
import com.palmercodingclub.tentensolver.Board;
import com.palmercodingclub.tentensolver.Piece;
import com.palmercodingclub.tentensolver.Coordinate;

public class ImprovedAdjacencySol extends Solution{
	

		public ImprovedAdjacencySol() {
			super("ImprovedAdjacencySolution");
		}

		public int doMove(ArrayList<Piece> choices) {
			final boolean[][] rawBoard = b.getBoard(); /* we're going to scan/traverse the board to count adjacencies. */
			
			Piece bestPiece = null;
			Piece bestPiece2 = null;
			Coordinate bestMove = null;
			Coordinate bestMove2 = null;
			int mostCollisions = -1, mostCleared = 0, tmp, tmp2; /* tracks */
			for (Piece p : choices) {
				ArrayList<Coordinate> spots = b.getAvailableSpots(p);
				for (Coordinate c : spots) {
					
					tmp = countCollisions(p, c, rawBoard);
					tmp2 = countClearable(p, c, b);
					
					if (tmp > mostCollisions) {
						bestPiece = p;
						bestMove = c;
						mostCollisions = tmp;
					}
					if (tmp2 > mostCleared) {
						bestPiece2 = p;
						bestMove2 = c;
						mostCleared = tmp;
					}

				}
			}
			if(mostCleared > 0){
				choices.remove(bestPiece2);
				b.placePiece( bestPiece2 , bestMove2);
				return bestPiece2.getScore();
			}
			else{
				choices.remove(bestPiece);
				b.placePiece( bestPiece , bestMove );
				return bestPiece.getScore();
			}
		}
		private int countClearable(Piece p, Coordinate atSpot, Board b){
			Board temp = b.copyBoard();
			temp.placePiece(p, atSpot);
			return temp.scanAndClear();
		}
		

		private int countCollisions(Piece p, Coordinate atSpot, final boolean[][] rawBoard) {
			int count = 0;
			for (int i = 0, j, curr_row, curr_col; i < p.getRaw().length; i++) {
				for (j = 0; j < p.getRaw()[i].length; j++) {
					if (!p.getRaw()[i][j]) continue; /* skip over empty spots in the piece (just there for square-buffering)*/

					curr_row = atSpot.row + i;
					curr_col = atSpot.col + j;
					if(curr_row == 0 || curr_row + 1 >= rawBoard.length)
						++count;
					if (curr_col == 0 || curr_row + 1 >= rawBoard[0].length)
						++count;
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

			return count;
		}

}
