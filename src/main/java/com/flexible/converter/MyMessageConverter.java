package com.flexible.converter;

import com.flexible.bean.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: chendom
 * Date: 2018-12-25
 * Time: 8:39
 */
//1.此处集成AbstractHttpMessageConverter接口实现自定义的HttpMessageConverter
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {
    //2.新建一个自定义的媒体类型application/x-demo
   public MyMessageConverter(){
       super(new MediaType("application","x-demo", Charset.forName("UTF-8")));
   }

    //3重写readInternal,读取请求的数据，代码表明我们处理由"-"间隔开的数据，最后转换成DemoObj对象
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
      String temp = StreamUtils.copyToString(httpInputMessage.getBody(),Charset.forName("UTF-8"));
      String[] tempArr = temp.split("-");
        return new DemoObj(Long.valueOf(tempArr[0]),tempArr[1]);
    }
    //4.表明MyMessageConverter只处理DemoObj这个类
    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }
    //5重写writeInternal，处理如何输出数据到response，在这里加上了test
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
    String out = "test:"+demoObj.getId()+" * "+demoObj.getName();
    httpOutputMessage.getBody().write(out.getBytes());
    }
}
