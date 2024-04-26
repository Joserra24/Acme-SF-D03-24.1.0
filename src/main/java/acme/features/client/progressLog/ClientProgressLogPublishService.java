
package acme.features.client.progressLog;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogPublishService extends AbstractService<Client, ProgressLog> {

	@Autowired
	private ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Contract contract = null;
		Principal principal;
		ProgressLog progressLog;

		id = super.getRequest().getData("id", int.class);
		progressLog = this.repository.findProgressLogById(id);

		if (progressLog != null)
			contract = progressLog.getContract();

		principal = super.getRequest().getPrincipal();

		status = progressLog != null && contract.getClient().getId() == principal.getActiveRoleId() && progressLog.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int id;
		ProgressLog progressLog;

		id = super.getRequest().getData("id", int.class);
		progressLog = this.repository.findProgressLogById(id);

		super.getBuffer().addData(progressLog);
	}

	@Override
	public void bind(final ProgressLog object) {
		assert object != null;

		super.bind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson");

	}

	@Override
	public void validate(final ProgressLog object) {
		boolean isCodeChanged = false;

		final Collection<String> allCodes = this.repository.findAllProgressLogCodes();
		final ProgressLog progressLog = this.repository.findProgressLogById(object.getId());

		if (!super.getBuffer().getErrors().hasErrors("recordId")) {
			isCodeChanged = !progressLog.getRecordId().equals(object.getRecordId());
			super.state(!isCodeChanged || !allCodes.contains(object.getRecordId()), "recordId", "client.progressLog.error.recordIdDuplicate");
		}

	}

	@Override
	public void perform(final ProgressLog object) {
		assert object != null;

		object.setDraftMode(false);

		this.repository.save(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;

		Dataset dataset;
		Contract objectContract = object.getContract();

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "draftmode");
		dataset.put("contractCode", objectContract.getCode());
		super.getResponse().addData(dataset);
	}

}
