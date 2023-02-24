package blockchain;

import java.util.Date;

public class Miner {
    private final Blockchain blockchain;

    private Block block;

    private long miningTime;
    int numberOfMiner;

    int numberOfZeros;

    Miner (Blockchain blockchain, int numberOfZeros, int numberOfMiner) {
        this.blockchain = blockchain;
        this.numberOfZeros = numberOfZeros;
        this.numberOfMiner = numberOfMiner;
    }

    private int getBlockId () {
        return blockchain.getLastId();
    }

    private String getLastHashCode() {
        return blockchain.getLastHashCode();
    }

    private void mineBlock() {
        long start = new Date().getTime();
        block = new Block(getBlockId(), getLastHashCode(), numberOfZeros, numberOfMiner);
        long finish = new Date().getTime();
        miningTime = (finish - start) /1000;
    }

    public Block getBlock() {
        mineBlock();
        return block;
    }

    public long getMiningTime () {
        return miningTime;
    }
}
