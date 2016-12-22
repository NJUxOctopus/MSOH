package ui.view.presentation.util;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import rmi.RemoteHelper;
import util.ImageHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

/**
 * Created by island on 2016/12/21.
 */
public class ImageController {
    private WritableImage wr = null;

    public WritableImage loadImage(String urls, int width, int height){
        try {
            ImageHelper imageHelper = RemoteHelper.getInstance().getImageHelper();
            ImageIcon imageIcon = imageHelper.getImage(urls);
            Image image = imageIcon.getImage().getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);

            BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D gc = buf.createGraphics();
            buf = gc.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            gc = buf.createGraphics();
            gc.drawImage(image, 0, 0, null);

            if (buf != null) {
                wr = new WritableImage(buf.getWidth(), buf.getHeight());
                PixelWriter pw = wr.getPixelWriter();
                for (int x = 0; x < buf.getWidth(); x++) {
                    for (int y = 0; y < buf.getHeight(); y++) {
                        pw.setArgb(x, y, buf.getRGB(x, y));
                    }
                }
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return wr;
    }
}
