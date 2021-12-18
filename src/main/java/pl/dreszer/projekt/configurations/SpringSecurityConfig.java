package pl.dreszer.projekt.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Profile(Profiles.USERS_IN_MEMORY)
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
	{
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		User.UserBuilder userBuilder = User.builder();
		UserDetails user = userBuilder.username("user")
						.password(passwordEncoder.encode("123"))
						.roles("USER").build();
		UserDetails admin = userBuilder.username("admin")
						.password(passwordEncoder.encode("admin"))
						.roles("ADMIN").build();
		UserDetails superUser = userBuilder.username("superUser")
				.password(passwordEncoder.encode("secret"))
				.roles("ADMIN", "USER").build();
		manager.createUser(user);
		manager.createUser(admin);
		manager.createUser(superUser);
		return manager;
	}

	@Override
	protected void configure(HttpSecurity httpSec) throws Exception
	{
		httpSec.authorizeRequests()
			.antMatchers("/css/**", "/index**", "/paintings**", "/h2-console/**").permitAll()
			.antMatchers("/paintingsDetails**").hasRole("USER")
			.antMatchers("/paintingForm**", "delete**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().csrf().ignoringAntMatchers("/h2-console/**")
			.and().headers().frameOptions().sameOrigin();
		httpSec.formLogin().loginPage("/login").permitAll();
		httpSec.logout().permitAll();
		httpSec.exceptionHandling().accessDeniedPage("/403");
	}
}
