/**
 * FileName: ShiroRealm
 * Author:   X450J
 * Date:     2019/5/30 10:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package springbootshiro2.demo.configurer;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import springbootshiro2.demo.model.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈自定义Realm〉
 *
 * @author X450J
 * @create 2019/5/30
 * @since 1.0.0
 */
public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给当前角色授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        User user = (User) principalCollection.getPrimaryPrincipal();
        //在这里 实际开发应该从数据库进行获取
        if (user.getUsername().equals("xslde")){
            //设置该用户拥有user角色
            authorizationInfo.addRole("user");
            //设置该用户拥有query权限
            authorizationInfo.addStringPermission("user:query");
        }

        if(user.getUsername().equals("admin")){
            //admin用户拥有admin、user角色
            authorizationInfo.addRole("admin");
            authorizationInfo.addRole("user");
            //设置该用户拥有query权限
            authorizationInfo.addStringPermission("user:query");
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //进行用户信息获取
        String username = (String)token.getPrincipal();
        //开发中，这里都是去数据库查询
        if (!"xslde".equals(username)&&!"test".equals(username)&&!"xslde.com".equals(username)&&!"admin".equals(username)){
            throw new UnknownAccountException("用户不存在！");
        }
        User user = null;
        if ("xslde".equals(username)){
            user = new User();
            user.setUsername("xslde");
            user.setPassword("0caf568dbf30f5c33a13c56b869259fc");
            user.setSalt("abcd");
            user.setAvailable(1);
        }
        if ("admin".equals(username)){
            user = new User();
            user.setUsername("admin");
            user.setPassword("0caf568dbf30f5c33a13c56b869259fc");
            user.setSalt("abcd");
            user.setAvailable(1);
        }
        if ("test".equals(username)){
            user = new User();
            user.setUsername("test");
            user.setPassword("0caf568dbf30f5c33a13c56b869259fc");
            user.setSalt("abcd");
            user.setAvailable(0);
        }

        //这是模拟数据库里面拥有QQ第三方用户信息
        if ("xslde.com".equals(username)){
            user = new User();
            user.setUsername("xslde.com");
            user.setAvailable(1);
            user.setSalt("abcd");
            user.setPassword("6e20337c6b222fa0a8c3bbb9dd979374");//
        }
        if (user.getAvailable()!=1){
            throw  new LockedAccountException("账户已被锁定");
        }
        return  new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }

   //生成一个加盐密码
    public static void main(String[] args){
        //加密类型
        String hashAlgorithmName = "md5";
        //迭代次数
        Integer count = 2;
        String password = "123456";
        String salt = "abcd";
        String s = new SimpleHash(hashAlgorithmName,password,salt,count).toHex();
        System.out.println(s);

    }
}