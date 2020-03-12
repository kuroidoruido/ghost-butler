package ghost.butler.providers;

import java.time.LocalDate;

import javax.inject.Singleton;

@Singleton
public class TimeProviderImpl implements TimeProvider {

    @Override
    public LocalDate today() {
        return LocalDate.now();
    }
}