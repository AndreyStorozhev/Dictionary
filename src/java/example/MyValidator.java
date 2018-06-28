package example;

public class MyValidator {
    private String keyMath;
    private int keyLength;
    private FileManager fileManager;
    private String error;

    public MyValidator() {
        fileManager = new FileManager();
        System.out.println();
        System.out.println();
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
