
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.enumerated.Level;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrainingModule extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{1,3}\\d{3}$")
	private String				code;

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotNull
	@NotBlank
	@Length(max = 101)
	private String				details;

	@NotNull
	private Level				difficultyLevel;

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	//after the creation moment??
	private Date				updateMoment;

	@URL
	private String				link;

	//¿Cómo pongo "and an estimated total time"

	//2)	A training module consists of one or several short-term training activities aimed at extending or updating knowledge and skills related to the topic of a project. 
	//esto es una relacion 1..1 con project?

}
