package br.org.cesar.animationpoc;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AudioFragment extends CustomFragment {
    private ImageView mMask;
    private ImageView mDevice;
    private TextView mInfoText;
    private Button mChangeFrags;
    private LinearLayout mNumOfTestPanel;
    private LinearLayout mEstTimePanel;
    private ObjectAnimator mDeviceAnimator;
    private ObjectAnimator mWaveAnimator;
    private ObjectAnimator mInfoTextAnimator;
    private ObjectAnimator mNumOfTestsPanelAnimator;
    private ObjectAnimator mEstTimePanelAnimator;
    private ObjectAnimator mStartTestButtonAnimator;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AudioFragment.
     */
    public static AudioFragment newInstance() {
        return new AudioFragment();
    }

    public AudioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_audio, container, false);
        mChangeFrags = (Button) rootView.findViewById(R.id.change_frag_button);
        mChangeFrags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call new fragment
                mCallbackListener.changeParentActivityBackgroundColor(R.color.app_light_blue);
                mCallbackListener.replaceFragment(TestFragment.newInstance(), R.animator.curtain_up, R.animator.curtain_down);
            }
        });
        mDevice = (ImageView) rootView.findViewById(R.id.device);
        mMask = (ImageView) rootView.findViewById(R.id.mask);
        mInfoText = (TextView) rootView.findViewById(R.id.info_text);
        mNumOfTestPanel = (LinearLayout) rootView.findViewById(R.id.num_tests_panel);
        mEstTimePanel = (LinearLayout) rootView.findViewById(R.id.est_time_panel);

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
                if (!enter) {
                    mCallbackListener.changeActionBarBackgroundColor(R.color.app_light_blue);
                } else {
                    mCallbackListener.changeActionBarBackgroundColor(R.color.app_navy_blue);
                    mountScreen();
                }
            }
        });

        return anim;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (mCallbackListener != null) {
            mCallbackListener.changeActionBarBackgroundColor(R.color.app_navy_blue);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void setupAnimations() {
        setupDeviceAnimation();
        setupWaveAnimation();
        setupInfoTextAnimation();
        setupEstTimePanelAnimation();
        setupNumOfTestsPanelAnimation();
        setupStartTestButtonAnimation();
    }

    private void setupDeviceAnimation() {
        mDeviceAnimator = ObjectAnimator.ofFloat(mDevice, View.TRANSLATION_X, 0);
        mDeviceAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mDeviceAnimator.setDuration(300);
    }

    private void setupWaveAnimation() {
        mWaveAnimator = ObjectAnimator.ofFloat(mMask, View.TRANSLATION_X, 416);
        mWaveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mWaveAnimator.setRepeatMode(ValueAnimator.RESTART);
        mWaveAnimator.setDuration(3000);
    }

    private void setupInfoTextAnimation() {
        mInfoTextAnimator = ObjectAnimator.ofFloat(mInfoText, View.ALPHA, 1);
        mInfoTextAnimator.setDuration(300);
    }

    private void setupNumOfTestsPanelAnimation() {
        mNumOfTestsPanelAnimator = ObjectAnimator.ofFloat(mNumOfTestPanel, View.ALPHA, 1);
        mNumOfTestsPanelAnimator.setDuration(300);
    }

    private void setupEstTimePanelAnimation() {
        mEstTimePanelAnimator = ObjectAnimator.ofFloat(mEstTimePanel, View.ALPHA, 1);
        mEstTimePanelAnimator.setDuration(300);
    }

    private void setupStartTestButtonAnimation(){
        mStartTestButtonAnimator = ObjectAnimator.ofFloat(mChangeFrags,View.TRANSLATION_Y,0);
        mStartTestButtonAnimator.setInterpolator(new BounceInterpolator());
        mStartTestButtonAnimator.setDuration(200);
    }

    private void mountScreen() {
        AnimatorSet as = new AnimatorSet();
        as.play(mInfoTextAnimator).after(mDeviceAnimator).after(50);
        as.play(mNumOfTestsPanelAnimator).after(mInfoTextAnimator).after(50);
        as.play(mEstTimePanelAnimator).after(mNumOfTestsPanelAnimator).after(50);
        as.play(mStartTestButtonAnimator).after(mEstTimePanelAnimator).after(50);
        as.play(mWaveAnimator).after(mStartTestButtonAnimator);
        as.start();
    }

}
