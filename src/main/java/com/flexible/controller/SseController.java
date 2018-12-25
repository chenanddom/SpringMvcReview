package com.flexible.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: chendom
 * Date: 2018-12-25
 * Time: 12:30
 */
@Controller
public class SseController {
    /**
     * 这里使用输出的媒体类型为text/event-stream，也是服务器端SSE的支持。
     * @return
     */
    @RequestMapping(value="/push",produces="text/event-stream") //1
    public @ResponseBody String push(){
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3" + r.nextInt() +"\n\n";
    }
}
