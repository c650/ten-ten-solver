public class Board {
	private boolean[][] board;

	public Board() {
		/* board will always be a square. */
		board = new boolean[10][10];
	}

	public int scanAndClear() {
		int cleared = 0;
		boolean clearableRows[10], clearableCols[10];

		/* Loop thru each row and validate.	*/
		for (int i = 0,j ; i < board.length ; i++) {
			clearableRows[i] = clearableCols[i]  = true;
			
			for (j = 0; j < board[i].length ; j++) {
				clearableRows[i] = clearableRows[i] && board[i][j];
				clearableCols[i] = clearableCols[i] && board[j][i];
			}
			if (clearableRows[i]) cleared++;
			if (clearableCols[i]) cleared++;
		}

		/* CLEAR all clearable rows */
		for (int i = 0, j ; i < clearableRows.length ; i++)
			if (clearableRows[i])
				for (j = 0 ; j < board[i].length ; i++)
					board[i][j] = false;

		/* CLEAR all clearable columns. */
		for (int i = 0, j ; j < clearableCols.length ; i++)
			if (clearableCols[i])
				for (j = 0 ; j < board.length ; j++)
					board[j][i] = false;

		return cleared;
		/* TODO: ensure that intersecting fields do not affect score
			in the wrong whey. */
	}
		
}
