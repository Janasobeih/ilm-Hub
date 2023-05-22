public class PasswordHandling {
    public String encrypt(String s) {
        StringBuilder temp = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            int encryptedKey = (int) s.charAt(i) + 4;
            char encryptedChar = (char) encryptedKey;
            temp.setCharAt(i, encryptedChar);
        }
        return temp.toString();
    }
    public String decrypt(String s) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int decryptedKey = (int) s.charAt(i) - 4;
            char decryptedChar = (char) decryptedKey;
            decrypted.append(decryptedChar);
        }
        return decrypted.toString();
    }
}
