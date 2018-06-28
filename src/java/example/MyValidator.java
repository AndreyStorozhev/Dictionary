package example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("validator")
public class MyValidator {
    private final FileManager fileManager;
    private String keyMath;
    private int keyLength;
    private String error;

    @Autowired
    public MyValidator(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setKeyMath(String keyMath, int keyLength) {
        this.keyMath = keyMath;
        this.keyLength = keyLength;
    }
    public boolean validateKey(String key) {
        if (key.length() != keyLength) {
            error = fileManager.getValidMessage(".length");
            return false;
        }
        else if (!key.matches(keyMath)) {
            error = fileManager.getValidMessage(".math");
            return false;
        } else
            return true;
    }

    public String getError() {
        return error;
    }
}
