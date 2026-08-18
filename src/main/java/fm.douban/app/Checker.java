package fm.douban.app;

import java.io.*;

import org.spriingframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service 
  public class Checker implements InitializingBean/** 这里的接口是spring自己提供的，接口实现类需要重写*/{
@Override
    public void afterPropertiesSet()throws Exception{
Thread thread = new Thread(new Runnable(){
  @Override
  public void run(){
try{
  Thread.sleep(6000);
    }catch(InterruptedException e){
  e.printStackTrace();
    }
try{
  System.exit(0);
}catch(Exception e){
  
}
    
  }
});
thread.start();
      
    }

    
  }
