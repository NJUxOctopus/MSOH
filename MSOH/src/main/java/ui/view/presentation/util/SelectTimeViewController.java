package ui.view.presentation.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import ui.view.presentation.StageController;
import ui.view.presentation.clerk.ClerkCreateOfflineOrderController;
import ui.view.presentation.customer.CustomerHotelListViewController;
import ui.view.presentation.customer.CustomerMainViewController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by island on 2016/12/1.
 */
public class SelectTimeViewController implements ControlledStage {
    StageController stageController;

    private String resources = "util/SelectTimeView.fxml";

    private String toBeSet = "";

    private String type;

    private int[] int_date;

    @FXML
    private ChoiceBox yearChoiceBox;

    @FXML
    private ChoiceBox monthChoiceBox;

    @FXML
    private ChoiceBox dayChoiceBox;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * 保存当前选择的日期，关闭窗口
     */
    @FXML
    private void confirm() {
        String year = (String) yearChoiceBox.getSelectionModel().getSelectedItem();
        String month = (String) monthChoiceBox.getSelectionModel().getSelectedItem();
        String day = (String) dayChoiceBox.getSelectionModel().getSelectedItem();
        if (year == null || month == null || day == null) {
            stageController.loadStage("util/ErrorBoxView.fxml", 0.8);
            ErrorBoxController controller = (ErrorBoxController) stageController.getController();
            controller.setLabel("未完成日期选择！");
        } else {
            String time = year + "-" + month + "-" + day;
            setStage(time);
            stageController.closeStage(resources);
        }
    }

    /**
     * 设置打开日期选择框的界面上的日期显示
     */
    private void setStage(String time) {
        if (toBeSet.equals("customer/CustomerMainView.fxml")) {
            CustomerMainViewController customerMainViewController = (CustomerMainViewController) stageController.getController(toBeSet);
            if (type.equals("checkIn"))
                customerMainViewController.setCheckInTime(time);
            if (type.equals("checkOut"))
                customerMainViewController.setCheckOutTime(time);
        }
        if (toBeSet.equals("customer/CustomerHotelListView.fxml")) {
            CustomerHotelListViewController customerHotelListViewController = (CustomerHotelListViewController) stageController.getController(toBeSet);
            if (type.equals("checkIn"))
                customerHotelListViewController.setCheckInTime(time);
            if (type.equals("checkOut"))
                customerHotelListViewController.setCheckOutTime(time);
        }
        if (toBeSet.equals("clerk/ClerkCreateOfflineOrder.fxml")) {
            ClerkCreateOfflineOrderController clerkCreateOfflineOrderController = (ClerkCreateOfflineOrderController) stageController.getController();
            if (type.equals("checkIn"))
                clerkCreateOfflineOrderController.setCheckInTime(time);
            if (type.equals("checkOut"))
                clerkCreateOfflineOrderController.setExpectedCheckOutTime(time);
        }
    }

    /**
     * 不选择日期，直接关闭窗口
     */
    @FXML
    private void cancel() {
        stageController.closeStage(resources);
    }


    /**
     * 日期选择框初始化方法
     */
    public void init() {
        //获得当前日期
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String[] string_date = new String[3];
        string_date = df.format(today).split("/");
        int_date = new int[3];
        for (int i = 0; i < 3; i++) {
            int_date[i] = Integer.parseInt(string_date[i]);
        }

        //设置年份数值
        ObservableList<String> year = FXCollections.observableArrayList();
        year.add(int_date[0] + "");
        if (int_date[1] == 12) {
            year.add(int_date[0] + 1 + "");
        }
        yearChoiceBox.setItems(year);


        //根据选择的年份数值设置月份数值与日期数值（客户可选择三十天以内的日期进行酒店预订）
        yearChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                //判断是否跨年
                if ((yearChoiceBox.getSelectionModel().getSelectedItem()).equals(int_date[0] + "")) {
                    ObservableList<String> month = FXCollections.observableArrayList();
                    month.add(int_date[1] + "");
                    if (int_date[1] != 12) {
                        month.add(int_date[1] + 1 + "");
                    }
                    monthChoiceBox.setItems(month);

                    ObservableList<String> day = FXCollections.observableArrayList();
                    for (int i = int_date[2]; i <= lengthOfMonth(int_date[0], int_date[1]); i++) {
                        day.add("" + i);
                    }
                    dayChoiceBox.setItems(day);
                }
                if ((yearChoiceBox.getSelectionModel().getSelectedItem()).equals(int_date[0] + 1 + "")) {
                    ObservableList<String> newMonth = FXCollections.observableArrayList();
                    newMonth.add("1");
                    monthChoiceBox.setItems(newMonth);

                    ObservableList<String> newDay = FXCollections.observableArrayList();
                    for (int i = 1; i <= 30 - (lengthOfMonth(int_date[0], int_date[1]) - int_date[2]); i++) {
                        newDay.add("" + i);
                    }
                    dayChoiceBox.setItems(newDay);
                }
            }
        });
    }

    private int lengthOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
                return 29;
            else
                return 28;
        }
        return 0;
    }

    /**
     * set方法，设置要被改变的界面
     */
    public void setToBeSet(String theStage, String type) {
        this.toBeSet = theStage;
        this.type = type;
    }

}
