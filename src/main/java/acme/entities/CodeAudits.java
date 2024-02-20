
package acme.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.enumerated.Tipo;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CodeAudits extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------------------------------------

	@NotNull
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{1,3}-[0-9]{3}$")
	private String				code;

	@NotNull
	@Past
	private Date				execution;

	@NotNull
	private Tipo				type;

	@NotNull
	@NotBlank
	@Length(max = 101)
	private List<String>		corrective_actions;

	//////
	@NotNull
	private Integer				mark;
	//////

	@URL
	private String				optionalLink;
}
