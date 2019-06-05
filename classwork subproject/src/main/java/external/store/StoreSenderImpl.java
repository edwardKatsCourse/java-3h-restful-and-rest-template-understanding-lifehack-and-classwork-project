package external.store;

import external.exception.StoreException;

public class StoreSenderImpl implements StoreSender {
    private String token;
    @Override
    public void saveToken(String token) throws StoreException {
        this.token = token;
    }

    @Override
    public String getCurrentToken() throws StoreException {
        return token;
    }

    @Override
    public void clearToken() throws StoreException {
        token = null;
    }
}
