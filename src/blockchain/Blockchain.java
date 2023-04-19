package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Block> blockList = new ArrayList<>();
    private final List<String> zeroesStory = new ArrayList<>();
    volatile private List<String> messages = new ArrayList<>();

    private int numberOfZeroes;

    private int numberOfChains;

    public Blockchain(int numberOfChains, int numberOfZeroes) {
        this.numberOfChains = numberOfChains;
        this.numberOfZeroes = numberOfZeroes;
    }

    public synchronized boolean addBlock(Block block) {
        if (isValid(block)) {
            blockList.add(block);
            if (block.getTimeOfCreating() < 1) {
                numberOfZeroes++;
                zeroesStory.add(String.format("N was increased to %d\n\n", numberOfZeroes));
            } else if (block.getTimeOfCreating() > 1) {
                numberOfZeroes--;
                zeroesStory.add(String.format("N was decreased to %d\n\n", numberOfZeroes));
            } else {
                zeroesStory.add("N stays the same\n\n");
            }
            return true;
        }
        return false;
    }

    private boolean isValid(Block block) {
        return block.getHashOfPreviousBlock().equals(getLastHashCode());
    }

    private Block getLastBlock() {
        return blockList.get(blockList.size() - 1);
    }

    public int getLastId() {
        return blockList.size() + 1;
    }

    public String getLastHashCode() {
        return blockList.size() == 0 ? "0" : getLastBlock().getHashCode();
    }

    public void printBlocks(int numberOfChains) {
        for (int i = 0; i < numberOfChains; i++) {
            System.out.print(blockList.get(i).toString() + zeroesStory.get(i));
        }
    }

    public int getBlockchainSize() {
        return blockList.size();
    }

    public int getNumberOfZeroes() {
        return numberOfZeroes;
    }

    public synchronized void setMessage(String message) {
        messages.add(message);
    }

    public synchronized String getMessages() {
        String all = messages.size() != 0 ? String.join("\n", messages) : "no messages";
        messages.clear();
        return all;
    }
}
