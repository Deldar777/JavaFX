package Logic;

import DAL.Database;
import Model.User;

import javax.xml.crypto.Data;
import java.util.List;

public class Login {

    Database database = new Database();

    public User validateLogin(String username, String password){

        User user;

        for (User u:database.getUsers()
             ) {

            if (u.checkUsername(username)){
                user = u;
                if (user.checkPassword(password))
                    return user;
            }
        }

        return null;
    }
}
