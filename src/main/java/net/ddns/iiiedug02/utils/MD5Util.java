package net.ddns.iiiedug02.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
  public String StringToMD5(String password) {
    byte[] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    byte[] theMD5digest = md.digest(bytesOfPassword);
    StringBuilder sb = new StringBuilder();
    for (byte b : theMD5digest) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }
}
