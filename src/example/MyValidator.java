package example;

public class MyValidator {
    private String keyMath;
    private int keyLength;
    private FileManager fileManager;

    public MyValidator(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setKeyMath(String keyMath, int keyLength) {
        this.keyMath = keyMath;
        this.keyLength = keyLength;
    }
    public boolean validateKey(String key) {
        if (key.length() != keyLength) {
            System.out.println(fileManager.getValidMessage(".length"));
            return false;
        }
        else if (!key.matches(keyMath)) {
            System.out.println(fileManager.getValidMessage(".math"));
            return false;
        } else
            return true;
    }
}
