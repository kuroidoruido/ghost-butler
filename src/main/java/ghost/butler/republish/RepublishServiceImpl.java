package ghost.butler.republish;

import javax.inject.Singleton;

@Singleton
public class RepublishServiceImpl implements RepublishService {

    @Override
    public void republish() {
        System.out.println("republish");
    }

}