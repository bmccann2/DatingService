
 // DatingService
 
 /****** Since some students have had more experience with GUI's than others, I am giving sample
  //code for setting up components.  (This will be the only time, after this you should know how to do it.
   ASTERICKS INDICATE AREAS WHERE YOU SUPPLY CODE
I will give sample code and then you will do the rest.  Since the JList as we are using it is not in either of your texts
, I have supplied full code for it */

//program to set up a client database using a List class
   import java.util.StringTokenizer;
   import java.io.*;
   import java.text.*;
   import java.util.*;
   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
	import java.util.ArrayList;
	import java.util.Iterator;
   

// you will have to change the name of your DatingServiceGUIHandout to DatingServiceGUI
public class DatingService
{    		
	public static void main(String [] args)
   {
		//Frame for the GUI
      final JFrame jFrame = new DatingServiceGUI();
		//Exits program when X is clicked
      jFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      jFrame.pack();
		//Sets size of the window to 600x500
      jFrame.setSize(600,500);
		//Allow visibility of the frame
      jFrame.setVisible(true); // Show frame      
   }
}
/********************************************************************
* class DatingService
********************************************************************/
class DatingServiceGUI extends JFrame
{
           // Here you declare all the components for the GUI
	private JList jList;
	private DefaultListModel listModel; // List with a vector
   
	
	// declare JButtons for all the fields except status and gender which will be JComboBoxes
    
	/*********************** declare the buttons-  add, delete etc.example below*/
   private JButton addButton;// you need to declare more buttons  - run the dating service example to see what buttons are needed
   private JButton deleteButton;
   private JButton findButton;
   private JButton helpButton;
   
	// Declare all the labels for textfields and combo boxes -here is a sample
	 private  JLabel addLabel;  // you need to declare more labels
	 private JLabel deleteLabel;
    private JLabel findLabel; 
	  
	  private JTextField ageField, genderField, hobbyField, FnameField, LnameField, phoneField; // you need to declare more textfields
	  
     
	  private JComboBox statusBox, genderBox;
	  
	  private JTextArea outputArea;
     
	  
     private String filename ="Client.inp";  // filename to read in data
   

     private ActionHandler action;  // is the object that implements ActionListener and contains the actionPerformed method
     
	  
   
