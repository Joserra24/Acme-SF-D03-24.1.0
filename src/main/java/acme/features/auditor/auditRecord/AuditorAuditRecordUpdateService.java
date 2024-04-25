
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
public class AuditorAuditRecordUpdateService extends AbstractService<Auditor, AuditRecord> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuditorAuditRecordRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int auditRecordId;
		AuditRecord object;
		CodeAudit codeAudit;

		auditRecordId = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditRecordById(auditRecordId);
		codeAudit = object == null ? null : object.getCodeAudit();
		status = super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor()) || object != null && !object.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditRecordById(id);
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		super.bind(object, "code", "initialPeriod", "finalPeriod", "mark", "optionalLink", "draftMode");
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {

			AuditRecord auditRecordWithCodeDuplicated = this.repository.findOneAuditRecordByCode(object.getCode());

			if (auditRecordWithCodeDuplicated != null)
				super.state(auditRecordWithCodeDuplicated.getId() == object.getId(), "code", "auditor.audit-record.form.error.code");
		}

		if (!super.getBuffer().getErrors().hasErrors("finalPeriod") && object.getFinalPeriod() != null)
			super.state(MomentHelper.isAfter(object.getFinalPeriod(), object.getInitialPeriod()), "finalPeriod", "auditor.audit-record.form.error.invalidFinalPeriod");
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;
		this.repository.save(object);
	}

	//	SELECTCHOICES DE MARK
	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		Dataset dataset;
		Collection<CodeAudit> codeAudits;
		SelectChoices choices;

		codeAudits = this.repository.findAllCodeAudits();
		choices = SelectChoices.from(codeAudits, "code", object.getCodeAudit());

		dataset = super.unbind(object, "code", "initialPeriod", "finalPeriod", "mark", "optionalLink", "draftMode");
		dataset.put("codeAudit", choices.getSelected().getKey());
		dataset.put("codeAudits", choices);
		super.getResponse().addData(dataset);
	}
}
