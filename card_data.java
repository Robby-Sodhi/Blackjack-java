/* Robby Sodhi
 * J. Bains
 * ICS3U0
 * Dec.18 2021
 * This file handles classes for player data (dealer and user)
 */ 

import java.util.Arrays; //Import arrays so we can use arrays.fill which lets us fill the entirety of an array with the same element

//the card_data class is meant to represent one half the board (the 5 cards and value counter)
class card_data{
 private int M_value; //holds the value of all our cards combined
 private image_card[] M_cards; //holds all of the cards and their values (image_card is a struct which contains both the value of a particular card and the BufferedImage object telling java.swing where to find the image)
 private int M_populated; //the amount of cards that have been drawn
 private int M_balance; //the balance that player has (for the dealer we ignore this)
 private int M_bet_amount; //the bet amount the player has chosen (for the dealer we ignore this)
  
  card_data(){ //constructor for our class (this method is called when we create an instance of this class)
    M_cards = new image_card[5]; //create the array for all 5 of the cards that can be in a players hand (dealer or user)
    reset_cards(); //sets the class to a default state (resets value, populated card, makes all cards the image of a cardback, etc)
  }
  
  //false means no more cards are able to be played
  //adds a random card to that side of the field (we can only have 5 cards per side if we try to add more the method ignores it and tells the caller)
  Boolean add_random_card(){
    
    if (M_populated >= 5){ //if all 5 cards slots have been used
      return false; //tell the caller that this user (player or dealer) can't add any more cards
    }
    image_card card = constants.card_set.randomcard(); //grab a random one of our cards
    M_value += card.get_value(); //get the value of our card and add it to the total value of the users hand (player or dealer)
    M_cards[M_populated] = card; //add the card to our array of cards
    M_populated++; //increase the amount of cards that have been drawn so we don't accidentally go over 5 cards
    
    return true; //true indiciates that the method was able to add a card to that users hand (player or dealer)
    
  }
  
  //returns the value of the users hand (player or dealer)
  int get_value(){ 
   return M_value;  
  }
  //returns the array of cards 
  image_card[] get_cards(){
   return M_cards; 
    
  }
  //resets the class to a default state (this is useful when we want to reset the game)
  void reset_cards(){
    Arrays.fill(M_cards, constants.card_set.getBackCard()); //set all of the cards in the array as the backcard
    M_populated = 0; //indicate that no cards have been drawn
    M_value = 0; //if no cards have been drawn the value should be 0
    M_bet_amount = 0; //the bet amount resets so the user can change it 
  }
  
  //returns the amount of cards that have been drawn
  int get_populated(){
   return M_populated; 
  }
  //returns the balance of the user (we just ignore this for the dealer)
  int get_balance(){
   return M_balance; 
  }
  //sets the bet_amount so we can later use it when calculating the amount won/lost
  void set_bet_amount(int bet_amount){
    M_bet_amount = bet_amount;
  }
  //returns the amount the user decided to bet that round
  int get_bet_amount(){
    return M_bet_amount;
  }
  //allows us to set the balance to whatever we want (used when paying out wins/losses and giving the player loans)
  void set_balance(int balance){
   M_balance = balance; 
  }
  
  
}