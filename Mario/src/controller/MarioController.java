package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import model.*;
import javafx.stage.Stage;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import au.edu.uts.ap.javafx.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import view.*;

public class MarioController extends Controller<World> {
	@FXML private WorldView worldView;
        @FXML private Button leftBtn;

	@FXML public void initialize() {
//            income.bind(sold.multiply(price));
//            income.bind(Bindings.multiply(sold, price));
//            left.bind(capacity.subtract(sold));
//            left.bind(Bindings.subtract(capacity, sold));
		worldView.setWorld(getWorld());

//                getWorld().overProperty().addListener(
//                        (o, before, after) -> {
//                            leftBtn.setDisable(getWorld().isOver());
//                        }
//                );
                getWorld().overProperty().addListener(
                        (o, before, after) -> {
                            if (after) {
                                try {
                                    ViewLoader.showStage(getWorld(), "/view/highscore_add.fxml", "Add highscore", new Stage());
                                } catch (IOException ex) {
                                    Logger.getLogger(MarioController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        });
	}

	public final World getWorld() {
		return model;
	}

	public final Mario getMario() {
		return getWorld().getMario();
	}

	@FXML public void handleLeft(ActionEvent event) {
		move(-1);
	}

	@FXML public void handleRight(ActionEvent event) {
		move(1);
	}

	private void move(int direction) {
		getMario().walk(direction);
		getWorld().animate();
		worldView.update(); // This is not MVC!
	}

	@FXML public void handleJump(ActionEvent event) {
		getMario().jump();
		getWorld().animate();
		worldView.update(); // This is not MVC!
	}

	@FXML public void handleViewHighscores(ActionEvent event) throws IOException {
		ViewLoader.showStage(getWorld(), "/view/highscores_view.fxml", "View highscores", new Stage());
	}
        @FXML public void handleStart(ActionEvent event) {
            getWorld().startGame();
            worldView.update(); // This is not MVC!
        }
}
