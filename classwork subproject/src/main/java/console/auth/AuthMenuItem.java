package console.auth;

import service.auth.AuthService;
import service.exception.ServiceException;
import console.configuration.InputOutput;
import console.configuration.Item;

public class AuthMenuItem implements Item {
    public static enum Mode {
        REGISTRATION, LOGIN
    }

    private final String name;
    private Item subMenu;
    private final Mode mode;
    private final AuthService service;

    public AuthMenuItem(AuthService service, String name, Mode mode, Item subMenu) {
        this.subMenu = subMenu;
        this.service = service;
        this.name = name;
        this.mode = mode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void perform(InputOutput io) {
        subMenu = this;
        String email = io.readString("Email: ");
        String password = io.readString("Password: ");
        try {
            boolean res = false;
            switch (mode) {
                case REGISTRATION :
                    res = service.registration(email, password);
                case LOGIN :
                    res = service.login(email, password);
            };

            if (res) {
                io.writeln("Authorization success!");
                subMenu.perform(io);
            }
        } catch (ServiceException ex) {
            Throwable cause = ex.getCause();
            if (cause != null) {
                io.writeln(cause.getMessage());
            } else {
                io.writeln(ex.getMessage());
            }
        }
    }
}
