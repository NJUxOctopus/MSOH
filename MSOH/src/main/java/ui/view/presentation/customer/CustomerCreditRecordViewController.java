package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import po.CreditRecordPO;
import ui.controller.CreditRecordController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.CreditRecord;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.CreditRecordVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by island on 2016/11/23.
 */
public class CustomerCreditRecordViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerCreditRecordView.fxml";

    private String customerID = "";

    CustomerSingleCreditRecordViewController customerSingleCreditRecordViewController;

    @FXML
    private ImageView background;

    @FXML
    private Label creditLabel;

    @FXML
    private AnchorPane creditListScrollPane;

    @FXML
    private Label emptyRecordLabel;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    /**
     * 信用记录面板添加单个信用记录
     * @param creditRecordVOList
     */
    private void addCreditPane(List<CreditRecordVO> creditRecordVOList){
        PaneAdder paneAdder = new PaneAdder();
        if(!creditRecordVOList.isEmpty()) {
            int num = creditRecordVOList.size();
            creditListScrollPane.setPrefHeight(180 * num);
            for (int i = 0; i < num; i++) {
                paneAdder.addPane(creditListScrollPane, "customer/CustomerSingleCreditRecordView.fxml", 3, 180 * num - 170);
                customerSingleCreditRecordViewController = (CustomerSingleCreditRecordViewController) paneAdder.getController();
                customerSingleCreditRecordViewController.init(creditRecordVOList.get(num));
            }
        }
        else{
            emptyRecordLabel.setOpacity(1);
        }
    }

    /**
     * 信用记录界面初始化方法
     * @param customerID
     */
    public void init(String customerID){
        this.customerID = customerID;

        try {
            UserAdmin userAdmin = new UserAdminController();
            creditLabel.setText(userAdmin.findCustomerByID(customerID).credit + "");
        }catch (RemoteException e){
            e.printStackTrace();
        }
        getAllCreditRecord(customerID);
    }

    /**
     * 获得所有的信用记录并添加信用记录面板
     * @param customerID
     */
    private void getAllCreditRecord(String customerID){
        CreditRecord creditRecord = new CreditRecordController();
        try {
            List<CreditRecordVO> creditRecordVOList = creditRecord.getCreditRecord(customerID);
            addCreditPane(creditRecordVOList);
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
