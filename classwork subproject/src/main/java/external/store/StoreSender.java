package external.store;

import external.exception.StoreException;

public interface StoreSender {
    void saveToken(String token) throws StoreException;
    String getCurrentToken() throws StoreException;
    void clearToken() throws StoreException;
}
