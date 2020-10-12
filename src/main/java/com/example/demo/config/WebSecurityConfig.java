package com.example.demo.config;

import com.example.demo.filter.JWTAuthenticationFilter;
import com.example.demo.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable().authorizeRequests()
                //we are using server.. not browser.. so we disable csrf and do some filter instead
                //https://stackoverflow.com/questions/52363487/what-is-the-reason-to-disable-csrf-in-spring-boot-web-application
                .antMatchers("/").permitAll()//allow all request that don't have child page
                .antMatchers("/test").permitAll()//allow test request pass
                .antMatchers(HttpMethod.POST, "/login").permitAll()//allow login request to access
                .anyRequest().authenticated()
                // Add các filter vào ứng dụng của chúng ta, thứ mà sẽ hứng các request để xử lý trước khi tới các xử lý trong controllers.
                // Về thứ tự của các filter, các bạn tham khảo thêm tại http://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html mục 7.3 Filter Ordering
                .and()
                //config mapping to filter
                //add filter to authen from username and password
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)//others request is must be authenticate
                //add filter to authen from JWT token
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //FIXME remove it. only for test.. we generate password with encoded to save it to database
        System.out.println("pass:" + new BCryptPasswordEncoder().encode("doandeptrai"));
        System.out.println("pass:" + new BCryptPasswordEncoder().encode("thiepxinhgai"));


        //super.configure(auth);
        //do authenticate in memory
        //auth.inMemoryAuthentication().withUser("doanpt").password("{noop}doanpt").roles("admin");
        //do authen with database
        auth.jdbcAuthentication().dataSource(dataSource)
                //FIXME create column named "enabled" in user table mysql
                .usersByUsernameQuery("select username,password, enabled from user where  username=?")
                .authoritiesByUsernameQuery("select username,rule from user where username=?")
        .passwordEncoder(new BCryptPasswordEncoder());
    }

}
