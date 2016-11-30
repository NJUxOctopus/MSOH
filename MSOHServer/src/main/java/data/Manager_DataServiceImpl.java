package data;

import DataHelper.DataFactory;
import DataHelper.ManagerDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.manager_dataservice.Manager_DataService;
import po.ManagerPO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/11/28.
 */
public class Manager_DataServiceImpl implements Manager_DataService {
    private ManagerDataHelper managerDataHelper;

    private DataFactory dataFactory;

    private static Manager_DataServiceImpl manager_dataServiceImpl;


    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return managerDataService的实例
     */
    public static Manager_DataServiceImpl getInstance() {
        if (null == manager_dataServiceImpl) {
            manager_dataServiceImpl = new Manager_DataServiceImpl();
        }
        return manager_dataServiceImpl;
    }

    private Manager_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        managerDataHelper = dataFactory.getManagerDataHelper();
    }

    /**
     * 更新网站管理人员信息
     *
     * @param managerPO
     * @throws RemoteException
     */
    public void modifyManager(ManagerPO managerPO) throws RemoteException {
        managerDataHelper.modifyManager(managerPO);
    }

    /**
     * 根据ID查找网站管理人员
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ManagerPO findManagerByID(String ID) throws RemoteException {
        ManagerPO managerPO = managerDataHelper.findManagerByID(ID);

        if (null == managerPO) {
            return null;
        }

        return (ManagerPO)managerPO.clone();
    }

    /**
     * 获取所有网站管理人员信息
     *
     * @return 所有网站管理人员信息
     * @throws RemoteException
     */
    public List<ManagerPO> findAllManagers() throws RemoteException {
        List<ManagerPO> managersList=managerDataHelper.findAllManagers();

        List<ManagerPO> returnManagersList=new ArrayList<ManagerPO>();
        returnManagersList.addAll(managersList);

        return returnManagersList;
    }

    /**
     * 根据姓名查找网站管理人员信息
     *
     * @param name
     * @return 与姓名相匹配的网站管理人员列表
     * @throws RemoteException
     */
    public List<ManagerPO> findManagerByName(String name) throws RemoteException {
        List<ManagerPO> list = managerDataHelper.findManagerByName(name);

        if (null == list) {
            return null;
        }

        List<ManagerPO> returnManagersList=new ArrayList<ManagerPO>();
        returnManagersList.addAll(list);

        return returnManagersList;
    }
}
