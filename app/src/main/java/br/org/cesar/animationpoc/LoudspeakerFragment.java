package br.org.cesar.animationpoc;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoudspeakerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoudspeakerFragment extends CustomFragment {


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoudspeakerFragment.
     */
    public static LoudspeakerFragment newInstance() {
         return new LoudspeakerFragment();
    }

    public LoudspeakerFragment() {
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
        return inflater.inflate(R.layout.fragment_loudspeaker, container, false);
    }


}
