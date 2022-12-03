package telran.sahapes;

public class SquareTriangle extends Square{
	
	private boolean isLeftDiagonale;

	protected SquareTriangle(int size, boolean isLeftDiagonale) {
		super(size);
		this.isLeftDiagonale = isLeftDiagonale;
	}
	
	public boolean isLeftDiagonale() {
		return isLeftDiagonale;
	}

	public void setLeftDiagonale(boolean isLeftDiagonale) {
		this.isLeftDiagonale = isLeftDiagonale;
	}


	@Override
	public String[] presentation(int offset) {
		String [] res = super.presentation(offset);
		
		for (int i = 0; i <res.length; i++) {
		int currLen = res[i].length();
			if (isLeftDiagonale) {
				res [i] = new StringBuilder(res[i]).delete(offset+1, currLen-i).toString();
			} else {
				res[i] = new StringBuilder(res[i]).delete(offset+1, currLen-i).insert(offset, " ".repeat(res.length-i)).toString();
			}	
		}
		return res;
	}
}
