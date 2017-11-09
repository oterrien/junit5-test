package sample;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Getter
    private final String name = "HelloService";
}
