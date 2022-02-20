package net.ddns.iiiedug02.utils;

import java.text.SimpleDateFormat;

/**
 * DAO物件，針對MemberBean對資料庫做增刪改查
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
