package data;

import dataobjects.User;
import org.testng.annotations.DataProvider;

public class UserData {

    @DataProvider(name="InputDataProvider")
    public static Object[][] getUserData(){

        return new Object[][]{
                {new User("User 1", "user1@gmail.com")},
                {new User("User 2", "user2@gmail.com")},
                {new User("User 3", "user3@gmail.com")}
        };
    }
}
