package com.example.everis.android.androidbasicoaplicado.characterdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.everis.android.androidbasicoaplicado.R;
import com.example.everis.android.androidbasicoaplicado.model.MarvelCharacter;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String ARGS_CHARACTER = "CharacterDetailActivity_ARGS_CHARACTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MarvelCharacter character = (MarvelCharacter) getIntent().getSerializableExtra(ARGS_CHARACTER);

        setContentView(R.layout.activity_character_list);

        Fragment fragment = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(CharacterDetailFragment.ARGS_CHARACTER, character);
        fragment.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }
}
