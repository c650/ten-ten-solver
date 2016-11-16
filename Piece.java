public class Piece {

	/* actual incidence matrix for piece  */
	public final boolean[][] raw;

	public Piece( boolean[][] r  ) {
		raw = r;
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
