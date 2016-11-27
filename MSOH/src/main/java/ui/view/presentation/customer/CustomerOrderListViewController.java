package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.view.presentation.StageController;
import ui.view.presentation.ControlledStage;

/**
 * Created by island on 2016/11/23.
 */
public class CustomerOrderListViewController implements ControlledStage {
    StageController stageController;

    CustomerSingleOrderPaneViewController  customerSingleOrderPaneViewController;
    private String resources = "customer/CustomerOrderListView.fxml";

    @FXML
    private Pane orderListPane;

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

    public void addOrderPanel(){
        Button b = new Button();
        orderListPane.getChildren().add(b);
    }


}
