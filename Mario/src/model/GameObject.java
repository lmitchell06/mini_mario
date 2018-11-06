package model;

public abstract class GameObject {
	protected World world;
	protected String kind;
        protected int initialX;
        protected int initialY;
	protected int x;
	protected int y;

	public GameObject(World world, String kind, int x, int y) {
		this.world = world;
		this.kind = kind;
		this.x = initialX = x;
		this.y = initialY = y;
	}
        
        public void reset() {
            x = initialX;
            y = initialY;
        }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return kind + "(" + x + "," + y + ")";
	}
}
