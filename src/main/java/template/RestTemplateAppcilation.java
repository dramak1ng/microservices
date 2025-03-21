package template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import template.service.UserService;

@Slf4j
@SpringBootApplication
public class RestTemplateAppcilation {
    public static void main(String[] args) {


        ConfigurableApplicationContext context = SpringApplication.run(RestTemplateAppcilation.class);
        UserService userService = context.getBean(UserService.class);

        String complecteTask = userService.task();
        log.info(complecteTask);
        context.close();

    }
    }





