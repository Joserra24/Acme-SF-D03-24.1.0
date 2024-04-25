
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.AuditRecord;
import acme.entities.CodeAudit;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordShowService extends AbstractService<Auditor, AuditRecord> {

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

	//CHOICES DE MARK
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
