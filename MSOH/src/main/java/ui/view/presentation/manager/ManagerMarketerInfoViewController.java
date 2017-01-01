package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import ui.controller.ManagerInfoChangeController;
import ui.controller.MarketerInfoChangeController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.ManagerInfoChange;
import ui.view.controllerservice.MarketerInfoChange;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.ImageController;
import util.ResultMessage;
import vo.MarketerVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerMarketerInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resource = "manager/ManagerMarketerInfoView.fxml";

    private String marketerID = "";

    private MarketerVO marketerVO;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField marketerNameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField marketerIDTextField;

    @FXML
    private Pane modifyMarketerPane;

    @FXML
    private Pane addMarketerPane;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ImageView iconLabel;

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
     * 添加／修改按钮响应
     */
    @FXML
    private void confirmInfo(){
        if(addButton.getText().equals("添加")) {
            isModified();
            addMarketer();
        }
        if(addButton.getText().equals("修改")) {
            if(isModified()){
                modfiyMarketer();
            }else{
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
                ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
                errorBoxController.setLabel("未修改信息！");
            }
        }
    }

    /**
     * 添加网站营销人员方法
     */
    private void addMarketer(){
        UserAdmin userAdmin = new UserAdminController();
        try{
            ResultMessage resultMessage = userAdmin.addMarketer(new MarketerVO(name, phone, password, ID, ""));
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
            if(resultMessage == ResultMessage.Blank){
                errorBoxController.setLabel("请填写完整的信息！");
            }
            if(resultMessage == ResultMessage.Marketer_MarketerAlreadyExist){
                errorBoxController.setLabel("该营销人员已存在！");
            }
            if(resultMessage == ResultMessage.DataFormatWrong){
                errorBoxController.setLabel("信息格式错误！");
            }
            if(resultMessage == ResultMessage.Marketer_AddMarketerSuccess){
                errorBoxController.setLabel("成功添加网站营销人员！");
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 修改网络营销人员信息方法
     */
    private void modfiyMarketer(){
        MarketerInfoChange marketerInfoChange = new MarketerInfoChangeController();
        try{
            ResultMessage resultMessage = marketerInfoChange.changeInfo(new MarketerVO(name, phone, "", ID, ""));
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
            if(resultMessage == ResultMessage.Blank){
                errorBoxController.setLabel("请填写完整的信息！");
            }
            if(resultMessage == ResultMessage.DataFormatWrong){
                errorBoxController.setLabel("信息格式错误！");
            }
            if(resultMessage == ResultMessage.Marketer_ChangeInfoSuccess){
                errorBoxController.setLabel("成功修改网站营销人员信息！");
                stageController.closeStage(resource);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 设置界面为修改网络营销人员信息界面
     */
    public void setModifyVer(String marketerID){
        this.marketerID = marketerID;
        addButton.setText("修改");
        addMarketerPane.setOpacity(0);
        modifyMarketerPane.setOpacity(1);
        marketerIDTextField.setEditable(false);
        passwordTextField.setEditable(false);
        passwordLabel.setOpacity(0);
        passwordTextField.setOpacity(0);
        setMarkterInfo();
    }

    /**
     * 设置界面为添加网络营销人员界面
     */
    public void setAddVer(){
        addButton.setText("添加");
        addMarketerPane.setOpacity(1);
        modifyMarketerPane.setOpacity(0);
        marketerNameTextField.setText(name);
        phoneTextField.setText(phone);
        marketerIDTextField.setText(marketerID);
        passwordTextField.setText(password);
    }

    /**
     * 设置网络营销人员原信息
     */
    private void setMarkterInfo(){
        UserAdmin userAdmin = new UserAdminController();
        try {
            marketerVO = userAdmin.findMarketerByID(marketerID);
            name = marketerVO.name;
            phone = marketerVO.phone;
            ID = marketerVO.ID;
            password = marketerVO.password;
            marketerNameTextField.setText(name);
            phoneTextField.setText(phone);
            marketerIDTextField.setText(marketerVO.ID);
            if(!marketerVO.picUrl.equals("")) {
                ImageController imageController = new ImageController();
                WritableImage wr = imageController.loadImage(marketerVO.picUrl, 200, 200);
                iconLabel.setImage(wr);
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    /**
     * 判断当前界面是否有信息被修改
     * @return
     */
    private boolean isModified(){
        name = marketerNameTextField.getText();
        phone = phoneTextField.getText();
        ID = marketerIDTextField.getText();
        password = passwordTextField.getText();
        if(!marketerID.equals("")) {
            if (name.equals(marketerVO.name) && phone.equals(marketerVO.phone)
                    && ID.equals(marketerVO.ID) ) {
                return false;
            } else {
                return true;
            }
        }else{
            if (!name.equals("") || !phone.equals("")
                    || !ID.equals("") || !password.equals("")) {
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
