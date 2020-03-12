package ghost.butler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import ghost.butler.providers.*;
import ghost.butler.republish.RepublishScheduler;
import ghost.butler.republish.RepublishService;

public class RepublicationSchedulerTest {

    @Test()
    public void should_republish_when_today_is_one_of_the_wanted_days() {
        var timeProvider = new TimeProvider() {
			public LocalDate today() { return LocalDate.of(2020,3,10); }
        };
        var configurationProvider = new ConfigurationProvider() {
            public List<DayOfWeek> getRepublishDays() {
               return List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY); 
            }
        };
        var republishService = mock(RepublishService.class);
        var scheduler = new RepublishScheduler(timeProvider, configurationProvider, republishService);

        scheduler.everyDays();

        verify(republishService, times(1)).republish();
    }

    @Test()
    public void should_not_republish_when_today_is_not_one_of_the_wanted_days() {
        var timeProvider = new TimeProvider() {
			public LocalDate today() { return LocalDate.of(2020,3,11); }
        };
        var configurationProvider = new ConfigurationProvider() {
            public List<DayOfWeek> getRepublishDays() {
               return List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY); 
            }
        };
        var republishService = mock(RepublishService.class);
        var scheduler = new RepublishScheduler(timeProvider, configurationProvider, republishService);

        scheduler.everyDays();

        verify(republishService, never()).republish();
    }
    
}