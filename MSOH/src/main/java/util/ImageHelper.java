package util;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 与服务器端传输图片的工具类
 * <p>
 * Created by zqh on 2016/12/16.
 */
public interface ImageHelper extends Remote {
    // 根据图片地址得到图片
    public ImageIcon getImage(String imgPath) throws RemoteException;

    // 保存图片
    public String saveImage(ImageIcon img) throws RemoteException;
}
