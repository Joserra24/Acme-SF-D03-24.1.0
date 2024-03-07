
package acme.forms;

import java.util.Map;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Map<String, int>		totalPrincipalEachRole;
	Double						ratioOfNoticesWithEmailAndLink;
	Double						ratioOfCriticalObjectives;

	Double						avgValueRisk;
	Double						minValueRisk;
	Double						maxValueRisk;
	Double						devValueRisk;

	Double						avgClaimsOverLastTenWeeks;
	Double						minClaimsOverLastTenWeeks;
	Double						maxClaimsOverLastTenWeeks;
	Double						devClaimsOverLastTenWeeks;

	// Derived attributes -----------------------------------------------------

	Double						ratioOfNotCriticalObjectives;

	// Relationships ----------------------------------------------------------
}
