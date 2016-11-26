package ui.view.presentation;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.view.presentation.customer.ControlledStage;

import java.util.HashMap;

/**
 * Created by island on 2016/11/23.
 * 控制界面的显示
 */
public class StageController {
    private Stage stage;

    private static HashMap<String, Stage> stages = new HashMap<String, Stage>();

    public boolean loadStage(String resource) {
        try {
            //加载FXML资源文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Pane pane = (Pane) loader.load();
            pane.getLayoutBounds().getHeight();

            //pane.setStyle(
            //        "-fx-background-color: rgba(255,255,255,0.5);"
            //);

            //通过Loader获取FXML对应的ViewCtr，并将本StageController注入到ViewCtr中
            ControlledStage controlledStage = (ControlledStage) loader.getController();
            controlledStage.setStageController(this);

            //构造对应的Stage
            Scene scene = new Scene(pane);
            scene.setFill(null);
            stage = new Stage();
            stage.setScene(scene);
            //无法改变stage窗口
            stage.setResizable(false);
            //将该stage添加至stages
            stages.put(resource, stage);
            //stage背景透明
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            //去除stage外框
            stage.initStyle(StageStyle.UNDECORATED);

            stage.show();
            System.out.print(stages.size());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getStage(String resource){
        return stages.get(resource);
    }


    public void closeStage(String resource){
        //System.out.print(stages.size());
        getStage(resource).close();
        stages.remove(resource);
    }

}



