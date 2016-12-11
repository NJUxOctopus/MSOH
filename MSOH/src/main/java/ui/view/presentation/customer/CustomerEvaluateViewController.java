package ui.view.presentation.customer;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import ui.controller.CommentHotelController;
import ui.controller.ProcessOrderController;
import ui.view.controllerservice.CommentHotel;
import ui.view.controllerservice.ProcessOrder;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.CommentVO;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerEvaluateViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerEvaluateView.fxml";

    private OrderVO orderVO;

    private String customerID;

    private String orderID;

    private String hotelName;

    private String hotelID ;

    private String customerName;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox scoreChoiceBox;

    @FXML
    private TextArea textArea;

    @FXML
    private Button confirmButton;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label hotelIDLabel;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 关闭评论界面
     */
    @FXML
    private void closeStage() {
        stageController = new StageController();
        System.out.print(textArea.getText());
        if (textArea.getText().equals("")){
            stageController.closeStage(resource);
        }
        else {
            stageController.loadStage("util/ConfirmExit.fxml", 1);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
        }
    }

    /**
     * 添加评论方法
     */
    @FXML
    private void submitEvaluation(){
        String comment = textArea.getText();
        double score = scoreChoiceBox.getSelectionModel().selectedIndexProperty().intValue();
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        Timestamp commentTime = Timestamp.valueOf(time);
        CommentVO commentVO = new CommentVO(score, comment, customerName, customerID, hotelName, hotelID, orderID, commentTime);
        try {
            CommentHotel commentHotel = new CommentHotelController();
            ResultMessage resultMessage = commentHotel.addComment(commentVO, orderVO);
            if(resultMessage == ResultMessage.Hotel_addCommentSuccess){
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
                ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
                errorBoxController.setLabel("评价已提交！");
                CustomerOrderDetailViewController customerOrderDetailViewController = (CustomerOrderDetailViewController) stageController.getController("cutsomer/CustomerEvaluateView.fxml");
                customerOrderDetailViewController.hideButton();
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 评论界面的初始化方法
     * @param customerID
     * @param orderID
     */
    public void init(String customerID, String orderID){
        this.customerID = customerID;
        this.orderID = orderID;
        scoreChoiceBox.setItems(FXCollections.observableArrayList(
                "1","2", "3", "4", "5"));
        //setHotelInfo();

    }

    /**
     * 获得评论对应酒店的信息
     */
    private void setHotelInfo(){
        try {
            ProcessOrder processOrder = new ProcessOrderController();
            orderVO = processOrder.getSingle(customerID);
            hotelName = orderVO.hotelName;
            hotelID = orderVO.hotelID;
            customerName = orderVO.customerName;
            hotelIDLabel.setText(hotelID);
            hotelNameLabel.setText(hotelName);
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
