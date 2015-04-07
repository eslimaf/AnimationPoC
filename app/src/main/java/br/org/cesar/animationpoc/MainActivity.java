package br.org.cesar.animationpoc;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity implements FragmentCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            AudioFragment audioFragment = AudioFragment.newInstance();
            audioFragment.setCallbackListener(this);
            getFragmentManager().beginTransaction()
                    .add(R.id.container,audioFragment )
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorHex)));
    }
}
