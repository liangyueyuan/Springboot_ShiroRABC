/**
 * FileName: LoginAction
 * Author:   X450J
 * Date:     2019/5/30 14:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package springbootshiro2.demo.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author X450J
 * @create 2019/5/30
 * @since 1.0.0
 */
@Controller
public class LoginAction {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(String username, String password, boolean rememberMe, Model model) {
        //判断用户名和密码为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            model.addAttribute("msg", "用户名和密码不能为空！");
            return "login.html";
        }

        //开始登录
        //实际开发中，用户名和密码错误，不给出明确提示
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, rememberMe));
        } catch (UnknownAccountException e) {//未知用户异常
            model.addAttribute("msg", "用户名错误！");
            return "login.html";
        } catch (LockedAccountException e) {//账户锁定
            model.addAttribute("msg", "账户被锁定！");
            return "login.html";
        } catch (CredentialsException e) {//用户密码错误异常
            model.addAttribute("msg", "用户密码错误！");
            return "login.html";
        } catch (Exception e) {
            model.addAttribute("msg", "其他异常！");
            return "login.html";
        }
        return "redirect:/index";
    }

}