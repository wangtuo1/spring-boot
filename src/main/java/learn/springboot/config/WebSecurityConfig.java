package learn.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/home", "/error","/index")
					.permitAll()
				.anyRequest()
					.authenticated()
				.and()
					.formLogin()
					.permitAll()
				.and()
					.logout()
					.deleteCookies("JSESSIONID")
					.permitAll()
				.and()
					.csrf()
					.disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("password")
			.roles("USER");
	}
}
