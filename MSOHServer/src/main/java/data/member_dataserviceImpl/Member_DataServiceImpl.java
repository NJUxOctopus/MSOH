package data.member_dataserviceImpl;

import DataHelper.DataFactory;
import DataHelper.memberDataHelper.MemberDataHelper;
import DataHelperImpl.DataFactoryImpl;
import dataservice.member_dataservice.Member_DataService;
import po.MemberPO;
import util.DataUtil.CopyUtil;
import util.DataUtil.EncryptionUtil;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqh on 2016/12/2.
 */
public class Member_DataServiceImpl implements Member_DataService {
    private MemberDataHelper memberDataHelper;

    private DataFactory dataFactory;

    private static Member_DataServiceImpl member_dataService;

    private static final String key = "20162017";

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
        po.setID(EncryptionUtil.encode(key, po.getID()));

        return memberDataHelper.addMember(po);
    }

    /**
     * 删除会员
     *
     * @param memberPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean deleteMember(MemberPO memberPO) throws RemoteException {
        // 数据库中的主键必须存在，否则删除失败
        if (memberPO.getID() == null) {
            return false;
        }

        memberPO.setID(EncryptionUtil.encode(key, memberPO.getID()));

        return memberDataHelper.deleteMember(memberPO);
    }

    /**
     * 更新会员信息
     *
     * @param memberPO
     * @return 是否成功
     * @throws RemoteException
     */
    public boolean updateMember(MemberPO memberPO) throws RemoteException {
        // 数据库中的主键必须存在，否则更新失败
        if (memberPO.getID() == null) {
            return false;
        }

        memberPO.setID(EncryptionUtil.encode(key, memberPO.getID()));

        return memberDataHelper.updateMember(memberPO);
    }

    /**
     * 根据ID查找会员
     *
     * @param ID
     * @return 与ID对应的会员信息
     * @throws RemoteException
     */
    public MemberPO findMemberByID(String ID) throws RemoteException {
        ID = EncryptionUtil.encode(key, ID);

        MemberPO memberPO = memberDataHelper.findMemberByID(ID);

        if (memberPO != null) {
            memberPO.setID(EncryptionUtil.decode(key, memberPO.getID()));
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
    public List<MemberPO> findAllMembers() throws IOException, ClassNotFoundException {
        List<MemberPO> memberPOList = memberDataHelper.findAllMembers();

        if (null == memberPOList || memberPOList.isEmpty()) {
            return new ArrayList<MemberPO>();
        } else {
            for (MemberPO member : memberPOList) {
                member.setID(EncryptionUtil.decode(key, member.getID()));
            }
        }

        List<MemberPO> list = CopyUtil.deepCopy(memberPOList);

        return list;
    }
}
