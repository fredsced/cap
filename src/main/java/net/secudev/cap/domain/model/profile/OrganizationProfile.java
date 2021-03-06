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
@ToString(callSuper = true, includeFieldNames = true)
@DiscriminatorValue("org")
public class OrganizationProfile extends AProfile {

	@Getter
	@Setter
	@Column(unique = true)
	private String orgName;
	@Getter
	@Setter
	private String webSiteUrl;

	public OrganizationProfile(String orgName, String webSiteUrl) {
		setOrgName(orgName);
		setWebSiteUrl(webSiteUrl);
	}

}
