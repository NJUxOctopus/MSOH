package DataHelper.hotelDataHelper;

import po.CommentPO;

import java.util.List;

/**
 * Created by zqh on 2016/12/1.
 */
public interface CommentDataHelper {
    public boolean addComment(CommentPO commentPO);

    public List<CommentPO> getCommentsByHotel(String hotelID);

    public CommentPO getCommentByOrder(String orderID);
}
