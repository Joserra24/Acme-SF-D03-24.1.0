
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						numberOfInvoicesTaxLessEqual21;
	Integer						numberSponsorshipsWithLink;
	Double						averageAmountSponsorships;
	Double						minimumAmountSponsorships;
	Double						maximumAmountSponsorships;
	Double						standardDeviationAmountSponsorships;
	Double						averageQuantityInvoices;
	Integer						minimumQuantityInvoices;
	Integer						maximumQuantityInvoices;
	Double						standardQuantityInvoices;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
