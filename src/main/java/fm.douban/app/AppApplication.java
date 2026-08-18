package fm.douban.app;

import org.springframework.boot.SpringApplication;//导入springboot提供的启动工具
import org.springframework.boot.autoconfigure.SpringBootApplication;//自动配置spring-boot-starter-web类似的方法，帮我配置们配置Tomcat，mvc
//扫描Bean，类似@Controller @service，创建对象
//扫描配置@Configuration类也会被发现
@SpringBootApplication(scanBasePackages={"fm.douban.app","fm.douban.service"})//这个注解是指定spring扫描哪些包，比如app和service这些包
  public class AppApplication{
pulic static void main(String[]args){SpringApplication.run(AppApplication.class,args);//启动spring
      }
    }

//运行main方法-->创建spring容器-->扫描不同注解-->创建对象-->注入依赖@Autowired-->启动Tomcat服务器-->等待用户请求
