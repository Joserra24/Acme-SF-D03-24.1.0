
package acme.roles;

import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Developer extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@NotBlank
	@Length(max = 76)
	private String				degree;

	@NotNull
	@NotBlank
	@Length(max = 101)
	private String				specialisation;

	@NotNull
	@NotBlank
	@Length(max = 101)
	private List<String>		skills;

	@Email
	//Es optional?
	private String				email;

	@URL
	private String				link;
}
