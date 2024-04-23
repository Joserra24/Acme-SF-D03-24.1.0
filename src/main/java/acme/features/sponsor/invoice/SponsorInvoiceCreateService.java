
package acme.features.sponsor.invoice;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.Invoice;
import acme.entities.Sponsorship;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceCreateService extends AbstractService<Sponsor, Invoice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorInvoiceRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Sponsorship sponsorship;

		masterId = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(masterId);
		status = sponsorship != null && sponsorship.isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsorship.getSponsor());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Invoice object;
		int masterId;
		Sponsorship sponsorship;
		Money money;

		masterId = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(masterId);
		money = new Money();
		money.setAmount(0.0);
		money.setCurrency("EUR");
		object = new Invoice();

		object.setQuantity(money);
		object.setDraftMode(true);
		object.setSponsorship(sponsorship);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Invoice object) {
		assert object != null;
		super.bind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link", "draftMode");
	}

	@Override
	public void validate(final Invoice object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Invoice existing;
			existing = this.repository.findOneInvoiceByCode(object.getCode());
			super.state(existing == null, "code", "sponsor.invoice.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("dueDate")) {
			Date minimumDeadline;
			if (object.getRegistrationTime() == null)
				super.state(false, "dueDate", "sponsor.invoice.form.error.too-short");
			else {
				minimumDeadline = MomentHelper.deltaFromMoment(object.getRegistrationTime(), 30, ChronoUnit.DAYS);
				super.state(MomentHelper.isAfter(object.getDueDate(), minimumDeadline), "dueDate", "sponsor.invoice.form.error.too-short");
			}
		}

		if (!super.getBuffer().getErrors().hasErrors("quantity"))
			super.state(object.getQuantity().getAmount() > 0, "quantity", "sponsor.invoice.form.error.negative-amount");
	}

	@Override
	public void perform(final Invoice object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link", "draftMode");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));
		dataset.put("draftMode", object.getSponsorship().isDraftMode());
		dataset.put("totalAmount", object.totalAmount());
		dataset.put("sponsorship", object.getSponsorship().getCode());

		super.getResponse().addData(dataset);
	}

}
