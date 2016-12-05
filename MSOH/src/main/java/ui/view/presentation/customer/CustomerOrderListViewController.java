package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import vo.HotelVO;

import java.io.IOException;

/**
 * Created by island on 2016/11/23.
 */
public class CustomerOrderListViewController implements ControlledStage {
    StageController stageController;

    CustomerSingleOrderPaneViewController  customerSingleOrderPaneViewController;

    private String resource = "customer/CustomerOrderListView.fxml";

    @FXML
    private Pane orderListPane;

    @FXML
    private AnchorPane orderListScrollPane;

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
        stageController.closeStage(resource);
    }

    @FXML
    private void showAllOrder(){
        orderButtonShade.setY(0);
        orderListScrollPane.getChildren().clear();
        addOrderPane();
    }

    @FXML
    private void showNormalOrder(){
        orderButtonShade.setY(100);
        orderListScrollPane.getChildren().clear();


    }

    @FXML
    private void showAbnormalOrder(){
        orderButtonShade.setY(200);
        orderListScrollPane.getChildren().clear();

    }

    @FXML
    private void showCanceledOrder(){
        orderButtonShade.setY(300);
        orderListScrollPane.getChildren().clear();

    }

    @FXML
    private void showEvaluateOrder(){
        orderButtonShade.setY(400);
        orderListScrollPane.getChildren().clear();

    }

    public void addOrderPane(){
        PaneAdder paneAdder = new PaneAdder();
        paneAdder.addPane(orderListScrollPane, "customer/CustomerSingleOrderPaneView.fxml", 5, 5);
        customerSingleOrderPaneViewController = (CustomerSingleOrderPaneViewController) paneAdder.getController();
        customerSingleOrderPaneViewController.init();

    }

    public void init(){

    }


}
