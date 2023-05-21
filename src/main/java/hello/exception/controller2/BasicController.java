package hello.exception.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping("basic/main2")
    public String basicMain(){
        return "basic/main2";
    }
}
