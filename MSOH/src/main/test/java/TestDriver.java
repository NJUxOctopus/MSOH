import rmi.RemoteHelper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Pxr on 2016/12/24.
 */
public class TestDriver {
    private RemoteHelper remoteHelper;
    private String IP;

    public TestDriver(String IP) {
        this.IP = IP;
    }

    public void linkServer() {
        try {
//            initProperties();

            remoteHelper = RemoteHelper.getInstance();
//            InetAddress ipv4Address = InetAddress.getByName(hostname);
//            String ipAddress = ipv4Address.getHostAddress();

//            remoteHelper.setRemote(Naming.lookup("rmi://172.28.179.228:1099/DataRemoteObject"));
            remoteHelper.setRemote(Naming.lookup("rmi://" + IP + ":1099/DataRemoteObject"));
            System.out.println("Octopus: 连接服务器成功");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
