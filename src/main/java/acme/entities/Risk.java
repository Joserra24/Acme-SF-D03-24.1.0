
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Risk extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "R-[0-9]{3}")
	private String				reference;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Length(max = 255)
	private Date				identificationDate;

	// Impact must be positive.
	// This requirement will be implemented soon.
	private double				impact;

	@Range(min = 0, max = 100)
	private int					probability;

	// Value must be the result of the multiplication of impact and probability.
	// This requirement will be implemented soon.
	private double				value;

	@NotBlank
	@Length(max = 100)
	private String				description;

	@URL
	private String				link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
