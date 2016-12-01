package data;

import DataHelper.DataFactory;
import DataHelper.MemberLevelDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.memberlevel_dataservice.MemberLevel_DataService;
import po.MemberLevelPO;

import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/12/1.
 */
public class MemberLevel_DataServiceImpl implements MemberLevel_DataService{
    private MemberLevelDataHelper memberLevelDataHelper;

    private DataFactory dataFactory;

    private static MemberLevel_DataServiceImpl memberLevel_dataService;

    public static MemberLevel_DataServiceImpl getInstance(){
        if(memberLevel_dataService==null){
            memberLevel_dataService=new MemberLevel_DataServiceImpl();
        }

        return memberLevel_dataService;
    }

    private MemberLevel_DataServiceImpl(){
        dataFactory=new DataFactoryImpl();
        memberLevelDataHelper=dataFactory.getMemberLevelDataHelper();
    }
    public boolean updateMemberLevel(MemberLevelPO memberLevelPO) throws RemoteException {
        return memberLevelDataHelper.updateMemberLevel(memberLevelPO);
    }

    public MemberLevelPO getMemberLevel() throws RemoteException {
        MemberLevelPO memberLevelPO=memberLevelDataHelper.getMemberLevel();

        return (MemberLevelPO)memberLevelPO.clone();
    }
}
