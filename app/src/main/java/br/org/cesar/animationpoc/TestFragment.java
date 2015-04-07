package br.org.cesar.animationpoc;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TestFragment extends CustomFragment {

    private ImageView mMask;
    private ImageView mDevice;
    private ImageView mEar;
    private RelativeLayout mInfoBar;
    private TextView mTitle;
    private ImageView mAudioIcon;
    private AnimatorSet mFullAnimationSet;
    private ObjectAnimator mDeviceAnimation;
    private ObjectAnimator mEarAnimation;
    private ObjectAnimator mWaveAnimation;
    private ObjectAnimator mTitleAnimation;
    private ObjectAnimator mInfoBarAnimation;
    private ObjectAnimator mErrorButtonAnimation;
    private ObjectAnimator mNoiseButtonAnimation;
    private ObjectAnimator mSuccessButtonAnimation;
    private TextView mInfoTextView;
    private ObjectAnimator mInfoTextAnimation;
    private ImageView mPlayer;
    private ObjectAnimator mPlayerAnimation;
    private Button mErrorButton;
    private Button mNoiseButton;
    private Button mSuccessButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TestFragment.
     */
    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }


    public TestFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, container, false);

        mMask = (ImageView) rootView.findViewById(R.id.mask);
        mDevice = (ImageView) rootView.findViewById(R.id.device);
        mEar = (ImageView) rootView.findViewById(R.id.ear);
        mTitle = (TextView) rootView.findViewById(R.id.testTitle);
        mAudioIcon = (ImageView) rootView.findViewById(R.id.audioIcon);
        mInfoBar = (RelativeLayout)rootView.findViewById(R.id.info_bar);

        mErrorButton = (Button) rootView.findViewById(R.id.error);
        mNoiseButton = (Button) rootView.findViewById(R.id.wrong);
        mSuccessButton = (Button) rootView.findViewById(R.id.ok);
        mInfoTextView = (TextView) rootView.findViewById(R.id.test_info_text);
        mPlayer = (ImageView) rootView.findViewById(R.id.player);


        setupAnimations();
        screenMount();
        return rootView;
    }

    private void animateWave() {
        mWaveAnimation = ObjectAnimator.ofFloat(mMask, View.TRANSLATION_X, 280);
        mWaveAnimation.setRepeatCount(ValueAnimator.INFINITE);
        mWaveAnimation.setRepeatMode(ValueAnimator.RESTART);
        mWaveAnimation.setDuration(3000);
    }

    private void setupAnimations(){
        animateDevice();
        animateEar();
        animateTitle();
        animateWave();
        animateBottomButtons();
        animateInfoBar();
        animateTestInfoText();
        animatePlayer();
    }

    private void screenMount() {
        mFullAnimationSet = new AnimatorSet();
        mFullAnimationSet.play(mInfoBarAnimation).with(mTitleAnimation);
        mFullAnimationSet.play(mDeviceAnimation).after(mTitleAnimation).after(50);
        mFullAnimationSet.play(mEarAnimation).after(mDeviceAnimation).after(50);
        mFullAnimationSet.play(mInfoTextAnimation).with(mWaveAnimation).after(mEarAnimation);
        mFullAnimationSet.play(mPlayerAnimation).after(mInfoTextAnimation).after(50);
        mFullAnimationSet.play(mErrorButtonAnimation).after(mEarAnimation).after(50);
        mFullAnimationSet.play(mNoiseButtonAnimation).after(mErrorButtonAnimation).after(20);
        mFullAnimationSet.play(mSuccessButtonAnimation).after(mNoiseButtonAnimation).after(20);
        mFullAnimationSet.start();
    }

    private void animateDevice(){
        mDeviceAnimation = ObjectAnimator.ofFloat(mDevice, View.TRANSLATION_X, 0);
        mDeviceAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mDeviceAnimation.setDuration(500);
        mDeviceAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mAudioIcon, View.ALPHA, 1);
                fadeIn.setDuration(500);
                fadeIn.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void animateEar() {
        mEarAnimation = ObjectAnimator.ofFloat(mEar, View.TRANSLATION_X, 0);
        mEarAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mEarAnimation.setDuration(500);
        mEarAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animateWave();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void animateBottomButtons(){

//        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        BounceInterpolator interpolator = new BounceInterpolator();
        mErrorButtonAnimation = ObjectAnimator.ofFloat(mErrorButton, View.TRANSLATION_Y, 0);
        mErrorButtonAnimation.setInterpolator(interpolator);
        mErrorButtonAnimation.setDuration(300);


        mNoiseButtonAnimation = ObjectAnimator.ofFloat(mNoiseButton, View.TRANSLATION_Y, 0);
        mNoiseButtonAnimation.setInterpolator(interpolator);
        mNoiseButtonAnimation.setDuration(300);


        mSuccessButtonAnimation = ObjectAnimator.ofFloat(mSuccessButton, View.TRANSLATION_Y, 0);
        mSuccessButtonAnimation.setInterpolator(interpolator);
        mSuccessButtonAnimation.setDuration(300);
    }

    private void animateTitle(){
        mTitleAnimation = ObjectAnimator.ofFloat(mTitle, View.ALPHA, 1);
        mTitleAnimation.setDuration(500);
    }

    private void animateTestInfoText(){
        mInfoTextAnimation = ObjectAnimator.ofFloat(mInfoTextView, View.ALPHA, 1);
        mInfoTextAnimation.setDuration(500);
    }

    private void animateInfoBar(){
        mInfoBarAnimation = ObjectAnimator.ofFloat(mInfoBar, View.ALPHA, 1);
        mInfoBarAnimation.setDuration(500);
    }

    private void animatePlayer(){
        mPlayerAnimation = ObjectAnimator.ofFloat(mPlayer, View.ALPHA, 1);
        mPlayerAnimation.setDuration(500);
    }

}
