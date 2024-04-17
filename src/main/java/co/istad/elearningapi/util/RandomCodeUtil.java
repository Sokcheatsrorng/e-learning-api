package co.istad.elearningapi.util;

import java.util.Random;

public class RandomCodeUtil {
    // Define character set
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 4; // Length of the random code

    public static String generateRandomCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        // Generate code of specified length
        for (int i = 0; i < CODE_LENGTH; i++) {
            // Generate random index to select character from character set
            int randomIndex = random.nextInt(CHARACTERS.length());
            // Append selected character to code
            codeBuilder.append(CHARACTERS.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }
}