     private ArrayList<Client> List = new ArrayList<Client>();


/********************************************************************
* DatingService::DatingService()
* setup GUI and load database file
******************************************************************/
public DatingServiceGUI() 
{
  
	  
	  // Be sure to declare all objects as instance variables above the constructor -
	  // I have done most of them, but you still need to declare more buttons and labels

// set up the JList – I am giving you this code because this is not in your text.  
//By using the DefaultListModel instead of a simple JList we have access to a vector - a type of ArrayList
	     listModel = new DefaultListModel(); // Setup JList
	     jList = new JList(listModel); // this allows us access to a vector
	
      // allows only one item to be selected at a time
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
        jList.setSelectedIndex(-1);// make initial screen empty
            
    // put the JList in a scrollpane for when Clients cannot be seen in the JList
       JScrollPane listScrollPane = new JScrollPane(jList);

	   // set up a compound border for the Jlist object- this is the fanciest one I could find 
      listScrollPane.setBorder(
		BorderFactory.createCompoundBorder(
		BorderFactory.createCompoundBorder(
		BorderFactory.createLineBorder(Color.blue),
		BorderFactory.createLoweredBevelBorder()),
		BorderFactory.createCompoundBorder(
		BorderFactory.createLineBorder(Color.gray),
		BorderFactory.createLoweredBevelBorder())));
		
  // call BuildService - this method reads in Clients from file - do this later
   BuildService();
 // I create a JTextArea to output client names - e.g
  outputArea = new JTextArea();
  outputArea.setEditable(false);
  
 // set a background color for the JTextArea and use thl  setEditable method to false so that user can't write in it
 // optionally you can set its font
  outputArea.setBackground(Color.cyan);// where outputArea is the JTextArea
 
  // create  an object of the listener class ActionHandler to listen to all the buttons - it is declared above as an instance variable
  // as "action"
  action = new ActionHandler();
    
  // set up all the buttons - example below - 
     addButton = new JButton("ADD"); // Setup Buttons
     findButton= new JButton ("FIND");
     deleteButton= new JButton ("DELETE");
     helpButton= new JButton ("HELP");

	  // SET A TOOLTIP
     addButton.setToolTipText("Adds a client to the database after fields are filled");
     addButton.setMargin(new Insets(5,5,10,5));// sets margins around the string "Add"
     addButton.addActionListener(action); // attaches a listener to this button

      //  YOU DO REST OF THE BUTTONS
     findButton.setToolTipText ("Lets you search for a client based on name or hobby");
     findButton.setMargin(new Insets(5,5,10,5));
     findButton.addActionListener(action);

     deleteButton.setToolTipText ("Lets you select a client and then removes it from the viewing list");
     deleteButton.setMargin(new Insets(5,5,10,5));
     deleteButton.addActionListener(action);

     helpButton.setMargin(new Insets (5,5,10,5));
     helpButton.addActionListener(action); 
   //create the  panel  to put the buttons on  - I called the panel buttonPane
     JPanel buttonPane = new JPanel();
	  
    // a sample border to use
    buttonPane.setBorder(BorderFactory.createLineBorder(Color.blue,4));// 4 is the width of line
     	   
    // add the  buttons to the panel - buttonPane
    buttonPane.add(helpButton);
    buttonPane.add(addButton);
	 buttonPane.add(deleteButton);
    buttonPane.add(findButton);
	    
	 // create the JTextfields- example below
       FnameField = new JTextField(16); 
       LnameField= new JTextField(16);
       ageField= new JTextField (16);
       hobbyField= new JTextField(16);
       phoneField= new JTextField(16);
          // 16 represents the number of  columns 	  
   // you can also put some initial text in the field.  this would be the first parameter
	// before the 16
   // you can set the background color for any componenet- see the example above
	   
      
	
  	//  // create comboBoxes for status and gender
	// the addItem method is used but you can also just add an array when you create it.  Look it up
         statusBox = new JComboBox();
         statusBox.addItem("R");
         statusBox.addItem("S");
         statusBox.setBackground(Color.white); 
         genderBox= new JComboBox();
         genderBox.addItem("M");
         genderBox.addItem("F");
         genderBox.setBackground(Color.white);     
         // set a background color
			// add the actionListener to it
			statusBox.addActionListener(action);
        
		   // do same for genderbox
         genderBox.addActionListener(action);

    
     // create a  label for each of the JTextFields and JComboBoxes - example below
       //********************************************
	   
	   JLabel statusLabel= new JLabel("Status R or S"); // 'S or R (case-insensitive)	// this is for the combo box statusBox
      JLabel genderLabel= new JLabel("Gender M or F"); // for genderbox
      JLabel firstFieldLabel= new JLabel("Client List"); //for text field
      JLabel secondFieldLabel= new JLabel("Results"); 
      JLabel firstName= new JLabel ("First Name"); //for panel
      JLabel lastName= new JLabel ("Last Name");
      JLabel phone= new JLabel ("Phone");
      JLabel hobby= new JLabel ("Hobby");
      JLabel age= new JLabel ("Age");
      JLabel gender= new JLabel ("Gender");
			
           
	   // create a panel for the labels and one for the JTexfields
		// BE ABSOLUTELY SURE THAT YOU ADD THE TEXTFIELDS to its panel  IN THE SAME ORDER AS you add THE LABELS
		// to the Labelpanel
	   JPanel TextPanel= new JPanel();
      TextPanel.setBackground(Color.gray);
      JPanel LabelPanel= new JPanel();
      JPanel ListPanel= new JPanel();
      ListPanel.setBackground(Color.white);
      ListPanel.setPreferredSize(new Dimension(100, 100)); 
      JPanel ResultPanel= new JPanel();
      ResultPanel.setBackground(Color.cyan);
      ResultPanel.setPreferredSize(new Dimension(250, 200));
           
      // set up a gridlayout with 1 column and as many rows as needed(however many labels you have) for  label and textfield panels
      		
		 LabelPanel.setLayout(new GridLayout(7,1,3,3));// where labelPane is the panel for the labels
      // look up what the gridlayout parameters mean
		
		// put borders around the panels e.g.
       TextPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		 TextPanel.setLayout(new GridLayout(7,1,3,3));
       ResultPanel.setBorder(BorderFactory.createLoweredBevelBorder()); 
       ListPanel.setBorder(BorderFactory.createLoweredBevelBorder());
      // add the labels to the label panel
      LabelPanel.add(firstName);
      LabelPanel.add(lastName);
      LabelPanel.add(phone);
      LabelPanel.add(age);
      LabelPanel.add(hobby);
      LabelPanel.add(gender);
      LabelPanel.add(statusLabel);
      // add the JTextFields and combo boxes to the textfield panel  - see example on my web site
	   TextPanel.add(FnameField);
      TextPanel.add(LnameField);
      TextPanel.add(phoneField);
      TextPanel.add(ageField);
      TextPanel.add(hobbyField);
      TextPanel.add(genderBox);
      TextPanel.add(statusBox);
	   // put the combo boxes on the end of the panel for textfields  - trust me on this.
     
     
	  // make sure labels and textfields match in order of how they are put on the panels.      	   
           
     // get the contentpane and add the various panels to the frame using a borderlayout
	   Container c = getContentPane(); // Setup main pane layout
      ListPanel.add(listScrollPane);
      ListPanel.add(jList);
      ResultPanel.add(outputArea);
      
       
		c.add(LabelPanel, BorderLayout.WEST);// where c is the contentPane
      c.add(TextPanel, BorderLayout.CENTER);
      c.add(buttonPane, BorderLayout.SOUTH);
      c.add(ResultPanel, BorderLayout.EAST);
      c.add(ListPanel, BorderLayout.NORTH);
      		
		// now add to the contentpane the buttonpanel, textfieldpanel, the JtextArea and the listScrollPane declared above(this is the JList)
		// we will go over this in class    
    }  // close constructor
protected Client client;
/// method to read in from a file - this is new with the JDK 1.5 and some of the textbooks' example
// have an error so I am giving you the correct version.  Save this for future programs
// Note set up your input file so that the age for the regular clients  is at the end of each line
// see sample file on my website
   public  void BuildService()  //Look at slides for help with this class
      {
          
          try {
			   
               //variable needed to store input from the file
				
				
				// initalize string
			   String lastname  = " ", firstname  = " ", hobby  = "",data;
            String sex  = " ",status  = " ";
            int age, phone;
            String line;
            char gender;

            Scanner scan = new Scanner(new File("Clients.inp")); // create an iterator to read in clients from a file "Client.dat"  
				Scanner linescan;  // create another iterator to break up the line read in
				    
                                
           while(scan.hasNext()) // this reads in lines from a file until there are no more (no hasNext())
            {
               line = scan.nextLine();//reads in one line from the file
					System.out.println(line); // for debugging
					
					linescan = new Scanner(line);// create a scanner object  - an iterator -
					// to break up the line of input into usable fields called "tokens" - e.g. first and last names
					
				  if(linescan.hasNext())// the line read in has more fields/tokens/ - lastname, firstname etc.
				  {
				      
                // use next and nextInt method to tokenize the line and store the input in variables lastname, hobby etc.
					 // for age use nextInt() e.g.
					 status = linescan.next();  // read in R or S for a regular client or a senior client
                firstname = linescan.next(); // read in firstname
                lastname= linescan.next();
                sex= linescan.next();
                phone= linescan.nextInt();
                hobby= linescan.next();
                                                 				             
                gender= sex.charAt(0);
					 
                 if(status.charAt(0) == 'R') // if this is a regular client
		              {
                    age=linescan.nextInt(); 
                    client= new Client(firstname, lastname, gender, phone, age, hobby);
                    } 
                 if (status.charAt(0) == 'S')
                    client= new SeniorClient(firstname, lastname, gender, phone, hobby);
                       
					  // create a client with input values// make sure when you create the Client that your parameters are exactly matched
					   // to the parameters in the constructor in the Client class
					
					  // add the newly created Client  to the JList(listModel)which is displayed at the top of the GUI
					  listModel.addElement(client);  
					  List.add(client);
					 // also add it to the ArrayList declare above  - use add method.  See how it is done BuildService
					 // list.add(client)// where client is the client you created above
               		               
               } // close if
         } // close while
			} // close try
            catch(FileNotFoundException e)
            {
               System.out.println("File Not Found: ") ; // !!FILE_NOT_FOUND
            }
            /*catch (IOException e)
            {
               System.out.println("IOException " + e.getMessage() ); // !!READ_ONLY!!
            }
           */ 
       System.out.println("Output file has been created: ");
        
      }// close method 
      
   

/********************************************************************
*class ActionHandler handles events from the buttons – it is nested inside DatingService
*The actionPerformed method is called when the buttons are pushed	 
	************************************************************/
    class ActionHandler implements ActionListener
    {
    protected String status, firstname, lastname, hobby, sex, HobbySearch, choice, response;
    protected int age, phone, AgeSearch;
    protected char gender; 
    protected Client c;      
   
