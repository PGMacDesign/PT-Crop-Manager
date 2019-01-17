package pgmacdesign.ptcropmanager.interfaces;

/**
 * The listener is designed to be a link between the Activity and the Fragments
 */
public interface MainActivityListener {

    /**
     * Either display or hide the loading animation to cover up the screen
     * @param bool if true, will show, else will not
     */
    public void showOrHideLoadingAnimation(boolean bool);

    /**
     * Set the toolbar title (In the top center of the screen)
     * @param str String to set. If null or empty, will set as ""
     */
    public void setToolbarTitle(String str);

}
