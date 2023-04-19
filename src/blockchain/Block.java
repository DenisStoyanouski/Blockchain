package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Block {
    final private int id;
    private long magicNumber;
    private final String minerNumber;
    final private String hashOfPreviousBlock;
    private String hashCode;
    final private int numberOfZero;
    final private long timeStamp;
    private long timeOfCreating;
    private String blockData;

    public Block(int id, String hashOfPreviousBlock, int numberOfZero, String minerNumber, String blockData) {
        this.id = id;
        this.hashOfPreviousBlock = hashOfPreviousBlock;
        this.timeStamp = new Date().getTime();
        this.numberOfZero = numberOfZero;
        this.minerNumber = minerNumber;
        this.blockData = blockData;
        setHashCode();

    }

    public long getTimeOfCreating() {
        return timeOfCreating;
    }

    private void setHashCode() {
        Pattern pattern = Pattern.compile(String.format("0{%d}.*", numberOfZero));
        String allFields;
        Matcher matcher;
        do {
            magicNumber = getMagicNumber();
            allFields = id + hashOfPreviousBlock + timeStamp + blockData + magicNumber;
            hashCode = applySha256(allFields);
            matcher = pattern.matcher(hashCode);
        } while (!matcher.matches());
        timeOfCreating = (new Date().getTime() - timeStamp) / 1000;
    }

    public void setBlockData(List<String> messages) {
        blockData = String.join("\n", messages);
    }

    public String getHashCode() {
        return hashCode;
    }

    public String getHashOfPreviousBlock() {
        return hashOfPreviousBlock;
    }

    private static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Block:\n" +
                        "Created by miner # %s\n" +
                        "Id: %d\n" +
                        "Timestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block:\n%s\n" +
                        "Hash of the block:\n%s\n" +
                        "Block data: \n%s\n" +
                        "Block was generating for %d seconds\n",
                minerNumber, id, timeStamp, magicNumber, hashOfPreviousBlock, hashCode, blockData, getTimeOfCreating());
    }

    private long getMagicNumber() {
        return ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
    }
}
