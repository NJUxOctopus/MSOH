import junit.framework.TestCase;
import rmi.RemoteHelper;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

/**
 * Created by Pxr on 2016/12/22.
 */
public class TestDriver extends TestCase{
    private static RemoteHelper remoteHelper;

    private static String hostname;

    public static void main(String[] args) throws RemoteException {
        linkServer();
        junit.textui.TestRunner.run(ClerkTest.class);
    }

    private static void linkServer() {
        try {
            initProperties();

            remoteHelper = RemoteHelper.getInstance();
            InetAddress ipv4Address = InetAddress.getByName(hostname);
            String ipAddress = ipv4Address.getHostAddress();

//            remoteHelper.setRemote(Naming.lookup("rmi://172.28.179.228:1099/DataRemoteObject"));
            remoteHelper.setRemote(Naming.lookup("rmi://" + ipAddress + ":1099/DataRemoteObject"));
            System.out.println("Octopus: 连接服务器成功");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void initProperties() {
        Properties pro = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("host.properties"));
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        hostname = pro.getProperty("hostname");
    }

}
