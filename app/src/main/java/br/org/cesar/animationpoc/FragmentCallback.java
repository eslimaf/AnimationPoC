package br.org.cesar.animationpoc;

import android.app.Fragment;
import android.graphics.Color;

public interface FragmentCallback {
    public void changeParentActivityBackgroundColor(int colorResId);
    public void replaceFragment(CustomFragment f,int animIn, int animOut);
    public void changeActionBarBackgroundColor(int colorResId);
}
