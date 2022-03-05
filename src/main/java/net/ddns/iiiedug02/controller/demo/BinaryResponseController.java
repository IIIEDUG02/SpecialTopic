package net.ddns.iiiedug02.controller.demo;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BinaryResponseController {

  @RequestMapping(path = "ioAction", method = RequestMethod.GET)
  public void processImage(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    InputStream is1 =
        request.getServletContext().getResourceAsStream("/WEB-INF/resources/images/git.png");
    IOUtils.copy(is1, response.getOutputStream());
  }

  @ResponseBody
  @RequestMapping(path = "ioActionByteArray.mov", method = RequestMethod.GET,
      produces = "text/plain;charset=UTF-8")
  public byte[] processImageByteArray(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    InputStream is1 =
        request.getServletContext().getResourceAsStream("/WEB-INF/resources/images/movie1.mov");
    return IOUtils.toByteArray(is1);
  }

}
