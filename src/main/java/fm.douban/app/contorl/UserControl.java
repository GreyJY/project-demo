//这个类是一个spring mvc 的用户登陆控制器（Controller）接收用户输入的账号密码 →
//判断是否正确 → 登录成功后保存用户信息到 Session → 返回登录结果。本质是模拟登陆状态的类信息从配置文件中提取
package fm.douban.app.control;

import fm.douban.model.UserLoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
//这个告诉spring这个类是控制层，用来接收浏览器请求，就是浏览器有什么信息会反馈到这里
@Controller
//给整个类设置一个统一路径
@RequestMapping(path = "app")
  //创建了一个日志对象，用来记录登陆的时间各种状态，然后方便开发者产看这个程序运行情况
public class UserControl {
    private static final Logger LOG = LoggerFactory.getLogger(UserControl.class);
//从配置文件读取loginmock.username=admin，这个也是赋予名字
    @Value("${loginmock.userName}")
    private String mockedName;
//从配置文件读取密码，然后赋予到mockedname这个对象里
    @Value("${loginmock.password}")
    private String mockedPassword;
//spring创建完这个对象之后，自动执行这个方法，@PostConstruct
    @PostConstruct
    public void init() {
        LOG.info("UserControl 启动啦");
    }
//显示登陆页面，用户访问login你就会返回这个方法，进入到login这个前端页面（在templates里面的）
    @GetMapping(path = "/login")
    public String loginPage(Model model) {
        return "login";
    }
//post一般是登陆接口
    @PostMapping(path = "/authenticate")//<form class="login-form"
  //action="/app/authenticate" method="post" name="user">跟前端页面这个spring mvc有关，模板类
    @ResponseBody//这个注解表示不用跳页面直接返回数据
  //这里用来接收前端传来的name=admin，password=123456
    public Map login(@RequestParam String name, @RequestParam String password,
                     HttpServletRequest request, HttpServletResponse response) {
        Map returnData = new HashMap();//创建返回数据，用于存储返回数据
      //用于判断用户输入的的name和passwo是否等于配置文件里面的同等信息
        if (mockedName.equals(name) && mockedPassword.equals(password)) {
            UserLoginInfo userLoginInfo = new UserLoginInfo();//创建一个信息用户对象用户:
/*{
 id:"",
 username:""
}*/     
            userLoginInfo.setUserId("123456789abcd");//这里把这个写死了，真是项目是来自数据库的，select id from user这样写
            userLoginInfo.setUserName(name);//模拟登陆写的，这个name应该是admin
            // 取得 HttpSession 对象
            HttpSession session = request.getSession();//这个是获取session，用于保存用户的登陆状态的一个数据空间
            // 写入登录信息
            session.setAttribute("userLoginInfo", userLoginInfo);//key，value,开发者用getAttribute方法来获取对应session
            returnData.put("result", true);
            returnData.put("message", "login successfule")/*两个一起返回{
"result":true,
"message":"login successful"
}*/
        } else {
            returnData.put("result", false);
            returnData.put("message", "userName or password not correct");//登陆失败给的信息
        }

        return returnData;
    }
}
/*浏览器输入账号密码

        |
        ↓

POST /app/authenticate

        |
        ↓

UserControl.login()

        |
        ↓

比较账号密码

        |
        |
   +----+----+
   |         |
正确       错误
   |         |
   ↓         ↓

创建用户对象   返回false

   |
   ↓

保存Session

   |
   ↓

返回JSON*/

