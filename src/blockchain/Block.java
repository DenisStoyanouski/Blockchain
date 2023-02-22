package blockchain;

import java.security.MessageDigest;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Block {

    private int id;

    private long magicNumber;

    private String hashOfPreviousBlock;

    private String hashCode;

    private Pattern pattern;

    private long timeStamp;

    public Block(int id, String hashOfPreviousBlock, int numberOfZero) {
        this.id = id;
        this.hashOfPreviousBlock = hashOfPreviousBlock;
        this.timeStamp = new Date().getTime();
        this.pattern = Pattern.compile("0{%d}.*".formatted(numberOfZero));
        setHashCode();

    }

    private void setHashCode() {
        String allFields;
        Matcher matcher;
        do {
            magicNumber = getMagicNumber();
            allFields = String.valueOf(id) + hashOfPreviousBlock + timeStamp + magicNumber;
            hashCode = applySha256(allFields);
            matcher = pattern.matcher(hashCode);
        } while (!matcher.matches());
    }

    public String getHashCode() {
        return hashCode;
    }

    public String getHashOfPreviousBlock() {
        return hashOfPreviousBlock;
    }

    private static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Block:\n" +
                "Id: %d\n" +
                "Timestamp: %d\n" +
                "Magic number: %d\n" +
                "Hash of the previous block:\n%s\n" +
                "Hash of the block:\n%s\n" +
                "Block was generating for %d seconds\n",
                id,timeStamp, magicNumber, hashOfPreviousBlock, hashCode, (new Date().getTime() - timeStamp) / 1000);
    }

    private long getMagicNumber() {
        return ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
    }


}
