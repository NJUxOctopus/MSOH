package businesslogic.manager_bl;

import businesslogicservice.manager_blservice.ManagerUtil_BLService;
import dataservice.manager_dataservice.Manager_DataService;
import po.ManagerPO;
import rmi.RemoteHelper;
import vo.ManagerVO;

import java.rmi.RemoteException;

/**
 * Created by Pxr on 16/12/13.
 */
public class ManagerUtil implements ManagerUtil_BLService {
    private Manager_DataService manager_dataService = RemoteHelper.getInstance().getManagerDataService();

    /**
     * 根据ID获得管理人员
     *
     * @param managerID
     * @return
     * @throws RemoteException
     */
    public ManagerVO getByID(String managerID) throws RemoteException {
        ManagerPO managerPO = manager_dataService.findManagerByID(managerID);
        if (managerPO == null)
            return null;
        return new ManagerVO(managerPO.getName(), managerPO.getPhone(), managerPO.getPassword(), managerPO.getID(), managerPO.getPic());
    }
}
