
package acme.features.client.progressLog;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogCreateService extends AbstractService<Client, ProgressLog> {

	@Autowired
	private ClientProgressLogRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Contract contract;
		Principal principal;

		id = super.getRequest().getData("contractId", int.class);
		contract = this.repository.findContractById(id);

		principal = super.getRequest().getPrincipal();

		status = contract.getClient().getId() == principal.getActiveRoleId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void validate(final ProgressLog object) {

		final Collection<String> allCodes = this.repository.findAllProgressLogCodes();

		if (!super.getBuffer().getErrors().hasErrors("recordId"))
			super.state(!allCodes.contains(object.getRecordId()), "recordId", "client.progressLog.error.recordIdDuplicate");

	}

	@Override
	public void load() {
		int id;
		ProgressLog progressLog;
		Date moment;
		Contract contract;

		id = super.getRequest().getData("contractId", int.class);
		contract = this.repository.findContractById(id);
		progressLog = new ProgressLog();

		moment = MomentHelper.getCurrentMoment();
		progressLog.setRegistrationMoment(moment);
		progressLog.setContract(contract);

		super.getBuffer().addData(progressLog);
	}

	@Override
	public void perform(final ProgressLog object) {
		assert object != null;

		Date moment;

		moment = MomentHelper.getCurrentMoment();
		object.setRegistrationMoment(moment);
		object.setDraftMode(true);

		this.repository.save(object);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;

		Dataset dataset;
		Contract objectContract = object.getContract();

		dataset = super.unbind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson", "draftmode");
		dataset.put("contractCode", objectContract.getCode());
		dataset.put("contractId", objectContract.getId());
		super.getResponse().addData(dataset);
	}

	@Override
	public void bind(final ProgressLog object) {
		assert object != null;

		int id;
		Contract contract;

		id = super.getRequest().getData("contractId", int.class);
		contract = this.repository.findContractById(id);

		super.bind(object, "recordId", "completeness", "comment", "registrationMoment", "responsiblePerson");
		object.setContract(contract);
	}

}
