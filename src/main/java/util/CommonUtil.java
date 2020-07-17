package util;

import java.util.ResourceBundle;

/**
 * 工具类
 */
public class CommonUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");

    /**
     * 获得每页的记录条数
     * @return
     */
    public static int getPageSize(){
        return Integer.parseInt(bundle.getString("pageSize"));
    }

}
