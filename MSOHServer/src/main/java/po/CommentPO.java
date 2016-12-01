package po;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author zqh
 */
@Entity
@Table(name = "comment", schema = "msoh_database")
public class CommentPO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 评价编号,供数据库存储使用，无实际意义
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    @Column(name = "commentID")
    private int commentId;
    // 评分
    @Column(name = "score")
    private double score;
    // 评价具体内容
    @Column(name = "comment")
    private String comment;
    // 评价客户姓名
    @Column(name = "customerName")
    private String customerName;
    // 评价客户ID
    @Column(name = "customerID")
    private String customerID;
    // 评价酒店的名字
    @Column(name = "hotelName")
    private String hotelName;
    // 评价酒店ID
    @Column(name = "hotelID")
    private String hotelID;
    // 评价订单ID
    @Column(name = "orderID")
    private String orderID;
    // 评价时间
    @Column(name = "commentTime")
    private Timestamp commentTime;


    public CommentPO() {
    }

    public CommentPO(double score, String comment, String customerName, String customerID, String hotelName, String hotelID,
                     String orderID, Timestamp commentTime) {
        this.score = score;
        this.comment = comment;
        this.customerName = customerName;
        this.customerID = customerID;
        this.hotelName = hotelName;
        this.hotelID = hotelID;
        this.orderID = orderID;
        this.commentTime = commentTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
