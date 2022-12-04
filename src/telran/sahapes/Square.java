package telran.sahapes;

public class Square extends Rectangle{

		public Square(int width) {
			super(width, width);
		}
		
		@Override
		public void setHeight(int height) {
			super.setWidth(height);
		}
}
