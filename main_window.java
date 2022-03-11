/* Robby Sodhi
 * J. Bains
 * ICS3U0
 * Dec.18 2021
 * This file handles the managment of all the gui elements
 */ 

//import all of our gui elements and any coresponding methods/classes/objects we need to make them function correctly
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionListener;


//this JPanel class displays the 5 cards and the value of one side of the board (we then create multiple instances of it for both the dealer and player) 
class cards extends JPanel{
  int[][] M_card_positions; //array containing all of the positions (x, y)
  card_data M_cards; //holds all of the card_data for the player (dealer or user)
  JLabel count = new JLabel(); //JLabel to display the value of the cards on that side of the board
        cards(int[][] card_positions, int[] label_positions) { //constructor for the JPanel, it takes in the positions of the cards and the position of the label (I did it like this so I can have one class for both dealer and player)
            setBounds(0,0,constants.main_window_width,constants.main_window_height); //the panel covers the entire GUI window (main_window JFrame)
            setOpaque(false); //make the panel transparent
            M_card_positions = card_positions; //save the card positions in this instance
            card_data cards = new card_data();  //create temporarily blank cards to display while we wait for the blakcjack class to give us the actual cards the player (dealer or user) has
            update_cards(cards); //update the cards in the instance
            
            this.setLayout(null); //allows us to freely place gui elements anywhere we'd like
            count.setBounds(label_positions[0], label_positions[1], constants.main_window_width, constants.main_window_height); //set the position of the label displaying the value of all the cards on one side of the table
            this.add(count); //add the label to the panel
        }
        //we're overriding a method within a JPanel which is responsible for the drawing of elements whenever the panel is repainted
        //we use this to display all of the images and text that needs to constantly be updated (this method is called whenver we repaint the main window, allowing us to update our images whenver we need)
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); //inherits the data from original paintcomponent class (This lets us keep the original method and just add functionality to it rather than completely overwrite it)
            
            count.setText(String.valueOf(M_cards.get_value())); //update the text which displays the card values on that side of the field
            
            for (int i = 0; i < 5; i++){ //loops through all of our cards and displays the image of the card in the correct position
            g.drawImage(M_cards.get_cards()[i].get_image(), M_card_positions[i][0], M_card_positions[i][1], null);
          }
        }
        
        //updates the cards stored in the class (we must call repaint() after updating the cards for the new images to show)
        void update_cards(card_data cards){
         M_cards = cards;
        }
    }

