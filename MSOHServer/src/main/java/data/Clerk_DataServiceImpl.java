package data;

import DataHelper.ClerkDataHelper;
import DataHelper.DataFactory;
import DataHelperImpl.DataFactoryImpl;
import dataservice.clerk_dataservice.Clerk_DataService;
import po.ClerkPO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zqh on 2016/11/27.
 */
public class Clerk_DataServiceImpl implements Clerk_DataService {

    private ClerkDataHelper clerkDataHelper;

    private DataFactory dataFactory;

    private static Clerk_DataServiceImpl clerk_dataServiceImpl;

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return clerkDataService的实例
     */
    public static Clerk_DataServiceImpl getInstance() {
        if (clerk_dataServiceImpl == null) {
            clerk_dataServiceImpl = new Clerk_DataServiceImpl();
        }
        return clerk_dataServiceImpl;
    }


    private Clerk_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        clerkDataHelper = dataFactory.getClerkDataHelper();
    }

    /**
     * 新增酒店工作人员信息
     *
     * @param clerkPO
     * @return 新增酒店工作人员是否成功
     * @throws RemoteException
     */
    public void addClerk(ClerkPO clerkPO) throws RemoteException {
        clerkDataHelper.addClerk(clerkPO);
    }

    /**
     * 更改酒店工作人员信息
     *
     * @param clerkPO
     * @return 更改酒店工作人员信息是否成功
     * @throws RemoteException
     */
    public void modifyClerk(ClerkPO clerkPO) throws RemoteException {
        clerkDataHelper.modifyClerk(clerkPO);
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
        if (null == clerksFound) {
            return null;
        }

        List<ClerkPO> returnClerksFound=new ArrayList<ClerkPO>();
        returnClerksFound.addAll(clerksFound);

        return returnClerksFound;
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
        return (ClerkPO)clerkFound.clone();
    }

    /**
     * 得到所有酒店工作人员的列表
     *
     * @return 所有酒店工作人员的列表
     * @throws RemoteException
     */
    public List<ClerkPO> findAllClerks() throws RemoteException {
        List<ClerkPO> clerkList=clerkDataHelper.getAllClerks();

        List<ClerkPO> returnClerkList=new ArrayList<ClerkPO>();
        returnClerkList.addAll(clerkList);
        return returnClerkList;
    }

    /**
     * 删除酒店工作人员
     *
     * @param clerkPO
     * @return 删除酒店工作人员是否成功
     * @throws RemoteException
     */
    public void deleteClerk(ClerkPO clerkPO) throws RemoteException {
        clerkDataHelper.deleteClerk(clerkPO);
    }
}
