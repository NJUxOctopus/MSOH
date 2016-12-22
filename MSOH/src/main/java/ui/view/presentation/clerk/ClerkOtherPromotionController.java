package ui.view.presentation.clerk;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ui.controller.EditPromotionController;
import ui.controller.HotelAdminController;
import ui.controller.UserAdminController;
import ui.view.controllerservice.EditPromotion;
import ui.view.controllerservice.HotelAdmin;
import ui.view.controllerservice.UserAdmin;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.util.ErrorBoxController;
import ui.view.presentation.util.SelectTimeViewController;
import util.MemberType;
import util.PromotionType;
import util.ResultMessage;
import vo.ClerkVO;
import vo.HotelVO;
import vo.PromotionVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ST on 2016/12/12.
 */
public class ClerkOtherPromotionController implements ControlledStage {

    private StageController stageController;

    @FXML
    private Label discountLabel;
    @FXML
    private Label roomNumLabel;
    @FXML
    private TextField promotionNameTextField;
    @FXML
    private ChoiceBox targetMemberChoiceBox;
    @FXML
    private Button startTimeButton;
    @FXML
    private Button endTimeButton;
    @FXML
    private Button confirmButton;

    private String resource = "clerk/ClerkOtherPromotion.fxml";

    private String clerkID;
    private String clerkName;
    private ClerkVO clerkVO;
    private HotelVO hotelVO;
    private String hotelID;
    private UserAdmin userAdmin;
    private HotelAdmin hotelAdmin;
    private EditPromotion editPromotion;
    private DecimalFormat df = new DecimalFormat("0.0");

