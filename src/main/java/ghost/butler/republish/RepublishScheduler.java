package ghost.butler.republish;

import io.micronaut.scheduling.annotation.Scheduled;

import javax.inject.Singleton;

import ghost.butler.providers.ConfigurationProvider;
import ghost.butler.providers.TimeProvider;

@Singleton
public class RepublishScheduler {
    private TimeProvider timeProvider;
    private ConfigurationProvider configurationProvider;
    private RepublishService republishService;

    public RepublishScheduler(TimeProvider timeProvider, ConfigurationProvider configurationProvider, RepublishService republishService) {
        this.timeProvider = timeProvider;
        this.configurationProvider = configurationProvider;
        this.republishService = republishService;
    }

    @Scheduled(fixedDelay = "1d")
    public void everyDays() {
        var authorizedDaysOfWeek = configurationProvider.getRepublishDays();
        var currentDayOfWeek = this.timeProvider.today().getDayOfWeek();

        if (authorizedDaysOfWeek.contains(currentDayOfWeek)) {
            this.republishService.republish();
        }
    }
}