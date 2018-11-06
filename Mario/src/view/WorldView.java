package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.LinkedList;
import javafx.scene.layout.Pane;
import model.*;

public class WorldView extends Pane {
	private int scale = 100;
	private double spriteScale = 0.5;

	private World world;
	private Mario mario;
	private Image marioLeftImg;
	private Image marioRightImg;
        private Image goombaLeftImg;
        private Image goombaRightImg;
	private ImageView marioSprite;
	private LinkedList<ImageView> itemSprites = new LinkedList<ImageView>();
	private LinkedList<ImageView> goombaSprites = new LinkedList<ImageView>();

	public WorldView() {
	}

	public void setWorld(World world) {
		this.world = world;
		mario = world.getMario();

		setPrefSize(scale * world.getRight(), scale * world.getTop());
		setStyle("-fx-background-image: url(/view/background.png); -fx-background-position: center;");

		marioLeftImg = new Image("/view/mario_left.png");
		marioRightImg = new Image("/view/mario_right.png");
		marioSprite = new ImageView(marioRightImg);
		marioSprite.setScaleX(spriteScale);
		marioSprite.setScaleY(spriteScale);
		getChildren().add(marioSprite);
                
                goombaLeftImg = new Image("/view/goomba_left.png");
                goombaRightImg = new Image("/view/goomba_right.png");

		for (Item item : world.getItems()) {
			ImageView itemSprite = new ImageView("/view/coin.png");
			itemSprite.setScaleX(spriteScale);
			itemSprite.setScaleY(spriteScale);
			itemSprites.add(itemSprite);
                        getChildren().add(itemSprite);
		}
                
                for (Goomba goomba : world.getGoombas()) {
			ImageView goombaSprite = new ImageView();
			goombaSprite.setScaleX(spriteScale);
			goombaSprite.setScaleY(spriteScale);
			goombaSprites.add(goombaSprite);
                        getChildren().add(goombaSprite);
                }

		update();
	}


	public void update() {
		translate(mario, marioSprite);
		marioSprite.setImage(mario.getDirection() > 0 ? marioRightImg : marioLeftImg);

		LinkedList<Item> items = world.getItems();
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			ImageView itemSprite = itemSprites.get(i);
			translate(item, itemSprite);
                        itemSprite.setVisible(item.exists());
		}
		LinkedList<Goomba> goombas = world.getGoombas();
		for (int i = 0; i < goombas.size(); i++) {
			Goomba goomba = goombas.get(i);
			ImageView goombaSprite = goombaSprites.get(i);
			translate(goomba, goombaSprite);
                        goombaSprite.setImage(goomba.getDirection() == 1 ? goombaRightImg : goombaLeftImg);
		}
	}

	private void translate(GameObject gameObject, ImageView sprite) {
		sprite.setTranslateX(scale * gameObject.getX());
		sprite.setTranslateY(scale * (world.getTop() - gameObject.getY() - 1));
	}
}
