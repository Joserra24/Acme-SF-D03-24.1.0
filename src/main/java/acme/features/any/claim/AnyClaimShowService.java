
package acme.features.any.claim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.Claim;

@Service
public class AnyClaimShowService extends AbstractService<Any, Claim> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyClaimRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Claim object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneClaimById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final Claim object) {
		assert object != null;

		Dataset dataset;
		dataset = super.unbind(object, "code", "instantiationMoment", "heading", "description", "department", "email", "link");
		dataset.put("readonly", true);

		super.getResponse().addData(dataset);
	}
}
