package telran.sahapes;

public class Rectangle extends Shape {

	public Rectangle(int width, int height) {
		super(width, height);
	}

	public String[] presentation(int offset) {
		String res[] = new String[getHeight()];
		String line = getLine(offset);
		res[0] = line;
		int lastLine = getHeight() - 1;
		res[lastLine] = line;
		for (int i = 1; i < lastLine; i++) {
			res[i] = this.getMiddleLine(offset);
		}
		return res;
	}
	private String getMiddleLine(int offset) {
		
		return getOffset(offset) + symbol + getOffset(getWidth() - 2) + symbol;
	}
	private String getLine(int offset) {
		return getOffset(offset) + symbol.repeat(getWidth());
	}
	private String getOffset(int offset) {
		
		return " ".repeat(offset);
	}
	
}
