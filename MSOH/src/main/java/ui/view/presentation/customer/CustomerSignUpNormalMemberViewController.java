package ui.view.presentation.customer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by island on 2016/11/24.
 */
public class CustomerSignUpNormalMemberViewController implements ControlledStage {
    StageController stageController;

    private String resource = "customer/CustomerSignUpNormalMemberView.fxml";

    private String customerID;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox yearChoiceBox;

    @FXML
    private ChoiceBox monthChoiceBox;

    @FXML
    private ChoiceBox dayChoiceBox;

    @FXML
    private Button confirmButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    @FXML
    private void closeStage() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    @FXML
    private void signUpNormal(){
        String year = yearChoiceBox.getSelectionModel().getSelectedItem() + "";
        String month = monthChoiceBox.getSelectionModel().getSelectedItem() + "";
        String day = dayChoiceBox.getSelectionModel().getSelectedItem() + "";
        Timestamp birthday = Timestamp.valueOf(year + "-" + month + "-" + day + " 00:00:00");
        if(year == "" || month == "" || day == ""){
            stageController = new StageController();
            stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
            ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
            errorBoxController.setLabel("未填写完整信息！");
        }else {
            MemberVO memberVO = new MemberVO(customerID, MemberType.NORMAL, 0, birthday);
            try {
                MemberRegister memberRegister = new MemberRegisterController();
                ResultMessage resultMessage = memberRegister.signUp(memberVO);
                stageController = new StageController();
                stageController.loadStage("util/ErrorBoxView.fxml", 0.75);
                ErrorBoxController errorBoxController = (ErrorBoxController) stageController.getController();
                if (resultMessage == ResultMessage.Blank) {
                    errorBoxController.setLabel("未填写完整信息！");
                } else if (resultMessage == ResultMessage.Member_AddMemberAlreadyExist) {
                    errorBoxController.setLabel("您已成为会员，无法再次注册！");
                } else if (resultMessage == ResultMessage.Member_NormalSignupSuccess) {
                    errorBoxController.setLabel("成功注册成为普通会员！");
                    stageController = new StageController();
                    stageController.closeStage(resource);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void init(String customerID){
        this.customerID = customerID;
        //获得当前日期
        Date today=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String[] string_date = new String[3];
        string_date = df.format(today).split("/");

        //设置年份数值
        ObservableList<String> year = FXCollections.observableArrayList();
        for(int i = 1900; i < Integer.parseInt(string_date[0]); i++){
            year.add(i + "");
        }
        yearChoiceBox.setItems(year);

        //设置月份数值
        ObservableList<String> monthList = FXCollections.observableArrayList();
        for(int i = 1; i <= 12; i++){
            monthList.add(i + "");
        }
        monthChoiceBox.setItems(monthList);

        monthChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                int month = Integer.parseInt(monthChoiceBox.getSelectionModel().getSelectedItem() + "");
                int year = Integer.parseInt(yearChoiceBox.getSelectionModel().getSelectedItem() + "");
                int day = 0;
                if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                    day = 31;
                }
                if(month == 4 || month == 6 || month == 9 || month == 11){
                    day = 30;
                }
                if(month == 2){
                    if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                        day = 29;
                    else
                        day = 28;
                }
                ObservableList<String> dayList = FXCollections.observableArrayList();
                for(int i = 1; i <= day; i++){
                    dayList.add(i + "");
                }
                dayChoiceBox.setItems(dayList);
            }
        });
    yearChoiceBox.setValue("1970");
    }

    /**
     * 退出系统
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
