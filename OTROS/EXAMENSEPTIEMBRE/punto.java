import java.io.Serializable;

public class punto implements Serializable {
	private float x;
	private float y;
	
	public punto(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	
	
}
