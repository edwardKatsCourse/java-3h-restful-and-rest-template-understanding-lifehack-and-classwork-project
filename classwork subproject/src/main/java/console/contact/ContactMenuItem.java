package console.contact;

import console.configuration.InputOutput;
import console.configuration.Item;
import service.contact.ContactService;

public class ContactMenuItem implements Item {
    private String name;
    private ContactService contactService;

    public ContactMenuItem(String name, ContactService contactService) {
        this.name = name;
        this.contactService = contactService;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void perform(InputOutput io) {
        contactService.callSender();
    }
}
