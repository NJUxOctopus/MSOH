/**
 * created by zqh on 2016-11-13
 */
package init;

import init.DailyUpdater.DailyOrderUpdater;
import init.DailyUpdater.DailyRoomUpdater;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rmi.RemoteHelper;
import ui.presentation.util.ServerMainView;
import ui.presentation.util.ServerMainViewController;
import util.TimerUtil.HotelRoomTimer;
import util.TimerUtil.OrderTimer;

public class ServerDriver extends Application {

    public ServerDriver() {
        new RemoteHelper();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initGUI();
        initServer();
    }

    private void initServer() {
        System.out.println("Octopus: 启动服务器中...");

        System.out.println("Octopus: 正在检查客户订单执行情况...");
        //new DailyOrderUpdater();
        System.out.println("Octopus: 更新订单状态成功");

        System.out.println("Octopus: 正在更新房间信息...");
        //new DailyRoomUpdater();
        System.out.println("Octopus: 更新房间信息成功");

        System.out.println("Octopus: 正在连接客户端...");
        //new ServerDriver();


    }

    private void initGUI() {
        ServerMainView serverMainView = new ServerMainView();
        serverMainView.loadStage();
        ServerMainViewController serverMainViewController = serverMainView.getController();
        RemoteHelper remoteHelper = new RemoteHelper();
        serverMainViewController.setIP(remoteHelper.getIP());
    }
    // 以下注释内容是在真实情况下启动Timer以实现 按时更新房间信息和订单信息
//    public ServerDriver(){
//        new RemoteHelper();
//    }
//
//    public static void main(String[] args){
//        new HotelRoomTimer();
//        new OrderTimer();
//
//        new ServerDriver();
//    }

}
