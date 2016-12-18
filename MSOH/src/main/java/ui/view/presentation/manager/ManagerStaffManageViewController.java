package ui.view.presentation.manager;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import vo.ClerkVO;
import vo.CustomerVO;
import vo.MarketerVO;
import vo.UserVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by island on 2016/12/1.
 */
public class ManagerStaffManageViewController implements ControlledStage {
    StageController stageController = new StageController();

    ManagerSinglePersonViewController managerSinglePersonViewController;

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox staffChoiceBox;

    @FXML
    private ChoiceBox typeChoiceBox;

    @FXML
    private TextField textField;

    @FXML
    private Button addMarketerButton;

    @FXML
    private Button addCleckButton;

    @FXML
    private AnchorPane staffListScrollPane;

    @FXML
    private Label emptyLabel;

    private String inputType = "";

    private String input = "";

    private List<CustomerVO> customerVOList = new ArrayList<CustomerVO>();

    private List<ClerkVO> clerkVOList = new ArrayList<ClerkVO>();

    private List<MarketerVO> marketerVOList = new ArrayList<MarketerVO>();

    private List<UserVO>  userVOList = new ArrayList<UserVO>();


    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 显示添加网站营销人员界面
     */
    @FXML
    private void showAddMarketerView(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerMarketerInfoView.fxml", 1);
        ManagerMarketerInfoViewController managerMarketerInfoViewController = (ManagerMarketerInfoViewController) stageController.getController();
        managerMarketerInfoViewController.setAddVer();
    }

    /**
     * 显示添加酒店工作人员界面
     */
    @FXML
    private void showAddCleckButton(){
        stageController = new StageController();
        stageController.loadStage("manager/ManagerClerkInfoView.fxml", 1);
        ManagerClerkInfoViewController managerClerkInfoViewController = (ManagerClerkInfoViewController) stageController.getController();
        managerClerkInfoViewController.setAddVer("");
    }

    /**
     * 搜索按钮响应，根据类型和输入调用不同搜索方法
     */
    @FXML
    private void search(){
        String staffType = (String)staffChoiceBox.getSelectionModel().getSelectedItem();
        inputType = (String)typeChoiceBox.getSelectionModel().getSelectedItem();
        input = textField.getText();

        if (staffType == null) {
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            errorBoxController.setLabel("请先选择搜索条件！");
        }else {
            if (!input.equals("")) {
                if (staffType.equals("客户")) {
                    findCustomer();
                }
                if (staffType.equals("酒店工作人员")) {
                    findClerk();
                }
                if (staffType.equals("网站营销人员")) {
                    findMarketer();
                }

            }
        }
    }

    /**
     * 查找客户方法
     */
    private void findCustomer(){
        UserAdmin userAdmin = new UserAdminController();
        CustomerVO customerVO;
        try {
            if (inputType.equals("ID")){
                customerVOList.clear();
                customerVO = userAdmin.findCustomerByID(input);
                if(customerVO != null) {
                    customerVOList.add(customerVO);
                }
                    addPersonPane("客户");
            }
            if (inputType.equals("姓名")){
                customerVOList.clear();
                customerVOList = userAdmin.findCustomerByName(input);
                addPersonPane("客户");
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

    /**
     * 查找网站营销人员方法
     */
    private void findMarketer(){
        UserAdmin userAdmin = new UserAdminController();
        MarketerVO marketerVO;
        try {
            if (inputType.equals("ID")){
                marketerVOList.clear();
                marketerVO = userAdmin.findMarketerByID(input);
                if(marketerVO != null){
                    marketerVOList.add(marketerVO);
                }
            }
            if (inputType.equals("姓名")){
                marketerVOList.clear();
                marketerVOList = userAdmin.findMarketerByName(input);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
        addPersonPane("网站营销人员");
    }

    /**
     * 查找酒店工作人员方法
     */
    private void findClerk(){
        UserAdmin userAdmin = new UserAdminController();
        ClerkVO clerkVO;
        try {
            if (inputType.equals("ID")){
                clerkVOList.clear();
                clerkVO = userAdmin.findClerkByID(input);
                if(clerkVO != null)
                    clerkVOList.add(clerkVO);
            }
            if (inputType.equals("姓名")){
                clerkVOList.clear();
                clerkVOList = userAdmin.findClerkByName(input);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
        addPersonPane("酒店工作人员");
    }



    /**
     * 根据人员类型添加单个人员信息面板
     * @param type
     */
    private void addPersonPane(String type){
        boolean b = false;
        staffListScrollPane.getChildren().clear();
        if(type.equals("客户") && customerVOList != null) {
            b = true;
            emptyLabel.setOpacity(0);
            staffListScrollPane.setPrefHeight(150 * customerVOList.size());
            for(int i = 0; i < customerVOList.size(); i++) {
                PaneAdder paneAdder = new PaneAdder();
                paneAdder.addPane(staffListScrollPane, "manager/ManagerSinglePersonView.fxml", 10, 10 + 150*i);
                ManagerSinglePersonViewController managerSinglePersonViewController = (ManagerSinglePersonViewController) paneAdder.getController();
                managerSinglePersonViewController.init("客户", customerVOList.get(i).ID);
            }
        }
        if(type.equals("网站营销人员") && marketerVOList != null) {
            b = true;
            emptyLabel.setOpacity(0);
            staffListScrollPane.setPrefHeight(150 * marketerVOList.size());
            for(int i = 0; i < marketerVOList.size(); i++) {
                PaneAdder paneAdder = new PaneAdder();
                paneAdder.addPane(staffListScrollPane, "manager/ManagerSinglePersonView.fxml", 10, 10 + 150*i);
                ManagerSinglePersonViewController managerSinglePersonViewController = (ManagerSinglePersonViewController) paneAdder.getController();
                managerSinglePersonViewController.init("网站营销人员", marketerVOList.get(i).ID);
            }
        }
        if(type.equals("酒店工作人员") && clerkVOList != null) {
            b = true;
            emptyLabel.setOpacity(0);
            staffListScrollPane.setPrefHeight(150 * clerkVOList.size());
            for(int i = 0; i < clerkVOList.size(); i++) {
                PaneAdder paneAdder = new PaneAdder();
                paneAdder.addPane(staffListScrollPane, "manager/ManagerSinglePersonView.fxml", 10, 10 + 150*i);
                ManagerSinglePersonViewController managerSinglePersonViewController = (ManagerSinglePersonViewController) paneAdder.getController();
                managerSinglePersonViewController.init("酒店工作人员", clerkVOList.get(i).ID);
            }
        }

        if(!b)
            emptyLabel.setOpacity(1);
        else
            emptyLabel.setOpacity(0);
    }

    /**
     * 初始化选择框
     */
    public void init(){
        staffChoiceBox.setItems(FXCollections.observableArrayList(
                "客户", "酒店工作人员", "网站营销人员"));

        typeChoiceBox.setItems(FXCollections.observableArrayList(
                "ID", "姓名"));

        typeChoiceBox.setValue("ID");

    }

}
