package sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

   @Autowired
   private HelloService service;

   @GetMapping("/hello")
   public String hello() {
      log.info("Hello");
      return "Hello from " + service.getName();
   }

   @GetMapping("/isAlive")
   public boolean isAlive() {
      log.info("Alive");
      return true;
   }
 
}