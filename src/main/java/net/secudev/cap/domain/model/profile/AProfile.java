package net.secudev.cap.domain.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.secudev.cap.domain.model.appUser.AppUser;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user_profile")
@ToString(callSuper = true, includeFieldNames = true)
public abstract class AProfile {
	@Getter
	@Setter(AccessLevel.PROTECTED)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@Getter
	@Setter
	private AppUser user;	

}
