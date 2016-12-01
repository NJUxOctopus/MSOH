package DataHelper;

import po.MemberLevelPO;

/**
 * Created by zqh on 2016/11/24.
 */
public interface MemberLevelDataHelper {
    // 更新会员等级制度
    public boolean updateMemberLevel(MemberLevelPO memberLevelPO);

    // 获取会员等级制度
    public MemberLevelPO getMemberLevel();
}
