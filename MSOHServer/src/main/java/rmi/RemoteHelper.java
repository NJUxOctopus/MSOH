package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

/**
 * RMI远程连接助手
 * <p>
 * Created by zqh on 2016/11/13.
 */
public class RemoteHelper {
    private static String ipAddress="";
    private Scanner scanner=new Scanner(System.in);

    public RemoteHelper() {
        initServer();
    }

    public void initServer() {
        System.out.println("Octopus: 请输入IP地址: ");
        ipAddress=scanner.nextLine();
        //ipconfig/all
        System.setProperty("java.rmi.server.hostname",ipAddress);
        DataRemoteObject dataRemoteObject;
        try {
            dataRemoteObject = new DataRemoteObject();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://"+ipAddress+":1099/DataRemoteObject", dataRemoteObject);
//            Naming.bind("rmi://localhost:1099/DataRemoteObject",dataRemoteObject);
            System.out.println("Octopus: 连接客户端成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
