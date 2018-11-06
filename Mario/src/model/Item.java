package model;

public class Item extends GameObject {
	private int points;
        private int initialPoints;

	public Item(World world, int x, int y, int points) {
		super(world, "Item", x, y);
		this.points = initialPoints = points;
	}
        
        @Override
        public void reset() {
            super.reset();
            points = initialPoints;
        }

	public boolean isAt(int x, int y) {
		return this.x == x && this.y == y;
	}

	public boolean exists() {
		return points > 0;
	}

	public void disappear() {
		points = 0;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return super.toString() + ": " + points + "p";
	}
}
