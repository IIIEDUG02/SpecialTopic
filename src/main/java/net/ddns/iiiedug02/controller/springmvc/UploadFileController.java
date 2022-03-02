package net.ddns.iiiedug02.controller.springmvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import net.ddns.iiiedug02.model.beans.Picture;
import net.ddns.iiiedug02.model.services.PictureService;

@Controller
public class UploadFileController {
  @Autowired
  private PictureService pService;

  @RequestMapping(path = "UploadFileController", method = RequestMethod.POST,
      produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String uploadFileController(@RequestParam("myfile") MultipartFile mf,
      HttpServletRequest request) throws IOException {
    String saveDirPath = request.getSession().getServletContext().getRealPath("/") + "uploads";
    File dirPath = new File(saveDirPath);
    dirPath.mkdirs();

    mf.transferTo(new File(dirPath + "/" + mf.getOriginalFilename()));
    SaveToDB(mf.getOriginalFilename(), dirPath + "/" + mf.getOriginalFilename());
    return dirPath + "/" + mf.getOriginalFilename() + "已上傳";
  }

  @RequestMapping(path = "uploadToDB", method = RequestMethod.POST,
      produces = "text/plain;charset=UTF-8")
  @ResponseBody
  public String uploadToDB(@RequestParam("myfile") MultipartFile mf, HttpServletRequest request)
      throws IOException {
    Picture p1 = new Picture();
    p1.setFilename(mf.getOriginalFilename());
    p1.setPicture(mf.getBytes());
    pService.upload(p1);
    return mf.getOriginalFilename() + "已上傳資料庫";
  }

  private void SaveToDB(String fileName, String savePath) throws IOException {
    FileInputStream is1 = new FileInputStream(savePath);
    byte[] b1 = new byte[is1.available()];
    is1.read(b1);
    is1.close();

    Picture p = new Picture();
    p.setFilename(fileName);
    p.setPicture(b1);
    pService.upload(p);

  }

}
