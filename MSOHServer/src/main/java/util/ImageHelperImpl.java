package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Calendar;

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
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 保存图片
     *
     * @param img
     * @return 图片路径
     * @throws RemoteException
     */
    public String saveImage(BufferedImage img) throws RemoteException {
        int w = img.getWidth();
        int h = img.getHeight();

        String savePath = "F:/MSOHPics/";
        String imgName = generateImgPath(w, h);
        String postfix = ".png";

        BufferedImage toSave = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

        // 用来画出来要保持的图片，及上面传递过来的Image变量
        Graphics g = toSave.getGraphics();
        try {
            g.drawImage(img, 0, 0, null);
            // 将BufferedImage变量写入文件中
            ImageIO.write(toSave, "png", new File(savePath + imgName + postfix));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savePath + imgName + postfix;
    }

    private String generateImgPath(int w, int h) {
        int randomNum = (int) (Math.random() * 1000);

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_YEAR);

        int pathNum = (int) ((w * h) / 100) - randomNum - day;

        return String.valueOf(pathNum);
    }
}
