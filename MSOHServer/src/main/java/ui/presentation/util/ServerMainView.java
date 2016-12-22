package ui.presentation.util;/**
 * Created by island on 2016/12/22.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ServerMainView  {
    private FXMLLoader loader;

    private static Stage stage;

    public void loadStage() {
        double width = 0;
        double height = 0;
        try {
            //加载FXML资源文件
            loader = new FXMLLoader(getClass().getResource("ServerMainView.fxml"));
            Pane pane = (Pane) loader.load();
            width = pane.getPrefWidth();
            height = pane.getPrefHeight();

            //构造对应的Stage
            Scene scene = new Scene(pane);
            scene.setFill(null);
            stage = new Stage();
            stage.setScene(scene);
            //无法改变stage窗口
            stage.setResizable(false);

            //stage背景透明
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);

            //去除stage外框
            stage.initStyle(StageStyle.UNDECORATED);
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            stage.setX((bounds.getWidth() - width) / 2);
            stage.setY((bounds.getHeight() - height) / 2);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ServerMainViewController getController(){
        return loader.getController();
    }


}
