package net.secudev.cap.domain.model.profile;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@DiscriminatorValue("emp")
public class EmployeeProfile extends AProfile{
	
	@Getter
	@Setter
	@Column(unique = true)
	private String employeeId;
	
	public EmployeeProfile(String employeeId)
	{
		setEmployeeId(employeeId);
	}

}
