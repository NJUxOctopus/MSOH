package data.manager_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.managerDataHelper.ManagerDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.manager_dataservice.Manager_DataService;
import po.ManagerPO;
import util.DataUtil.CopyUtil;
import util.DataUtil.EncryptionUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/11/28.
 */
@SuppressWarnings(value = {"Duplicates"})
public class Manager_DataServiceImpl implements Manager_DataService {
    private ManagerDataHelper managerDataHelper;

    private DataFactory dataFactory;

    private static Manager_DataServiceImpl manager_dataServiceImpl;

    private static final String key = "20162017";

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
    public boolean modifyManager(ManagerPO managerPO) throws RemoteException {
        // 数据库中主键必须存在，否则更新不成功
        if (managerPO.getID() == null) {
            return false;
        }

        // 网站管理人员姓名、密码、联系方式、ID加密
        String name = EncryptionUtil.encode(key, managerPO.getName());
        String pw = EncryptionUtil.encode(key, managerPO.getPassword());
        String phone = EncryptionUtil.encode(key, managerPO.getPhone());
        String manangerID = EncryptionUtil.encode(key, managerPO.getID());
        managerPO.setName(name);
        managerPO.setPassword(pw);
        managerPO.setPhone(phone);
        managerPO.setID(manangerID);

        return managerDataHelper.modifyManager(managerPO);
    }

    /**
     * 根据ID查找网站管理人员
     *
     * @param ID
     * @return
     * @throws RemoteException
     */
    public ManagerPO findManagerByID(String ID) throws RemoteException {
        ID = EncryptionUtil.encode(key, ID);

        ManagerPO managerPO = managerDataHelper.findManagerByID(ID);

        if (null == managerPO) {
            return null;
        } else {
            // 姓名、密码、联系方式、ID解密
            managerPO.setName(EncryptionUtil.decode(key, managerPO.getName()));
            managerPO.setPassword(EncryptionUtil.decode(key, managerPO.getPassword()));
            managerPO.setPhone(EncryptionUtil.decode(key, managerPO.getPhone()));
            managerPO.setID(EncryptionUtil.decode(key, managerPO.getID()));
        }

        return (ManagerPO) managerPO.clone();
    }

    /**
     * 获取所有网站管理人员信息
     *
     * @return 所有网站管理人员信息
     * @throws RemoteException
     */
    public List<ManagerPO> findAllManagers() throws IOException, ClassNotFoundException {
        List<ManagerPO> managersList = managerDataHelper.findAllManagers();
        if (null == managersList || managersList.isEmpty()) {
            return new ArrayList<ManagerPO>();
        } else {
            // 姓名、密码、联系方式、ID解密
            for (ManagerPO manager : managersList) {
                manager.setName(EncryptionUtil.decode(key, manager.getName()));
                manager.setPassword(EncryptionUtil.decode(key, manager.getPassword()));
                manager.setPhone(EncryptionUtil.decode(key, manager.getPhone()));
                manager.setID(EncryptionUtil.decode(key, manager.getID()));
            }
        }

        List<ManagerPO> returnManagersList = CopyUtil.deepCopy(managersList);

        return returnManagersList;
    }

    /**
     * 根据姓名查找网站管理人员信息
     *
     * @param name
     * @return 与姓名相匹配的网站管理人员列表
     * @throws RemoteException
     */
    public List<ManagerPO> findManagerByName(String name) throws IOException, ClassNotFoundException {
        name = EncryptionUtil.encode(key, name);

        List<ManagerPO> list = managerDataHelper.findManagerByName(name);

        if (list == null || list.isEmpty()) {
            return new ArrayList<ManagerPO>();
        } else {
            // 姓名、密码、联系方式、ID解密
            for (ManagerPO manager : list) {
                manager.setPassword(EncryptionUtil.decode(key, manager.getPassword()));
                manager.setName(EncryptionUtil.decode(key, manager.getName()));
                manager.setPhone(EncryptionUtil.decode(key, manager.getPhone()));
                manager.setID(EncryptionUtil.decode(key, manager.getID()));
            }
        }

        List<ManagerPO> returnManagersList = CopyUtil.deepCopy(list);

        return returnManagersList;
    }
}
