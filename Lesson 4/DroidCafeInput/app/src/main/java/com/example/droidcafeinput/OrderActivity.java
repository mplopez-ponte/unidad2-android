package com.example.droidcafeinput;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity handles radio buttons for choosing a delivery method for an
 * order, a spinner for setting the label for a phone number, and EditText input
 * controls.
 *
 * La activity muestra radio buttons para escoger el método de envio de un pedido, un spinner
 * para solicitar el tipo de número de teléfono y un EditText con controles de entrada.
 */

public class OrderActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener{

    /**
     * Sets the content view to activity_order, and gets the intent and its
     * data. Also creates an array adapter and layout for a spinner.
     *
     * Configuramos la vista de activity_order, y obtenemos el intent y sus datos.
     * También creamos un array adapter y un layout para un spinner.
     *
     * @param savedInstanceState Saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Obtenemos el intent y los datos asociados.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);

        // Creamos el spinner.
        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Se crea un ArrayAdapter usando el array y el layout del spinner por defecto
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.labels_array,
                android.R.layout.simple_spinner_item);

        // Especificamos el layout a usar cuando la lista de opciones aparezca.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    /**
     * Checks which radio button was clicked and displays a toast message to
     * show the choice.
     *
     * Comprobamos cual es el radio button seleccionado y mostramos un mensaje toast
     * mostrando la opción
     *
     * @param view The radio button view.
     */
    public void onRadioButtonClicked(View view) {
        // Está el botón seleccionado?
        boolean checked = ((RadioButton) view).isChecked();
        // Comprobamos cual es el radio button seleccionado
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Envio en el mismo día
                    displayToast(getString(
                            R.string.same_day_messenger_service));
                break;
            case R.id.nextday:
                if (checked)
                    // Envío al día siguiente
                    displayToast(getString(
                            R.string.next_day_ground_delivery));
                break;
            case R.id.pickup:
                if (checked)
                    // Recogida en local
                    displayToast(getString(
                            R.string.pick_up));
                break;
            default:
                // Do nothing.
                break;
        }
    }

    /**
     * Displays the actual message in a toast message.
     *
     * Se muestra el mensaje toast de la opción seleccionada.
     *
     * @param message Message to display.
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    // El interface realiza la llamada cuando un item del spinner está seleccionado
    @Override
    public void onItemSelected(AdapterView<?> adapterView,
                               View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    // En este caso el interface no realiza nada ya que no se selecciona ninguna opción.
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing.
    }
}



