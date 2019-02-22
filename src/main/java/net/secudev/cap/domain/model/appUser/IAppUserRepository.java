package net.secudev.cap.domain.model.appUser;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.secudev.cap.domain.model.appRole.AppRole;

public interface IAppUserRepository extends PagingAndSortingRepository<AppUser, Long>{
	
	AppUser findByLogin(String login);
	AppUser findByEmail(String email);
	List<AppUser> findByEnabledFalse();
	List<AppUser> findByEnabledTrue();
	List<AppUser> findByRolesContains(AppRole role);

}
