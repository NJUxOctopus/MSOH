package data.memberlevel_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.memberLevelDataHelper.MemberLevelDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.memberlevel_dataservice.MemberLevel_DataService;
import po.MemberLevelPO;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/12/1.
 */
public class MemberLevel_DataServiceImpl implements MemberLevel_DataService {
    private MemberLevelDataHelper memberLevelDataHelper;

    private DataFactory dataFactory;

    private static MemberLevel_DataServiceImpl memberLevel_dataService;

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return memberLevelDataService的实例
     */
    public static MemberLevel_DataServiceImpl getInstance() {
        if (memberLevel_dataService == null) {
            memberLevel_dataService = new MemberLevel_DataServiceImpl();
        }

        return memberLevel_dataService;
    }

    private MemberLevel_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        memberLevelDataHelper = dataFactory.getMemberLevelDataHelper();
    }

    /**
     * 更新会员等级制度
     *
     * @param memberLevelPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean updateMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return memberLevelDataHelper.updateMemberLevel(memberLevelPO);
    }

    /**
     * 获得会员等级制度
     *
     * @return 会员等级制度
     * @throws RemoteException
     */
    public MemberLevelPO getMemberLevel() throws RemoteException {
        MemberLevelPO memberLevelPO = memberLevelDataHelper.getMemberLevel();

        return (MemberLevelPO) memberLevelPO.clone();
    }
}
