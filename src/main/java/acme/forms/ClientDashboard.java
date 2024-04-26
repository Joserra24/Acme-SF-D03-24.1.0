
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	int							totalLogsLessThan25;
	int							totalLogsLessBetween25And50;
	int							totalLogsLessBetween50And75;
	int							totalLogsAbove75;

	Double						averageBudgetContract;
	Double						deviationBudgetContract;
	Double						minimumBudgetContract;
	Double						maximumBudgetContract;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
