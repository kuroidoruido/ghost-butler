package ghost.butler.providers;

import java.time.LocalDate;

public interface TimeProvider {
    LocalDate today();
}