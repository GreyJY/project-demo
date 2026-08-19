//这个是spring session的配置类，配置session如何保存用户登录信息，以及cookie如何保存session ID
package fm.douban.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import java.util.concurrent.ConcurrentHashMap;

@Configuration//spring配置类注解
@EnableSpringHttpSession//开启spring Session功能
public class SpringHttpSessionConfig {
  //cookie配置，自己定义cookie规则
    @Bean
    public CookieSerializer cookieSerializer() {
      //创建序列化器，创建cookie，读取cookie，设置cookie
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
      //设置cookie名字
        serializer.setCookieName("JSESSIONID");
        // 用正则表达式配置匹配的域名，可以兼容 localhost、127.0.0.1 等各种场景
      serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
      //表示cookie对所有网站有效
        serializer.setCookiePath("/");
      //这里设置false是可以让js读取cookie的，一般用true
        serializer.setUseHttpOnlyCookie(false);
        // 最大生命周期的单位是秒
        serializer.setCookieMaxAge(48 * 60 * 60);
        return serializer;
    }
//Session储存位置
    @Bean
  //多线程服务器用下面这个方法
    public MapSessionRepository sessionRepository() {
        return new MapSessionRepository(new ConcurrentHashMap<>());
    }
}
//输入账号密码

/////↓

///Controller验证

////↓

//创建Session

//↓

//Spring Session保存

///↓

//生成JSESSIONID

/////↓

//返回Cookie给浏览器
