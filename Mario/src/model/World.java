package model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Location Table
//
//                World    Mario    Item    Goomba
// walk             *        *                                               
// collect          *        *       *                                       
// move goombas     *        *                *                              
// show             *        *       *        *                              
//
public class World {
	private int left;
	private int right;
	private int top;
	private int bottom;
	private Mario mario;
	private LinkedList<Goomba> goombas = new LinkedList<Goomba>();
	private LinkedList<Item> items = new LinkedList<Item>();
	private ObservableList<Highscore> highscores = FXCollections.observableArrayList();
        private BooleanProperty over = new SimpleBooleanProperty();
        private StringProperty overMessage = new SimpleStringProperty();

	public World(int left, int right, int top, int bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
		mario = new Mario(this, 1, 1);
		goombas.add(new Goomba(this, 13, 1, 1));
		goombas.add(new Goomba(this, 17, 1, -1));
		items.add(new Item(this, 10, 1, 10));
		items.add(new Item(this, 20, 1, 15));
		items.add(new Item(this, 3, 3, 15));
                over.set(true);
	}
        
        public final String getOverMessage() { return overMessage.get(); }
        public ReadOnlyStringProperty overMessageProperty() { return overMessage; }
        
        public final boolean isOver() {
            return over.get();
        }
        
        public ReadOnlyBooleanProperty overProperty() { 
            return over;
        }
        
        public void startGame() {
            over.set(false);
            overMessage.set("");
            mario.reset();
            for (Item item : items)
                item.reset();
            for (Goomba goomba : goombas)
                goomba.reset();
        }
        
        public void endGame() {
            over.set(true);
            if (won()) {
                overMessage.set("You win!");
            }
            else {
                overMessage.set("Game Over!");
            }
        }

	public void addHighscore(Highscore highscore) {
		highscores.add(highscore);
	}

	public ObservableList<Highscore> getHighscores() {
		return highscores;
	}

	public LinkedList<Item> getItems() {
		return items;
	}
        
        public LinkedList<Goomba> getGoombas() {
            return goombas;
        }

	public final Mario getMario() {
		return mario;
	}
 
	public int getLeft() {
		return left;
	}
 
	public int getRight() {
		return right;
	}
 
	public int getTop() {
		return top;
	}
 
	public int getBottom() {
		return bottom;
	}

	/* public void goRound() { */
	/* 	char choice; */
	/* 	showWorld(); */
	/* 	while (!isGameOver() && (choice = readChoice()) != 'a') { */
	/* 		switch (choice) { */
	/* 		case 'l': move(-1); break; */
	/* 		case 'r': move(1); break; */
	/* 		case 'j': jump(); break; */
	/* 		default: help(); break; */
	/* 		} */
	/* 		collectItem(); */
	/* 		moveGoombas(); */
	/* 		showWorld(); */
	/* 	} */

	/* 	if (won()) */
	/* 		System.out.println("You won!!"); */
	/* 	else */
	/* 		System.out.println("You loooseee!"); */
	/* } */

	private boolean isGameOver() {
		return collectedAllItems() || !mario.isAlive();
	}

	private boolean won() {
		return collectedAllItems() && mario.isAlive();
	}

	public boolean collectedAllItems() {
		// every pattern
		for (Item item : items) {
			if (item.exists())
				return false;
		}
		return true;
	}

	/* private void collectItem() { */
	/* 	Item item = item(mario.getX(), mario.getY()); */
	/* 	if (item != null) */
	/* 		mario.collect(item); */
	/* } */

	public Item item(int x, int y) {
		// lookup pattern
		for (Item item : items)
			if (item.isAt(x, y))
				return item;
		return null;
	}

	public void animate() {
		moveGoombas();
	}

	private void moveGoombas() {
		for (Goomba goomba : goombas) {
			goomba.walk();
		}
	}

	@Override
	public String toString() {
		return mario + " " + items() + " " +  goombas();
	}

	private String items() {
		String s = "";
		for (Item item : items) {
			s += item;
			s += " ";
		}
		return s;
	}

	private String goombas() {
		String s = "";
		for (Goomba goomba : goombas)
			s += goomba + " ";
		return s;
	}
}
