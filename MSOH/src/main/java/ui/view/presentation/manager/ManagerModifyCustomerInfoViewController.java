package ui.view.presentation.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ui.controller.CustomerInfoChangeController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.CustomerInfoChange;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.MemberType;
import util.ResultMessage;
import vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/2.
 */
public class ManagerModifyCustomerInfoViewController implements ControlledStage {
    StageController stageController = new StageController();

    private String resource = "manager/ManagerModifyCustomerInfoView.fxml";

    private String customerID = "";

    private CustomerVO customerVO;

    @FXML
    private Button saveInfoButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox typeChoiceBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField creditTextField;

    @FXML
    private TextField memberTextField;

    @FXML
    private TextField idTextField;

    private String name = "";

    private String email = "";

    private String phone = "";

    private String ID = "";

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
     * 修改信息方法
     */
    @FXML
    private void modifyInfo(){
        if(isModified()){
            CustomerInfoChange customerInfoChange = new CustomerInfoChangeController();
            try {
                ResultMessage resultMessage = customerInfoChange.changeInfo(new CustomerVO(ID, name, email, phone));
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
                ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
                if(resultMessage == ResultMessage.Blank){
                    errorBoxController.setLabel("请填写完整的信息！");
                }
                if(resultMessage == ResultMessage.emailFormatWrong){
                    errorBoxController.setLabel("邮件格式错误！");
                }
                if(resultMessage == ResultMessage.phoneFormatWrong){
                    errorBoxController.setLabel("手机格式错误！");
                }
                if(resultMessage == ResultMessage.ChangeInfoSuccess){
                    errorBoxController.setLabel("成功修改客户信息！");
                    stageController.closeStage(resource);
                }
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }else{
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController)stageController.getController();
            errorBoxController.setLabel("未修改信息！");
        }
    }

    /**
     * 初始化修改客户信息界面
     * @param customerID
     */
    public void init(String customerID){
        this.customerID = customerID;
        UserAdmin userAdmin = new UserAdminController();
        try{
            customerVO = userAdmin.findCustomerByID(customerID);
            nameTextField.setText(customerVO.name);
            phoneTextField.setText(customerVO.phone);
            emailTextField.setText(customerVO.email);
            creditTextField.setText(customerVO.credit + "");
            idTextField.setText(customerVO.ID);
            if(customerVO.memberType == MemberType.ENTREPRISE) {
                memberTextField.setText("企业会员");
            }else if(customerVO.memberType == MemberType.NORMAL){
                memberTextField.setText("普通会员");
            }else if(customerVO.memberType == MemberType.NONMEMBER){
                memberTextField.setText("非会员");
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
        name = nameTextField.getText();
        email = emailTextField.getText();
        phone = phoneTextField.getText();
        ID = idTextField.getText();
        if (name.equals(customerVO.name) && phone.equals(customerVO.phone)
                && ID.equals(customerVO.ID) && email.equals(customerVO.email) ) {
            return false;
        } else {
            return true;
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
