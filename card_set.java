/* Robby Sodhi
 * J. Bains
 * ICS3U0
 * Dec.18 2021
 * This file handles the managment of all of the images used in the game
 */ 



import java.util.ArrayList; //ArrayList is my dyanmic array of choice(resizeable array for when we don't know how many elements we have)
import java.io.File; //lets use create a refrence to a file on our disk
import javax.imageio.ImageIO; //lets us read an image file
import java.lang.Math; //used for math.random (to give out random cards)
import java.awt.image.BufferedImage; //BufferedImage is how java swing wants refrences to images 
import javax.swing.*; //used for dialog boxes

//struct for linking an image and it's coresponding value
class image_card{
  BufferedImage M_image;
  int M_value;
  image_card(BufferedImage image, int value){
    M_image = image;
    M_value = value;
  }
  //returns the value of a card
  int get_value(){
   return M_value;  
  }
  //returns the image for a card
  BufferedImage get_image(){
   return  M_image;
  }
  
}


//this class manages every single image of a card we have and randomly selecting one of them
class card_set {
  //array which contains all of the cards we have for use
  ArrayList<image_card> available_cards = new ArrayList<image_card>();
  
  //constructor for card_set (we technically don't need this to be instantiable class but im not sure how to statically add elements to an array and error check them in java
  card_set(){
    //the purpose of this try catch is to catch any IoExceptions (when the program is unable to find a file I refrenced, this should only happen if the folder containing the images is not structured correctly/is not in the correct place)
    try{
      
      //create instances of our image_card struct with every single image and their corresponding value
      image_card card_back = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_back.png")), 0);
      //add these instances to our available_cards array
      available_cards.add(card_back);
      
      image_card card_clubs_02 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_02.png")), 2);
      available_cards.add(card_clubs_02);
      
      image_card card_clubs_03 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_03.png")), 3);
      available_cards.add(card_clubs_03);
      
      image_card card_clubs_04 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_04.png")), 4);
      available_cards.add(card_clubs_04);
      
      image_card card_clubs_05 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_05.png")), 5);
      available_cards.add(card_clubs_05);
      
      image_card card_clubs_06 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_06.png")), 6);
      available_cards.add(card_clubs_06);
      
      image_card card_clubs_07 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_07.png")), 7);
      available_cards.add(card_clubs_07);
      
      image_card card_clubs_08 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_08.png")), 8);
      available_cards.add(card_clubs_08);
      
      image_card card_clubs_09 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_09.png")), 9);
      available_cards.add(card_clubs_09);
      
      image_card card_clubs_10 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_10.png")), 10);
      available_cards.add(card_clubs_10);
      
      image_card card_clubs_A = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_A.png")), 11);
      available_cards.add(card_clubs_A);
      
      image_card card_clubs_J = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_J.png")), 10);
      available_cards.add(card_clubs_J);
      
      image_card card_clubs_K = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_K.png")), 10);
      available_cards.add(card_clubs_K);
      
      image_card card_clubs_Q = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_clubs_Q.png")), 10);
      available_cards.add(card_clubs_Q);
      
      
      image_card card_diamonds_02 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_02.png")), 2);
      available_cards.add(card_diamonds_02);
      
      image_card card_diamonds_03 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_03.png")), 3);
      available_cards.add(card_diamonds_03);
      
      image_card card_diamonds_04 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_04.png")), 4);
      available_cards.add(card_diamonds_04);
      
      image_card card_diamonds_05 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_05.png")), 5);
      available_cards.add(card_diamonds_05);
      
      image_card card_diamonds_06 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_06.png")), 6);
      available_cards.add(card_diamonds_06);
      
      image_card card_diamonds_07 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_07.png")), 7);
      available_cards.add(card_diamonds_07);
      
      image_card card_diamonds_08 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_08.png")), 8);
      available_cards.add(card_diamonds_08);
      
      image_card card_diamonds_09 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_09.png")), 9);
      available_cards.add(card_diamonds_09);
      
      image_card card_diamonds_10 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_10.png")), 10);
      available_cards.add(card_diamonds_10);
      
      image_card card_diamonds_A = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_A.png")), 11);
      available_cards.add(card_diamonds_A);
      
      image_card card_diamonds_J = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_J.png")), 10);
      available_cards.add(card_diamonds_J);
      
      image_card card_diamonds_K = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_K.png")), 10);
      available_cards.add(card_diamonds_K);
      
      image_card card_diamonds_Q = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_diamonds_Q.png")), 10);
      available_cards.add(card_diamonds_Q);
      
      
      image_card card_hearts_02 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_02.png")), 2);
      available_cards.add(card_hearts_02);
      
      image_card card_hearts_03 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_03.png")), 3);
      available_cards.add(card_hearts_03);
      
      image_card card_hearts_04 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_04.png")), 4);
      available_cards.add(card_hearts_04);
      
      image_card card_hearts_05 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_05.png")), 5);
      available_cards.add(card_hearts_05);
      
      image_card card_hearts_06 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_06.png")), 6);
      available_cards.add(card_hearts_06);
      
      image_card card_hearts_07 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_07.png")), 7);
      available_cards.add(card_hearts_07);
      
      image_card card_hearts_08 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_08.png")), 8);
      available_cards.add(card_hearts_08);
      
      image_card card_hearts_09 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_09.png")), 9);
      available_cards.add(card_hearts_09);
      
      image_card card_hearts_10 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_10.png")), 10);
      available_cards.add(card_hearts_10);
      
      image_card card_hearts_A = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_A.png")), 11);
      available_cards.add(card_hearts_A);
      
      image_card card_hearts_J = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_J.png")), 10);
      available_cards.add(card_hearts_J);
      
      image_card card_hearts_K = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_K.png")), 10);
      available_cards.add(card_hearts_K);
      
      image_card card_hearts_Q = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_hearts_Q.png")), 10);
      available_cards.add(card_hearts_Q);
      
      
      image_card card_spades_02 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_02.png")), 2);
      available_cards.add(card_spades_02);
      
      image_card card_spades_03 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_03.png")), 3);
      available_cards.add(card_spades_03);
      
      image_card card_spades_04 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_04.png")), 4);
      available_cards.add(card_spades_04);
      
      image_card card_spades_05 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_05.png")), 5);
      available_cards.add(card_spades_05);
      
      image_card card_spades_06 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_06.png")), 6);
      available_cards.add(card_spades_06);
      
      image_card card_spades_07 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_07.png")), 7);
      available_cards.add(card_spades_07);
      
      image_card card_spades_08 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_08.png")), 8);
      available_cards.add(card_spades_08);
      
      image_card card_spades_09 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_09.png")), 9);
      available_cards.add(card_spades_09);
      
      image_card card_spades_10 = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_10.png")), 10);
      available_cards.add(card_spades_10);
      
      image_card card_spades_A = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_A.png")), 11);
      available_cards.add(card_spades_A);
      
      image_card card_spades_J = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_J.png")), 10);
      available_cards.add(card_spades_J);
      
      image_card card_spades_K = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_K.png")), 10);
      available_cards.add(card_spades_K);
      
      image_card card_spades_Q = new image_card(ImageIO.read(new File(".\\playing cards\\resized\\card_spades_Q.png")), 10);
      available_cards.add(card_spades_Q);
      
    } catch (java.io.IOException e){
      System.out.println(e);
      //tell the user if one or more of the image files were unable to be found by the program
      JOptionPane.showMessageDialog(null, "IOException (Can't find one or more of the image files for the cards), make sure they're all in \"current_directory\\playing cards\\resized\\card_name.png\"");  
     
    }
    
  
  }
  
   //returns a random card to the caller
   image_card randomcard(){
     return available_cards.get(((int)(Math.random() * available_cards.size()  - 1)) + 1); //+ 1 skips the first card(back card) 
    }
   //returns the backcard to the caller
   image_card getBackCard(){
    return available_cards.get(0); //the first card in our array (index 0) is the backcard
     
   }
  
  
}