
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.AuditRecord;
import acme.entities.CodeAudit;
import acme.enumerated.Mark;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordCreateService extends AbstractService<Auditor, AuditRecord> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuditorAuditRecordRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		CodeAudit codeAudit;
		Principal principal;
		int id;

		id = super.getRequest().getData("codeAuditId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(id);
		principal = super.getRequest().getPrincipal();
		status = codeAudit.getAuditor().getId() == principal.getActiveRoleId();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int codeAuditId;
		AuditRecord object;
		CodeAudit codeAudit;

		codeAuditId = super.getRequest().getData("codeAuditId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(codeAuditId);

		object = new AuditRecord();
		object.setCodeAudit(codeAudit);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		int id;
		CodeAudit codeAudit;

		id = super.getRequest().getData("codeAuditId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(id);

		super.bind(object, "code", "initialPeriod", "finalPeriod", "mark", "optionalLink");
		object.setCodeAudit(codeAudit);
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			AuditRecord isCodeUnique;
			isCodeUnique = this.repository.findOneAuditRecordByCode(object.getCode());
			super.state(isCodeUnique == null, "code", "auditor.audit-record.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("initialPeriod"))
			super.state(MomentHelper.isAfter(object.getFinalPeriod(), object.getInitialPeriod()), "initialPeriod", "auditor.audit-record.form.error.invalidFinalPeriod");
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;

		object.setDraftMode(true);
		this.repository.save(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		Dataset dataset;
		SelectChoices choices;
		choices = SelectChoices.from(Mark.class, object.getMark());

		CodeAudit codeAudit = object.getCodeAudit();

		dataset = super.unbind(object, "code", "initialPeriod", "finalPeriod", "mark", "optionalLink", "draftMode");
		dataset.put("codeAuditCode", codeAudit.getCode());
		dataset.put("marks", choices);
		dataset.put("codeAuditId", codeAudit.getId());

		super.getResponse().addData(dataset);
	}
}
