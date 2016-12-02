package data;

import DataHelper.DataFactory;
import DataHelper.MemberDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.member_dataservice.Member_DataService;
import po.MemberPO;
import util.CopyUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by zqh on 2016/12/2.
 */
public class Member_DataServiceImpl implements Member_DataService {
    private MemberDataHelper memberDataHelper;

    private DataFactory dataFactory;

    private static Member_DataServiceImpl member_dataService;

    /**
     * 提供给外界获取实例的方法，采用单例模式使该类构造方法私有化
     *
     * @return memberDataService的实例
     */
    public static Member_DataServiceImpl getInstance() {
        if (member_dataService == null) {
            member_dataService = new Member_DataServiceImpl();
        }
        return member_dataService;
    }

    private Member_DataServiceImpl() {
        dataFactory = new DataFactoryImpl();
        memberDataHelper = dataFactory.getMemberDataHelper();
    }

    /**
     * 新增会员
     *
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean addMember(MemberPO po) throws RemoteException {
        return memberDataHelper.addMember(po);
    }

    /**
     * 删除会员
     *
     * @param po
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean deleteMember(MemberPO po) throws RemoteException {
        return memberDataHelper.deleteMember(po);
    }

    /**
     * 更新会员信息
     *
     * @param po
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean updateMember(MemberPO po) throws RemoteException {
        return memberDataHelper.updateMember(po);
    }

    /**
     * 根据ID查找会员
     *
     * @param ID
     * @return 与ID对应的会员信息
     * @throws RemoteException
     */
    public MemberPO findMemberByID(String ID) throws RemoteException {
        MemberPO memberPO = memberDataHelper.findMemberByID(ID);

        if (memberPO == null) {
            return null;
        }

        return (MemberPO) memberPO.clone();
    }

    /**
     * 获取所有会员信息
     *
     * @return 所有会员的列表
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<MemberPO> findAllMemebers() throws IOException, ClassNotFoundException {
        List<MemberPO> memberPOList = memberDataHelper.findAllMembers();

        if (null == memberPOList || memberPOList.isEmpty()) {
            return null;
        }

        List<MemberPO> list = CopyUtil.deepCopy(memberPOList);

        return list;
    }
}
