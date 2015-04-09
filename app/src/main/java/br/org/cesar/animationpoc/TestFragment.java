package br.org.cesar.animationpoc;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
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
        return new TestFragment();
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
        mInfoBar = (RelativeLayout) rootView.findViewById(R.id.info_bar);

        mErrorButton = (Button) rootView.findViewById(R.id.error);
        mNoiseButton = (Button) rootView.findViewById(R.id.wrong);
        mSuccessButton = (Button) rootView.findViewById(R.id.ok);
        mSuccessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallbackListener.replaceFragment(AudioFragment.newInstance(), R.animator.curtain_down, R.animator.curtain_up);
            }
        });
        mInfoTextView = (TextView) rootView.findViewById(R.id.test_info_text);
        mPlayer = (ImageView) rootView.findViewById(R.id.player);
        mPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateBottomButtons();
            }
        });


        setupAnimations();
        return rootView;
    }

    @Override
    public Animator onCreateAnimator(int transit, final boolean enter, int nextAnim) {
        final int animatorId = (enter) ? R.animator.curtain_up : R.animator.curtain_down;
        final Animator anim = AnimatorInflater.loadAnimator(getActivity(), animatorId);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                screenMountAnim();
            }
        });

        return anim;
    }

    private void setupAnimations() {
        setupDeviceAnim();
        setupEarAnim();
        setupTitleAnim();
        setupWaveAnim();
        setupBottomButtonsAnim();
        setupInfoBarAnim();
        setupTestInfoTextAnim();
        setupPlayerAnim();
    }

    private void screenMountAnim() {
        AnimatorSet fullAnimationSet = new AnimatorSet();
        fullAnimationSet.play(mInfoBarAnimation).after(mTitleAnimation).after(20);
        fullAnimationSet.play(mDeviceAnimation).after(mInfoBarAnimation).after(50);
        fullAnimationSet.play(mEarAnimation).after(mDeviceAnimation).after(50);
        fullAnimationSet.play(mInfoTextAnimation).with(mWaveAnimation).after(mEarAnimation);
        fullAnimationSet.play(mPlayerAnimation).after(mInfoTextAnimation).after(50);
        fullAnimationSet.start();
    }

    private void setupWaveAnim() {
        mWaveAnimation = ObjectAnimator.ofFloat(mMask, View.TRANSLATION_X, 280);
        mWaveAnimation.setRepeatCount(ValueAnimator.INFINITE);
        mWaveAnimation.setRepeatMode(ValueAnimator.RESTART);
        mWaveAnimation.setDuration(3000);
    }

    private void setupDeviceAnim() {
        mDeviceAnimation = ObjectAnimator.ofFloat(mDevice, View.TRANSLATION_X, 0);
        mDeviceAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mDeviceAnimation.setDuration(300);
        mDeviceAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mAudioIcon, View.ALPHA, 1);
                fadeIn.setDuration(300);
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

    private void setupEarAnim() {
        mEarAnimation = ObjectAnimator.ofFloat(mEar, View.TRANSLATION_X, 0);
        mEarAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        mEarAnimation.setDuration(300);
        mEarAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                setupWaveAnim();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private void setupBottomButtonsAnim() {

//        AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
        BounceInterpolator interpolator = new BounceInterpolator();
        mErrorButtonAnimation = ObjectAnimator.ofFloat(mErrorButton, View.TRANSLATION_Y, 0);
        mErrorButtonAnimation.setInterpolator(interpolator);
        mErrorButtonAnimation.setDuration(200);


        mNoiseButtonAnimation = ObjectAnimator.ofFloat(mNoiseButton, View.TRANSLATION_Y, 0);
        mNoiseButtonAnimation.setInterpolator(interpolator);
        mNoiseButtonAnimation.setDuration(200);


        mSuccessButtonAnimation = ObjectAnimator.ofFloat(mSuccessButton, View.TRANSLATION_Y, 0);
        mSuccessButtonAnimation.setInterpolator(interpolator);
        mSuccessButtonAnimation.setDuration(200);
    }

    private void setupTitleAnim() {
        mTitleAnimation = ObjectAnimator.ofFloat(mTitle, View.ALPHA, 1);
        mTitleAnimation.setDuration(300);
    }

    private void setupTestInfoTextAnim() {
        mInfoTextAnimation = ObjectAnimator.ofFloat(mInfoTextView, View.ALPHA, 1);
        mInfoTextAnimation.setDuration(300);
    }

    private void setupInfoBarAnim() {
        mInfoBarAnimation = ObjectAnimator.ofFloat(mInfoBar, View.ALPHA, 1);
        mInfoBarAnimation.setDuration(300);
    }

    private void setupPlayerAnim() {
        mPlayerAnimation = ObjectAnimator.ofFloat(mPlayer, View.ALPHA, 1);
        mPlayerAnimation.setDuration(300);
    }

    private void animateBottomButtons() {
        AnimatorSet buttonAnimatorSet = new AnimatorSet();
        buttonAnimatorSet.play(mNoiseButtonAnimation).after(mErrorButtonAnimation).after(20);
        buttonAnimatorSet.play(mSuccessButtonAnimation).after(mNoiseButtonAnimation).after(20);
        buttonAnimatorSet.start();
    }

}
