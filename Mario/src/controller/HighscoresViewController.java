package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import model.*;

public class HighscoresViewController extends Controller<World> {
        @FXML private TableColumn<Highscore, String> scoreClm;

	public final World getWorld() {
		return model;
	}
        
        @FXML public void initialize() {
                scoreClm.setCellValueFactory(
                        cellData -> 
                            Bindings.concat(cellData.getValue().getScore(), " pts")
                        
                );
                
        }
}
