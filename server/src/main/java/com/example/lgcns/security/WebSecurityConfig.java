package com.example.lgcns.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * WebSecurityConfig
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		// cors 적용
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Override
	public void configure(WebSecurity web) {
		// api로 시작하지 않는 경로는 무시한다 (선순위 조건)
		web.ignoring().regexMatchers("^(?!/api).*");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// disable CSRF
		http.cors().and().csrf().disable();

		// Entry points
		String[] privatePaths = { "/api/employees" };
		//@formatter:off
		http.authorizeRequests()
		.antMatchers(privatePaths).authenticated()
		.antMatchers("/api/**").permitAll();
		//@formatter:on

		// No Session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// apply jwt filter
		//@formatter:off
		http.addFilter(getJwtAuthenticationFilter())
			.addFilterBefore(getJwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		//@formatter:on

		// error handling
		http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
	}

	public JwtAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
		final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/api/user/login"); // 인증(로그인) URL
		return filter;
	}

	public JwtAuthorizationFilter getJwtAuthorizationFilter() throws Exception {
		return new JwtAuthorizationFilter(authenticationManager());
	}
}