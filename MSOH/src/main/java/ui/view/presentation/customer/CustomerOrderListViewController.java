package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/23.
 */
public class CustomerOrderListViewController implements ControlledStage {
    StageController stageController;

    private String resources = "customer/CustomerOrderListView.fxml";

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private ImageView orderButtonShade;

    @FXML
    private Button allOrderButton;

    @FXML
    private Button normalOrderButton;

    @FXML
    private Button abnormalOrderButton;

    @FXML
    private Button canceledOrderButton;

    @FXML
    private Button evaluateButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button lastPageButton;

    @FXML
    private ChoiceBox selectPageBox;

    private Stage stage;

    private CustomerMainViewController customerMainViewController;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resources);
    }

    @FXML
    private void showAllOrder(){
        orderButtonShade.setY(0);
    }

    @FXML
    private void showNormalOrder(){
        orderButtonShade.setY(100);

    }

    @FXML
    private void showAbnormalOrder(){
        orderButtonShade.setY(200);

    }

    @FXML
    private void showCanceledOrder(){
        orderButtonShade.setY(300);

    }

    @FXML
    private void showEvaluateOrder(){
        orderButtonShade.setY(400);

    }

    @FXML
    private void nextPage(){

    }

    @FXML
    private void lastPage(){

    }

    @FXML
    private void selectPage(){

    }


}
