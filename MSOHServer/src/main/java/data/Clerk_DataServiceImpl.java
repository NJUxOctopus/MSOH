package data;

import DataHelper.ClerkDataHelper;
import DataHelper.DataFactory;
import DataHelperImpl.DataFactoryImpl;
import dataservice.clerk_dataservice.Clerk_DataService;
import po.ClerkPO;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zqh on 2016/11/27.
 */
public class Clerk_DataServiceImpl implements Clerk_DataService {
    private List<ClerkPO> clerkList;

    private ClerkDataHelper clerkDataHelper;

    private DataFactory dataFactory;

    private static Clerk_DataServiceImpl clerk_dataServiceImpl;

    // 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
    public static Clerk_DataServiceImpl getInstance() {
        if (clerk_dataServiceImpl == null) {
            clerk_dataServiceImpl = new Clerk_DataServiceImpl();
        }
        return clerk_dataServiceImpl;
    }

    private Clerk_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        clerkDataHelper = dataFactory.getClerkDataHelper();
        clerkList = clerkDataHelper.getAllClerks();
    }

    /**
     * 新增酒店工作人员信息
     *
     * @param clerkPO
     * @return 新增酒店工作人员是否成功
     * @throws RemoteException
     */
    public boolean addClerk(ClerkPO clerkPO) throws RemoteException {
        Iterator<ClerkPO> it = clerkList.iterator();

        while (it.hasNext()) {
            ClerkPO cp = (ClerkPO) it.next();
            if (cp.equals(clerkPO)) {
                return false;
            } else if (cp.getID().equals(clerkPO.getID()) ||
                    cp.getHotelID().equals(clerkPO.getHotelID())) {
                // 数据库中只允许一个酒店有一个酒店工作人员存在
                // 一个酒店工作人员只允许在一个酒店任职
                return false;
            }
        }

        clerkDataHelper.addClerk(clerkPO);
        return true;
    }

    /**
     * 更改酒店工作人员信息
     *
     * @param clerkPO
     * @return 更改酒店工作人员信息是否成功
     * @throws RemoteException
     */
    public boolean modifyClerk(ClerkPO clerkPO) throws RemoteException {
        if (!clerkList.contains(clerkPO)) {
            return false;
        } else {
            clerkDataHelper.modifyClerk(clerkPO);
            return true;
        }
    }

    /**
     * 根据姓名查找酒店工作人员
     *
     * @param name
     * @return 酒店工作人员组成的列表，可能为空
     * @throws RemoteException
     */
    public List<ClerkPO> findClerkByName(String name) throws RemoteException {
        List<ClerkPO> clerksFound = clerkDataHelper.getClerkByName(name);

        return clerksFound;
    }

    /**
     * 根据ID查询酒店工作人员
     *
     * @param id
     * @return 查询得到的酒店工作人员信息
     * @throws RemoteException
     */
    public ClerkPO findClerkByID(String id) throws RemoteException {
        ClerkPO clerkFound = clerkDataHelper.getClerkByID(id);

        if (null == clerkFound) {
            return null;
        }
        return clerkFound;
    }

    /**
     * 得到所有酒店工作人员的列表
     *
     * @return 所有酒店工作人员的列表
     * @throws RemoteException
     */
    public List<ClerkPO> findAllClerks() throws RemoteException {
        return clerkList;
    }

    /**
     * 删除酒店工作人员
     *
     * @param clerkPO
     * @return 删除酒店工作人员是否成功
     * @throws RemoteException
     */
    public boolean deleteClerk(ClerkPO clerkPO) throws RemoteException {
        if (!clerkList.contains(clerkPO)) {
            return false;
        } else {
            clerkDataHelper.deleteClerk(clerkPO);
            return true;
        }
    }
}
