package com.example.everis.android.androidbasicoaplicado.characterlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.everis.android.androidbasicoaplicado.R;

public class CharacterListActivity extends AppCompatActivity {

    public static final String ARGS_LETTER = "CharacterListActivity_ARGS_LETTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String letter = getIntent().getStringExtra(ARGS_LETTER);

        setContentView(R.layout.activity_character_list);

        Fragment fragment = new CharacterListFragment();
        Bundle args = new Bundle();
        args.putString(CharacterListFragment.ARGS_LETTER, letter);
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
