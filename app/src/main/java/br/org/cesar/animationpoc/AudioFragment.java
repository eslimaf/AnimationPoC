package br.org.cesar.animationpoc;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AudioFragment extends CustomFragment {

    private Button mChangeFrags;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AudioFragment.
     */
    public static AudioFragment newInstance() {
        AudioFragment fragment = new AudioFragment();
        return fragment;
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
                mCallbackListener.changeParentActivityBackgroundColor("#3EADDC");
                mCallbackListener.replaceFragment(TestFragment.newInstance(), R.animator.curtain_up, R.animator.curtain_down);
            }
        });
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
                if(!enter) {
                    mCallbackListener.changeActionBarBackgroundColor("#3EADDC");
                }
            }
        });

        return anim;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(mCallbackListener != null) {
            mCallbackListener.changeActionBarBackgroundColor("#307fe2");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
