package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;

/**
 * Created by island on 2016/11/26.
 */
public class CustomerSingleOrderPaneViewController implements ControlledStage {
    StageController stageController = new StageController();

    @FXML
    private Pane singleOrderPane;

    @FXML
    private Button processOrderButton;

    @FXML
    private Button orderDetailButton;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label peopleLabel;

    @FXML
    private Label checkOutTimeLabel;

    @FXML
    private Label checkInTimeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label orderStatusLabel;

    @FXML
    private Button hotelButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void processOrder(){
        if(processOrderButton.getText().equals("撤销订单"))
            cancelOrder();
        if(processOrderButton.getText().equals("评价酒店"))
            showEvaluateView();

    }

    private void cancelOrder(){

    }

    private void showEvaluateView(){
        System.out.print("!");
        stageController = new StageController();
        stageController.loadStage("customer/CustomerEvaluateView.fxml", 1);
        System.out.print("?");
    }

    @FXML
    private void viewDetails(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerOrderDetailView.fxml", 1);
    }

    public void init(){
        orderIDLabel.setText("222222");
        peopleLabel.setText("");
        checkOutTimeLabel.setText("");
        checkInTimeLabel.setText("");
        priceLabel.setText("");
        roomTypeLabel.setText("");
        orderStatusLabel.setText("");
        hotelButton.setText("");
        processOrderButton.setText("评价酒店");

    }


}
