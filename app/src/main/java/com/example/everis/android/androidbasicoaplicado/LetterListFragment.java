package com.example.everis.android.androidbasicoaplicado;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class LetterListFragment extends ListFragment {

    private String[] mLetters;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letter_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLetters = getResources().getStringArray(R.array.letters);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
            R.array.letters, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Item: " + mLetters[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
