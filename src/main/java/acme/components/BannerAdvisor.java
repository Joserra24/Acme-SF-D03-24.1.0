
package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.entities.Banner;

@ControllerAdvice
public class BannerAdvisor {

	@Autowired
	protected BannerRepository repository;


	@ModelAttribute("banner")
	public Banner getBanner() {
		Banner result;

		try {
			result = this.repository.findRandomAdvertisement();
		} catch (final Throwable oops) {
			result = null;
		}
		return result;
	}
}
