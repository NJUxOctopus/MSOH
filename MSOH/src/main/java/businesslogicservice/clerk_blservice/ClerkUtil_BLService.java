package businesslogicservice.clerk_blservice;

import vo.ClerkVO;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Pxr on 16/11/17.
 */
public interface ClerkUtil_BLService {

    public List<ClerkVO> getAll() throws RemoteException;

    public ClerkVO getSingle(String ID) throws RemoteException;

    public List<ClerkVO> getByName(String name) throws RemoteException;
}