//the class for our main gui window (JFrame)
public class main_window extends JFrame implements ActionListener{ //implements actionlistener so we can access methods callback functions for when buttons are pressed
   //setting up variables that are used throughout the class
   private JFrame main_window; 
   private cards dealer_cards;
   private cards player_cards;
   private JButton hit_button;
   private JButton stand_button;
   private JTextField bet_field;
   private JLabel balance_text = new JLabel();
  main_window(){   //constructor for our main_window
    main_window  = new JFrame(); //creates our JFrame
    main_window.setSize(constants.main_window_width, constants.main_window_height); //sets the size to the sizes we have in our constants file
    main_window.setLocationRelativeTo(null); //make the window start in the center of the monitor
   
    main_window.getContentPane().setBackground(new Color(64, 224, 208)); //change the background color
    main_window.setTitle("Robby's blackjack"); //set the title of the window
    main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //make the program exit when the user trys to close the program
    main_window.setResizable(false); //prevent the user from resizing the window 
    main_window.setLayout(null); //allows us to freely place gui elements around the window
    
    
    //arrays containing the x, y positions for all 5 of our cards for both the dealer and the player
    int[][] dealer_cards_positions = new int[5][2];
    int[][] player_cards_positions = new int[5][2];
    
    //push arrays of all dealer card and player card positions
    //using some preset values we're able to calculate where subsequent cards should be and add them to our positions array
    for (int i = 0; i < 5; i++){
     int start_x = 150;
     int start_y_dealer = 50;
     int start_y_player = 500;
     int offset = 130;
     int[] card_D = new int[2];
     card_D[0] = (start_x + (offset * i));
     card_D[1] = start_y_dealer;
     dealer_cards_positions[i] = card_D;
     
     int[] card_P = new int[2];
     card_P[0] = (start_x + (offset * i));
     card_P[1] = (start_y_player);
     player_cards_positions[i] = card_P;

    }
    
    //arrays containing the positions for the labels for both the player and dealer
    int[] dealer_label = new int[]{475, -150};
    int[] player_label = new int[]{475, 85};
    
    //create JPanels for both the player and dealer
    //we pass the positions their cards should be in and the positions for the labels in that panel (dealer is at the top of the screen and player is at the bottom)
    dealer_cards = new cards(dealer_cards_positions, dealer_label);
    player_cards = new cards(player_cards_positions, player_label);
    
    
    //add both the created JPanels to our main window
    main_window.add(dealer_cards);
    main_window.add(player_cards);
    
    //create the hit button
    hit_button = new JButton();
    //sets the location of the button
    hit_button.setBounds(375, 450, 75, 25);
    //set the text of the button
    hit_button.setText("Hit");
    //the actionlistener gives us a callback when the button is pressed allowing us to call the blackjack.hit() to respond to the users input
    hit_button.addActionListener(this);
    //add the button to the main window
    main_window.add(hit_button);
    
    
    //create the stand button 
    stand_button = new JButton();
    stand_button.setBounds(250, 450, 75, 25);
    stand_button.setText("Stand");
    stand_button.addActionListener(this);
    main_window.add(stand_button);
    
    //create the text field for taking in the user's input (how much they want to bet in a round)
    bet_field = new JTextField("0");
    bet_field.setBounds(150, 450, 75, 25);
    main_window.add(bet_field);
    
    //create the text to describe the bet_field
    JLabel bet_text = new JLabel();
    bet_text.setText("Bet amount:");
    bet_text.setBounds(150, 50,constants.main_window_width, constants.main_window_height);
    main_window.add(bet_text);
    
    //create the text which displays the balance to the user
    balance_text.setBounds(50, 0,constants.main_window_width, constants.main_window_height);
    main_window.add(balance_text);
    
   //display the main_window 
    main_window.setVisible(true);
    
  
    
  }
  
  //override the default actionPerformed function in the JFrame class to receive callbacks when any of our buttons are pressed
  @Override
  public void actionPerformed(java.awt.event.ActionEvent e){
    if (e.getSource() == hit_button){ //if the hit button is pressed
      blackjack.hit(); //call the hit method in the class which handles the games logic
      
    } else if (e.getSource() == stand_button){ //if the stand button is pressed
      blackjack.stand(); //call the stand method in the which handles the game logic
      
    }
  }
  
  //this method causes all of the images to be reloaded in our JFrame and it's elements (mainly JPanel) this allows paintComponent to be called again refreshing the images of the cards and the value text
  void reload_images(){
   main_window.repaint(); 
    
  }
  
  //updates all of the cards on the dealers side of the gui
  void update_dealer(card_data cards){
    dealer_cards.update_cards(cards);
    reload_images();
  }
  
  //updates all of the cards on the players side of the gui
  void update_player(card_data cards){
    player_cards.update_cards(cards);
    reload_images();
  }
  
  //returns the bet_amount entered into the bet_field (as a string so we in our hit method we convert it to an int and check for illegal characters)
  String get_bet_amount(){
    return bet_field.getText();
    
  }
  //allows us to enable or disable (grey out) the bet_field so the user can't change the bet in the middle of a round
  void bet_amount_state(Boolean state){
   bet_field.setEditable(state); 
  }
  //allows us to set the balance text
  void set_balance(String balance){
    balance_text.setText("Balance: $" + balance);
  }
}


