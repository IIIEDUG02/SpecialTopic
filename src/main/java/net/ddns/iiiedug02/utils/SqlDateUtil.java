package net.ddns.iiiedug02.utils;

import java.text.SimpleDateFormat;

/**
 * 工具物件，將yyyy-MM-dd的日期格式轉換成SQL的Date物件
 */
public class SqlDateUtil {
  public static java.sql.Date strToDate(String strDate) {
    String str = strDate;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date d = null;
    try {
      d = format.parse(str);
    } catch (Exception e) {
      e.printStackTrace();
    }
    java.sql.Date date = new java.sql.Date(d.getTime());
    return date;
  }
}
