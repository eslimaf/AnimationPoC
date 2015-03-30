package br.org.cesar.animationpoc;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        getActionBar().hide();
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ImageView mWave;
        private ImageView mMask;
        private ImageView mDevice;
        private ImageView mEar;
        private LinearLayout mBottomButtons;
        private RelativeLayout mInfoBar;
        private TextView mTitle;
        private ImageView mAudioIcon;

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mWave = (ImageView)rootView.findViewById(R.id.wave);
            mMask = (ImageView) rootView.findViewById(R.id.mask);
            mDevice = (ImageView) rootView.findViewById(R.id.device);
            mEar = (ImageView) rootView.findViewById(R.id.ear);
            mBottomButtons = (LinearLayout) rootView.findViewById(R.id.bottomButtons);
            mInfoBar = (RelativeLayout) rootView.findViewById(R.id.info_bar);
            mTitle = (TextView) rootView.findViewById(R.id.testTitle);
            mAudioIcon = (ImageView)rootView.findViewById(R.id.audioIcon);
            animation();
            screenMount();
            return rootView;
        }

        private void animation(){
            ObjectAnimator translateX = ObjectAnimator.ofFloat(mMask, View.TRANSLATION_X, 200);
            translateX.setRepeatCount(ValueAnimator.INFINITE);
            translateX.setRepeatMode(ValueAnimator.RESTART);
            translateX.setDuration(4000);
            translateX.start();
        }

        private void screenMount(){
            ObjectAnimator translateXDevice = ObjectAnimator.ofFloat(mDevice, View.TRANSLATION_X, 0);
            translateXDevice.setInterpolator(new AccelerateInterpolator());
            translateXDevice.setDuration(1500);
            translateXDevice.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mAudioIcon, View.ALPHA,1);
                    fadeIn.setDuration(1500);
                    fadeIn.start();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            translateXDevice.start();

            ObjectAnimator translateXEar = ObjectAnimator.ofFloat(mEar, View.TRANSLATION_X, 0);
            translateXEar.setInterpolator(new AccelerateInterpolator());
            translateXEar.setDuration(1500);
            translateXEar.start();

            ObjectAnimator translateY = ObjectAnimator.ofFloat(mBottomButtons, View.TRANSLATION_Y, 0);
            translateY.setInterpolator(new AccelerateInterpolator());
            translateY.setDuration(1500);
            translateY.start();

            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mTitle, View.ALPHA,1);
            fadeIn.setDuration(1500);
            fadeIn.start();
            ObjectAnimator fadeIn2 = ObjectAnimator.ofFloat(mInfoBar, View.ALPHA,1);
            fadeIn2.setDuration(1500);
            fadeIn2.start();
        }
    }
}
