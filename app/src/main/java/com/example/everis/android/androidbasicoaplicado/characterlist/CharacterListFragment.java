package com.example.everis.android.androidbasicoaplicado.characterlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.everis.android.androidbasicoaplicado.characterdetail.CharacterDetailFragment;
import com.example.everis.android.androidbasicoaplicado.model.Api;
import com.example.everis.android.androidbasicoaplicado.R;
import com.example.everis.android.androidbasicoaplicado.characterdetail.CharacterDetailActivity;
import com.example.everis.android.androidbasicoaplicado.model.MarvelCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CharacterListFragment extends ListFragment {

    public static final String ARGS_LETTER = "CharacterListFragment_ARGS_LETTER";

    private static final String BASE_URL = "https://gateway.marvel.com/v1/public/characters?nameStartsWith=%s&apikey=3b286e26ac6f7907b051d44e86af4fb6&ts=1&hash=e3c884ef86d9e64e2043be7b9faae261";
    private ArrayList<MarvelCharacter> mCharacterList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String url = String.format(BASE_URL, getArguments().getString(ARGS_LETTER));

        new Api.ApiCallGet(new Api.ApiCallListener() {
            @Override
            public void success(String response) {
                try {

                    mCharacterList = new ArrayList<>();

                    JSONObject json = new JSONObject(response);
                    JSONArray characterListJson = json.getJSONObject("data").getJSONArray("results");
                    int characterCount = characterListJson.length();
                    for (int i = 0; i < characterCount; i++) {
                        JSONObject characterJson = characterListJson.getJSONObject(i);
                        MarvelCharacter character = new MarvelCharacter();
                        character.setName(characterJson.getString("name"));
                        character.setDescription(characterJson.getString("description"));
                        JSONObject thumbnailJson = characterJson.getJSONObject("thumbnail");
                        character.setThumbnailUrl(thumbnailJson.getString("path"));
                        character.setThumbnailExtension(thumbnailJson.getString("extension"));
                        mCharacterList.add(character);
                    }

                    setListAdapter(new CharacterAdapter(mCharacterList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).execute(url);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CharacterDetailActivity.class);
                intent.putExtra(CharacterDetailActivity.ARGS_CHARACTER, mCharacterList.get(position));
                startActivity(intent);
            }
        });
    }

    private class CharacterAdapter extends BaseAdapter {

        private ArrayList<MarvelCharacter> mCharacterList;

        public CharacterAdapter(ArrayList<MarvelCharacter> characterList) {
            mCharacterList = characterList;
        }

        @Override
        public int getCount() {
            return mCharacterList.size();
        }

        @Override
        public MarvelCharacter getItem(int position) {
            return mCharacterList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
            }

            ((TextView) convertView).setText(getItem(position).getName());

            return convertView;
        }
    }
}
