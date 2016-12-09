package ui.view.presentation.customer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.controller.UserAdminController;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import util.MemberType;
import vo.CustomerVO;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerMyMemberViewController implements ControlledStage{
    StageController stageController;

    private String resource = "customer/CustomerMyMemberView.fxml";

    private String customerID;

    @FXML
    private ImageView background;

    @FXML
    private Button backButton;

    @FXML
    private Label typeOfMemberLabel;

    @FXML
    private Label gradeOfMemberLabel;

    @FXML
    private Label discountOfMemberLabel;

    @FXML
    private Button signUpButton;

    @FXML
    private Label creditLabel;

    @FXML
    private Pane creditPane;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController.closeStage(resource);
    }

    @FXML
    private void showSignUpSelectView(){
        stageController = new StageController();
        stageController.loadStage("customer/CustomerSignUpSelectView.fxml", 0.5);
        CustomerSignUpSelectViewController customerSignUpSelectViewController = (CustomerSignUpSelectViewController) stageController.getController();
        customerSignUpSelectViewController.init(customerID);
    }

    public void init(String customerID){
        this.customerID = customerID;
        setCustomerInfo();
    }

    private void setCustomerInfo(){
        UserAdmin userAdmin = new UserAdminController();
        try {
            //信用值信息
            int credit = userAdmin.findCustomerByID(customerID).credit;
            creditLabel.setText(credit + "");
            double locate = 957 / 10000 * credit;
            creditPane.setLayoutX(locate);
            CustomerVO customerVO = userAdmin.findCustomerByID(customerID);
            if(customerVO.memberType == MemberType.ENTREPRISE) {
                typeOfMemberLabel.setText("企业会员");
            }else if(customerVO.memberType == MemberType.NORMAL){
                typeOfMemberLabel.setText("普通会员");
            }else if(customerVO.memberType == MemberType.NONMEMBER){
                typeOfMemberLabel.setText("非会员");
            }
            //会员信息
            if(customerVO.memberType != MemberType.NONMEMBER) {
                MemberVO memberVO = userAdmin.findMemberByID(customerID);
                gradeOfMemberLabel.setText(memberVO.level + "");
                //// TODO: 2016/12/9  获得该等级会员折扣
                discountOfMemberLabel.setText("部分酒店" );
            }
            else{
                gradeOfMemberLabel.setText("无");
                gradeOfMemberLabel.setText("非会员无法享受");
            }

        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
