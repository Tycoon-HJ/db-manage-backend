package org.chat.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeat {

    @GetMapping("/getHeartBeat")
    public String heartBeat() {
        return "77777777这是一个heart";
    }
}
