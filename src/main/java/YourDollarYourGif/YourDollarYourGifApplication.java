package YourDollarYourGif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YourDollarYourGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourDollarYourGifApplication.class, args);
    }

}
