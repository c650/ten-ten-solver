package com.palmercodingclub.tentensolver;

public class Piece {

	/* actual incidence matrix for piece  */
	private final boolean[][] raw;
	private int score=0;

	public Piece( boolean[][] r  ) {
		raw = r;
		calcScore();
		// raw = new boolean[r.length][];
		// for (int i = 0, j = 0; i < raw.length; i++) {
		// 	raw[i] = new boolean[r[i].length];
		// 	for (j = 0; j < raw[i].length; j++) {
		// 		raw[i][j] = r[i][j];
		// 	}
		// }
	}

	public String toString() {
		String ret = "";
		for (boolean [] arr : raw) {
			for (boolean cell : arr) {
				ret += (cell ? "O" : "-");
			}
			ret += "\n";
		}
		return ret;
	}

	/**
	* Returns the values of the piece without handing over the actual array object
	* @return gives a copy of the 2d boolean array
	*/
	public boolean[][] getRaw() {
		boolean[][] result=new boolean[raw.length][];
		System.arraycopy(raw, 0, result, 0, raw.length);
		return result;
	}
	
	private void calcScore() {
		for(int a=0;a<raw.length;a++) {
			for(int b=0; b<raw[a].length;b++) {
				if (raw[a][b]) {
					score++;
				}
			}
		}
	}
	
	public int getScore() {
		return score;
	}
}
