package net.secudev.cap.domain.model.appRole;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface IAppRoleRepository extends PagingAndSortingRepository<AppRole, Long>{
	
	AppRole findByName(String name);
}

