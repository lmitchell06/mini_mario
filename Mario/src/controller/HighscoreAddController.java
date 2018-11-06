package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import model.*;

public class HighscoreAddController extends Controller<World> {
	@FXML private TextField nameTf;

	public final World getWorld() {
		return model;
	}

	private String getName() {
		return nameTf.getText();
	}

	@FXML public void handleAdd(ActionEvent event) {
		Highscore highscore = new Highscore(getName(), getWorld().getMario().getScore());
		getWorld().addHighscore(highscore);
		stage.close();
	}
}
