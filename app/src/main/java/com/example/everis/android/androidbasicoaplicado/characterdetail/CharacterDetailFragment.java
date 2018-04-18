package com.example.everis.android.androidbasicoaplicado.characterdetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.everis.android.androidbasicoaplicado.R;
import com.example.everis.android.androidbasicoaplicado.model.MarvelCharacter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class CharacterDetailFragment extends Fragment {

    public static final String ARGS_CHARACTER = "CharacterDetailFragment_ARGS_CHARACTER";

    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private ImageView mImageView;

    private Animation mImageAnimation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_detail, container, false);
        mNameTextView = view.findViewById(R.id.text_name);
        mDescriptionTextView = view.findViewById(R.id.text_description);
        mImageView = view.findViewById(R.id.imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.startAnimation(mImageAnimation);
            }
        });

        mImageAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MarvelCharacter character = (MarvelCharacter) getArguments().getSerializable(ARGS_CHARACTER);
        mNameTextView.setText(character.getName());
        mDescriptionTextView.setText(character.getDescription());
        mImageView.setImageResource(R.drawable.logo);

        new DownloadImagesTask().execute(new String[]{character.getImageUrl(MarvelCharacter.SIZE_MEDIUM)});
    }

    private class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            return download_Image(urls[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            mImageView.setImageBitmap(result);
        }

        private Bitmap download_Image(String url) {
            Bitmap bitmap = null;
            BufferedInputStream bis = null;
            try {
                URLConnection conn = new URL(url).openConnection();
                conn.connect();
                bis = new BufferedInputStream(conn.getInputStream());
                bitmap = BitmapFactory.decodeStream(bis);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bis.close();
                } catch (IOException e) {
                }
            }
            return bitmap;
        }
    }
}
