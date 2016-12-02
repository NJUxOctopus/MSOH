package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

import java.io.IOException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerMyHotelViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerMyHotelView.fxml";

    private CustomerSingleHotelViewController customerSingleHotelViewController;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane hotelListScrollPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    public void addHotelPane(){
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(hotelListScrollPane, "customer/CustomerSingleHotelView.fxml", 5, 10);

    }


}
