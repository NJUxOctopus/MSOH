package dataservice.comment_dataservice;

import java.util.List;

import po.CommentPO;
import util.ResultMessage;

public interface Comment_DataService {
	public ResultMessage add (CommentPO po);
	
	public List<CommentPO> getCommentPo (String HotelID);
}
