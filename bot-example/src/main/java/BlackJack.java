import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    private static List<String> shuffledDeck = new ArrayList<>();
    private static List<String> deck = new ArrayList<>();
    /*
    H = Hearts
    S = Spades
    C = Clubs
    D = Diamonds

    A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K
     */
    static{
        String[] suits = {"H", "S", "C", "D"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(String suit: suits){
            for(String rank: ranks){
                deck.add(suit + rank);
            }
        }
    }

    private static void shuffle() {
        int counterIndex = 0;
        Random rand = new Random();
        while (counterIndex < 52) {
            int index = rand.nextInt(52 - 1) + 1;
            if (shuffledDeck.get(index).isEmpty()) {
                    shuffledDeck.add(index, deck.get(counterIndex));
                    counterIndex++;
            }
        }

    }

    private String withdraw(){
        return shuffledDeck.remove(shuffledDeck.size() - 1);
    }

    public void play() {
        Scanner s = new Scanner(System.in);
        String input;
        String name;

        int winCount = 0;
        int lossCount = 0;
        //System.out.println("What is your name stranger?");
        // name = s.nextLine();
        do {

            String playerCard1 = withdraw();
            String playerCard2 = withdraw();

//        System.out.println(playerCard1);
//        System.out.println(playerCard2);
            int playerTotal = calculatePoints(0, playerCard1, true);
            playerTotal = calculatePoints(playerTotal, playerCard2, true);
            String dealerCard1 = withdraw();
            String dealercard2 = withdraw();
            int dealerTotal = calculatePoints(0, dealerCard1, true);
            dealerTotal = calculatePoints(dealerTotal, dealercard2, true);

//        System.out.println(initTotal);
//        System.out.println(dealerCard1);
//        System.out.println(dealercard2);
//        System.out.println(dealerTotal);

            System.out.println(" ");
            System.out.println("Welcome to Akshay's BlackJack Program!");
            System.out.println("Do you want to play blackJack? ");
            input = s.nextLine();

            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yea")) {
//

                do {
                    System.out.println("you get a " + playerCard1 + " and a " + playerCard2);
                    System.out.println("your total is " + playerTotal + "\n");

                    if (playerTotal > 21) {
                        System.out.println("You bust right at the beginning wow!! BAD LUCK!");
                        System.out.println("Try again");
                        lossCount++;
                    } else if (playerTotal == 21) {
                        System.out.println("YOU WIN Right at the beginning! What luck!");
                        System.out.println("lets go again!");
                        winCount++;
                    }
                } while (playerTotal >= 21);


//        System.out.println("playertotal is " + playerTotal);
                System.out.println("The dealer has a " + dealerCard1 + " showing, and a hidden card.");
                System.out.println("His total is hidden too\n");

//        System.out.println("dealers total is " + dealerTotal2);
                do {
                    int hitCard = hitcard();
                    System.out.println("Would you like to \"hit\" or \"stay\"?");
                    input = s.nextLine();

                    if (input.equalsIgnoreCase("hit")) {
                        System.out.println("you drew a " + hitCard);
                        playerTotal = playerTotal + hitCard;
                        System.out.println("your total is " + playerTotal);
                        if (playerTotal == 21) {
                            System.out.println( " Wins!!");
                            //wagerTotal = wagerTotal + wager;
                            winCount++;
                            break;
                        }else if (playerTotal > 21) {
                            System.out.println(" loses :(");
                            // wagerTotal = wagerTotal - wager;
                            lossCount++;
                            break;
                        }


                    }
                } while (input.equalsIgnoreCase("hit"));


                if (input.equalsIgnoreCase("stay") && playerTotal != 21 && playerTotal < 21) {
                    System.out.println("Okay dealer's turn \n");
                    System.out.println("His hidden card was a " + dealercard2);
                    System.out.println("his total was " + dealerTotal2);

//        if(playerTotal > 21){
//            break;
//        }
//        if(playerTotal == 21){
//            break;
//        }
                    do {
                        int dealerHitCard = dealInitialPlayerCards();
                        if (dealerTotal2 > 21) {
                            System.out.println("The dealer busts! The player wins! \n");
                            winCount++;
                            // wagerTotal = wagerTotal + wager;
                            break;
                        }
                        if (dealerTotal2 == 21) {
                            System.out.println("The dealer wins! \n");
                            // wagerTotal = wagerTotal - wager;
                            lossCount++;
                            break;
                        }
                        if (dealerTotal2 < 16 && playerTotal != 21 && playerTotal < 21) {
                            System.out.println("The dealer decides to hit\n");
                            System.out.println("he draws a " + dealerHitCard);
                            dealerTotal2 = dealerTotal2 + dealerHitCard;
                            System.out.println("His total is " + dealerTotal2);
                        }
                        if (dealerTotal2 >= 16 && dealerTotal2 < 21) {
                            if (dealerTotal2 == playerTotal) {
                                System.out.println("Its a push! no one wins!");
                                break;
                            }
                            if (dealerTotal2 > playerTotal) {
                                System.out.println("the dealer stays");
                                System.out.println("The dealer wins!\n");
                                //wagerTotal = wagerTotal - wager;
                                lossCount++;
                                break;
                            } else {
                                System.out.println("The dealer decides to stay...");
                                System.out.println(name + " wins!\n");
                                // wagerTotal = wagerTotal + wager;
                                winCount++;
                                break;
                            }
                        }


                    } while (true);


                }
            }else{
                System.out.println("see ya");
                break;
            }

            System.out.println("you have won " + winCount + " times");
            System.out.println("you have lost " + lossCount + " times");
            // System.out.println("your winnings are " + wagerTotal);
            System.out.println("");

        } while (true);
    }


    public void letsPlay() {
        Scanner s = new Scanner(System.in);
        String input;
        String name;

        int winCount = 0;
        int lossCount = 0;
        //System.out.println("What is your name stranger?");
       // name = s.nextLine();
        do {
            //int wager = 0;
            //int wagerTotal = 0;
            int playerCard1 = dealInitialPlayerCards();
            int playerCard2 = dealInitialPlayerCards();

//        System.out.println(playerCard1);
//        System.out.println(playerCard2);
            int initTotal = saveTotalInitialPlayerCards(playerCard1, playerCard2);
            int playerTotal;
            int dealerCard1 = dealInitialPlayerCards();
            int dealercard2 = dealInitialPlayerCards();
            int dealerTotal = saveTotalInitialPlayerCards(dealerCard1, dealercard2);
            int dealerTotal2;
//        System.out.println(initTotal);
//        System.out.println(dealerCard1);
//        System.out.println(dealercard2);
//        System.out.println(dealerTotal);

            System.out.println(" ");
            System.out.println("Welcome to Akshay's BlackJack Program!");
            System.out.println("Do you want to play blackJack? " + name);
            input = s.nextLine();

            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yea")) {
                System.out.println("How much will you wager my friend?");
               // wager = s.nextInt();
                s.nextLine();

                do {
                    System.out.println("you get a " + playerCard1 + " and a " + playerCard2);
                    System.out.println("your total is " + initTotal + "\n");

                    playerTotal = initTotal;
                    if (playerTotal > 21) {
                        System.out.println("You bust right at the beginning wow!! BAD LUCK!");
                        System.out.println("Try again");
                       // wagerTotal = wagerTotal - wager;
                        lossCount++;
                    }
                    if (playerTotal == 21) {
                        System.out.println("YOU WIN Right at the beginning! What luck!");
                        System.out.println("lets go again!");
                       // wagerTotal = wagerTotal + wager;
                        winCount++;
                    }
                } while (playerTotal >= 21);


//        System.out.println("playertotal is " + playerTotal);
                System.out.println("The dealer has a " + dealerCard1 + " showing, and a hidden card.");
                System.out.println("His total is hidden too\n");
                dealerTotal2 = dealerTotal;
//        System.out.println("dealers total is " + dealerTotal2);
                do {
                    int hitCard = hitcard();
                    System.out.println("Would you like to \"hit\" or \"stay\"?");
                    input = s.nextLine();

                    if (input.equalsIgnoreCase("hit")) {
                        System.out.println("you drew a " + hitCard);
                        playerTotal = playerTotal + hitCard;
                        System.out.println("your total is " + playerTotal);
                        if (playerTotal == 21) {
                            System.out.println(name + " Wins!!");
                            //wagerTotal = wagerTotal + wager;
                            winCount++;
                            break;
                        }
                        if (playerTotal > 21) {
                            System.out.println(name + " loses :(");
                           // wagerTotal = wagerTotal - wager;
                            lossCount++;
                            break;
                        }


                    }
                } while (input.equalsIgnoreCase("hit"));


                if (input.equalsIgnoreCase("stay") && playerTotal != 21 && playerTotal < 21) {
                    System.out.println("Okay dealer's turn \n");
                    System.out.println("His hidden card was a " + dealercard2);
                    System.out.println("his total was " + dealerTotal2);

//        if(playerTotal > 21){
//            break;
//        }
//        if(playerTotal == 21){
//            break;
//        }
                    do {
                        int dealerHitCard = dealInitialPlayerCards();
                        if (dealerTotal2 > 21) {
                            System.out.println("The dealer busts! The player wins! \n");
                            winCount++;
                           // wagerTotal = wagerTotal + wager;
                            break;
                        }
                        if (dealerTotal2 == 21) {
                            System.out.println("The dealer wins! \n");
                           // wagerTotal = wagerTotal - wager;
                            lossCount++;
                            break;
                        }
                        if (dealerTotal2 < 16 && playerTotal != 21 && playerTotal < 21) {
                            System.out.println("The dealer decides to hit\n");
                            System.out.println("he draws a " + dealerHitCard);
                            dealerTotal2 = dealerTotal2 + dealerHitCard;
                            System.out.println("His total is " + dealerTotal2);
                        }
                        if (dealerTotal2 >= 16 && dealerTotal2 < 21) {
                            if (dealerTotal2 == playerTotal) {
                                System.out.println("Its a push! no one wins!");
                                break;
                            }
                            if (dealerTotal2 > playerTotal) {
                                System.out.println("the dealer stays");
                                System.out.println("The dealer wins!\n");
                                //wagerTotal = wagerTotal - wager;
                                lossCount++;
                                break;
                            } else {
                                System.out.println("The dealer decides to stay...");
                                System.out.println(name + " wins!\n");
                               // wagerTotal = wagerTotal + wager;
                                winCount++;
                                break;
                            }
                        }


                    } while (true);


                }
                }else{
                System.out.println("see ya");
                break;
            }

            System.out.println("you have won " + winCount + " times");
            System.out.println("you have lost " + lossCount + " times");
           // System.out.println("your winnings are " + wagerTotal);
            System.out.println("");

        } while (true);
    }

        // method for dealing cards
        public Integer dealInitialPlayerCards () {
            int high = 13;
            int low = 2;
            int playerCard1 = 0;

            for (int i = 0; i < 1; i++) {
                Random rand = new Random();
                playerCard1 = rand.nextInt(high - low) + low;

            }
            return playerCard1;

        }

        public Integer hitcard () {
            int high = 13;
            int low = 2;
            int hitCard = 0;

            for (int i = 0; i < 1; i++) {
                Random rand = new Random();
                hitCard = rand.nextInt(high - low) + low;

            }
            return hitCard;

        }

        public Integer saveTotalInitialPlayerCards ( int playerCard1, int playerCard2){
            int Total = playerCard1 + playerCard2;
            return Total;
        }

    public int calculatePoints ( int currentTotal, String newCard, boolean aIsEleven){
        //The rules of blackjack are: all cards count their face value;
        // J, Q, and K count as 10; aces A count as 1 or 11 at the players choice.
        // You want to draw cards and score as close as possible to 21 without going over.

        String rank = newCard.substring(1);
        if(rank.equalsIgnoreCase("A")){
            if(aIsEleven){
                currentTotal += 11;
            }else{
                currentTotal += 1;
            }
        }else if(rank.equalsIgnoreCase("J") || rank.equalsIgnoreCase("Q")
                || rank.equalsIgnoreCase("K") )
        {
            currentTotal += 10;
        }else{
            currentTotal += Integer.parseInt(rank);
        }

        return currentTotal;
    }




    }