		public void actionPerformed(ActionEvent e) // a button was pressed 
        {
		 		  		  
		    // you need to get what was entered into the StatusBox  - the JCombo box
			 // this must be done first  - will explain in class
			 // get "R" or "S" for a regular or a senior client
           if(e.getSource() == statusBox);          
              status = (String)statusBox.getSelectedItem();

		    // do same for the gender box
			  if(e.getSource() == genderBox);
              sex = (String)genderBox.getSelectedItem();
           gender= sex.charAt(0);   
         // IF SOMEONE PRESSES THE HELP BUTTON A DIALOG BOX POPS UP
        // the JOPtionPane dialog box displays instructions for entering data
		  
		  // the getActionCommand method DETERMINES IF THE HELP BUTTON WAS PRESSED

          if (e.getActionCommand().equals("HELP")) // help button pressed
          JOptionPane.showMessageDialog (null,  "Enter data in all the fields and then press add \n",   
            "INSTRUCTIONS", JOptionPane.PLAIN_MESSAGE );
        
         /* IF THE ADD BUTTON IS PRESSED THE USER HAS ENTERED DATA INTO THE TEXTFIELDS
			// AND THIS DATA MUST BE COLLECTED AND  ASSEMBLED INTO A CLIENT OBJECT AND STORED IN THE DATABASE(ArrayList and the listModel  */

         // the getActionCommand method checks to see if the ADD button was pressed
         if (e.getActionCommand().equals("ADD")) // add button pressed
         {
          if(status.equalsIgnoreCase("R"))  // If it’s a regular client...
          {                    
                 
         firstname= FnameField.getText();
         lastname= LnameField.getText();
         phone= Integer.parseInt(phoneField.getText());
         age= Integer.parseInt(ageField.getText());
         hobby= hobbyField.getText();
          
            
         c=new Client (firstname, lastname, gender, phone, age, hobby);
         System.out.println(c);
          }
                       
             if(status.equalsIgnoreCase("S"))
             {
             firstname= FnameField.getText();
             lastname= LnameField.getText();
             phone= Integer.parseInt(phoneField.getText());
             hobby= hobbyField.getText();
             
             
  
             c= new SeniorClient (firstname, lastname, gender, phone, hobby); 
             }  
              /* ADD THE NEW CLIENT TO THE DATABASE - THE FOLLOWING LINE WILL PUT THE NEW CLIENT INTO THE JLIST ON THE SCREEN */

                listModel.addElement(c); // adds the client to the JList
                List.add(c);
				  //ALSO ADD THE CLIENT TO THE ARRAYLIST
            
               
                
                    
            FnameField.setText(""); // CLEAR ALL THE TEXTFIELDS
            LnameField.setText("");
            phoneField.setText("");
            ageField.setText("");
            hobbyField.setText("");
				// clear all other textfields
        		
         } // close add

   //  if the delete button was pressed
       if (e.getActionCommand().equals("DELETE"))
       {
            /*THE USER SELECTS A CLIENT IN THE JLIST WINDOW AND WE WILL NOW  DELETE IT -  
				// FIRST GET THE SELECTED VALUE IN THE JList  WINDOW */

       Client temp = (Client)jList.getSelectedValue();  //method returns an  client object  
   	    
             // Remove client from the JList
       listModel.removeElement(temp); // Remove item from list
       List.remove(c);
				 // ALSO REMOVE THE CLIENT FROM THE ARRAYLIST
        
       } // close if delete
         

  //   if find is pressed
         if (e.getActionCommand().equals("FIND"))       
         {
           outputArea.setText(""); // clear data from the JTextArea
           choice  = JOptionPane.showInputDialog(null, "Find by Hobby or Age - Enter H or A", "Input H or A  ", JOptionPane.PLAIN_MESSAGE );
           
                 
            // based on whether choice is an H or A , use another dialog box to get the specific  hobby or age from the user 
				if (choice.equals("H"))
            {
            HobbySearch= JOptionPane.showInputDialog(null, "Which hobby are you interested in finding?", JOptionPane.PLAIN_MESSAGE);
				 //and step through clients stored in the ArrayList using the array Iterator 
								
			 Iterator<Client> iter = List.iterator();// this returns an iterator to traverse the array
            while (iter.hasNext()) 
                  {
                     client =  iter.next();// steps through the ArrayList client by Client
							// compares the hobby of each client with the age the user entered.
                     HobbySearch=HobbySearch.toUpperCase();
                     hobby= client.getHobby();
                     hobby= hobby.toUpperCase();
                     
                     if(HobbySearch.equals(hobby))                                      
                       response += (client.toString() + "\n");
                                      
                  } // close for
			
             }     
			  //if the user selected age
				if (choice.equals("A"))
            {
            AgeSearch= Integer.parseInt(JOptionPane.showInputDialog(null, "Which age are you interested in finding?", JOptionPane.PLAIN_MESSAGE));
			
            // to  find a match with ages, you iterate over the collection, and add clients with the correct age to
				// a string called "response". 
				
                  Iterator<Client> iter = List.iterator();
                  while (iter.hasNext()) 
                  {
                     client =  iter.next();// steps through the ArrayList client by Client
							// compares the ages of each client with the age the user entered.
                     
                     if(client.getAge()== AgeSearch)                                      
                       response = (client.toString() + "\n");
                                      
                  } // close for
             }
             // you can then output the whole series of clients stored in the string to the outputArea
             outputArea.append(response);
             
      
         }// close find

   } // close actionperformed
} //close ActionHandler
} // close class

   


