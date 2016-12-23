/**
 * created by zqh on 2016-11-13
 */
package init;

import init.DailyUpdater.DailyOrderUpdater;
import init.DailyUpdater.DailyRoomUpdater;
import javafx.application.Application;
import javafx.stage.Stage;
import rmi.RemoteHelper;
import ui.presentation.util.ServerMainView;
import ui.presentation.util.ServerMainViewController;

public class ServerDriver extends Application {
    RemoteHelper remoteHelper;
    private String ip;

    public ServerDriver() {
        remoteHelper=new RemoteHelper();
        ip=remoteHelper.getIP();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initServer();
        initGUI();
    }

    private void initServer() {
        System.out.println("Octopus: 启动服务器中...");

        System.out.println("Octopus: 正在检查客户订单执行情况...");
        new DailyOrderUpdater();
        System.out.println("Octopus: 更新订单状态成功");

        System.out.println("Octopus: 正在更新房间信息...");
        new DailyRoomUpdater();
        System.out.println("Octopus: 更新房间信息成功");

        System.out.println("Octopus: 正在连接客户端...");
        System.out.println("Octopus: 连接客户端成功");
    }

    private void initGUI() {
        ServerMainView serverMainView = new ServerMainView();
        serverMainView.loadStage();
        ServerMainViewController serverMainViewController = serverMainView.getController();

        serverMainViewController.setIP(ip);
    }
    // TODO 改下面的
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
