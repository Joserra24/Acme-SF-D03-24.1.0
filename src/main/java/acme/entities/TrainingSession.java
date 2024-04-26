
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrainingSession extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^TS-[A-Z]{1,3}-\\d{3}$", message = "{validation.trainingSession.code}")
	private String				code;

	//El periodo debe crearse con al menos una semana de antelación y tiene que durar como minimo una semana.
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startPeriod;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				endPeriod;

	@NotBlank
	@Length(max = 75)
	private String				location;

	@NotBlank
	@Length(max = 75)
	private String				instructor;

	@NotNull
	@Email
	private String				email;

	@URL
	@Length(max = 255)
	private String				link;

	private boolean				draftMode;

	// Relations  -------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private TrainingModule		trainingModule;
}
