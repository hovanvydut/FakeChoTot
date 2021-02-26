package dut.hovanvy.chotot.config;

import dut.hovanvy.chotot.core.auth.jwt.JwtEmailAndPasswordAuthenticationFilter;
import dut.hovanvy.chotot.core.auth.jwt.JwtTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("myUserDetailsService")
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.cors()
			.and()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(new JwtEmailAndPasswordAuthenticationFilter(this.authenticationManager()))
				.addFilterAfter(new JwtTokenVerifier(), JwtEmailAndPasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/", "/api/**/auth/register/**")
					.permitAll()
				.anyRequest()
					.authenticated();

	}

/*
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return (PasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
*/

}
