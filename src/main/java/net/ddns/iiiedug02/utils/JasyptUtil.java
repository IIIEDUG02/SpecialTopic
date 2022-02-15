package net.ddns.iiiedug02.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptUtil {
  StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

  public JasyptUtil() {

    String encryptorAlgorithm = "PBEWithMD5AndTripleDES";
    String encryptorPassword = "X6nVP89KZ8Ar";

    encryptor.setAlgorithm(encryptorAlgorithm);
    encryptor.setPassword(encryptorPassword);
  }

  public String encrypt(String text) {
    return encryptor.encrypt(text);
  }

  public String decrypt(String text) {
    return encryptor.decrypt(text);
  }

}
