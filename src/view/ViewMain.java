/*****************************************************************
Main view contains all GUI elements and controls their implementation.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.*;

import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.User;
import utilities.*;

/*****************************************************************
The main view which contains all GUI elements.
 *****************************************************************/
public class ViewMain extends JPanel {

    /**Top panel containing all semi-permanent panels.*/
    JPanel topPanel;
    
    /***/
    private JPanel topCenter;

    /**The posting panel.*/
    private PostingPanel postPanel;

    /**The main Panel.*/
    JPanel mainPanel;


    /*****************************************************************
    Constructor to set up all the properties of this panel.

    @param user the main user.
     *****************************************************************/
    public ViewMain(User user) {

        setPreferredSize(ProgramStyle.windowSize());
        setName("backgroundPanel");
      // setBackground(ProgramStyle.BACKGROUND_COLOR);

        mainPanel = new JPanel();
    	mainPanel.setName("backgroundPanel");

        this.setLayout(new BorderLayout());
        add(setTopPanel(user), BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

    }


    /*****************************************************************
    Sets up the three panel sections at the top of the window and
    returns it to display.

    @return JPanel
     *****************************************************************/
    private JPanel setTopPanel (User user) {
    	
        topPanel = new JPanel();
        //set the size of the panel
        topPanel.setPreferredSize(ProgramStyle.TOP_SIZE);
    	topPanel.setName("backgroundPanel");

        //sets the color of the background
        //topPanel.setBackground(ProgramStyle.BACKGROUND_COLOR);

        //sets up the three sections using BorderLayout method
        topPanel.setLayout(new BorderLayout());
        
        //sets up the panels
        postPanel = new PostingPanel();
        topPanel.add(postPanel, BorderLayout.WEST);
        topCenter = new SmallProfilePanel(user);
        topPanel.add(topCenter, BorderLayout.CENTER);
        topPanel.add(new ButtonPanel(), BorderLayout.EAST);

        return topPanel;
    }
    
    /*****************************************************************


     *****************************************************************/
    public void resetSmallProfile (User user) {		

    	topPanel.remove(topCenter);
    	//resetMainPanel();
    	topCenter = new SmallProfilePanel(user);
    	topPanel.add(topCenter, BorderLayout.CENTER);

        //add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    	
    }

    /*****************************************************************


     *****************************************************************/
    public void resetMainPanel () {	
    	remove(mainPanel);
    	mainPanel = new JPanel();
    	mainPanel.setName("backgroundPanel");
        add(mainPanel, BorderLayout.CENTER);
    	//refresh window
        updateUI();
    }

    /*****************************************************************
    Display an array of trends in the main portion of the window.
    and then updates the display.

    @param trends Trend[]
     *****************************************************************/
    public void showTrends(Trend[] trends, String place, 
    		TrendLocations locals) {

    	mainPanel.add(new WorldTrendsPanel(locals.getArray()));

        //remove(mainPanel);
        mainPanel.add(new TrendingList(trends, place));

        //add new trending panel to main section
        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    For future use.
    @param trends Trend[]
     *****************************************************************/
  public void addTrends (Trend[] trends, String place) {

      //if (mainPanel instanceof TrendingList) {
    	  mainPanel.add(new TrendingList(trends, place));
          //((TrendingList) mainPanel).addTrendList(trends, place);
//      }
//     else {
//
//      remove(mainPanel);
//        mainPanel = new TrendingList(trends, place);
//
//        //add new trending panel to main section
//        add(mainPanel, BorderLayout.CENTER);
//      }
//        //refresh window
//        updateUI();
  }

    /*****************************************************************
    Show the search panel in the main section.
     *****************************************************************/
    public void showSearch() {

    	topPanel.remove(topCenter);
    	//resetMainPanel();
    	topCenter = new SearchPanel();
    	topPanel.add(topCenter, BorderLayout.CENTER);

        //add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    Show the results of a tweet search in the main section.
    @param stati Status[]
     *****************************************************************/
    public void showSearchResults(Status[] stati) {

    	resetMainPanel();
    	mainPanel.add(new StatusList(stati));

        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    Show the search results of a user search in main panel.
    @param userSearch User[]
     *****************************************************************/
    public void showUserSearch(User[] userSearch) {

    	resetMainPanel();
    	mainPanel.add(new UserList(userSearch));

        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    Collects the text in the tweet posting panel.

    @return String text in text field
     *****************************************************************/
    public String getPost() {
        return postPanel.getText();
    }

    /*****************************************************************
    Clears out the text that is written in the post text field.

     *****************************************************************/
    public void clearPost() {
        postPanel.clearText();
    }
    
    
    /*****************************************************************
    Displays an error message.
     *****************************************************************/
    public void showError() {

        remove(mainPanel);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.RED);
        mainPanel.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20,
               ProgramStyle.BACKGROUND_COLOR));

        JLabel errorLabel = new JLabel("Twitter is Not Responding :(");
        errorLabel.setFont(ProgramStyle.getFont(70));
        mainPanel.add(errorLabel);

        JLabel errorDesc = new JLabel("Please check your internet access.");
        errorDesc.setFont(ProgramStyle.getFont(15));
        mainPanel.add(errorDesc);

        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    Allows user to refresh the profile by clicking on the Profile Panel.
    @param user User to show in Profile Panel.
     *****************************************************************/
    public void refreshProfile(User user) {
    	
    	remove(topPanel);
        add(setTopPanel(user), BorderLayout.NORTH);
    }


	/*****************************************************************
	Displays the file chooser for image uploading.
	
	 *****************************************************************/
	public File imageChooser(){
	JFileChooser imageChooser = new JFileChooser();
	imageChooser.showOpenDialog(this);
	File picture = imageChooser.getSelectedFile();
	return picture;
	}
}