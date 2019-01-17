package pgmacdesign.ptcropmanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import pgmacdesign.ptcropmanager.R;
import pgmacdesign.ptcropmanager.misc.Constants;

public class CropsFragment extends ParentFragment  {

    //region Static Vars

    private static final Constants.AllFragments THIS_FRAGMENT =
            Constants.AllFragments.CropsFragment;
    private static final int LAYOUT_RES_ID = R.layout.crops_fragment;

    //endregion

    //region Instance Variables

    private boolean isTrue;

    //endregion

    //region Instance UI Variables

    private Button button;

    //endregion

    //region Initialization

    public CropsFragment(){
        this.setRetainInstance(true); //todo replaceme
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT_RES_ID, container, false);
        this.initUI(view);
        return view;
    }

    private void initVariables(){
        this.isTrue = false; //todo replaceme
    }

    private void initUI(View view){
        //todo replaceme
        this.button = (Button) view.findViewById(
                R.id.button);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    //endregion

    //region Override Methods

    @Override
    public void onResume() {
        super.onResume();
        if(listener.getCurrentFragment() == THIS_FRAGMENT){
            //todo
        }
    }
    //endregion

    //region Class Methods

    private void doStuff(){
        //todo replaceme
    }
    //endregion
}
