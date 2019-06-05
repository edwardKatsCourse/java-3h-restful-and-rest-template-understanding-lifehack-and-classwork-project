import console.contact.ContactMenuItem;
import service.auth.AuthService;

import console.auth.AuthMenuItem;
import console.configuration.InputOutput;
import console.configuration.Item;
import console.configuration.Menu;
import service.contact.ContactService;

public class App {
    public static void main(String[] args) {
        Menu main = new Menu("Authorization",
                new AuthMenuItem(Context.get(AuthService.class),"Registration", AuthMenuItem.Mode.REGISTRATION,
                        new Menu("Fake Menu",
                                Item.of("Add contact",io->{}),
                                Item.exit())),

                new AuthMenuItem(Context.get(AuthService.class),"Login", AuthMenuItem.Mode.LOGIN,
                        new Menu("Contact Menu",
                                new ContactMenuItem("Print Contact", Context.get(ContactService.class)),
                                Item.exit())),
                Item.exit()
        );
        main.perform(Context.get(InputOutput.class));
    }
}
