package businesslogic.member_bl;

import businesslogic.bl_Factory.Abstract_BLFactory;
import businesslogic.bl_Factory.Default_BLFactory;
import businesslogicservice.member_blservice.MemberLevel_BLService;
import dataservice.memberlevel_dataservice.MemberLevel_DataService;
import po.MemberLevelPO;
import po.MemberPO;
import rmi.RemoteHelper;
import util.ResultMessage;
import vo.MemberLevelVO;
import vo.MemberVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/11/10.
 */
public class MemberLevel implements MemberLevel_BLService {
    private MemberLevel_DataService memberLevel_dataService = RemoteHelper.getInstance().getMemberLevelDataService();
    private Abstract_BLFactory abstract_blFactory = new Default_BLFactory();


    /**
     * 修改会员等级制度
     *
     * @param memberLevelVO
     * @return
     * @throws RemoteException
     */
    public ResultMessage modifyMemberLevel(MemberLevelVO memberLevelVO) throws RemoteException {
        if (memberLevelVO.num < 0)
            //若数量小于0
            return ResultMessage.DataFormatWrong;
        if (memberLevelVO.creditBoundaries == null)
            //若未填写信用界限
            return ResultMessage.Blank;
        else {
            String creditBoundaries = "";
            for (int i = 0; i < memberLevelVO.creditBoundaries.length; i++) {
                if (i != memberLevelVO.creditBoundaries.length - 1)
                    creditBoundaries += memberLevelVO.creditBoundaries[i] + ";";
                else
                    creditBoundaries += memberLevelVO.creditBoundaries[i];
            }
            String discountList = "";
            for (int i = 0; i < memberLevelVO.discountList.size(); i++) {
                if (i != memberLevelVO.creditBoundaries.length - 1)
                    discountList += memberLevelVO.discountList.get(i) + ";";
                else
                    discountList += memberLevelVO.discountList.get(i);
            }
            MemberLevelPO memberLevelPO = memberLevel_dataService.getMemberLevel();
            memberLevelPO.setCreditBoundaries(creditBoundaries);
            memberLevelPO.setFrameDate(memberLevelVO.frameDate);
            memberLevelPO.setNum(memberLevelVO.num);
            memberLevelPO.setDiscountList(discountList);
            Member member = abstract_blFactory.createMember();
            MemberUtil memberUtil = abstract_blFactory.createMemberUtil();
            if (memberLevel_dataService.updateMemberLevel(memberLevelPO)) {
                List<MemberVO> memberVOList = memberUtil.getAllMembers();
                for (MemberVO memberVO : memberVOList) {
                    member.changeGrade(memberVO.ID);
                }
                return ResultMessage.MemberLevel_ModifyMemberLevelSuccess;
            } else
                return ResultMessage.Fail;
        }
    }

    /**
     * 获取会员等级制度
     *
     * @return
     * @throws RemoteException
     */
    public MemberLevelVO getMemberLevel() throws RemoteException {
        MemberLevelPO memberLevelPO = memberLevel_dataService.getMemberLevel();
        String[] creditBoundaries = memberLevelPO.getCreditBoundaries().split(";");
        int[] boundaries = new int[creditBoundaries.length];
        for (int i = 0; i < creditBoundaries.length; i++) {
            boundaries[i] = Integer.parseInt(creditBoundaries[i]);
        }
        List<String> discountList = new ArrayList<String>();
        String[] str = memberLevelPO.getDiscountList().split(";");
        for (int i = 0; i < str.length; i++) {
            discountList.add((Double.parseDouble(str[i]) + ""));
        }
        return new MemberLevelVO(memberLevelPO.getMemberlevelID() + "", memberLevelPO.getFramerName(), memberLevelPO.getFrameDate(), memberLevelPO.getNum(),
                boundaries, discountList);
    }
}
