package com.example.whowroteitloader;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // URL Base para Books API
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?";
    // Parámetro para la búsqueda del string
    private static final String QUERY_PARAM = "q";
    // Parámetro que limita la búsqueda de resultados
    private static final String MAX_RESULTS = "maxResults";
    // Parámetro de filtrado según el tipo de impresión
    private static final String PRINT_TYPE = "printType";

    static String getBookInfo(String queryString) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Obtenemos el InputStream
            InputStream inputStream = urlConnection.getInputStream();

            // Se crea el buffer de lectura desde el input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Utilizamos el StringBuilder para obtener la respuesta entrante
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);

                builder.append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            bookJSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(LOG_TAG, bookJSONString);

        return bookJSONString;
    }
}
