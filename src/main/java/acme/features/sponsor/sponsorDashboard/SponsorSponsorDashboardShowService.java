
package acme.features.sponsor.sponsorDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.SponsorDashboard;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorDashboardShowService extends AbstractService<Sponsor, SponsorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorSponsorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		SponsorDashboard dashboard;
		int sponsorId;
		int sponsorshipsPublished;
		int invoicesPublished;

		int numberOfInvoicesTaxLessEqual21;
		int numberSponsorshipsWithLink;
		Double averageAmountSponsorships;
		Double deviationAmountSponsorships;
		Double minimumAmountSponsorships;
		Double maximumAmountSponsorships;
		Double averageQuantityInvoices;
		Double deviationQuantityInvoices;
		Double minimumQuantityInvoices;
		Double maximumQuantityInvoices;

		sponsorId = super.getRequest().getPrincipal().getActiveRoleId();
		sponsorshipsPublished = this.repository.findNumberPublishedSponsorshipsBySponsorId(sponsorId);
		invoicesPublished = this.repository.findNumberPublishedInvoicesBySponsorId(sponsorId);

		numberOfInvoicesTaxLessEqual21 = this.repository.numberOfInvoicesTaxLessEqual21(sponsorId);
		numberSponsorshipsWithLink = this.repository.numberSponsorshipsWithLink(sponsorId);

		if (sponsorshipsPublished >= 1) {
			averageAmountSponsorships = this.repository.averageAmountSponsorships(sponsorId);
			deviationAmountSponsorships = this.repository.deviationAmountSponsorships(sponsorId);
			minimumAmountSponsorships = this.repository.minimumAmountSponsorships(sponsorId);
			maximumAmountSponsorships = this.repository.maximumAmountSponsorships(sponsorId);
		} else {
			averageAmountSponsorships = null;
			deviationAmountSponsorships = null;
			minimumAmountSponsorships = null;
			maximumAmountSponsorships = null;
		}

		if (invoicesPublished >= 1) {
			averageQuantityInvoices = this.repository.averageQuantityInvoices(sponsorId);
			deviationQuantityInvoices = this.repository.deviationQuantityInvoices(sponsorId);
			minimumQuantityInvoices = this.repository.minimumQuantityInvoices(sponsorId);
			maximumQuantityInvoices = this.repository.maximumQuantityInvoices(sponsorId);
		} else {
			averageQuantityInvoices = null;
			deviationQuantityInvoices = null;
			minimumQuantityInvoices = null;
			maximumQuantityInvoices = null;
		}

		dashboard = new SponsorDashboard();
		dashboard.setNumberOfInvoicesTaxLessEqual21(numberOfInvoicesTaxLessEqual21);
		dashboard.setNumberSponsorshipsWithLink(numberSponsorshipsWithLink);
		dashboard.setAverageAmountSponsorships(averageAmountSponsorships);
		dashboard.setDeviationAmountSponsorships(deviationAmountSponsorships);
		dashboard.setMinimumAmountSponsorships(minimumAmountSponsorships);
		dashboard.setMaximumAmountSponsorships(maximumAmountSponsorships);
		dashboard.setAverageQuantityInvoices(averageQuantityInvoices);
		dashboard.setDeviationQuantityInvoices(deviationQuantityInvoices);
		dashboard.setMinimumQuantityInvoices(minimumQuantityInvoices);
		dashboard.setMaximumQuantityInvoices(maximumQuantityInvoices);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final SponsorDashboard object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "numberOfInvoicesTaxLessEqual21", "numberSponsorshipsWithLink", // 
			"averageAmountSponsorships", "deviationAmountSponsorships", "minimumAmountSponsorships",  //
			"maximumAmountSponsorships", "averageQuantityInvoices", "deviationQuantityInvoices", //
			"minimumQuantityInvoices", "maximumQuantityInvoices");

		super.getResponse().addData(dataset);
	}

}
