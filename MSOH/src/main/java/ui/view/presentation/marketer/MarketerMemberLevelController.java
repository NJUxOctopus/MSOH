package ui.view.presentation.marketer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import ui.controller.EditMemberLevelController;
import ui.view.controllerservice.EditMemberLevel;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import ui.view.presentation.util.ErrorBoxController;
import util.ResultMessage;
import vo.MemberLevelVO;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerMemberLevelController implements ControlledStage {

    private StageController stageController;

    @FXML
    private TableView<MemberLevelVO> memberLevelTableView;
    @FXML
    private TableColumn<MemberLevelVO, String> levelColumn;
    @FXML
    private TableColumn<MemberLevelVO, String> boundaryColumn;
    @FXML
    private TableColumn<MemberLevelVO, String> discountColumn;
    @FXML
    private Label levelLabel;
    @FXML
    private TextField boundaryTextField;
    @FXML
    private TextField discountTextField;

    private MemberLevelVO memberLevelVO;
    private EditMemberLevel editMemberLevel;
    private String marketerName;

//    ObservableList<Integer> levels = FXCollections.observableArrayList(1, 2, 3, 4, 5);
//    ObservableList<Integer> boundaries = FXCollections.observableArrayList(100, 200, 300, 400, 500);
//    ObservableList<Double> discount = FXCollections.observableArrayList(9.0, 8.0, 7.0, 6.0, 5.0);

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(final MemberLevelVO memberLevelVO) throws RemoteException {
        editMemberLevel = new EditMemberLevelController();
        this.memberLevelVO = memberLevelVO;
        this.marketerName = memberLevelVO.framerName;
        int[] boundaries = memberLevelVO.creditBoundaries;
        List<String> discount = memberLevelVO.discountList;

//        //初始化表
//        memberLevelTableView.setItems(null);

        ObservableList<MemberLevelVO> memberLevelVOObservableList = FXCollections.observableArrayList();
        for (int i = 1; i <= memberLevelVO.num; i++) {
            memberLevelVOObservableList.add(new MemberLevelVO(String.valueOf(i), String.valueOf(boundaries[i - 1]), discount.get(i - 1)));
        }

        memberLevelTableView.setItems(memberLevelVOObservableList);

        levelColumn.setCellValueFactory(new PropertyValueFactory<MemberLevelVO, String>("specificLevel"));
        boundaryColumn.setCellValueFactory(new PropertyValueFactory<MemberLevelVO, String>("specificBoundary"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<MemberLevelVO, String>("specificDiscount"));

        levelLabel.setText(String.valueOf(memberLevelVO.num + 1));
        boundaryTextField.setText("");
        discountTextField.setText("");
        memberLevelTableView.setEditable(true);

        boundaryColumn.setCellFactory(TextFieldTableCell.<MemberLevelVO>forTableColumn());
        boundaryColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MemberLevelVO, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<MemberLevelVO, String> event) {
                int[] newBoundaries = memberLevelVO.creditBoundaries;
                newBoundaries[event.getTablePosition().getRow()] = Integer.parseInt(event.getNewValue());
                MemberLevelVO newMemberLevelVO = new MemberLevelVO(memberLevelVO.memberLevelID, marketerName,
                        getCurrentTime(), memberLevelVO.num, newBoundaries, memberLevelVO.discountList);
                try {
                    if (editMemberLevel.modifyMemberLevel(newMemberLevelVO).equals(ResultMessage.MemberLevel_ModifyMemberLevelSuccess)) {
                        //增加等级制度成功，刷新表单
                        initial(newMemberLevelVO);
                    } else {
                        returnMessage("修改等级失败！");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


        discountColumn.setCellFactory(TextFieldTableCell.<MemberLevelVO>forTableColumn());
        discountColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MemberLevelVO, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<MemberLevelVO, String> event) {
                String[] newDiscount = new String[memberLevelVO.discountList.size()];
                List<String> newDiscountList = new ArrayList<String>();
                for (int i = 0; i < newDiscount.length; i++) {
                    newDiscount[i] = memberLevelVO.discountList.get(i);
                }
                newDiscount[event.getTablePosition().getRow()] = event.getNewValue();
                for (int i = 0; i < newDiscount.length; i++) {
                    newDiscountList.add(newDiscount[i]);
                }
                MemberLevelVO newMemberLevelVO = new MemberLevelVO(memberLevelVO.memberLevelID, marketerName,
                        getCurrentTime(), memberLevelVO.num, memberLevelVO.creditBoundaries, newDiscountList);
                try {
                    if (editMemberLevel.modifyMemberLevel(newMemberLevelVO).equals(ResultMessage.MemberLevel_ModifyMemberLevelSuccess)) {
                        //增加等级制度成功，刷新表单
                        initial(newMemberLevelVO);
                    } else {
                        returnMessage("修改等级失败！");
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 添加按钮结果，添加会员等级
     */
    @FXML
    private void addMemberLevel() throws RemoteException {
        editMemberLevel = new EditMemberLevelController();


        if (boundaryTextField.getText().equals("") || discountTextField.getText().equals("")) {
            this.returnMessage("请填写完整！");
        } else if (Integer.parseInt(boundaryTextField.getText())
                <= memberLevelVO.creditBoundaries[memberLevelVO.creditBoundaries.length - 1]) {
            this.returnMessage("信用值必须大于" + memberLevelVO.creditBoundaries[memberLevelVO.creditBoundaries.length - 1] + "!");
        } else {
            int[] newBoundaries = new int[memberLevelVO.num + 1];
            for (int i = 0; i < memberLevelVO.num; i++) {
                newBoundaries[i] = memberLevelVO.creditBoundaries[i];
            }
            newBoundaries[memberLevelVO.num] = Integer.parseInt(boundaryTextField.getText());
            List<String> newDiscountList = memberLevelVO.discountList;
            newDiscountList.add(discountTextField.getText());

            MemberLevelVO newMemberLevelVO = new MemberLevelVO(memberLevelVO.memberLevelID, marketerName, getCurrentTime(),
                    memberLevelVO.num + 1, newBoundaries, newDiscountList);

            if (editMemberLevel.modifyMemberLevel(newMemberLevelVO).equals(ResultMessage.MemberLevel_ModifyMemberLevelSuccess)) {
                //增加等级制度成功，刷新表单
                initial(newMemberLevelVO);
            } else {
                this.returnMessage("修改等级失败！");
            }
        }
    }

    /**
     * 修改按钮结果，修改会员制度
     */
    @FXML
    private void modifyMemberLevel() {

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
     * 获取当前时间
     *
     * @return
     */
    private Timestamp getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = Timestamp.valueOf(dateFormat.format(date));
        return time;
    }


}
