package init;

import DataHelper.hotelDataHelper.RoomDataHelper;
import DataHelperImpl.hotelDataHelperImpl.RoomDataHelperSQLImpl;
import po.RoomPO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zqh on 2016/12/10.
 */
public class DailyRoomUpdater {
    private RoomDataHelper roomDataHelper=new RoomDataHelperSQLImpl();

    public DailyRoomUpdater(){
        updateDailyRoom(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * 更新每日房间信息：删除前一天的所有房间，新增未来30天的房间，保证每个酒店都可以搜索到30天的酒店
     * @param timestamp
     */
    private void updateDailyRoom(Timestamp timestamp){
        List<RoomPO> allRoomList=roomDataHelper.getAllRooms();

        // 删除昨日房间
        if(deleteYesterdayRooms(timestamp,allRoomList)){
            System.out.println("Octopus: 昨日房间信息不存在，无需更新");
        }else{
            System.out.println("Octopus: 服务器房间信息已更新");
        }

        // 新增第30天的房间
        if(!addRoomsIn30DaysAfter(timestamp,allRoomList)){
            System.out.println("Octopus: 第30天的房间信息已存在，无需更新");
        }else{
            System.out.println("Octopus: 服务器房间信息已更新");
        }
    }

    /**
     * 删除昨天的房间
     * @param timestamp
     * @param allRoomList
     */
    private boolean deleteYesterdayRooms(Timestamp timestamp,List<RoomPO> allRoomList){
        boolean deletedBeforeThisTime=true;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        Date date=new Date();

        // 如果昨天的还在，先删除所有昨天的
        // 先根据今天的日期，得到昨天日期
        date=timestamp;
        cal.setTime(date);
        cal.add(Calendar.DATE,-1);
        // 得到昨天日期的String形式
        String yesterdayStr=simpleDateFormat.format(cal.getTime());
        // 遍历所有房间，将日期转化为String
        // 比较房间的string和日期的string，如果相同，删掉昨天的房间
        // 如果没有符合的，则不删除
        String roomDateStr="";
        for(RoomPO room:allRoomList){
            roomDateStr=simpleDateFormat.format(room.getDate());
            if(roomDateStr.equals(yesterdayStr)){
                roomDataHelper.deleteRoom(room);
                deletedBeforeThisTime=false;
            }
        }

        return deletedBeforeThisTime;
    }

    /**
     * 增加第30天的房间信息
     * @param timestamp
     * @param allRoomList
     * @return 第30天的房间信息是否添加成功
     */
    private boolean addRoomsIn30DaysAfter(Timestamp timestamp,List<RoomPO> allRoomList){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=Calendar.getInstance();
        Date date30=new Date();
        Date date29=new Date();
        // 先根据今天的日期，得到30天后的日期
        date30=timestamp;
        cal.setTime(date30);
        cal.add(Calendar.DATE,30);
        // 将30天后的日期转换为string
        String thirtyDayStr=simpleDateFormat.format(cal.getTime());
        // 再根据今天的日期，得到29天后的日期
        date29=timestamp;
        cal.setTime(date29);
        cal.add(Calendar.DATE,29);
        // 将29天后的日期转换为string
        String twentyNineDayStr=simpleDateFormat.format(cal.getTime());
        // 遍历所有房间，获得第29天的房间信息
        List<RoomPO> twentyNineDayRoomInfo=new ArrayList<RoomPO>();
        String roomDateStr="";
        for(RoomPO roomPO:allRoomList){
            roomDateStr=simpleDateFormat.format(roomPO.getDate());
            if(roomDateStr.equals(twentyNineDayStr)){
                twentyNineDayRoomInfo.add(roomPO);
            }else if(roomDateStr.equals(thirtyDayStr)){
                return false;
            }
        }
        // 生成30天后的房间信息
        for(RoomPO roomPO:twentyNineDayRoomInfo){
            RoomPO thirtyDayRoom=new RoomPO(roomPO.getHotelID(),roomPO.getRoomType(),0,0,roomPO.getLeftRooms()+roomPO.getOccupiedRooms()+roomPO.getReservedRooms(),roomPO.getPrice(),Timestamp.valueOf(thirtyDayStr+" 00:00:00"));
            roomDataHelper.addRoom(thirtyDayRoom);
        }

        return true;
    }
}
