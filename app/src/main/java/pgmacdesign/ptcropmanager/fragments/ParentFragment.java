package pgmacdesign.ptcropmanager.fragments;

import android.content.Context;

import androidx.fragment.app.Fragment;
import pgmacdesign.ptcropmanager.interfaces.FragmentNetworkListener;
import pgmacdesign.ptcropmanager.interfaces.MainActivityListener;

/**
 * Parent fragment for inheritance purposes. All sub-classes of this implement hte network
 * listener for network connectivity changes as well as have direct access to the
 * {@link Context} and {@link MainActivityListener}
 */
public abstract class ParentFragment extends Fragment implements FragmentNetworkListener {

    public Context context;
    public MainActivityListener listener;

    public ParentFragment(){
        this.setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context != null){
            this.context = context;
            this.listener = (MainActivityListener)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.context = null;
        this.listener = null;
    }
}

