package ui.view.presentation.marketer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.MemberLevelVO;

import java.rmi.RemoteException;

/**
 * Created by ST on 2016/12/1.
 */
public class MarketerMemberLevelController implements ControlledStage {

    private StageController stageController;

    @FXML
    private TableView<MemberLevelVO> memberLevelTableView;
    @FXML
    private TableColumn<MemberLevelVO, Integer> levelColumn;
    @FXML
    private TableColumn<MemberLevelVO, Integer> boundaryColumn;
    @FXML
    private TableColumn<MemberLevelVO, Double> discountColumn;

    private MemberLevelVO memberLevelVO;

    ObservableList<MemberLevelVO> memberLevelVOObservableList = FXCollections.observableArrayList();
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
    public void initial(MemberLevelVO memberLevelVO) throws RemoteException {
        this.memberLevelVO = memberLevelVO;

        memberLevelVOObservableList.add(new MemberLevelVO(1, 100, 9.0));
        memberLevelVOObservableList.add(new MemberLevelVO(2, 200, 8.0));
        memberLevelVOObservableList.add(new MemberLevelVO(3, 300, 7.0));
        memberLevelVOObservableList.add(new MemberLevelVO(4, 400, 6.0));
        memberLevelVOObservableList.add(new MemberLevelVO(5, 500, 5.0));

//        levelColumn.setCellValueFactory();

    }

}
