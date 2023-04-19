package blockchain;


public class Miner {
    volatile private Blockchain blockchain;
    private Block block;
    String numberOfMiner;
    int numberOfZeros;

    Miner(Blockchain blockchain, int numberOfZeros, String numberOfMiner) {
        this.blockchain = blockchain;
        this.numberOfZeros = numberOfZeros;
        this.numberOfMiner = numberOfMiner;
    }

    private int getBlockId() {
        return blockchain.getLastId();
    }

    private String getLastHashCode() {
        return blockchain.getLastHashCode();
    }

    private void mineBlock() {
        block = new Block(getBlockId(), getLastHashCode(), numberOfZeros, numberOfMiner, blockchain.getMessages());
    }

    public Block getBlock() {
        mineBlock();
        return block;
    }
}
