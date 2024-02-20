
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.time.DurationMin;

import acme.client.data.AbstractEntity;
import acme.enumerated.Nota;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecords extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------------------------------------

	@NotNull
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^AU-[0-9]{4}-[0-9]{3}$")
	private String				code;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DurationMin(hours = 1)
	private Date				period;

	//PREGUNTAR  POR ENUM NOTA
	@NotNull
	private Nota				mark;

	@URL
	private String				optionalLink;
}
