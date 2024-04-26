
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.AuditRecord;
import acme.entities.CodeAudit;
import acme.entities.Project;
import acme.enumerated.Mark;
import acme.enumerated.Type;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditPublishService extends AbstractService<Auditor, CodeAudit> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuditorCodeAuditRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		final boolean status;
		int codeAuditId;
		CodeAudit object;
		Auditor auditor;

		codeAuditId = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCodeAuditById(codeAuditId);
		auditor = object == null ? null : object.getAuditor();
		status = object != null && object.isDraftMode() && super.getRequest().getPrincipal().hasRole(auditor);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		CodeAudit object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCodeAuditById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final CodeAudit object) {
		assert object != null;

		super.bind(object, "code", "execution", "type", "correctiveActions", "optionalLink");
	}

	@Override
	public void validate(final CodeAudit object) {
		assert object != null;
		boolean draftModeAuditRecord;
		Collection<AuditRecord> auditRecords = this.repository.findManyAuditRecordsByCodeAuditId(object.getId());

		for (AuditRecord ar : auditRecords)
			super.state(!ar.isDraftMode(), "code", "auditor.code-audit.form.error.draftMode");

		if (!auditRecords.isEmpty()) {
			draftModeAuditRecord = auditRecords.stream().anyMatch(x -> !x.isDraftMode());
			super.state(draftModeAuditRecord, "*", "auditor.code-audit.form.error.auditRecordInDraftMode");
		}

		if (!super.getBuffer().getErrors().hasErrors("mark")) {
			Mark mark = object.getMark(auditRecords);
			super.state(mark == Mark.A || mark == Mark.A_PLUS || mark == Mark.B || mark == Mark.C, "mark", "auditor.code-audit.form.error.markNotAtLeastC");
		}
	}

	@Override
	public void perform(final CodeAudit object) {
		assert object != null;

		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final CodeAudit object) {

		assert object != null;

		Dataset dataset;
		Collection<Project> projects;
		SelectChoices choices;
		Collection<AuditRecord> auditRecords = this.repository.findManyAuditRecordsByCodeAuditId(object.getId());
		SelectChoices choicesType;

		projects = this.repository.findManyProjectsAvailable();

		choicesType = SelectChoices.from(Type.class, object.getType());
		choices = SelectChoices.from(projects, "title", object.getProject());

		dataset = super.unbind(object, "code", "execution", "type", "correctiveActions", "optionalLink", "draftMode");
		dataset.put("type", choicesType);
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("mark", object.getMark(auditRecords));

		super.getResponse().addData(dataset);
	}

}
