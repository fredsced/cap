package net.secudev.cap.domain.model.appRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.secudev.cap.domain.model.appUser.AppUser;
import net.secudev.cap.domain.model.common.AEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, includeFieldNames = true)
public class AppRole extends AEntity {

	@Getter
	@Setter
	@Column(unique = true)
	private String name;

	@ManyToMany(mappedBy = "roles")
	@Setter(AccessLevel.NONE)
	private Set<AppUser> users = new HashSet<AppUser>();

	public AppRole(String name) {
		this.name = name;
	}

	public void addAppuser(AppUser user) {		
		this.users.add(user);
	}

	public void removeAppuser(AppUser user) {
		this.users.remove(user);
	}
	
	public List<AppUser> getUsers()	{
		return new ArrayList<AppUser>(users);
	}
}