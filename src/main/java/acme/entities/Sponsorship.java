
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
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import acme.enumerated.ProjectType;
import acme.roles.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsorship extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{1,3}-\\d{3}$", message = "{validation.sponsorship.code}")
	private String				code;

	@PastOrPresent
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				moment;

	// Must be after moment. This requirements will be implemented soon.
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				durationStart;

	// Must be at least one month later than durationStart. This requirements will be implemented soon.
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				durationEnd;

	// Must be positive. This requirement will be implemented soon.
	@Valid
	@NotNull
	private Money				amount;

	@NotNull
	private ProjectType			projectType;

	@Email
	@Length(max = 255)
	private String				email;

	@URL
	@Length(max = 255)
	private String				link;

	private boolean				draftMode;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project				project;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;

}
