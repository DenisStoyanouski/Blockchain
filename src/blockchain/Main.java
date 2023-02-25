package blockchain;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {
        int numberOfChains = 5;
        Blockchain blockchain = new Blockchain(numberOfChains, 0);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        while (blockchain.getBlockchainSize() < 5) {
            executor.submit(() -> {
                Miner miner = new Miner(blockchain, blockchain.getNumberOfZeroes(), String.valueOf(Thread.currentThread().getId()));
                Block block = miner.getBlock();
                blockchain.addBlock(block);
            });
        }
        executor.shutdownNow();
        blockchain.printBlocks(numberOfChains);
    }

/*
    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }
*/

}
