package com.appsdeveloperblog.photoapp.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdeveloperblog.photoapp.api.users.business.abstracts.UserService;

import net.bytebuddy.asm.Advice.Local;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	// kaynaklarımıza erişimi kısıtladığımız yer

	 private Environment environment;
	    private UserService userService;
	    private BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Autowired
	    public WebSecurity(Environment environment,UserService userService,BCryptPasswordEncoder bCryptPasswordEncoder) {
	        this.environment = environment;
	        this.userService=userService;
	        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**")
                .hasIpAddress(this.environment.getProperty("gateway.ip")).and().addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }
	
	 private AuthenticationFilter getAuthenticationFilter() throws Exception {
	        AuthenticationFilter authenticationFilter=new AuthenticationFilter(this.userService,environment,authenticationManager());
	        //authenticationFilter.setAuthenticationManager(authenticationManager());
	        authenticationFilter.setFilterProcessesUrl(this.environment.getProperty("login.url.path"));
	        return authenticationFilter;
	    }
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(this.userService).passwordEncoder(bCryptPasswordEncoder);
	    }

}
