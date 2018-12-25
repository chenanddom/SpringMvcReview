package com.flexible.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: chendom
 * Date: 2018-12-25
 * Time: 14:33
 */
@Service
public class PushService {

private DeferredResult<String> deferredResult;

    public DeferredResult<String> getAsyncUpdate() {
        deferredResult = new DeferredResult<>();
        return deferredResult;
    }
    @Scheduled(fixedRate = 5000)
    public void refresh(){
        if (deferredResult!=null){
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }
}
