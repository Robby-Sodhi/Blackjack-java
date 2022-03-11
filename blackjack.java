/* Robby Sodhi
 * J. Bains
 * ICS3U0
 * Dec.18 2021
 * A GUI based blackjack game (This file mostly handles the logic for the game)
 */ 

import javax.swing.*; //importing our gui elements (in this file I only use this for dialog boxes to inform the user about things that are happening)

public class blackjack{ //main class
  
   //instances of card_data to hold all of the neccesary data for the player and the dealer (Eg. what cards are in their hand, balance, bet amount etc.)
   static card_data dealer_cards = new card_data();
   static card_data player_cards = new card_data();
   
   //variable to hold an instance of our gui class (This allows us to pass it around to diferent methods allowing them to make changes to the visual elements)
   static main_window gui;
  
  //main method  
  public static void main(String[] args){
   
    
    gui = new main_window(); //instantiate our GUI class (Creating the gui)
    setup_game(); //calls a method which setups the game 
    
   //setting the players starting balance
   player_cards.set_balance(constants.starting_money);
   gui.set_balance(String.valueOf(player_cards.get_balance()));
   
   //asking the user if they want to play the tutorial or not
   int response = JOptionPane.showConfirmDialog(gui, "Welcome to Robby's blackjack casino, is this your first time playing?", "Tutorial?", JOptionPane.YES_NO_OPTION);
   if (response == JOptionPane.YES_OPTION){
     how_to_play();
   } 
    
  }
  //this method sets up all of the pre-requisite things that need to happen before we can start a round of blackjack
  public static void setup_game(){
   dealer_cards.add_random_card();  //gives the dealer a random card facing up (this is typical for blackjack the player can see the first card the dealer has)
   gui.update_dealer(dealer_cards); //update the gui displaying the new card
  }
  
  //this method is meant to handle the reseting of the game (when we want to start a new round after a winner has been decidied)
  public static void reset_game(){
    dealer_cards.reset_cards(); //reset all of the dealers cards (make them all the back of a card)
    player_cards.reset_cards(); //reset all of the players cards (make them all the back of a card)
    gui.update_dealer(dealer_cards); //update all of the dealers card images
    gui.update_player(player_cards); //update all of the players card images
    gui.bet_amount_state(true); //re-enable the betting field (this was disabled after you hit to prevent people from changing the bet after the round has started)
    setup_game(); //call setup game to perform our pre-requisite actions before allowing the game to start again
    
  }
  
