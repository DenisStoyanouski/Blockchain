package blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        while (true) {
            System.out.print("Enter how many zeros the hash must start with: ");
            try {
                blockchain.addNumberOfZero(Integer.parseInt(getInput()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("I need number!!! Try again");
            }
        }
        System.out.println();
        for(int i = 0; i < 5; i++) {
            blockchain.addBlock();
        }
        blockchain.printBlocks();
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }

}
