package util.sort;

import vo.RoomVO;

import java.util.Comparator;

/**
 * Created by Pxr on 16/12/13.
 */
public class sortRoomByNum implements Comparator<RoomVO> {
    @Override
    public int compare(RoomVO o1, RoomVO o2) {
        return o1.leftRooms - o2.leftRooms;
    }
}
