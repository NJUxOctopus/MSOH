package data;

import DataHelper.DataFactory;
import DataHelper.MarketerDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.marketer_dataservice.Marketer_DataService;
import po.MarketerPO;
import util.CopyUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/11/28.
 */
public class Marketer_DataServiceImpl implements Marketer_DataService {

    private MarketerDataHelper marketerDataHelper;

    private DataFactory dataFactory;

    private static Marketer_DataServiceImpl marketer_dataServiceImpl;

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
        return marketerDataHelper.addMarketer(marketerPO);
    }

    /**
     * 更改网站营销人员的信息
     *
     * @param marketerPO
     * @throws RemoteException
     */
    public boolean modifyMarketer(MarketerPO marketerPO) throws RemoteException {
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
        List<MarketerPO> marketersFound = marketerDataHelper.getMarketerByName(name);

        if (null == marketersFound) {
            return null;
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
        MarketerPO marketerPO = marketerDataHelper.getMarketerByID(id);

        if (null == marketerPO) {
            return null;
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
        if (null == marketerList) {
            return null;
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
        return marketerDataHelper.deleteMarketer(marketerPO);
    }

}
