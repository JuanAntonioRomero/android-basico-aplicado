package com.example.everis.android.androidbasicoaplicado;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    /*
    Este método se llama una sola vez por instancia, cuando el sistema añade el Fragment a una Activity.
    */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v(TAG, "onAttach");
    }

    /*
    Este método se llama una sola vez por instancia, cada vez que se crea la pantalla.
    */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
    }

    /*
    Este método se llama una sola vez por instancia, la primera vez que se va a dibujar el Fragment.
    */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_main, null);
    }

    /*
    Este método se llama una sola vez por instancia, cuando ha terminado la ejecución del onCreate() de la Activity.
    */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "onActivityCreated");
    }

    /*
    Este método se llama cada vez que la pantalla se va a mostrar al usuario
    */
    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    /*
    Este método se llama cada vez que la pantalla va a empezar a recibir interacción por parte del usuario.
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    /*
    Este método se llama cada vez que la pantalla deja de estar visible.
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    /*
    Este método se llama de forma paralela al onStop() de la Activity.
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    /*
    Este método se llama cuando la vista que devuelve onCreateView se libera del Fragment.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(TAG, "onDestroyView");
    }

    /*
    Este método se llama una sola vez por instancia, cuando el sistema destruye la Activity. Puede no llamarse.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

    /*
    Este método se llama cuando el Fragment se libera de la Activity.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.v(TAG, "onDetach");
    }
}
