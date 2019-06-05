package service.auth;

import service.exception.ServiceException;

public interface AuthService {
    boolean registration(String email, String password) throws ServiceException;
    boolean login(String email, String password) throws ServiceException;
}
