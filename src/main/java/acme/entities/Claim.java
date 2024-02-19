
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Claim extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotNull
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^C-[0-9]{4}$") 	//¿Está bien asi?
	private String				code;

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationMoment;

	@NotNull
	@NotBlank
	@Length(max = 76)
	private String				heading;

	@NotNull
	@NotBlank
	@Length(max = 101)
	private String				description;

	@NotNull
	@NotBlank
	@Length(max = 101)

	private String				departament;

	@Email
	private String				email;

	@URL
	private String				link;
}
