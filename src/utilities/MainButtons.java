/*****************************************************************
Enum of all the main buttons to be displayed on the top left of the
program window. All attributes needed for this button are attached.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package utilities;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;


public enum MainButtons implements ProgButton{
	
	//Show the basic home timeline
	HOMETIMELINE ("Timeline"),
	
	//show the post a tweet panel
	POST_TWEET ("Post"),
	
	//show the searching panel
	SEARCH ("Search"),
	
	//show the trending panel
	TRENDING ("Trending"),
	
	//show the profile panel
	VIEW_PROFILE ("Profile"),
	
	//blank button to maintain consistent styling
	//maybe come up with an easter egg
	BLANK ("");
	
	
	/**String to display in a none image button*/
    private final String title;
    
    /**Type of button for system use*/
    private final static String className = "MainButton";
    
    /**Image to display in button if wanted*/
    private final ImageIcon image;
    
    /**Listener attached to perform action*/
    public static ActionListener listener;
    
    /*****************************************************************
    Basic constructor for the enum type
    
    @param title String to display in the button.
     *****************************************************************/
    private MainButtons (String title) {
    	
    	this.title = title;
        image = new ImageIcon ("Images/ButtonImages/" + this.toString()
        		+ ".png"); 
          //this goes and collects the image for the button
        setListener();
        
    }

    /*****************************************************************
    Returns the title of the button for painting purposes
    
    @return String of the title of the enum
     *****************************************************************/
    @Override
    public String getTitle () {
        return title;
    }

    /*****************************************************************
    Returns that class name of the type of enum this is.
    
    @return String of the class name of the button.
     *****************************************************************/
    @Override
    public String getClassName() {
        return className;
    }
    
    /*****************************************************************
    Sets the listener for this type of button.
     *****************************************************************/
    public static void setListener () {
        listener = Listeners.getListener(className);
    }

    /*****************************************************************
    Returns the listener to be used in the buttons.
    
    @return ActionListener for this type of button.
     *****************************************************************/
    @Override
    public ActionListener getListener() {
        return listener;
    }

    /*****************************************************************
    Returns the Image that is used for this button. It might return
    a null if there is none.
    
    @return ImageIcon of the button to be painted.
     *****************************************************************/
    @Override
    public ImageIcon getIcon() {
        return image;
    }

    /*****************************************************************
    Returns the enum this consists of. Don't know if this is needed.
	
	@return ProgButton enum instance of the created type
     *****************************************************************/
    @Override
    public ProgButton getType() {
        return this;
    }

    /*****************************************************************
    Object that can be passed with this enum.
	
	@param obj Object to pass with enum.
     *****************************************************************/
	@Override
	public void setPassedObject(Object obj) {
		// TODO Auto-generated method stub
		
	}

	/*****************************************************************
	Getting the object that is attached to enum if set to pass.
	
	@return Object that is attached to enum.
	 *****************************************************************/
	@Override
	public Object getPassedObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
