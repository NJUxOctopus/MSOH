/**
 * created by zqh on 2016-11-13
 */
package init;

import rmi.RemoteHelper;

public class ServerDriver {

    public ServerDriver() {
        new RemoteHelper();
    }

    public static void main(String[] args) {
        System.out.println("Octopus: 启动服务器中...");
        System.out.println("Octopus: 正在更新房间信息...");
        new DailyRoomUpdater();
        System.out.println("Octopus: 更新房间信息成功");
        System.out.println("Octopus: 正在连接客户端...");
        new ServerDriver();
    }

}
