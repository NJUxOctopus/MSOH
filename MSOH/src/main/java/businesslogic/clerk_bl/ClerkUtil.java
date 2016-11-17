package businesslogic.clerk_bl;

import businesslogicservice.clerkUtil_blservice.ClerkUtil_BLService;
import po.ClerkPO;
import rmi.RemoteHelper;
import util.WorkerPosition;
import vo.ClerkVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pxr on 16/11/17.
 */
public class ClerkUtil implements ClerkUtil_BLService {
    public List<ClerkVO> getAll() throws RemoteException {
        return null;
    }

    public ClerkVO getSingle(String ID) throws RemoteException {
        ClerkPO clerkPO = RemoteHelper.getInstance().getClerkDataService().find(ID);
        return new ClerkVO(clerkPO.getName(), clerkPO.getPhone(), clerkPO.getPassword(), clerkPO.getID(),
                clerkPO.getPic(), clerkPO.getHotelName(), clerkPO.getHotelID());
    }

    public List<ClerkVO> getByName(String name) throws RemoteException {
        List<ClerkPO> clerkPOList = RemoteHelper.getInstance().getClerkDataService().findByClerkName(name);
        if (clerkPOList==null)
            return null;
        else{
            List<ClerkVO> clerkVOList = new ArrayList<ClerkVO>();
            Iterator iterator = clerkPOList.iterator();
            while (iterator.hasNext()){
                Object object = iterator.next();
                ClerkPO clerkPO = (ClerkPO)object;
                clerkVOList.add(new ClerkVO(clerkPO.getName(),clerkPO.getPhone(),clerkPO.getPassword(),clerkPO.getID(),
                        clerkPO.getPic(),clerkPO.getHotelName(),clerkPO.getHotelID()));
            }
            return clerkVOList;
        }
    }
}
