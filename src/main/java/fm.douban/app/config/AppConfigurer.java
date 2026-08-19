//这是一个springmvc配置类，作用是把你写的拦截器注册到springmvc当中，并规定哪些请求需要拦截，哪些请求需要放行

package fm.douban.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fm.douban.app.interceptor.UserInterceptor;
//这个注解说的是spring配置类，spring启动时会扫描它
@Configuration
//下面是spring自己提供的接口，意思是我要修改springmvc默认的一些设置，比如添加拦截器各种
public class AppConfigurer implements WebMvcConfigurer {
//依旧重写方法环节，可以理解成自己改配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // 仅演示，设置所有 url 都拦截
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**")//这说的是所有页面都要遵循以下规则
                .excludePathPatterns("/app/authenticate") // 登录操作不需要登录
                .excludePathPatterns("/app/login")        // 登录页面不需要登录
                .excludePathPatterns("/css/**")           // 静态资源为文件不需要登录，是说css类的文件全部都不需要登陆不然就会显示不出效果
                .excludePathPatterns("/error");           // 系统错误页面不需要登录
    }
}

///浏览器请求
    ↓
//Spring MVC收到请求
    //↓
//检查拦截规则
    //↓
///app/songlist
//符合 /**
   // ↓
//进入 UserInterceptor
  //  ↓
//检查session
   // ↓
//有用户
 //  ↓
//继续Controller
 //  ↓
//返回页面
