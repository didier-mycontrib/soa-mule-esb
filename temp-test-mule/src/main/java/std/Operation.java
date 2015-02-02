package std;

public class Operation {
	private double x;
	private double y;
	private String op; //"add" or "mult" or ...
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	@Override
	public String toString() {
		return "Operation [x=" + x + ", y=" + y + ", op=" + op + "]";
	}
	
	
	
	

}
