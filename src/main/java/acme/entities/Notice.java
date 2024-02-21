
package acme.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
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
public class Notice extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Past
	private LocalDateTime		instantiationMoment;

	@NotNull
	@NotBlank
	@Length(max = 76)
	private String				title;

	@NotNull
	@NotBlank
	@Length(max = 76)
	private String				author;   // Falta añadir la restricción del username

	@NotNull
	@NotBlank
	@Length(max = 101)
	private String				message;

	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String				email;

	@URL
	private String				link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
