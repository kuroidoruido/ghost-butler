package ghost.butler.providers;

import java.time.DayOfWeek;
import java.util.List;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Property;

@Singleton
public class ConfigurationProvider {

    @Property(name = "ghost-butler.republish-days")
    protected List<DayOfWeek> republishDays;

    public List<DayOfWeek> getRepublishDays() {
        return this.republishDays;
    }
}