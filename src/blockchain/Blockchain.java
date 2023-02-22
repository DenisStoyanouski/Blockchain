package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Block> blockList = new ArrayList<>();

    private int numberOfZero;

    public void addNumberOfZero(int numberOfZero) {
        this.numberOfZero = numberOfZero;
    }

    public void addBlock() {
        if (blockList.isEmpty()) {
            Block block = new Block(1, "0", numberOfZero);
            blockList.add(block);
        } else {
            Block block = new Block(blockList.size() + 1, blockList.get(blockList.size() - 1).getHashCode(), numberOfZero);
            blockList.add(block);
        }
    }

    public boolean isValid() {
        for (int i = 1; i < blockList.size(); i++) {
            if (!blockList.get(i).getHashOfPreviousBlock().equals(blockList.get(i - 1).getHashCode())) {
                return false;
            }
        }
        return true;
    }

    public void printBlocks(){
        blockList.forEach(x -> System.out.println(x.toString()));
    }



}
