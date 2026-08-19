//整个checker程序是为了测试启动是否成功，6s内没有报错就说明启动正常，这个相当于自己又开了一个线程然后控制项目生命周期
//main（）-->springapplication.run()-->扫描@Service-->创建Checker对象-->执行afterRropertiesSet()-->创建Thread对象-->
//thread.start()-->开启新线程-->等待6s-->system.exit(0)-->程序关闭
package fm.douban.app;

import java.io.*;

import org.spriingframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service 
  public class Checker implements InitializingBean/** 这里的接口是spring自己提供的，接口实现类需要重写*/{
@Override//这个方法的作用是bean创建完成，属性注入完成后自动执行
    public void afterPropertiesSet()throws Exception{//spring自动调用，抛出异常
//创建一个线程，Runable接口()表示创建一个任务
      Thread thread = new Thread(new Runnable(){
  @Override
  public void run(){
try{
  Thread.sleep(6000);//让当前线程睡眠6s
    }catch(InterruptedException e){
  e.printStackTrace();
    }
try{
  System.exit(0);//关闭java程序
}catch(Exception e){
  
}
    
  }
});
thread.start();//启动线程
      
    }

    
  }
//Spring启动

   //↓  

//创建Checker对象

 //  ↓

//@Autowired注入完成

 // ↓

//执行afterPropertiesSet()
