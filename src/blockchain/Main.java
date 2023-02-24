package blockchain;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        int numberOfZeroes = 1;

        for(int i = 1; i <= 10; i++) {
            Miner miner = new Miner(blockchain, numberOfZeroes, i);
            blockchain.addBlock(miner.getBlock());
            blockchain.printLastBlock();
            if (miner.getMiningTime() < 1) {
                numberOfZeroes++;
                System.out.printf("N was increased to %d\n", numberOfZeroes);
            } else if (miner.getMiningTime() > 1) {
                numberOfZeroes--;
                System.out.printf("N was decreased to %d\n", numberOfZeroes);

            }

        }
    }

/*
    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }
*/

}
