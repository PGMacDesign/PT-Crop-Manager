package pgmacdesign.ptcropmanager.interfaces;

import androidx.annotation.NonNull;
import pgmacdesign.ptcropmanager.misc.Constants;

/**
 * The listener is designed to be a link between the Activity and the Fragments
 */
public interface MainActivityListener {
    /**
     * Retrieve the current fragment being viewed
     * @return {@link Constants.AllFragments}
     */
    public Constants.AllFragments getCurrentFragment();

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

    /**
     * Switch to a different fragment
     * @param frag {@link Constants.AllFragments}
     */
    public void switchToFragment(@NonNull Constants.AllFragments frag);

    /**
     * Pop back X number of fragments (IE 1 if they hit the back button)
     * @param numToPopBack number of fragments to pop back. If > the size of the stack,
     *                     it will only pop back an allowed number of positions.
     */
    public void popBack(int numToPopBack);
}
