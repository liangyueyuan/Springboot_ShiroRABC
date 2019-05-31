/**
 * FileName: BeetlAction
 * Author:   X450J
 * Date:     2019/5/30 14:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package springbootshiro2.demo.action;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springbootshiro2.demo.model.User;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author X450J
 * @create 2019/5/30
 * @since 1.0.0
 */
@Controller
public class BeetlAction {

    @GetMapping({"/", "/index", "/beetl"})
    public String beetl(Model model) {
        //获取用户名
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //通过模板引擎将用户信息传递到页面
        model.addAttribute("beetl", "欢迎您：" + user.getUsername());
        return "index.html";
    }
}
