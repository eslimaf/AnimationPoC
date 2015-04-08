package br.org.cesar.animationpoc;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends ActionBarActivity implements FragmentCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState == null) {
            AudioFragment audioFragment = AudioFragment.newInstance();
            audioFragment.setCallbackListener(this);
            getFragmentManager().beginTransaction()
                    .add(R.id.container,audioFragment )
                    .commit();
        }
    }

    @Override
    public void changeParentActivityBackgroundColor(String colorHex) {
        getWindow().getDecorView().setBackgroundColor(Color.parseColor(colorHex));
    }

    @Override
    public void replaceFragment(CustomFragment f, int animIn, int animOut) {
        if(null != f) f.setCallbackListener(this);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(animIn,animOut)
                .replace(R.id.container, f)
                .commit();
    }

    @Override
    public void changeActionBarBackgroundColor(String colorHex) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorHex)));
    }
}
