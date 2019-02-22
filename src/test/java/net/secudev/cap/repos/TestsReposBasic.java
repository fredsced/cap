package net.secudev.cap.repos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import net.secudev.cap.domain.model.appRole.AppRole;
import net.secudev.cap.domain.model.appRole.IAppRoleRepository;
import net.secudev.cap.domain.model.appUser.AppUser;
import net.secudev.cap.domain.model.appUser.IAppUserRepository;
import net.secudev.cap.domain.model.profile.EmployeeProfile;
import net.secudev.cap.domain.model.profile.IProfileRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestsReposBasic {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	IAppUserRepository users;

	@Autowired
	IAppRoleRepository roles;
	
	@Autowired
	IProfileRepository profiles;

	@Test
	public void testAppUserRepo() {

		long id = users.save(new AppUser("ztevoz", passwordEncoder.encode("password"), "ztevoz@ztevoz.com", true))
				.getId();
		assertTrue(users.findById(id).isPresent());
		assertTrue(users.findByEmail("ztevoz@ztevoz.com") != null);
		assertTrue(users.findByLogin("ztevoz") != null);
		assertTrue(users.findByEnabledTrue().size() == 1);
		assertTrue(users.findByEnabledFalse().size() == 0);
		users.deleteById(id);
	}

	@Test
	public void testAppRoleRepo() {
		long id = roles.save(new AppRole("rh")).getId();
		assertTrue(roles.findByName("rh").getId() == id);
		roles.deleteById(id);
	}

	@Test
	public void testAppRoleAndAppUserRelation() {
		// Creation de 3 roles
		long idRoleRH = roles.save(new AppRole("rh")).getId();
		long idRoleEmployee = roles.save(new AppRole("employee")).getId();
		long idRoleAsso = roles.save(new AppRole("asso")).getId();

		// Creation du user alice associée aux roles rh et employee et son profil
		AppUser alice = new AppUser("alice", passwordEncoder.encode("password"), "alice@ztevoz.com", true);
		alice.addRole(roles.findById(idRoleRH).get());
		alice.addRole(roles.findById(idRoleEmployee).get());		
		users.save(alice);		
		EmployeeProfile aliceProfile = new EmployeeProfile("x78987");
		profiles.save(aliceProfile);
		alice.setProfile(aliceProfile);		
		users.save(alice);

		// Creation du user bob associée au role employee
		AppUser bob = new AppUser("bob", passwordEncoder.encode("password"), "bob@ztevoz.com", true);
		bob.addRole(roles.findById(idRoleEmployee).get());
		users.save(bob);

		// Creation du user charlie associé au role asso
		AppUser charlie = new AppUser("charlie", passwordEncoder.encode("password"), "charlie@ztevoz.com", true);
		charlie.addRole(roles.findById(idRoleAsso).get());
		users.save(charlie);

		// verifications
		assertTrue(users.findByRolesContains(roles.findById(idRoleEmployee).get()).size() == 2);
		assertTrue(users.findByLogin("alice").getRoles().size() == 2);
		assertTrue(users.findByRolesContains(roles.findById(idRoleRH).get()).size() == 1);
		assertTrue(users.findByLogin("bob").getRoles().size() == 1);
		assertTrue(users.findByRolesContains(roles.findById(idRoleAsso).get()).size() == 1);
		assertTrue(users.findByLogin("charlie").getRoles().size() == 1);

		assertTrue(roles.findByName("employee").getUsers().size() == 2);
		assertTrue(roles.findByName("rh").getUsers().size() == 1);
		assertTrue(roles.findByName("asso").getUsers().size() == 1);	

		// Nettoyage
		users.delete(alice);
		users.delete(bob);
		users.delete(charlie);
		
		//On verifie que les profils sont vides apres avoir supprimé les users
		assertTrue(profiles.count()==0);

		roles.deleteById(idRoleRH);
		roles.deleteById(idRoleEmployee);
		roles.deleteById(idRoleAsso);		
	}
}