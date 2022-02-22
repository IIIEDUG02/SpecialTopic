package net.ddns.iiiedug02.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class RequestHeadersUtil {
  private HttpServletRequest request;

  public RequestHeadersUtil(HttpServletRequest request) {
    this.request = request;
  }

  public Map<String, String> getHeaders() {
    HashMap<String, String> reseultMap = new HashMap<String, String>();

    if (request == null) {
      return null;
    }

    Enumeration<?> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      reseultMap.put(key, value);
    }
    return reseultMap;
  }
}
