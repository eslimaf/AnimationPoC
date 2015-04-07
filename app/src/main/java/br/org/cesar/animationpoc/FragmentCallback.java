package br.org.cesar.animationpoc;

import android.app.Fragment;
import android.graphics.Color;

public interface FragmentCallback {
    public void changeParentActivityBackgroundColor(String colorHex);
    public void replaceFragment(CustomFragment f,int animIn, int animOut);
    public void changeActionBarBackgroundColor(String colorHex);
}
