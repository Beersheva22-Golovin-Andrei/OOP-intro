package telran.sahapes.test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.sahapes.Canvas;
import telran.sahapes.Rectangle;
import telran.sahapes.Shape;
import telran.sahapes.Square;
import telran.sahapes.SquareLeftTriangle;
import telran.sahapes.SquareRightTriangle;

public class ShapeTests {

	@Test
	@Disabled
	public void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}
	
	
	private void displayStrings(String strings[]) {
		for(String str: strings) {
			System.out.println(str);
		}
	}
	
	@Test
	@Disabled
	public void treangleTest () {
		SquareLeftTriangle tr = new SquareLeftTriangle (11);
		SquareRightTriangle tr2 = new SquareRightTriangle(11);
		
		displayStrings(tr.presentation(20));
		displayStrings(tr2.presentation(20));
	}
	
	@Test
	public void canvasTest () {
		Canvas innerCanvas = new Canvas (5, 5, new Shape[] {new Rectangle(5,5), new Square (3)});
		Canvas canvas = new Canvas (5, 5, new Shape[] {new Rectangle(5,5), new Square (3), innerCanvas});
		canvas.setDirection(Canvas.COLUMN_DIRECTION);
		displayStrings(canvas.presentation(5));
	}

}
