package br.org.cesar.animationpoc;

import android.app.Fragment;

public class CustomFragment extends Fragment {
    protected FragmentCallback mCallbackListener;

    public void setCallbackListener(FragmentCallback callback){
        mCallbackListener = callback;
    }

    public void removeCallbackListener(){
        mCallbackListener = null;
    }
}
