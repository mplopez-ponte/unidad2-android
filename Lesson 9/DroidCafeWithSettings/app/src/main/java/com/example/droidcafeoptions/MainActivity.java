package com.example.droidcafeoptions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.droidcafeinput.R;

public class MainActivity extends AppCompatActivity {

    // Etiqueta para el extra intent
    public static final String EXTRA_MESSAGE = "com.example.droidcafeoptions.extra.MESSAGE";

    // El mensaje del orden, mostrado en el Toast y enviado a la nueva Activity
    private String mOrderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
            }
        });

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_account, false);

        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);
        String marketPref = sharedPref
                .getString("sync_frequency", "-1");
        displayToast(marketPref);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       switch (item.getItemId()) {
           // Relacionamos las opciones del menú con la activity y se crea un intent para que
           // aparezca activity_order.
           case R.id.action_order:
               Intent intent = new Intent(MainActivity.this, OrderActivity.class);
               intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
               startActivity(intent);
               return true;
           case R.id.action_settings:
               Intent settingsIntent = new Intent(this, SettingsActivity.class);
               startActivity(settingsIntent);
               return true;
           case R.id.action_status:
               displayToast(getString(R.string.action_status_message));
               return true;
           case R.id.action_favorites:
               displayToast(getString(R.string.action_favorites_message));
               return true;
           case R.id.action_contact:
               displayToast(getString(R.string.action_contact_message));
               return true;
           default:
               // No se hace nada
       }

       return super.onOptionsItemSelected(item);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }

    public void showDonutOrder(View view) {

        mOrderMessage = getString(R.string.donut_order_message);
        displayToast(mOrderMessage);
    }

    public void showIceCreamOrder(View view) {

        mOrderMessage = getString(R.string.ice_cream_order_message);
        displayToast(mOrderMessage);
    }

    public void showFroyoOrder(View view) {

        mOrderMessage = getString(R.string.froyo_order_message);
        displayToast(mOrderMessage);
    }
}