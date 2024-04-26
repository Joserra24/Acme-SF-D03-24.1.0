
package acme.features.administrator.banner;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.Banner;

@Service
public class AdministratorBannerCreateService extends AbstractService<Administrator, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorBannerRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		final boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		final Banner banner;

		banner = new Banner();

		super.getBuffer().addData(banner);
	}
	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "instationUpdateMoment", "periodStart", "periodFinish", "linkPicture", "slogan", "linkDocument");

	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		final String periodStart = "periodStart";
		final String periodFinish = "periodFinish";

		if (!super.getBuffer().getErrors().hasErrors(periodStart) && !super.getBuffer().getErrors().hasErrors("instationUpdateMoment")) {

			final boolean startAfterInstation = MomentHelper.isAfter(object.getPeriodStart(), object.getInstationUpdateMoment());

			super.state(startAfterInstation, periodStart, "administrator.banner.form.error.start-before-instation");
		}

		if (!super.getBuffer().getErrors().hasErrors(periodFinish) && !super.getBuffer().getErrors().hasErrors("instationUpdateMoment")) {

			final boolean finishBeforeStart = MomentHelper.isAfter(object.getPeriodFinish(), object.getPeriodStart());

			super.state(finishBeforeStart, periodFinish, "administrator.banner.form.error.finish-before-start");

			if (finishBeforeStart) {

				final boolean startOneWeekBeforeFinish = MomentHelper.isLongEnough(object.getPeriodStart(), object.getPeriodFinish(), 7, ChronoUnit.DAYS);

				super.state(startOneWeekBeforeFinish, periodFinish, "administrator.banner.form.error.one-week-period");
			}
		}
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "instationUpdateMoment", "periodStart", "periodFinish", "linkPicture", "slogan", "linkDocument");

		super.getResponse().addData(dataset);
	}

}
