package jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import util.SerizableUtil;

import java.io.*;
import java.util.List;
import java.util.ResourceBundle;

public class JedisUtil {
    private static JedisPool pool;
    private static ResourceBundle bundle = ResourceBundle.getBundle("redis");
            static{
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(Integer.parseInt(bundle.getString("maxTotal")));
                config.setMaxIdle(Integer.parseInt(bundle.getString("maxIdle")));
                config.setMinIdle(Integer.parseInt(bundle.getString("minIdle")));
                String host = bundle.getString("host");
                int port =Integer.parseInt(bundle.getString("port"));
                pool = new JedisPool(config,host,port);
            }

    /**
     * 获取连接
     * @return
     */
    public static Jedis getJedis(){
        Jedis jedis = null;

        jedis =  pool.getResource();
//        System.out.println(jedis);
        return jedis;
    }

    /********************String**************************/
    /**
     * 添加string类型
     * @param key
     * @param value
     */
    public static void set(String key,String value){
        Jedis jedis = getJedis();
        jedis.set(key,value);
        jedis.close();
    }

    /**
     * 获取string
     * @param key
     * @return
     */
    public static String get(String key){
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    public static void mset(String ...keyandValues){
        Jedis jedis = getJedis();
        jedis.mset(keyandValues);
        jedis.close();
    }

    public static List<String> mget(String ...keys){
        Jedis jedis = getJedis();
        List<String> list = jedis.mget(keys);
        jedis.close();
        return list;

    }

    public static void append(String key,String value){
        Jedis jedis = getJedis();
        jedis.append(key,value);
        jedis.close();
    }

    public static long strlen(String key){
        Jedis jedis = getJedis();
        long len = jedis.strlen(key);
        jedis.close();
        return len;
    }

    public static void expire(String key, int second){
        Jedis jedis = getJedis();
//        if(jedis.exists(key)){
        jedis.expire(key, second);
//        }
//        else{
//            throw new RuntimeException("redis的键不存在！");
//        }
        jedis.close();
    }

    public static void set(String key,Object value,int second){
        set(key,value);
        expire(key,second);
    }

    /**
     * 序列化存储
     * @param key
     * @param value
     */
    public static void set(String key,Object value){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream =new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(value);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            Jedis jedis = getJedis();
            jedis.set(key.getBytes(),bytes);
            jedis.close();
            outputStream.flush();
            outputStream.close();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 反序列化获取
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T get(String key,Class<T> tClass){
        T t =null;
        Jedis jedis = getJedis();
        byte[] bytes = jedis.get(key.getBytes());
        if (bytes !=null) {
            try {
                ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(arrayInputStream);
                t = (T) objectInputStream.readObject();
                jedis.close();
                objectInputStream.close();
                arrayInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return t;

    }

    /******************hash类型*******************/

    public static void hset(String key,String field,String value){
        Jedis jedis = getJedis();
        jedis.hset(key,field,value);
        jedis.close();
    }

    /**
     * 序列化写入
     * @param key
     * @param field
     * @param value
     * @param second
     */
    public static void hset(String key,String field,Object value,int second){
        Jedis jedis = getJedis();
        byte[] bytes = SerizableUtil.serizObject(value);
        jedis.hset(key.getBytes(),field.getBytes(),bytes);
        expire(key,second);
        jedis.close();
    }

    /**
     * 序列化读取对象
     * @param key
     * @param field
     * @return
     */
    public static Object hgetObj(String key,String field){
        Jedis jedis = getJedis();
        byte[] bytes = jedis.hget(key.getBytes(),field.getBytes());
        Object obj = null;
        if (bytes!=null) {
            obj = SerizableUtil.reverSerizObject(bytes);
            jedis.close();
        }
        return obj;
    }
}
