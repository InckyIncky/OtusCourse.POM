package TestRun;

import Pages.LoginPage;
import Pages.MyProfile;
import Pages.MyWorkroom;
import org.junit.jupiter.api.Test;

public class TestRunner extends BaseClass {
    String firstname = "Nikolay";
    String firstnameLatin = "Nikolay";
    String lastname = "Antonov";
    String lastnameLatin = "Antonov";
    String birthDate ="25.04.1989";
    String blogName = "Nikolay";
    String pathToPhoto = "C:\\Users\\nantonov\\Desktop\\PassBild\\ogurtsov.jpg";


    @Test
    public void run() {
        LoginPage.login();

        MyWorkroom.openMyProfile();

        MyProfile.fillData(firstname, firstnameLatin, lastname, lastnameLatin, birthDate, blogName, pathToPhoto);
    }

}
