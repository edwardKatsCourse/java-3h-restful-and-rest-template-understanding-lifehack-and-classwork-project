package external.auth;

import external.exception.LoginException;
import external.exception.RegistrationException;

public interface AuthSender {
    boolean login(String email, String password) throws LoginException;
    boolean registration(String email, String password) throws RegistrationException;

}
