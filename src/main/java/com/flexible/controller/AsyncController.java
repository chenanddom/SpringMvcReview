package com.flexible.controller;

import com.flexible.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: chendom
 * Date: 2018-12-25
 * Time: 14:30
 */
@Controller
public class AsyncController {

    @Autowired
    PushService pushService;

    /**
     * 异步任务的实现通过控制器从另外一个线程返回一个DeferredResult，这里的DeferredResult
     * 是从pushService中获取
     * @return
     */
    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> defferredCall() {
        return pushService.getAsyncUpdate();
    }

}
