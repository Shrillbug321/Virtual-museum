package pl.dreszer.projekt.models.authorization;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	@Size(min=2, max=36)
	private String username;

	private String password;
	@Transient
	private String passwordConfirm;
	private boolean enabled = false;

	@ManyToMany
	@JoinTable(name="users_roles",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;

	public User(String username) {
		this.username = username;
	}

	public User(String username, boolean enabled) {
		this.username = username;
		this.enabled = enabled;
	}


	public User(String username, String password, boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public void encodePassword(String password)
	{
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(password);
	}
}
