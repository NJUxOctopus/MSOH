package ui.view.presentation.clerk;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ui.controller.HotelAdminController;
import ui.view.controllerservice.HotelAdmin;
import ui.view.presentation.PaneAdder;
import ui.view.presentation.util.ControlledStage;
import ui.view.presentation.StageController;
import vo.CommentVO;
import vo.HotelVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ST on 2016/11/29.
 */
public class ClerkHotelCommentsController implements ControlledStage {

    private StageController stageController;

    private static String resource = "clerk/ClerkHotelComments.fxml";

    @FXML
    private AnchorPane commentPane;
    @FXML
    private ImageView noCommentIcon;
    @FXML
    private Label noCommentLabel;

    private String hotelID;
    private HotelVO hotelVO;
    private HotelAdmin hotelAdmin;
    private List<CommentVO> commentVOs = new ArrayList<CommentVO>();
    private CommentVO commentVO;

    @Override
    public void setStageController(StageController stageController) {
        this.stageController = stageController;
    }

    /**
     * initial方法，初始化界面
     */
    public void initial(String hotelID) throws RemoteException {
        hotelAdmin = new HotelAdminController();
        this.hotelID = hotelID;
        hotelVO = hotelAdmin.findByID(hotelID);

        if (hotelVO.comment == null || hotelVO.comment.isEmpty()) {

        } else {
            commentPane.getChildren().remove(noCommentLabel);
            commentPane.getChildren().remove(noCommentIcon);
            commentVOs = hotelVO.comment;
            PaneAdder paneAdder = new PaneAdder();
            int numOfComments = commentVOs.size();
            commentPane.setPrefWidth(numOfComments * 120);
            for (int i = 0; i < numOfComments; i++) {
                commentVO = commentVOs.get(i);
                ClerkSingleHotelCommentController clerkSingleHotelCommentController = (ClerkSingleHotelCommentController) paneAdder.getController();
                clerkSingleHotelCommentController.initial(commentVO);
                paneAdder.addPane(commentPane, "clerk/ClerkSingleHotelComment.fxml", 0, i * 120);
            }
        }

    }

    /**
     * 后退按钮结果，显示上级酒店信息页面
     */
    @FXML
    private void showConfirmExit() {
        stageController = new StageController();
        stageController.closeStage(resource);
    }

    /**
     * 退出按钮结果，退出程序
     */
    @FXML
    private void exit() {
        System.exit(0);
    }
}
