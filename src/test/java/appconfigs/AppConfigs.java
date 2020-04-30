package appconfigs;

import org.aeonbits.owner.Config;


@Config.Sources({"file:src/test/java/resources/config.properties"})
public interface AppConfigs extends Config {

    @Key("login")
    String login();

    @Key("pw")
    String pw();

    @Key("pathToPhoto")
    String pathToPhoto();

    @Key("firstName")
    String firstName();

    @Key("firstNameLatin")
    String firstNameLatin();

    @Key("lastname")
    String lastName();

    @Key("lastNameLatin")
    String lastNameLatin();

    @Key("birthDate")
    String birthDate();

    @Key("blogName")
    String blogName();

    @Key("firstContactType")
    String firstContactType();

    @Key("firstContactTypeValue")
    String firstContactTypeValue();

    @Key("secondContactType")
    String secondContactType();

    @Key("secondContactValue")
    String secondContactValue();
}
