package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import ui.controller.ProcessOrderController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.ProcessOrder;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.CustomerVO;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerOrderDetailViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerOrderDetailView.fxml";

    private String customerID;

    private String orderID;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Button cancelOrderButton;

    @FXML
    private Label customerIDLebel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label orderIDLabel;

    @FXML
    private Label orderStatusLabel;

    @FXML
    private Label estimatedCheckInTimeLabel;

    @FXML
    private Label estimatedCheckOutTimeLabel;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label hotelIdLabel;

    @FXML
    private Label roomTypeLabel;

    @FXML
    private Label numberOfPeopleLabel;

    @FXML
    private Label childLabel;

    @FXML
    private Label promotionLabel;

    @FXML
    private Label originalPriceLabel;

    @FXML
    private Label discountedPriceLabel;


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    @FXML
    private void cancelOrder(){

    }

    public void init(String customerID, String orderID){
        this.customerID = customerID;
        this.orderID = orderID;

        try {
            ProcessOrder processOrder = new ProcessOrderController();
            OrderVO orderVO = processOrder.getSingle(orderID);

            UserAdmin userAdmin = new UserAdminController();
            CustomerVO customerVO = userAdmin.findCustomerByID(customerID);



        } catch (RemoteException e) {
        e.printStackTrace();
    }
    }
}
