package data.marketer_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.marketerDataHelper.MarketerDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.marketer_dataservice.Marketer_DataService;
import po.MarketerPO;
import util.CopyUtil;
import util.EncryptionUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/11/28.
 */
@SuppressWarnings(value = {"Duplicates"})
public class Marketer_DataServiceImpl implements Marketer_DataService {

    private MarketerDataHelper marketerDataHelper;

    private DataFactory dataFactory;

    private static Marketer_DataServiceImpl marketer_dataServiceImpl;

    private static final String key = "20162017";

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return marketerDataService的实例
     */
    public static Marketer_DataServiceImpl getInstance() {
        if (marketer_dataServiceImpl == null) {
            marketer_dataServiceImpl = new Marketer_DataServiceImpl();
        }
        return marketer_dataServiceImpl;
    }

    private Marketer_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        marketerDataHelper = dataFactory.getMarketerDataHelper();
    }

    /**
     * 数据库中新增网站营销人员
     *
     * @param marketerPO
     * @throws RemoteException
     */
    public boolean addMarketer(MarketerPO marketerPO) throws RemoteException {
        // 姓名、密码、联系方式、ID加密
        String name = EncryptionUtil.encode(key, marketerPO.getName());
        String pw = EncryptionUtil.encode(key, marketerPO.getPassword());
        String phone = EncryptionUtil.encode(key, marketerPO.getPhone());
        String marketerID = EncryptionUtil.encode(key, marketerPO.getID());
        marketerPO.setName(name);
        marketerPO.setPassword(pw);
        marketerPO.setPhone(phone);
        marketerPO.setID(marketerID);

        return marketerDataHelper.addMarketer(marketerPO);
    }

    /**
     * 更改网站营销人员的信息
     *
     * @param marketerPO
     * @throws RemoteException
     */
    public boolean modifyMarketer(MarketerPO marketerPO) throws RemoteException {
        // 数据库中的主键必须存在，否则更新不成功
        if (marketerPO.getID() == null) {
            return false;
        }

        // 姓名、密码、联系方式、ID加密
        String name = EncryptionUtil.encode(key, marketerPO.getName());
        String pw = EncryptionUtil.encode(key, marketerPO.getPassword());
        String phone = EncryptionUtil.encode(key, marketerPO.getPhone());
        String marketerID = EncryptionUtil.encode(key, marketerPO.getID());
        marketerPO.setName(name);
        marketerPO.setPassword(pw);
        marketerPO.setPhone(phone);
        marketerPO.setID(marketerID);

        return marketerDataHelper.modifyMarketer(marketerPO);
    }

    /**
     * 根据姓名查找网站营销人员信息
     *
     * @param name
     * @return 与姓名相匹配的网站营销人员列表
     * @throws RemoteException
     */
    public List<MarketerPO> findMarketerByName(String name) throws IOException, ClassNotFoundException {
        name = EncryptionUtil.encode(key, name);

        List<MarketerPO> marketersFound = marketerDataHelper.getMarketerByName(name);

        if (null == marketersFound || marketersFound.isEmpty()) {
            return marketersFound;
        } else {
            // 姓名、密码、联系方式、ID解密
            for (MarketerPO marketer : marketersFound) {
                marketer.setName(EncryptionUtil.decode(key, marketer.getName()));
                marketer.setPassword(EncryptionUtil.decode(key, marketer.getPassword()));
                marketer.setPhone(EncryptionUtil.decode(key, marketer.getPhone()));
                marketer.setID(EncryptionUtil.decode(key, marketer.getID()));
            }
        }

        List<MarketerPO> returnMarketersFound = CopyUtil.deepCopy(marketersFound);
        return returnMarketersFound;
    }

    /**
     * 根据ID查找网站营销人员信息
     *
     * @param id
     * @return 网站营销人员的信息
     * @throws RemoteException
     */
    public MarketerPO findMarketerByID(String id) throws RemoteException {
        id = EncryptionUtil.encode(key, id);

        MarketerPO marketerPO = marketerDataHelper.getMarketerByID(id);

        if (marketerPO == null) {
            return marketerPO;
        } else {
            // 姓名、密码、联系方式、ID解密
            marketerPO.setName(EncryptionUtil.decode(key, marketerPO.getName()));
            marketerPO.setPassword(EncryptionUtil.decode(key, marketerPO.getPassword()));
            marketerPO.setPhone(EncryptionUtil.decode(key, marketerPO.getPhone()));
            marketerPO.setID(EncryptionUtil.decode(key, marketerPO.getID()));
        }
        return (MarketerPO) marketerPO.clone();

    }

    /**
     * 获取所有网站营销人员的列表
     *
     * @return 所有营销人员的列表
     * @throws RemoteException
     */
    public List<MarketerPO> findAllMarketers() throws IOException, ClassNotFoundException {
        List<MarketerPO> marketerList = marketerDataHelper.getAllMarketers();

        if (marketerList == null || marketerList.isEmpty()) {
            return new ArrayList<MarketerPO>();
        } else {
            // 姓名、密码、联系方式、ID解密
            for (MarketerPO marketer : marketerList) {
                marketer.setName(EncryptionUtil.decode(key, marketer.getName()));
                marketer.setPassword(EncryptionUtil.decode(key, marketer.getPassword()));
                marketer.setPhone(EncryptionUtil.decode(key, marketer.getPhone()));
                marketer.setID(EncryptionUtil.decode(key, marketer.getID()));
            }
        }

        List<MarketerPO> returnMarketerList = CopyUtil.deepCopy(marketerList);

        return returnMarketerList;
    }

    /**
     * 删除网站营销人员信息
     *
     * @param marketerPO
     * @throws RemoteException
     */
    public boolean deleteMarketer(MarketerPO marketerPO) throws RemoteException {
        // 数据库中的主键必须存在，否则更新不成功
        if (marketerPO.getID() == null) {
            return false;
        }

        // 姓名、密码、联系方式、ID加密
        String name = EncryptionUtil.encode(key, marketerPO.getName());
        String pw = EncryptionUtil.encode(key, marketerPO.getPassword());
        String phone = EncryptionUtil.encode(key, marketerPO.getPhone());
        String marketerID = EncryptionUtil.encode(key, marketerPO.getID());
        marketerPO.setName(name);
        marketerPO.setPassword(pw);
        marketerPO.setPhone(phone);
        marketerPO.setID(marketerID);

        return marketerDataHelper.deleteMarketer(marketerPO);
    }

}
