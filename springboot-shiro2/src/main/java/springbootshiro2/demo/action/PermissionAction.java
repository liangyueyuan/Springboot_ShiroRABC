/**
 * FileName: PermissionAction
 * Author:   X450J
 * Date:     2019/5/30 14:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package springbootshiro2.demo.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author X450J
 * @create 2019/5/30
 * @since 1.0.0
 */
@Controller
public class PermissionAction {

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized.html";
    }

    //拥有user角色才可访问
    @GetMapping("/user")
    public String user(Map<String,String> code){
        code.put("msg","拥有user角色");
        return "user.html";
    }

    //拥有user角色才可访问和user:query权限才可访问
    @GetMapping("/user/per")
    public String userPer(Map<String,String> mode){
        mode.put("msg","拥有user角色和user:query权限");
        return "user.html";
    }

    //拥有admin角色才可访问
    @GetMapping("/admin")
    public String admin(Map<String,String> mode){
        mode.put("msg","拥有admin角色");
        return "admin.html";
    }

    //通过注解控制授权
    @RequiresRoles({"admin"})
    @GetMapping("/abc")
    public String abc(Map<String,String> mode){
        mode.put("msg","拥有admin角色,并且是通过注解控制");
        return "admin.html";
    }

    //通过注解控制权限
    @RequiresPermissions({"user:query"})
    @GetMapping("/abcd")
    public String abcd(Map<String,String> mode){
        mode.put("msg","拥有user:query权限,并且是通过注解控制");
        return "user.html";
    }
}