package businesslogic.clerk_bl;

import businesslogicservice.clerkUtil_blservice.ClerkUtil_BLService;
import dataservice.clerk_dataservice.Clerk_DataService_Stub;
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
    Clerk_DataService_Stub clerk_dataService_stub = new Clerk_DataService_Stub();
    public List<ClerkVO> getAll() throws RemoteException {
        List<ClerkPO> clerkPOList = clerk_dataService_stub.findAllClerks();
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

    public ClerkVO getSingle(String ID) throws RemoteException {
        if(ID.equals(""))
            return null;
        ClerkPO clerkPO = clerk_dataService_stub.findClerkByID(ID);
        if(clerkPO==null)
            return null;
        return new ClerkVO(clerkPO.getName(), clerkPO.getPhone(), clerkPO.getPassword(), clerkPO.getID(),
                clerkPO.getPic(), clerkPO.getHotelName(), clerkPO.getHotelID());
    }

    public List<ClerkVO> getByName(String name) throws RemoteException {
        if(name.equals(""))
            return null;
        List<ClerkPO> clerkPOList = clerk_dataService_stub.findClerkByName(name);
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
