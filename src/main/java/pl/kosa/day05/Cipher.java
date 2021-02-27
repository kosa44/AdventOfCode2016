package main.java.pl.kosa.day05;

import java.io.UnsupportedEncodingException;
import java.security.*;

public class Cipher {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public String getMD5Sum(String s) {
        String byteArrayToHex = "";
        try {
            byte[] bytesOfMessage = s.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] theDigest = md.digest(bytesOfMessage);
            byteArrayToHex = byteArrayToHex(theDigest);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return byteArrayToHex;
    }

    public String byteArrayToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    public String findPassword(String identifier){
        String password = "";
        String byteArrayToHex = "";
        String test = "";
        int nextLetterDecrypted = 0;
        for (int i = 0; nextLetterDecrypted < 8; i++ ) {
            System.out.println(i);
            byteArrayToHex = getMD5Sum(identifier+String.valueOf(i));
            System.out.println(nextLetterDecrypted);
            test = byteArrayToHex.substring(0, 5);
//            could be byteArrayToHex.startsWith
            if (test.equals("00000")) {
                nextLetterDecrypted += 1;
                password = password + (String.valueOf(byteArrayToHex.charAt(5)));
            }
        }
        System.out.println(password);
        return password;
    }
}
