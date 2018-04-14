package com.example.everis.android.androidbasicoaplicado;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment extends Fragment {

    public static final String ARGS_SUBTITLE = "ARGS_SUBTITLE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        TextView subtitleTextView = view.findViewById(R.id.text_subtitle);
        String subtitle = getArguments().getString(ARGS_SUBTITLE, "Default subtitle");
        subtitleTextView.setText(subtitle);

        view.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SecondFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(MainActivity.FRAGMENT_CONTAINER, fragment);
                transaction.addToBackStack(SecondFragment.class.getName());
                transaction.commit();
            }
        });

        return view;
    }
}
