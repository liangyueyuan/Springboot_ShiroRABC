/**
 * FileName: User
 * Author:   X450J
 * Date:     2019/5/29 17:05
 * Description: 用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package springbootshiro2.demo.model;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户信息〉
 *
 * @author X450J
 * @create 2019/5/29
 * @since 1.0.0
 */
public class User implements Serializable{
    //用户名称
    private String username;

    //用户密码
    private String password;

    //密码加盐
    private String salt;

    //用户是否可用
    private Integer available;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}