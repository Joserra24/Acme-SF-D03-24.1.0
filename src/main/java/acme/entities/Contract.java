
package acme.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
// import javax.persistence.ManyToOne;
// import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
// import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contract extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{1,3}-[0-9]{3}$")
	private String				code;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime		instantiationMoment;

	@NotNull
	@NotBlank
	@Length(max = 76)
	private String				providerName;

	@NotNull
	@NotBlank
	@Length(max = 76)
	private String				customerName;

	@NotNull
	@NotBlank
	@Length(max = 101)
	private String				goals;

	// Derived attributes -----------------------------------------------------

	//	@NotNull
	//	private Money				budget;

	// Relationships ----------------------------------------------------------

	//	@Valid
	//	@ManyToOne(optional = false)
	//	private Project			    project;

	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProgressLog>	progressLogs;

}
