
package acme.forms;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	int							totalProgressLogsWithCompletenessRateBelow25;
	int							totalProgressLogsWithCompletenessRateBetween25And50;
	int							totalProgressLogsWithCompletenessRateBetween50And75;
	int							totalProgressLogsWithCompletenessRateAbove75;

	Money						averageBudgetContract;
	Money						deviationBudgetContract;
	Money						minimumBudgetContract;
	Money						maximumBudgetContract;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
