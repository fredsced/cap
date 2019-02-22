package net.secudev.cap.domain.model.appUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.secudev.cap.domain.model.appRole.AppRole;
import net.secudev.cap.domain.model.common.AEntity;
import net.secudev.cap.domain.model.profile.AProfile;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, includeFieldNames = true)
public class AppUser extends AEntity {

	@Getter
	@Setter
	@Column(unique = true)
	private String login;
	@Getter
	@Setter
	private String password;
	@Getter
	@Setter
	@Email
	@Column(unique = true)
	private String email;
	@Getter
	@Setter
	private boolean enabled;

	@ManyToMany
	@JoinTable(name = "user_role")
	private Set<AppRole> roles = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)		
	@Getter
	private AProfile profile;

	public AppUser(String login, String password, String email, boolean enabled) {
		setLogin(login);
		setPassword(password);
		setEmail(email);
		setEnabled(enabled);
	}

	public void addRole(AppRole role) {
		roles.add(role);
		role.addAppuser(this);
	}

	public void removeRole(AppRole role) {
		roles.remove(role);
		role.removeAppuser(this);
	}

	public List<AppRole> getRoles() {
		return new ArrayList<>(roles);
	}
	
	public void setProfile(AProfile profile)
	{
		this.profile=profile;
		profile.setUser(this);
	}
}
