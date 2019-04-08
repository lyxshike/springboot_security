package com.panda.security.config;

import com.panda.security.utils.MyPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
               // 定制请求的授权规则,首页所有人都可以访问
    	       // level1/**请求只能是登陆系统，有VIP1角色的人
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1") 
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
              //开启自动配置的登陆功能，如果没有权限就跳转到登陆页面。
             // 定制登陆页面：.loginPage("/userlogin");跳转到自定义的登录页       
        
        
        http.logout().logoutSuccessUrl("/");
                  //开启自动配置的注销功能。注销成功返回首页
              //1、访问/logout 表示用户注销，清空session
               //2, 2、注销成功后返回： /login?logout

    
        http.rememberMe().rememberMeParameter("remember");
              // 开启记住我功能  .rememberMeParameter("remember")：定制页面的“记住我”
              //登陆成功以后，将cookie发给浏览器保存，以后访问页面会带上这个cookie，只要通过检查就可以免登陆点击注销会删除cookie
    }

    
    @Override
             // 定义认证规则。这里是从内存中获取，实际可以从数据库中获取。
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // super.configure(auth);
        auth.inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("vip1").password("123456").roles("VIP1")
                .and()
                .withUser("vip2").password("123456").roles("VIP2")
                .and()
                .withUser("vip3").password("123456").roles("VIP3");
    }
}
