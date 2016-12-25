package data.clerk_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.clerkDataHelper.ClerkDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.clerk_dataservice.Clerk_DataService;
import po.ClerkPO;
import util.DataUtil.CopyUtil;
import util.DataUtil.EncryptionUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/11/27.
 */
@SuppressWarnings(value = {"Duplicates"})
public class Clerk_DataServiceImpl implements Clerk_DataService {

    private ClerkDataHelper clerkDataHelper;

    private DataFactory dataFactory;

    private static Clerk_DataServiceImpl clerk_dataServiceImpl;

    private static final String key = "20162017";

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
    public boolean addClerk(ClerkPO clerkPO) throws RemoteException {
        // 姓名、密码、联系方式、ID加密
        String pw = EncryptionUtil.encode(key, clerkPO.getPassword());
        String name = EncryptionUtil.encode(key, clerkPO.getName());
        String phone = EncryptionUtil.encode(key, clerkPO.getPhone());
        String clerkID = EncryptionUtil.encode(key, clerkPO.getID());
        clerkPO.setPassword(pw);
        clerkPO.setName(name);
        clerkPO.setPhone(phone);
        clerkPO.setID(clerkID);

        return clerkDataHelper.addClerk(clerkPO);
    }

    /**
     * 更改酒店工作人员信息
     *
     * @param clerkPO
     * @return 更改酒店工作人员信息是否成功
     * @throws RemoteException
     */
    public boolean modifyClerk(ClerkPO clerkPO) throws RemoteException {
        // 数据库中主键不能为空，否则更新不成功
        if (clerkPO.getID() == null) {
            return false;
        }

        // 姓名、密码、联系方式、ID加密
        String pw = EncryptionUtil.encode(key, clerkPO.getPassword());
        String name = EncryptionUtil.encode(key, clerkPO.getName());
        String phone = EncryptionUtil.encode(key, clerkPO.getPhone());
        String clerkID = EncryptionUtil.encode(key, clerkPO.getID());
        clerkPO.setPassword(pw);
        clerkPO.setName(name);
        clerkPO.setPhone(phone);
        clerkPO.setID(clerkID);

        return clerkDataHelper.modifyClerk(clerkPO);
    }

    /**
     * 根据姓名查找酒店工作人员
     *
     * @param name
     * @return 酒店工作人员组成的列表，可能为空
     * @throws RemoteException
     */
    public List<ClerkPO> findClerkByName(String name) throws IOException, ClassNotFoundException {
        name = EncryptionUtil.encode(key, name);

        List<ClerkPO> clerksFound = clerkDataHelper.getClerkByName(name);

        if (null == clerksFound || clerksFound.isEmpty()) {
            return clerksFound;
        }

        for (ClerkPO clerk : clerksFound) {
            // 姓名、密码、联系方式、ID解密
            clerk.setName(EncryptionUtil.decode(key, clerk.getName()));
            clerk.setPassword(EncryptionUtil.decode(key, clerk.getPassword()));
            clerk.setPhone(EncryptionUtil.decode(key, clerk.getPhone()));
            clerk.setID(EncryptionUtil.decode(key, clerk.getID()));
        }

        List<ClerkPO> returnClerksFound = CopyUtil.deepCopy(clerksFound);

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
        id = EncryptionUtil.encode(key, id);

        ClerkPO clerkFound = clerkDataHelper.getClerkByID(id);

        if (null == clerkFound) {
            return null;
        } else {
            // 姓名、密码、联系方式、ID解密
            clerkFound.setName(EncryptionUtil.decode(key, clerkFound.getName()));
            clerkFound.setPassword(EncryptionUtil.decode(key, clerkFound.getPassword()));
            clerkFound.setPhone(EncryptionUtil.decode(key, clerkFound.getPhone()));
            clerkFound.setID(EncryptionUtil.decode(key, clerkFound.getID()));
        }

        return (ClerkPO) clerkFound.clone();
    }

    /**
     * 得到所有酒店工作人员的列表
     *
     * @return 所有酒店工作人员的列表
     * @throws RemoteException
     */
    public List<ClerkPO> findAllClerks() throws IOException, ClassNotFoundException {
        List<ClerkPO> clerkList = clerkDataHelper.getAllClerks();
        if (null == clerkList || clerkList.isEmpty()) {
            return clerkList;
        }

        for (ClerkPO clerk : clerkList) {
            // 姓名、密码、联系方式、ID解密
            clerk.setName(EncryptionUtil.decode(key, clerk.getName()));
            clerk.setPassword(EncryptionUtil.decode(key, clerk.getPassword()));
            clerk.setPhone(EncryptionUtil.decode(key, clerk.getPhone()));
            clerk.setID(EncryptionUtil.decode(key, clerk.getID()));
        }

        List<ClerkPO> returnClerkList = CopyUtil.deepCopy(clerkList);

        return returnClerkList;
    }

    /**
     * 删除酒店工作人员
     *
     * @param clerkPO
     * @return 删除酒店工作人员是否成功
     * @throws RemoteException
     */
    public boolean deleteClerk(ClerkPO clerkPO) throws RemoteException {
        // 数据库中主键不能为空，否则删除不成功
        if (clerkPO.getID() == null) {
            return false;
        }

        // 姓名、密码、联系方式、ID加密
        String pw = EncryptionUtil.encode(key, clerkPO.getPassword());
        String name = EncryptionUtil.encode(key, clerkPO.getName());
        String phone = EncryptionUtil.encode(key, clerkPO.getPhone());
        String clerkID = EncryptionUtil.encode(key, clerkPO.getID());
        clerkPO.setPassword(pw);
        clerkPO.setName(name);
        clerkPO.setPhone(phone);
        clerkPO.setID(clerkID);

        return clerkDataHelper.deleteClerk(clerkPO);
    }

}