    //获取当前日期
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String initialTime = sdf.format(date);

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String clerkID) throws RemoteException {
        userAdmin = new UserAdminController();
        hotelAdmin = new HotelAdminController();
        this.clerkID = clerkID;
        this.clerkVO = userAdmin.findClerkByID(clerkID);
        this.clerkName = clerkVO.name;
        this.hotelVO = hotelAdmin.findHotelByClerkID(clerkID);
        this.hotelID = hotelVO.hotelID;
        startTimeButton.setText(initialTime);
        endTimeButton.setText(initialTime);

        targetMemberChoiceBox.setItems(FXCollections.observableArrayList("所有客户", "普通会员"));
    }

    /**
     * 重载initial方法，用于修改策略时初始化界面
     */
    public void initial(PromotionVO promotionVO) throws RemoteException {
        userAdmin = new UserAdminController();
        this.initial(userAdmin.findClerkByName(promotionVO.framerName).get(0).ID);
        confirmButton.setText("修改");
        promotionNameTextField.setText(promotionVO.promotionName);
        discountLabel.setText(String.valueOf(promotionVO.discount));
        startTimeButton.setText(String.valueOf(promotionVO.startTime).substring(0, 10));
        endTimeButton.setText(String.valueOf(promotionVO.endTime).substring(0, 10));
        roomNumLabel.setText(String.valueOf(promotionVO.minRoom));
        targetMemberChoiceBox.getSelectionModel().select(promotionVO.targetUser.equals(MemberType.NORMAL) ? "所有客户" : "普通会员");
    }

    /**
     * 加号按钮结果，增加房间数量
     */
    @FXML
    private void addRoomNum() {
        int roomNum = Integer.parseInt(roomNumLabel.getText());
        if (roomNum != 20) {
            roomNumLabel.setText(String.valueOf(roomNum + 1));
        }
    }

    /**
     * 减号按钮结果，减少房间数量
     */
    @FXML
    private void minusRoomNum() {
        int roomNum = Integer.parseInt(roomNumLabel.getText());
        if (roomNum != 20) {
            roomNumLabel.setText(String.valueOf(roomNum - 1));
        }
    }

    /**
     * 加号按钮结果，增加折扣数字
     */
    @FXML
    private void addDiscount() {
        double discount = Double.parseDouble(discountLabel.getText());
        if (discount != 9.9) {
            discountLabel.setText(df.format((discount + 0.1)));
        }
    }

    /**
     * 减号按钮结果，减少折扣数字
     */
    @FXML
    private void minusDiscount() {
        double discount = Double.parseDouble(discountLabel.getText());
        if (discount != 0.1) {
            discountLabel.setText(df.format((discount - 0.1)));
        }
    }

    /**
     * 开始时间按钮结果，显示选择开始时间弹窗
     */
    @FXML
    private void showBeginTime() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "start");
        selectTimeViewController.init();
    }

    /**
     * 结束时间按钮结果，显示选择结束时间弹窗
     */
    @FXML
    private void showEndTime() {
        stageController = new StageController();
        stageController.loadStage("util/SelectTimeView.fxml", 0.8);
        SelectTimeViewController selectTimeViewController = (SelectTimeViewController) stageController.getController();
        selectTimeViewController.setToBeSet(resource, "end");
        selectTimeViewController.init();
    }

    /**
     * 确定按钮结果，创建订单
     */
    @FXML
    private void confirmCreate() throws IOException, ClassNotFoundException {

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));

        editPromotion = new EditPromotionController();

        String promotionName = promotionNameTextField.getText();
        String[] targetHotel = new String[1];
        targetHotel[0] = hotelID;
        Timestamp startTime = Timestamp.valueOf(startTimeButton.getText() + " 00:00:00");
        Timestamp endTime = Timestamp.valueOf(endTimeButton.getText() + " 00:00:00");
        double discount = Double.parseDouble(discountLabel.getText());
        int minRoom = Integer.parseInt(roomNumLabel.getText());

        MemberType memberType;
        String selectedTargetMember = (String) targetMemberChoiceBox.getSelectionModel().getSelectedItem();
        if (selectedTargetMember.equals("所有客户")) {
            memberType = MemberType.NONMEMBER;
        } else if (selectedTargetMember.equals("普通会员")) {
            memberType = MemberType.NORMAL;
        } else {
            memberType = null;
        }

        if (confirmButton.getText().equals("制定")) {
            ResultMessage resultMessage = editPromotion.addHotelPromotion(new PromotionVO(clerkName, time, promotionName + "其他优惠", memberType
                    , targetHotel, startTime, endTime, discount, minRoom, PromotionType.HotelPromotion_Other));
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息未填写完整！");
            } else if (resultMessage.equals(ResultMessage.Promotion_AddPromotionSuccess)) {
                stageController = this.returnMessage("创建成功！");
                stageController.closeStage("clerk/ClerkCreateHotelPromotion.fxml");
            } else {
                this.returnMessage("未知错误！");
            }
        } else if (confirmButton.getText().equals("修改")) {
            ResultMessage resultMessage = editPromotion.addHotelPromotion(new PromotionVO(clerkName, time, promotionName + "其他优惠", memberType
                    , targetHotel, startTime, endTime, discount, minRoom, PromotionType.HotelPromotion_Other));
            if (resultMessage.equals(ResultMessage.Blank)) {
                this.returnMessage("信息未填写完整！");
            } else if (resultMessage.equals(ResultMessage.Promotion_ModifyPromotionSuccess)) {
                stageController = this.returnMessage("修改成功！");
                stageController.closeStage("clerk/ClerkModifyPromotion.fxml");
            } else {
                this.returnMessage("未知错误！");
            }
        }

    }

    /**
     * 显示提示信息
     *
     * @param error
     * @return
     */
    private StageController returnMessage(String error) {
        stageController = new StageController();
        stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
        ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
        errorBoxController.setLabel(error);
        return stageController;
    }

    /**
     * 回显选择的开始时间
     *
     * @param time
     */
    public void setStartTime(String time) {
        startTimeButton.setText(time);
    }

    /**
     * 回显选择的结束时间
     *
     * @param time
     */
    public void setEndTime(String time) {
        endTimeButton.setText(time);
    }
}
