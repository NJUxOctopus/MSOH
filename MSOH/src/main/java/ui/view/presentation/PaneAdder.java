package ui.view.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.customer.CustomerSingleCommentViewController;
import ui.view.presentation.customer.CustomerSingleHotelOrderViewController;
import ui.view.presentation.customer.CustomerSinglePromotionViewController;
import ui.view.presentation.customer.CustomerSingleRoomTypeViewController;
import ui.view.presentation.util.ControlledStage;

import java.io.IOException;

/**
 * Created by island on 2016/12/1.
 */
public class PaneAdder {
    FXMLLoader loader;
    public void addPane(Pane sourcePane, String resource, int x, int y ){
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(resource));
            Pane singlePane = (Pane) loader.load();
            sourcePane.getChildren().add(singlePane);
            singlePane.setLayoutX(x);
            singlePane.setLayoutY(y);

            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPane(AnchorPane sourcePane, String resource, int x, int y ){
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(resource));
            Pane singlePane = (Pane) loader.load();
            sourcePane.getChildren().add(singlePane);
            singlePane.setLayoutX(x);
            singlePane.setLayoutY(y);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ControlledStage getController(){
        return loader.getController();
    }
}
