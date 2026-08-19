//这个是springmvc配置的拦截器，作用是在用户访问controller方法前先检查有没有登陆，登陆则放行，跟刚刚的control代码是一起的

package fm.douban.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户信息拦截器
 */
//定义拦截器是spring提供拦截器的接口，后面又是经典的重写环节

public class UserInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);
//当用户访问/app/user/list时，浏览器-->Interceptor.preHandle()-->UserController.list()-->返回页面
    // Controller方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();/*这里是获取session的对象，保存先前session.setAttribute(
"userLoginInfo",
userLoginInfo
);*/
        if (session.getAttribute("userLoginInfo") != null) {//判断是否登陆，从session里面取userLoginInfo，若不是空的，就返回true，要是登录了是会有前面session信息的，这个方法常用于判断信息是否存在的
            return true;
        }

        // 跳转登录
        String url = "/app/login";
        response.sendRedirect(url);
        return false;
    }//告诉controller不要继续执行接下来的内容，并且转跳到登陆页面

    //Controller方法执行之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }//暂时用不到这个方法

    // 整个请求完成后（包括Thymeleaf渲染完毕）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }//清理资源
//记录日志
//统计耗时
}
/*用户访问页面
      |
      ↓
UserInterceptor拦截
      |
      ↓
检查Session有没有用户信息
      |
 ┌───────┴────────┐
 ↓                ↓
已登录             未登录
 ↓                ↓
进入Controller     跳转login页面*/

