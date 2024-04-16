
package acme.features.administrator.risk;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.Risk;

@Service
public class AdministratorRiskCreateService extends AbstractService<Administrator, Risk> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorRiskRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Risk object;

		object = new Risk();
		object.setReference("");
		//object.setMoment(moment);
		//object.setStatus(AnnouncementStatus.INFO);
		//object.setText("");
		//object.setMoreInfo("");

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Risk object) {
		assert object != null;

		super.bind(object, "title", "status", "text", "moreInfo");
	}

	@Override
	public void validate(final Risk object) {
		assert object != null;

		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void perform(final Risk object) {
		assert object != null;

		Date moment;

		moment = MomentHelper.getCurrentMoment();
		//object.setMoment(moment);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Risk object) {
		assert object != null;

		SelectChoices choices;
		Dataset dataset;

		dataset = super.unbind(object, "title", "status", "text", "moreInfo");
		dataset.put("confirmation", false);
		dataset.put("readonly", false);

		super.getResponse().addData(dataset);
	}

}
