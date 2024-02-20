
package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotNull
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}\\d{4}$") //esto esta bien????
	private String				code;

	@NotNull
	@NotBlank
	@Length(max = 76)
	private String				title;

	@NotNull
	@NotBlank
	@Length(max = 101)
	private String				avstract;

	@NotNull
	private Money				cost;

	@URL
	private String				link;

	// Derived attributes -----------------------------------------------------

	//an indication on whether it has fatal errors, e.g., panics//no se como hacerlo

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private UserStory			userStory; //tengo que poner esto asi o tengo que poner otra vez manager?

}
