package com.flexible.controller;

import com.flexible.bean.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: chendom
 * Date: 2018-12-25
 * Time: 9:10
 */
@Controller
public class ConverterController {

@RequestMapping(value = "/convert",produces = {"application/x-demo"})
public @ResponseBody DemoObj converter(@RequestBody DemoObj demoObj){
    return demoObj;
}
}
