package Logic;

import shekho.com.universityManager.DAL.Database;
import Model.User;

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
