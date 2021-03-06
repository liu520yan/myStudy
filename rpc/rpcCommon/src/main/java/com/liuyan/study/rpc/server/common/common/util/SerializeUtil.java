package com.liuyan.study.rpc.server.common.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by lei.yu on 2017/12/25.
 */
public class SerializeUtil {

    private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }

        byte[] result = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream(128);
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.flush();
            os.close();
            bos.close();
            result = bos.toByteArray();
        } catch (Exception e) {
            logger.error("Serialize error:", e);
        } finally {
            close(os);
            close(bos);
        }
        return result;
    }

    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    public static <T> T deserialize(byte[] in, Class<T> requiredType) {
        Object result = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null && in.length != 0) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                result = is.readObject();
            }
        } catch (Exception e) {
            logger.error("Deserialize error:", e);
        } finally {
            close(is);
            close(bis);
        }
        return (T) result;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("Close stream error:", e);
            }
        }
    }
}
