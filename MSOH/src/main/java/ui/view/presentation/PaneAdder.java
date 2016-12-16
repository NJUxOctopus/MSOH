package ui.view.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.customer.CustomerSingleCommentViewController;
import ui.view.presentation.customer.CustomerSingleHotelOrderViewController;
import ui.view.presentation.customer.CustomerSinglePromotionViewController;
import ui.view.presentation.customer.CustomerSingleRoomTypeViewController;
import ui.view.presentation.util.ControlledPane;
import ui.view.presentation.util.ControlledStage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by island on 2016/12/1.
 */
public class PaneAdder {
    private FXMLLoader loader;

    private static HashMap<String, FXMLLoader> loaders = new HashMap<String, FXMLLoader>();

    public void addPane(Pane sourcePane, String resource, int x, int y ){
        try {
            loader = new FXMLLoader(getClass().getResource(resource));
            loaders.put(resource, loader);
            Pane singlePane = (Pane) loader.load();
            sourcePane.getChildren().add(singlePane);
            singlePane.setLayoutX(x);
            singlePane.setLayoutY(y);
            //通过Loader获取FXML对应的ViewCtr，并将本StageController注入到ViewCtr中

            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPane(AnchorPane sourcePane, String resource, int x, int y ){
        try {
            loader = new FXMLLoader(getClass().getResource(resource));
            loaders.put(resource, loader);
            Pane singlePane = (Pane) loader.load();
            sourcePane.getChildren().add(singlePane);
            singlePane.setLayoutX(x);
            singlePane.setLayoutY(y);
            //通过Loader获取FXML对应的ViewCtr，并将本StageController注入到ViewCtr中

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ControlledStage getController(){
        return loader.getController();
    }

    public ControlledStage getController(String resource){
        return loaders.get(resource).getController();
    }
}
