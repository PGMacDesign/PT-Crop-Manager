package pgmacdesign.ptcropmanager.datamodels.miscmodels;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;

import pgmacdesign.ptcropmanager.misc.Constants;

/**
 * This class is used to maintain a saved state order or the fragments viewed and their order
 */
public class SavedStateObj {
    @SerializedName("fragOrder")
    private LinkedList<Constants.AllFragments> fragOrder;

    public LinkedList<Constants.AllFragments> getFragOrder() {
        return fragOrder;
    }

    public void setFragOrder(LinkedList<Constants.AllFragments> fragOrder) {
        this.fragOrder = fragOrder;
    }
}
