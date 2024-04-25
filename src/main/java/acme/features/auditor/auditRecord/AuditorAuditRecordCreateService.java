
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.AuditRecord;
import acme.entities.CodeAudit;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordCreateService extends AbstractService<Auditor, AuditRecord> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuditorAuditRecordRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		final boolean status;

		status = super.getRequest().getPrincipal().hasRole(Auditor.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;

		object = new AuditRecord();
		object.setDraftMode(true);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		super.bind(object, "code", "initialPeriod", "finalPeriod", "mark", "optionalLink", "draftMode", "codeAudit");
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			AuditRecord existing;

			existing = this.repository.findOneAuditRecordByCode(object.getCode());
			super.state(existing == null, "code", "auditor.audit-record.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("finalPeriod") && object.getFinalPeriod() != null)
			super.state(MomentHelper.isAfter(object.getFinalPeriod(), object.getInitialPeriod()), "finalPeriod", "auditor.audit-record.form.error.invalidFinalPeriod");
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;

		this.repository.save(object);
	}

	//PONER SELECTCHOICES DE MARK COMO EN CODE AUDIT
	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		Dataset dataset;
		Collection<CodeAudit> codeAudits;
		SelectChoices choices;

		codeAudits = this.repository.findManyCodeAuditAvailable2();
		choices = SelectChoices.from(codeAudits, "code", object.getCodeAudit());
		System.out.println(choices.getSelected().getKey());

		dataset = super.unbind(object, "code", "initialPeriod", "finalPeriod", "mark", "optionalLink", "draftMode", "codeAudit");
		dataset.put("codeAudit", choices.getSelected().getKey());
		dataset.put("codeAudits", choices);
		super.getResponse().addData(dataset);
	}
}
