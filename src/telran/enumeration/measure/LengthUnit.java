package telran.enumeration.measure;

public enum LengthUnit {

	MM(1), CM(10), IN(25.4f), M(1000),KM(1_000_000);
	
	float value;
	
	LengthUnit(float value) {
		this.value = value;
	}
	
	public Length between(Length l1, Length l2) {
		if (this != l1.getUnit()) {
			l1=l1.convert(this);
		}
		if (this != l2.getUnit()) {
			l2=l2.convert(this);
		}		
		return new Length(Math.abs(l2.getAmount()-l1.getAmount()), this);
	}
	public float getValue() {
		return value;
	}
}
