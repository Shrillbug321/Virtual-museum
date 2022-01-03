package pl.dreszer.projekt.models.authorization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer role_id;
	@Enumerated(EnumType.STRING)
	private Types type;
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role(Types type)
	{
		this.type = type;
	}

	public static enum Types
	{
		ROLE_ADMIN,
		ROLE_USER
	}
}
