package ui.controller;

import businesslogic.hotel_bl.Hotel;
import businesslogicservice.hotel_blservice.Hotel_BLService;
import ui.view.controllerservice.CommentHotel;
import util.ResultMessage;
import vo.CommentVO;
import vo.OrderVO;

import java.rmi.RemoteException;

/**
 * Created by apple on 16/11/10.
 */
public class CommentHotelController implements CommentHotel{
    private Hotel_BLService hotel_blService;

    public CommentHotelController(){
        hotel_blService = new Hotel();
    }
    public ResultMessage addComment(CommentVO commentVO, OrderVO orderVO) throws RemoteException {
        return hotel_blService.addComment(commentVO, orderVO);
    }
}
