package ui.view.presentation.customer;

import businesslogic.member_bl.MemberLevel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.controller.EditMemberLevelController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.EditMemberLevel;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import util.MemberType;
import vo.CustomerVO;
import vo.MemberLevelVO;
import vo.MemberVO;

import java.rmi.RemoteException;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerMyMemberViewController implements ControlledStage{
    StageController stageController;

    private String resource = "customer/CustomerMyMemberView.fxml";

    private String customerID;

    private MemberVO memberVO;

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

    @FXML
    private AnchorPane memberLevlScrollPane;

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
        if(memberVO == null) {
            stageController = new StageController();
            stageController.loadStage("customer/CustomerSignUpSelectView.fxml", 0.5);
            CustomerSignUpSelectViewController customerSignUpSelectViewController = (CustomerSignUpSelectViewController) stageController.getController();
            customerSignUpSelectViewController.init(customerID);
        }else{
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            errorBoxController.setLabel("您已成为会员！" + '\n' + "无法再次注册！");
        }
    }

    /**
     * 我的会员界面初始化方法
     * param customerID
     */
    public void init(String customerID){
        this.customerID = customerID;
        setCustomerInfo();
        setMemberLevelInfo();
    }

    /**
     * 设置客户信息
     */
    private void setCustomerInfo(){
        UserAdmin userAdmin = new UserAdminController();
        try {
            //信用值信息
            int credit = userAdmin.findCustomerByID(customerID).credit;
            MemberType memberType = userAdmin.findCustomerByID(customerID).memberType;
            creditLabel.setText(credit + "");
            double locate =  credit / 150;
            creditPane.setLayoutX(locate);
            if(memberType != MemberType.NONMEMBER){
                memberVO = userAdmin.findMemberByID(customerID);
                if(memberVO.memberType == MemberType.ENTREPRISE) {
                    typeOfMemberLabel.setText("企业会员");
                }else if(memberVO.memberType == MemberType.NORMAL){
                    typeOfMemberLabel.setText("普通会员");
                }
                //会员信息
                gradeOfMemberLabel.setText(memberVO.level + "");
                EditMemberLevel editMemberLevel = new EditMemberLevelController();
                String discount = editMemberLevel.getMemberLevel().discountList.get(memberVO.level - 1);
                discountOfMemberLabel.setText("部分酒店"  + discount + "折");

            }else{
                typeOfMemberLabel.setText("非会员");
                gradeOfMemberLabel.setText("无");
                discountOfMemberLabel.setText("非会员无法享受");
            }



        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置会员等级信息
     */
    private void setMemberLevelInfo(){
        EditMemberLevel editMemberLevel = new EditMemberLevelController();
        try {
            MemberLevelVO memberLevelVO = editMemberLevel.getMemberLevel();
            int grade = memberLevelVO.num;
            int[] bounds = memberLevelVO.creditBoundaries;
            memberLevlScrollPane.setPrefWidth(60 * grade);
            for(int i = 0; i < grade; i++ ){
                PaneAdder paneAdder = new PaneAdder();
                paneAdder.addPane(memberLevlScrollPane, "customer/CustomerSingleLevel.fxml",  60 * i, 10);
                CustomerSingleLevelController customerSingleLevelController = (CustomerSingleLevelController) paneAdder.getController();
                customerSingleLevelController.init(i + 1, bounds[i]);
            }
        }catch (RemoteException e){
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
