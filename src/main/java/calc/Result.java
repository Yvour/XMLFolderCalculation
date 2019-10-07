package calc;

public class Result {

	private String id;
	private double value;

	public Result(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getStringValue() {

		if (this.value == (long) this.value)
			return String.format("%d", (long) this.value);
		else
			return String.format("%s", this.value);

	}

}
