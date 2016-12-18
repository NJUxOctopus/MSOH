package ui.view.presentation.marketer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import vo.CustomerVO;
import vo.OrderVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerChargeCreditController implements ControlledStage {

    private StageController stageController;

    @FXML
    private TextField searchTextField;
    @FXML
    private Pane customerPane;

    private String marketerID;
    private UserAdmin userAdmin;
    private PaneAdder paneAdder;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String ID) throws RemoteException {
        userAdmin = new UserAdminController();
        paneAdder = new PaneAdder();
        this.marketerID = ID;
        List<CustomerVO> customerVOList = userAdmin.findAllCustomer();
        if (!customerVOList.isEmpty())
            this.addCustomer(customerVOList);
    }

    /**
     * 搜索按钮结果，显示搜索到的客户
     */
    @FXML
    private void searchCustomer() throws RemoteException {
        String searchID = searchTextField.getText();
        customerPane.getChildren().clear();
        List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();

        if (searchID.length() == 18) {
            customerVOList.add(userAdmin.findCustomerByID(searchID));
            this.addCustomer(customerVOList);
        } else {
            //输入的信息格式错误
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            errorBoxController.setLabel("格式错误！");
        }
    }

    /**
     * 把singleCustomer加到Pane上的方法
     *
     * @param customerVOList
     */
    private void addCustomer(List<CustomerVO> customerVOList) throws RemoteException {
        customerPane.getChildren().clear();
        int numOfCustomer = customerVOList.size();
        customerPane.setPrefHeight(numOfCustomer * 115);

        for (int i = 0; i < numOfCustomer; i++) {
            CustomerVO customerVO = customerVOList.get(i);
            paneAdder.addPane(customerPane, "marketer/MarketerSingleCustomer.fxml", 0, i * 115);
            MarketerSingleCustomerController marketerSingleCustomerController = (MarketerSingleCustomerController) paneAdder.getController();
            marketerSingleCustomerController.initial(customerVO, marketerID);
        }
    }
}
