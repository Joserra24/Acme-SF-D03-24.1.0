
package forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboards extends AbstractForm {

	//Serialisation identifier------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes --------------------------------------------------------------

	int							totalCodeAuditsStatic;
	int							totalCodeAuditsDynamic;

	Double						avgAuditRecords;
	Double						devAuditRecords;
	Double						minAuditRecords;
	Double						maxAuditRecords;

	Double						avgPeriod;
	Double						devPeriod;
	Double						minPeriod;
	Double						maxPeriod;
}
