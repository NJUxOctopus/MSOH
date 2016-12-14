package util;

import java.io.*;
import java.util.List;

/**
 * 列表深拷贝的工具包
 * <p>
 * Created by zqh on 2016/12/1.
 */
public class CopyUtil {

    public static <T> List<T> deepCopy(List<T> list) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream output = new ObjectOutputStream(byteOut);
        output.writeObject(list);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream input = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")

        List<T> dest = (List<T>) input.readObject();

        return dest;

    }
}
