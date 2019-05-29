package com.example.simpleasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    // El TextView que mostrará los resultados
    private WeakReference<TextView> mTextView;

    // El constructor provee la referencia al TextView desde MainActivity
    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    // Ejecutamos el hilo en background

    @Override
    protected String doInBackground(Void... voids) {
        // Se genera un número aleatorio entre 0 y 100.
        Random r = new Random();
        int n = r.nextInt(11);

        // Se crea la tarea para que tengamos tiempo de rotar el teléfono
        // mientras se está ejecutando
        int s = n * 200;

        // Dormimos el proceso por un periodo de tiempo
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Devolvemos como resultado un String
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    /**
     * Hace algo con el resultado en el hilo UI, en este caso las actualizaciones 7
     * del TextView
     */

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

}


