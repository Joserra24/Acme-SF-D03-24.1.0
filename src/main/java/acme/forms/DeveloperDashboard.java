
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeveloperDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	int							totalTrainingModulesWithUpdateMoment;
	int							totalTrainingSessionsWithLink;

	Double						avgTimeTrainingModule;
	Double						devTimeTrainingModule;
	Double						minTimeTrainingModule;
	Double						maxTimeTrainingModule;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
