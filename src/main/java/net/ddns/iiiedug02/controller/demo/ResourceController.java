package net.ddns.iiiedug02.controller.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

  @Autowired
  private ResourceLoader resourceLoader;

  @Value("classpath:static/image/Hibernate.png")
  private Resource resource1;

  @GetMapping(path = "resourceValueController", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] rValueController() throws IOException {
    File rFile = resource1.getFile();
    System.out.println("檔案位置: " + rFile.getAbsolutePath());
    System.out.println("檔案大小: " + rFile.length() + " bytes");
    InputStream ris = resource1.getInputStream();
    return IOUtils.toByteArray(ris);
  }



  @GetMapping(path = "resourceLoaderController", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] rLoaderController() throws IOException {
    Resource resource = resourceLoader.getResource("classpath:static/image/Hibernate.png");
    if (!resource.exists()) {
      return null;
    }
    File rFile = resource.getFile();
    System.out.println("檔案位置: " + rFile.getAbsolutePath());
    System.out.println("檔案大小: " + rFile.length() + " bytes");
    InputStream ris = resource.getInputStream();
    return IOUtils.toByteArray(ris);
  }


  @GetMapping(path = "resourceController", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] rController() throws IOException {
    ClassPathResource resource = new ClassPathResource("static/image/git.png");
    if (!resource.exists()) {
      return null;
    }
    File rFile = resource.getFile();
    System.out.println("檔案位置: " + rFile.getAbsolutePath());
    System.out.println("檔案大小: " + rFile.length() + " bytes");
    InputStream ris = resource.getInputStream();
    return IOUtils.toByteArray(ris);
  }
}

