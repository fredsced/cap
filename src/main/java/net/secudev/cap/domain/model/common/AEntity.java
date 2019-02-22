package net.secudev.cap.domain.model.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@ToString(callSuper=false, includeFieldNames=true)
public abstract class AEntity {
	@Getter
	@Setter(AccessLevel.PROTECTED)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
}
