package telran.sahapes;

public class Canvas extends Shape {

	public static final String RAW_DIRECTION = "raw";
	
	public static final String COLUMN_DIRECTION = "column";
	
	private Shape[] shapes;

	private String direction = RAW_DIRECTION;
	
	private int mergin = 5;

	public Canvas(int width, int height, Shape[] shapes) {
		super(width, height);
		this.shapes = shapes;
	}

	@Override
	public String[] presentation(int offset) {
		String [] res;
		if (direction.equals(RAW_DIRECTION)) {
			int canvasHeight = this.getHeight();			
			StringBuilder[] resBuilder = new StringBuilder[canvasHeight];
			Shape firstShape = shapes[0];
			
			firstShape.setHeight(canvasHeight);
			String[] firstShapeArr = firstShape.presentation(0);		
			for (int i = 0; i < resBuilder.length; i++) {
				resBuilder[i] = new StringBuilder(firstShapeArr[i]);
			}

			for (int j = 1; j < shapes.length; j++) {
				Shape shape = shapes[j];
				shape.setHeight(canvasHeight);
				String[] shapeArr = shape.presentation(mergin);
				for (int i = 0; i < resBuilder.length; i++) {
					resBuilder[i] = resBuilder[i].append(shapeArr[i]);
				}
			}
			res = convertBuilderArrToStringArr(resBuilder);
		} else {
			int columnHeight=0;
			for (Shape s: shapes) {
				columnHeight+=s.getHeight();
			}
			columnHeight = columnHeight +mergin*(shapes.length-1);
			res = new String[columnHeight];
			int commonCounter=0;
			int canvasWidt = this.getWidth();	
			for (int i = 0; i<shapes.length&&commonCounter<res.length ; i++) {
				Shape shape = shapes[i];
				shape.setWidth(canvasWidt);
				String [] shapeArr = shape.presentation(offset);
				for (int j = 0; j<shapeArr.length; j++) {
					res[commonCounter]=shapeArr[j];
					commonCounter++;
				}
				if (i <shapes.length-1) {
					for (int f =0; f<mergin; f++) {
						res[commonCounter] ="";
						commonCounter++;
					}
				}
			}
		}
		return res;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setMergin(int mergin) {
		this.mergin = mergin;
	}

	public String[] convertBuilderArrToStringArr (StringBuilder[] str) {
		String[]res = new String[str.length];
		for (int i=0; i<str.length; i++) {
			res[i] = str[i].toString();		
		}
		return res;
	}
}
