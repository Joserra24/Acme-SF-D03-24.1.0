
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.enumerated.Mark;
import acme.enumerated.Type;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CodeAudits extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{1,3}-[0-9]{3}$")
	private String				code;

	@NotNull
	@Past
	private Date				execution;

	@NotNull
	private Type				type;

	@NotBlank
	@Length(max = 101)
	private String				corrective_actions;

	@URL
	private String				optionalLink;

	//Atributo derivado


	@Transient
	private Mark getModeMark(final AuditRecords auditRecords) {

		Mark result;

		assert auditRecords.getMark() instanceof Mark; //verificar que recibe Mark

		result = auditRecords.getMark();

		return result;
	}

}
