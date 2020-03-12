package ghost.butler;

import javax.inject.Inject;

import ghost.butler.republish.RepublishScheduler;
import io.micronaut.runtime.Micronaut;

public class Application {

    @Inject
    RepublishScheduler republishScheduler;

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}