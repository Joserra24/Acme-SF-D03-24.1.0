
package acme.entities;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.enumerated.ObjectivePriority;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Objective extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationMoment;

	@NotNull
	@NotBlank
	@Length(max = 76)
	private String				title;

	@NotNull
	@NotBlank
	@Length(max = 101)
	private String				description;

	@NotNull
	private ObjectivePriority	priority;

	@NotNull
	private boolean				status;

	@NotNull
	private Duration			duration; //esto esta bien?

	@URL
	private String				link;

}
