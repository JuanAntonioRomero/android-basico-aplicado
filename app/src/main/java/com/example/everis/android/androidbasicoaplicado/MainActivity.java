package com.example.everis.android.androidbasicoaplicado;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int FRAGMENT_CONTAINER = R.id.container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Fragment
        Fragment fragment = new MainFragment();

        // Pasar argumentos al Fragment
        Bundle args = new Bundle();
        args.putString(MainFragment.ARGS_SUBTITLE, "This is the REAL subtitle!");
        fragment.setArguments(args);

        // Insertar el Fragment en el contenedor
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
