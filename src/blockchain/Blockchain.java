package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Block> blockList = new ArrayList<>();

    public void addBlock(Block block) {
        if (isValid(block)) {
            blockList.add(block);
        }
    }

    private boolean isValid(Block block) {
        return block.getHashOfPreviousBlock().equals(getLastHashCode());
    }

    private Block getLastBlock () {
        return blockList.get(blockList.size() - 1);
    }

    public void printLastBlock() {
        System.out.println(getLastBlock().toString());
    }

    public int getLastId() {
        return blockList.size() + 1;
    }

    public String getLastHashCode() {
        return blockList.size() == 0 ? "0" : getLastBlock().getHashCode();
    }

    public void printBlocks(){
        blockList.forEach(x -> System.out.println(x.toString()));
    }



}
