
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.CodeAudit;
import acme.entities.Project;
import acme.enumerated.Type;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditUpdateService extends AbstractService<Auditor, CodeAudit> {

	// Internal state ---------------------------------------------------------
	@Autowired
	protected AuditorCodeAuditRepository repository;

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
		CodeAudit codeAudit;
		int id;

		id = this.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditById(id);

		super.getBuffer().addData(codeAudit);
	}

	@Override
	public void bind(final CodeAudit object) {
		assert object != null;

		super.bind(object, "code", "execution", "type", "correctiveActions", "optionalLink", "draftMode", "project");
	}

	@Override
	public void validate(final CodeAudit object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {

			CodeAudit codeAuditWithCodeDuplicated = this.repository.findOneCodeAuditByCode(object.getCode());

			if (codeAuditWithCodeDuplicated != null)
				super.state(codeAuditWithCodeDuplicated.getId() == object.getId(), "code", "auditor.code-audit.form.error.code");
		}

		/*
		 * Hacer el de execution (Date)
		 * 
		 * if (!super.getBuffer().getErrors().hasErrors("conclusion")) {
		 * boolean status;
		 * String message;
		 * 
		 * message = object.getConclusion();
		 * status = this.configuration.hasSpam(message);
		 * 
		 * super.state(!status, "conclusion", "auditor.audit.error.spam");
		 * }
		 */
	}

	@Override
	public void perform(final CodeAudit object) {
		assert object != null;
		object.setExecution(MomentHelper.getCurrentMoment());

		this.repository.save(object);
	}

	@Override
	public void unbind(final CodeAudit object) {
		assert object != null;

		Dataset dataset;
		Collection<Project> projects;
		SelectChoices choices;
		SelectChoices choicesType;

		projects = this.repository.findManyProjectsAvailable();

		choicesType = SelectChoices.from(Type.class, object.getType());
		choices = SelectChoices.from(projects, "title", object.getProject());

		dataset = super.unbind(object, "code", "execution", "type", "correctiveActions", "optionalLink", "draftMode", "project");
		dataset.put("type", choicesType);
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

}
