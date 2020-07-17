package util;

import java.io.*;

/**
 * 序列化工具类
 */
public class SerizableUtil {
    /**
     * 序列化
     * @param value
     * @return
     */
    public static byte[] serizObject(Object value){
        byte[] bytes = null;
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(arrayOutputStream);
            outputStream.writeObject(value);
            bytes = arrayOutputStream.toByteArray();
            arrayOutputStream.flush();
            arrayOutputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 反序列化
     * @param bytes
     */
    public static Object reverSerizObject(byte[] bytes){
        Object obj = null;
        if (bytes!=null) {
            try {
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
                obj = inputStream.readObject();
                arrayInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
