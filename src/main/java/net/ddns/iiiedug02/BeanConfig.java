package net.ddns.iiiedug02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ecpay.payment.integration.AllInOne;
import net.ddns.iiiedug02.helpers.ArticleHelper;
import net.ddns.iiiedug02.util.UniversalTool;

@Configuration
@ComponentScan(basePackages = "net.ddns.iiiedug02")
public class BeanConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UniversalTool universalTool() {
        return new UniversalTool();
    }

    @Bean
    public AllInOne allInOne() {
        return new AllInOne("");
    }

    @Bean
    public ArticleHelper articleHelper() {
        return new ArticleHelper();
    }

}
