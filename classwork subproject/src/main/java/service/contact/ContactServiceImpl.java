package service.contact;

import external.contact.ContactSender;

public class ContactServiceImpl implements ContactService {

    private ContactSender contactSender;

    public ContactServiceImpl(ContactSender contactSender) {
        this.contactSender = contactSender;
    }

    @Override
    public void callSender() {
        contactSender.printContacts();
    }
}
