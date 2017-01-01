package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import ui.controller.ClerkInfoChangeController;
import ui.controller.HotelAdminController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.ClerkInfoChange;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.ImageController;
import util.ResultMessage;
import vo.ClerkVO;
import vo.HotelVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerClerkInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resource = "manager/ManagerClerkInfoView.fxml";

    private String clerkID = "";

    private ClerkVO clerkVO;

    private String hotelID = "";

    @FXML
    private Button addButton;

    @FXML
    private Button modifyIconButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField cleckNameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField cleckIDTextField;

    @FXML
    private TextField hotelIDTextField;

    @FXML
    private ImageView iconLabel;

    @FXML
    private Pane addCleckPane;

    @FXML
    private Pane modifyCleckPane;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label passwordLabel;

    private String name = "";

    private String phone = "";

    private String ID = "";

    private String password = "";

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 关闭当前界面
     */
    @FXML
    private void closeStage() {
        stageController = new StageController();
        if(isModified()) {
            stageController.loadStage("util/ConfirmExit.fxml", 0.75);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
        }else{
            stageController.closeStage(resource);
        }
    }

    /**
     * 确认／修改按钮响应
     */
    @FXML
    private void confirmInfo(){
        if(addButton.getText().equals("添加")) {
            isModified();
            addClerk();
        }
        if(addButton.getText().equals("修改")) {
            if(isModified()){
                modifyClerk();
            }else{
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
                ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
                errorBoxController.setLabel("未修改信息！");
            }
        }
    }

    /**
     * 添加酒店工作人员
     */
    private void addClerk(){
        UserAdmin userAdmin = new UserAdminController();
        try{
            name = cleckNameTextField.getText();
            phone = phoneTextField.getText();
            password = passwordTextField.getText();
            ID = cleckIDTextField.getText();
            hotelID = hotelIDTextField.getText();
            HotelAdmin hotelAdmin = new HotelAdminController();
            HotelVO hotelVO = hotelAdmin.findByID(hotelID);

            ResultMessage resultMessage = userAdmin.addClerk(new ClerkVO(name, phone, password, ID, hotelID, hotelVO.hotelName));
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
            if(resultMessage == ResultMessage.Blank){
                errorBoxController.setLabel("请填写完整的信息！");
            }
            if(resultMessage == ResultMessage.Clerk_AddClerkExist){
                errorBoxController.setLabel("该酒店工作人员已存在！");
            }
            if(resultMessage == ResultMessage.DataFormatWrong){
                errorBoxController.setLabel("信息格式错误！");
            }
//            if(resultMessage == ResultMessage.Hotel_HasClerk){
//                errorBoxController.setLabel("该酒店已存在工作人员！");
//            }
            if(resultMessage == ResultMessage.Clerk_AddClerkSuccess){
                errorBoxController.setLabel("成功添加酒店工作人员！");
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 修改酒店工作人员信息
     */
    private void modifyClerk(){
        ClerkInfoChange clerkInfoChange = new ClerkInfoChangeController();
        try{
            ResultMessage resultMessage = clerkInfoChange.changeInfo(new ClerkVO(name, phone, password, ID));
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
            if(resultMessage == ResultMessage.Blank){
                errorBoxController.setLabel("请填写完整的信息！");
            }
            if(resultMessage == ResultMessage.DataFormatWrong){
                errorBoxController.setLabel("信息格式错误！");
            }
            if(resultMessage == ResultMessage.ChangeInfoSuccess){
                errorBoxController.setLabel("成功修改酒店工作人员信息！");
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 初始化界面为修改信息界面
     * @param cleckID
     */
    public void setModifyVer(String cleckID){
        this.clerkID = cleckID;
        UserAdmin userAdmin = new UserAdminController();
        try {
            clerkVO = userAdmin.findClerkByID(cleckID);
        }catch (RemoteException e){

        }
        cleckNameTextField.setText(clerkVO.name);
        phoneTextField.setText(clerkVO.phone);
        cleckIDTextField.setText(clerkVO.ID);
        cleckIDTextField.setEditable(false);
        hotelIDTextField.setText(clerkVO.hotelID);
        if(!clerkVO.picUrl.equals("")) {
            ImageController imageController = new ImageController();
            WritableImage wr = imageController.loadImage(clerkVO.picUrl, 145, 145);
            iconLabel.setImage(wr);
        }
        passwordLabel.setOpacity(0);
        passwordTextField.setOpacity(0);
        addButton.setText("修改");
        addCleckPane.setOpacity(0);
        modifyCleckPane.setOpacity(1);
        this.hotelID = clerkVO.hotelID;
    }


    /**
     * 初始化界面为添加工作人员界面
     * @param hotelID
     */
    public void setAddVer(String hotelID){
        addButton.setText("添加");
        addCleckPane.setOpacity(1);
        modifyCleckPane.setOpacity(0);
        this.hotelID = hotelID;

        if (hotelID.equals("")) {
            hotelIDTextField.setEditable(true);
        } else {
            hotelIDTextField.setEditable(false);
        }

        cleckNameTextField.setText("");
        phoneTextField.setText("");
        cleckIDTextField.setText("");
        hotelIDTextField.setText(hotelID);
    }

    /**
     * 判断当前界面是否有信息被修改
     * @return
     */
    private boolean isModified(){
        name = cleckNameTextField.getText();
        phone = phoneTextField.getText();
        ID = cleckIDTextField.getText();
        if(!clerkID.equals("")) {
            if (name.equals(clerkVO.name) && phone.equals(clerkVO.phone)
                    && ID.equals(clerkVO.ID) ) {
                return false;
            } else {
                return true;
            }
        }else{
            if (!name.equals("") || !phone.equals("")
                    || !ID.equals("") ) {
                return true;
            } else {
                return false;
            }
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
