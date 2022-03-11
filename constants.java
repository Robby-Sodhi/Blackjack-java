/* Robby Sodhi
 * J. Bains
 * ICS3U0
 * Dec.18 2021
 * This file holds any constant variables that would be needed throughout the program
 */ 

//a class with all static members allows us to access the values anywhere in the program without instatniating the class
public final class constants {
 //dimensions of our window are needed throughout the program so it was easier to declare it here
 public static final int main_window_height = 750;
 public static final int main_window_width = 1000;
 
 //having our starting_money as a constants here allows us to change it easily later as it is used throughout the program
 public static final int starting_money = 500;
 
 //creates an instance of the card_set class so we can retrieve cards and their values
 public static final card_set card_set = new card_set();
 

}