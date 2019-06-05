import external.contact.ContactSender;
import external.contact.ContactSenderImpl;
import service.auth.AuthService;
import service.auth.AuthServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import external.auth.AuthSender;
import external.auth.AuthSenderImpl;
import external.store.StoreSender;
import external.store.StoreSenderImpl;
import org.springframework.web.client.RestTemplate;
import console.configuration.ConsoleInputOutput;
import console.configuration.InputOutput;
import service.contact.ContactService;
import service.contact.ContactServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Context {
    private static final Map<Class, ? super Object> map;

    static {
        InputOutput io = new ConsoleInputOutput();
        StoreSender storeSender = new StoreSenderImpl();
        AuthSender authSender = new AuthSenderImpl(storeSender, new RestTemplate(), new ObjectMapper());
        AuthService authService = new AuthServiceImpl(authSender);

        ContactSender contactSender = new ContactSenderImpl();
        ContactService contactService = new ContactServiceImpl(contactSender);

        map = new HashMap<>();
        map.put(StoreSender.class, storeSender);
        map.put(AuthService.class, authService);
        map.put(AuthSender.class, authSender);
        map.put(InputOutput.class, io);

        map.put(ContactSender.class, contactSender);
        map.put(ContactService.class, contactService);
    }

    private Context() {
        throw new UnsupportedOperationException();
    }

    public static <T extends Object> T get(Class<T> clazz) {
        Objects.requireNonNull(clazz);
        return (T) map.get(clazz);
    }
}
