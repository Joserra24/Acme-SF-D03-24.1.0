
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

	int							numberOfInvoicesTaxLessEqual21;
	int							numberSponsorshipsWithLink;

	Double						averageAmountSponsorships;
	Double						deviationAmountSponsorships;
	Double						minimumAmountSponsorships;
	Double						maximumAmountSponsorships;

	Double						averageQuantityInvoices;
	Double						deviationQuantityInvoices;
	Double						minimumQuantityInvoices;
	Double						maximumQuantityInvoices;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
