package model;

public class Goomba extends GameObject {
	private int direction;
        private int initialDirection;

	public Goomba(World world, int x, int y, int direction) {
		super(world, "Goomba", x, y);
		this.direction = initialDirection = direction;
	}
        
        @Override
        public void reset() {
            super.reset();
            direction = initialDirection;
        }

	public void walk() {
		if (x + direction < world.getLeft() || x + direction > world.getRight())
			direction *= -1;
		x += direction;
	}

	public int getDirection() {
		return direction;
	}
}
