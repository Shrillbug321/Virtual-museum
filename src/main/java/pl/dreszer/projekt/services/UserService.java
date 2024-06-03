package pl.dreszer.projekt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dreszer.projekt.configurations.Profiles;
import pl.dreszer.projekt.models.authorization.Role;
import pl.dreszer.projekt.models.authorization.User;
import pl.dreszer.projekt.repositories.UsersRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(Profiles.USERS_IN_DATABASE)
public class UserService implements IUserService
{
	@Autowired
	private UsersRepository usersRepository;
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		return convertToUserDetails(user);
	}

	private UserDetails convertToUserDetails(User user)
	{
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role: user.getRoles())
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getType().toString()));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				true, true, true, true, grantedAuthorities);
	}
}