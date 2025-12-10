package Other;

import Member.*;
import javafx.scene.control.Label;

public class Exceptions {
    MembershipService membershipService = new MembershipService(new MemberRegistry());
    MemberRegistry memberRegistry = new MemberRegistry();

    public void searchMException () {
        if (memberRegistry.getMembers() == null) {
            Label searchML = new Label("Användaren finns inte");
        }
    }
}
