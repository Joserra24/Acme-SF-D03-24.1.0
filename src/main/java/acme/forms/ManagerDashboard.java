
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalMustUserStory;
	Integer						totalShouldUserStory;
	Integer						totalCouldUserStory;
	Integer						totalWontUserStory;

	Double						avgEstimatedCostUserStory;
	Double						devEstimatedCostUserStory;
	Double						minEstimatedCostUserStory;
	Double						maxEstimatedCostUserStory;

	Double						avgCostProject;
	Double						devCostProject;
	Double						minCostProject;
	Double						maxCostProject;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
