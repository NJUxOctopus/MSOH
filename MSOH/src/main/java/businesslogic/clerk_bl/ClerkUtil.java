package businesslogic.clerk_bl;

import businesslogicservice.clerkUtil_blservice.ClerkUtil_BLService;
import dataservice.clerk_dataservice.Clerk_DataService;
import po.ClerkPO;
import rmi.RemoteHelper;
import vo.ClerkVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pxr on 16/11/17.
 */

public class ClerkUtil implements ClerkUtil_BLService {
    Clerk_DataService clerk_dataService = RemoteHelper.getInstance().getClerkDataService();

    /**
     * 得到所有工作人员的vo
     * @return
     * @throws RemoteException
     */
    public List<ClerkVO> getAll() throws RemoteException {
        List<ClerkPO> clerkPOList = clerk_dataService.findAllClerks();
        //如果没有员工
        if (clerkPOList==null||clerkPOList.isEmpty())
            return null;
        else{
            List<ClerkVO> clerkVOList = new ArrayList<ClerkVO>();
            Iterator iterator = clerkPOList.iterator();
            while (iterator.hasNext()){
                Object object = iterator.next();
                ClerkPO clerkPO = (ClerkPO)object;
                clerkVOList.add(new ClerkVO(clerkPO.getName(),clerkPO.getPhone(),clerkPO.getPassword(),clerkPO.getID(),
                        clerkPO.getPicUrl(),clerkPO.getHotelName(),clerkPO.getHotelID()));
            }
            return clerkVOList;
        }
    }

    /**
     *根据id得到一个工作人员
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ClerkVO getSingle(String ID) throws RemoteException {
        if(ID.equals(""))
            //若id为空
            return null;
        ClerkPO clerkPO = clerk_dataService.findClerkByID(ID);
        if(clerkPO==null)
            //若不存在该工作人员
            return null;
        return new ClerkVO(clerkPO.getName(), clerkPO.getPhone(), clerkPO.getPassword(), clerkPO.getID()
                ,clerkPO.getPicUrl(), clerkPO.getHotelName(), clerkPO.getHotelID());
    }

    /**
     * 根据名字得到工作人员
     * @param name
     * @return
     * @throws RemoteException
     */
    public List<ClerkVO> getByName(String name) throws RemoteException {
        if(name.equals(""))
            //若名字为空
            return null;
        List<ClerkPO> clerkPOList = clerk_dataService.findClerkByName(name);
        if (clerkPOList==null||clerkPOList.isEmpty())
            //若列表为空
            return null;
        else{
            List<ClerkVO> clerkVOList = new ArrayList<ClerkVO>();
            Iterator iterator = clerkPOList.iterator();
            while (iterator.hasNext()){
                Object object = iterator.next();
                ClerkPO clerkPO = (ClerkPO)object;
                clerkVOList.add(new ClerkVO(clerkPO.getName(),clerkPO.getPhone(),clerkPO.getPassword(),clerkPO.getID()
                        ,clerkPO.getPicUrl(),clerkPO.getHotelName(),clerkPO.getHotelID()));
            }
            return clerkVOList;
        }
    }
}
