package com.flexible.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoService {

@RequestMapping("/getInfo")
 public @ResponseBody String getInfo(){
    return "hello world";
}
}
