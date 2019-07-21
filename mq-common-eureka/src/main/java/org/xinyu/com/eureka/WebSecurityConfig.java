package org.xinyu.com.eureka;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * eureka开启验证后无法连接注册中心?
 * spring Cloud 2.0 以上）的security默认启用了csrf检验，要在eurekaServer端配置security的csrf检验为false
 * @author qxw
 * @data 2018年7月24日下午1:58:31
 */

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${users.admin.name}")
    private String admin_name;
    @Value("${users.admin.password}")
    private String admin_password;
    @Value("${users.admin.roles}")
    private String [] admin_roles;

    @Value("${users.pc.name}")
    private String pc_name;
    @Value("${users.pc.password}")
    private String pc_password;
    @Value("${users.pc.roles}")
    private String [] pc_roles;

    @Value("${users.app.name}")
    private String app_name;
    @Value("${users.app.password}")
    private String app_password;
    @Value("${users.app.roles}")
    private String [] app_roles;

    @Value("${users.apiuser.name}")
    private String apiuser_name;
    @Value("${users.apiuser.password}")
    private String apiuser_password;
    @Value("${users.apiuser.roles}")
    private String [] apiuser_roles;

    @Value("${users.zuul.name}")
    private String zuul_name;
    @Value("${users.zuul.password}")
    private String zuul_password;
    @Value("${users.zuul.roles}")
    private String [] zuul_roles;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        super.configure(http);
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        //这里忽略app调用的接口服务,让接口服务的Oauth去验证
//        web.ignoring().antMatchers("/app-server/api/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
//        http.authorizeRequests().anyRequest().fullyAuthenticated().antMatchers("/app-server/pc/**").hasRole("PCSERVER");
////        .antMatchers("/app-server/api/**").hasRole("APIUSER");
//        http.csrf().disable();
//        super.configure(http);
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//        auth.inMemoryAuthentication().withUser(admin_name).password(admin_password).roles(admin_roles)
//                .and().withUser(pc_name).password(pc_password).roles(pc_roles)//PC 服务
//                .and().withUser(app_name).password(app_password).roles(app_roles)//APP 服务
//                .and().withUser(zuul_name).password(zuul_password).roles(zuul_roles) //路由
//                .and().withUser(apiuser_name).password(apiuser_password).roles(apiuser_roles);//接口调用者
//    }

}