  //this method is called whenever the player presses the hit button (asks for another card)
  public static void hit(){
    if (player_cards.get_populated() == 0){ //if the player hasn't drawn any cards we check if the bet amount entered is valid and lock it into the round
      String data = gui.get_bet_amount(); //get the bet amount entered in the field
      int bet_amount = 0; 
      try{
        bet_amount = Integer.parseInt(data); //convert our bet amount string into an integer and check if it's valid (there are no characters in that field)
      }catch(NumberFormatException ex){
        JOptionPane.showMessageDialog(gui, "Please only enter integers into the bet amount field"); //if there are invalid characters in that field alert the user and ask them to fix it
        return; //ends the function prematurely so we don't give the player any cards until they resolve the text in the bet field
    }
      if (bet_amount > player_cards.get_balance()){ //if the amount the player is trying to bet is lower than their balance alert the user
        JOptionPane.showMessageDialog(gui, "Looks like you're broke.... try lowering the bet");
        return;
      }
      player_cards.set_bet_amount(bet_amount); //place the bet amount from the text box into the user data object
      gui.bet_amount_state(false); //lock the text field so the user can't change the bet during a round
    }
    
    if (!player_cards.add_random_card()){ //Trys to add a random card to the users hand if the method returns false that means that the player's side of the table is full and the player can't take any more cards
      stand(); //if there are no more cards the player can draw force the player to stand
      //technically in some casinos having 5 cards without busting is considered a win
      return; //end the method early
    }
    gui.update_player(player_cards); //update the images of cards on the players side of the table
    
    if (player_cards.get_value() == 21){ //if all of the player's cards equal to 21
      end_game("Player BlackJack, Player wins!", "win"); //end the game telling the user they won and award them their winnings
    } else if (player_cards.get_value() > 21){ //if the players cards value goes over 21 
       end_game("Player bust, Dealer wins!", "loss"); //end the game telling the user that they busted and remove the amount they bet from their balance
    }
    
  }
  //this method is called whenver the user pressed the stand button
  public static void stand(){
    if (player_cards.get_populated() == 0){ //if player hasn't hit once (player has no cards on the board)
      JOptionPane.showMessageDialog(gui, "You must take atleast one card to stand! Hint: (Hit)");
      return; //end the method
    }
    
    while (dealer_cards.get_value() < 17){ //most casinos force the dealer to draw until 17 or higher
      if (!dealer_cards.add_random_card()){ //if the dealer has 5 cards without busting (we can only display 5 cards so we just force the dealer to stop) 
       break; 
      }
      gui.update_dealer(dealer_cards); //update all of the images of the dealers cards
    }
    if (dealer_cards.get_value() == 21){ //if the dealer's cards add up to 21
      end_game("Dealer BlackJack, dealer wins!", "loss");
    } else if (dealer_cards.get_value() > 21){ //if the dealer's cards add up to over 21 (dealer busted)
      end_game("Dealer bust, Player wins!", "win");
    } else if (dealer_cards.get_value() > player_cards.get_value()){ //if dealer's cards value are more than players cards value (and not equal to or greater than 21 because of the previous if's)
      end_game("Dealer beats player, Dealer wins!", "loss");
    } else if (dealer_cards.get_value() == player_cards.get_value()){ //if both the dealer and players cards add up to the same amount
      end_game("Dealer and Player even, Push!", "push");
    } else if (dealer_cards.get_value() < player_cards.get_value()){ //if players cards value is more than the dealers cards value (and not equal to or greater than 21 because of the previous if's)
      end_game("Player beats Dealer, player wins!", "win");
    }
  }
  
  //result can either be win, loss, push
  //this method handles the distribution of the bets at the end of the game
 public static void end_game(String message, String result){
    JOptionPane.showMessageDialog(gui, message);
    
    if (result == "win"){ //if the player wins
     player_cards.set_balance(player_cards.get_balance() + (player_cards.get_bet_amount())); //they win double the amount they bet (added to their balance) (the reason we don't multiply by two is because we didn't take money from the player yet) 
    } else if (result == "loss"){ //if the player lost
     player_cards.set_balance(player_cards.get_balance() - player_cards.get_bet_amount()); //we subtract their bet from their balance 
     if (player_cards.get_balance() <= 0){ //if the player's balance drops to 0 or lower (technically it's impossible for the balance to go lower than 0 because you can't bet more than you have)
       JOptionPane.showMessageDialog(gui, "Uh-oh you ran out of money. Here's a $500 loan"); 
       player_cards.set_balance(constants.starting_money); //give the player a $500 loan so they can continue playing
     }
    } else if (result == "push"){ //if the dealer and player ended up at a draw
      //do nothing because no balance was taken from the player yet(Balance is taken at the end of the game if it is loss)
    }
    gui.set_balance(String.valueOf(player_cards.get_balance())); //update the balance text on the GUI so the user can see their new balance
    
    reset_game(); //reset the game so we can start a new round
  }
 
 //a method instructing the player on how to play the game (tutorial)
 public static void how_to_play(){
    JOptionPane.showMessageDialog(gui, "Welcome to Robby's blackjack Casino, the premise of this game is to beat the dealer by having a higher hand (which is below 21) or by having exactly 21 (a blackjack, guaranteed win) however if you go over 21 you will bust and immeditaly lose");
    JOptionPane.showMessageDialog(gui, "The player goes first and is able to hit (draw cards) until they bust or decide to stand (stop and check what the dealer has)");
    JOptionPane.showMessageDialog(gui, "The dealer is then required to keep drawing cards until they a hand value of 17 or higher");
    JOptionPane.showMessageDialog(gui, "Before starting a round of blackjack you're able to decide on an amount to bet (notice your balance, try not to go broke) if you win you will win twice your bet back (original bet + itself), if you lose you will lose the entirety of your bet and if you and the dealer draw you just get to keep your bet");
    JOptionPane.showMessageDialog(gui, "To get started enter an amount to bet and press hit to draw your first card, Good Luck!!!");
 }
  
  
}