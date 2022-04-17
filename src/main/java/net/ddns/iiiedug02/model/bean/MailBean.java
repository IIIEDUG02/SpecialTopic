package net.ddns.iiiedug02.model.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MailBean {
    private String toAddress;
    private String subject;
    private String msg;
}
