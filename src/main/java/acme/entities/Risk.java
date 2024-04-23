
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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
	@Pattern(regexp = "^R-\\d{3}$", message = "{validation.risk.reference}")
	private String				reference;

	@PastOrPresent
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				identificationDate;

	@Range(min = 0, max = 100)
	@Digits(integer = 3, fraction = 2)
	private double				impact;

	@Range(min = 0, max = 100)
	@Digits(integer = 3, fraction = 2)
	private double				probability;

	@NotBlank
	@Length(max = 100)
	private String				description;

	@URL
	@Length(max = 255)
	private String				link;

	// Derived attributes -----------------------------------------------------


	@Transient
	public double value() {
		double result;

		result = this.impact * this.probability / 100.0;

		return result;
	}

	// Relationships ----------------------------------------------------------

}
