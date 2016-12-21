package util.sort;

import vo.CommentVO;

import java.util.Comparator;

/**
 * Created by Pxr on 2016/12/21.
 */
public class sortCommentByDate implements Comparator<CommentVO> {
    @Override
    public int compare(CommentVO o1, CommentVO o2) {
        return o2.commentTime.compareTo(o1.commentTime);
    }
}
