package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import ui.controller.MemberRegisterController;
import ui.view.controllerservice.MemberRegister;
import ui.view.presentation.util.ConfirmExitController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.MemberType;
import util.ResultMessage;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerSignUpEnterpriseMemberViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerSignUpEnterpriseMemberView.fxml";

    private String customerID;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox companyChoiceBox;

    @FXML
    private Button confirmButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 关闭企业会员注册页面
     */
    @FXML
    private void closeStage() {
        stageController = new StageController();

        if(companyChoiceBox.getSelectionModel().getSelectedItem().equals("")) {
            stageController.loadStage("util/ConfirmExit.fxml", 0.75);
            ConfirmExitController controller = (ConfirmExitController) stageController.getController();
            controller.setToBeClosed(resource);
        }
        else{
            stageController.closeStage(resource);
        }
    }

    /**
     * 注册企业会员
     */
    @FXML
    private void signUpCorporate(){
        String companyName = (String)companyChoiceBox.getSelectionModel().getSelectedItem();
        MemberVO memberVO = new MemberVO(customerID, MemberType.ENTREPRISE, 0, companyName);
        try{
            MemberRegister memberRegister = new MemberRegisterController();
            ResultMessage resultMessage = memberRegister.signUp(memberVO);
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            if(resultMessage == ResultMessage.Blank){
                errorBoxController.setLabel("未填写完整信息！");
            }else if(resultMessage == ResultMessage.Member_AddMemberAlreadyExist){
                errorBoxController.setLabel("您已成为会员，无法再次注册！");
            }else if(resultMessage == ResultMessage.Member_EnterpriseSignupSuccess){
                errorBoxController.setLabel("成功注册成为企业会员！");
                stageController = new StageController();
                stageController.closeStage(resource);
            }
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }

    public void init(String customerID){
        this.customerID = customerID;
        //// TODO: 2016/12/11 获得所有酒店 
    }

}