class Client
{
static int count;
protected String lastname, firstname, hobby, result;
protected char gender;
protected int age, phone;


   public Client (String firstname, String lastname, char gender, int phone, int age, String hobby)
   {
   this.firstname=firstname;
   this.lastname= lastname;
   this.gender= gender;
   this.age= age;
   this.phone= phone;
   this.hobby= hobby;
   count++;
   }

   public Client (String firstname, String lastname, char gender, int phone, String hobby)
   {
   this.firstname=firstname;
   this.lastname= lastname;
   this.gender= gender;
   this.phone= phone;
   this.hobby= hobby;
   count++;
   }

   public Client (String firstname, String lastname)
   {
   this.firstname=firstname;
   this.lastname= lastname;
   count++;
   }
   
   public String getFirstName()
   {
   return firstname;
   }
   
   public String getLastName()
   {
   return lastname;
   }
   
   public char getGender()
   {
   return gender;
   }

   public int getAge()
   {
   return age;
   }
   
   public int getPhone()
   {
   return phone;
   }
   
   public String getHobby()
   {
   return hobby;
   }

 
   
   public String toString()
   {
   result= (firstname+" "+lastname+" "+gender+" "+age+" "+phone+" "+hobby);
   return result;
   }
}

class SeniorClient extends Client
{
   public SeniorClient (String firstname, String lastname, char gender, int phone, String hobby)
   {
   super(firstname,lastname,gender,phone,hobby);
   this.firstname=firstname;
   this.lastname= lastname;
   this.gender= gender;
   this.phone= phone;
   this.hobby= hobby;
   }
   
   public String toString()
   {
   result= (firstname+" "+lastname+" "+gender+" "+phone+" "+hobby);
   return result;
   }

}      
