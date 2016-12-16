package util;

import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

/**
 * Created by zqh on 2016/12/16.
 */
public class ImageHelperImpl implements ImageHelper {
    private static ImageHelperImpl imageHelper;

    private ImageHelperImpl() {
    }

    public static ImageHelperImpl getInstance() {
        if (imageHelper == null) {
            imageHelper = new ImageHelperImpl();
        }

        return imageHelper;
    }

    /**
     * 根据路径获得图片
     *
     * @param imgPath
     * @return 所在路径的图片
     * @throws RemoteException
     */
    public BufferedImage getImage(String imgPath) throws RemoteException {
        return null;
    }

    /**
     * 保存图片
     *
     * @param image
     * @return 图片路径
     * @throws RemoteException
     */
    public String saveImage(BufferedImage image) throws RemoteException {
        return "";
    }
}
