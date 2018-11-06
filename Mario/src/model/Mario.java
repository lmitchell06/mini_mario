package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import java.util.*;

public class Mario extends GameObject {
	private IntegerProperty lives = new SimpleIntegerProperty();
	private IntegerProperty score = new SimpleIntegerProperty();
	private int direction;

	public Mario(World world, int x, int y) {
		super(world, "Mario", x, y);
		lives.set(3);
		score.set(0);
		direction = 1;
	}
        
        @Override
        public void reset() {
            super.reset();
            lives.set(3);
            score.set(0);
            direction = 1;
        }

	public final int getScore() {
		return score.get();
	}

	public ReadOnlyIntegerProperty scoreProperty() {
		return score;
	}

	public final int getLives() {
		return lives.get();
	}

	public ReadOnlyIntegerProperty livesProperty() {
		return lives;
	}

	public int getDirection() {
		return direction;
	}

	public boolean isAlive() {
		return lives.get() > 0;
	}

	public void walk(int direction) {
		this.direction = direction;
		x += direction;
		if (y > world.getBottom())
			y--;
		if (x < world.getLeft() || x > world.getRight())
			die();

		collectItem();
	}

	private void collectItem() {
		Item item = world.item(x, y);
		if (item != null)
			collect(item);
                
                if (world.collectedAllItems())
                    world.endGame();
	}

	public void jump() {
		y += 2;
		collectItem();
	}

	public void die() {
		if (lives.get() > 0) {
			System.out.println("Splat!!");
			lives.set(lives.get() - 1);
                        if (!isAlive()) {
                            world.endGame();
                        }
                        else {
                            x = initialX;
                            y = initialY;
                            direction = 1;
                        }
			
		}
		else {
			System.out.println("You can't die, you're already dead!");
		}
	}

	public void collect(Item item) {
		score.set(score.get() + item.getPoints());
		item.disappear();
	}

	@Override
	public String toString() {
		return super.toString() + ":" + score.get() + "pts";
	}
}
