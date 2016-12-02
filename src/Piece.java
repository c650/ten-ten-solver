public class Piece {

	/* actual incidence matrix for piece  */
	public final boolean[][] raw;

	public Piece( boolean[][] r  ) {
		raw = r;
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

}
