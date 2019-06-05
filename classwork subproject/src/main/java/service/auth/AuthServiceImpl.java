package service.auth;

import service.exception.EmailFormatException;
import service.exception.PasswordFormatException;
import service.exception.ServiceException;
import external.auth.AuthSender;
import external.exception.RepositoryException;

public class AuthServiceImpl implements AuthService{
    AuthSender repository;

    public AuthServiceImpl(AuthSender repository) {
        this.repository = repository;
    }

    @Override
    public boolean registration(String email, String password) throws ServiceException {
        if(!emailValidation(email)){
            throw new EmailFormatException();
        }
        if(!passwordValidation(password)){
            throw new PasswordFormatException();
        }
        try {
            return repository.registration(email,password);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public boolean login(String email, String password) throws ServiceException {
        if(!emailValidation(email)){
            throw new EmailFormatException();
        }
        if(!passwordValidation(password)){
            throw new PasswordFormatException();
        }
        try {
            return repository.login(email,password);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    private boolean emailValidation(String email){
        return email != null && email.contains("@");
    }

    private boolean passwordValidation(String password){
        return password != null && password.length() >= 8;
    }
}
