
package acme.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.time.DurationMin;

import acme.client.data.AbstractEntity;
import acme.enumerated.Mark;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecords extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Atributes ---------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^AU-[0-9]{4}-[0-9]{3}$")
	private String				code;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DurationMin(hours = 1)
	private Date				period;

	@NotNull
	private Mark				mark;

	@URL
	private String				optionalLink;

	// Relationships ----------------------------------------------------------

	@ManyToOne
	@Valid
	@NotNull
	private List<CodeAudits>	codeAudits;
}
