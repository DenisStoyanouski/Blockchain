package blockchain;

import java.security.MessageDigest;
import java.util.Date;

public class Block {

    private int id;

    private String hashOfPreviousBlock;

    private String hashCode;

    private long timeStamp;

    public Block(int id, String hashOfPreviousBlock) {
        this.id = id;
        this.hashOfPreviousBlock = hashOfPreviousBlock;
        this.timeStamp = new Date().getTime();
        setHashCode();
    }

    private void setHashCode() {
        String allFields = String.valueOf(id) + hashOfPreviousBlock + timeStamp;
        hashCode = applySha256(allFields);
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
                "Hash of the previous block:\n%s\n" +
                "Hash of the block:\n%s\n", id,timeStamp, hashOfPreviousBlock, hashCode);
    }


}
