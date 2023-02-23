package blockchain;

public class Miner implements Runnable{
    Blockchain blockchain;

    Block block;

    int numberOfZeros;

    Miner (Blockchain blockchain, int numberOfZeros) {
        this.blockchain = blockchain;
        this.numberOfZeros = numberOfZeros;
    }

    private Block getBlock() {
        return block;
    }

    @Override
    public void run() {
        block = new Block(blockchain.getLastId(), blockchain.getLastHashCode(), numberOfZeros);
    }
}
