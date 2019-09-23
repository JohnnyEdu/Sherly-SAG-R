package ingenieria.de.software.sherly;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MotionEventCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Bundle;
import android.os.Handler;
import java.util.Set;
import java.util.UUID;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    //nombre del dispositivo emparejado (en nuestro caso va a ser dinámico)
    private static final  String DEVICE_NAME = "Galaxy A30";
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    int counter;
    volatile boolean stopWorker;

    /**
        Método que se ejecuta cuando se crea un Activity o pantalla
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //seteo el xml de layout que está en /res/layout
        setContentView(R.layout.activity_main);

        //Buscar los dispositivos bluetooth y conectarse a uno especificado
        //Para poder realizar la conexión con Bluetooth, se debió solicitar permisos al usuario para usarlo.
        //Para ello, en el archivo AndroidManifest.xml, se agregaron los permisos Bluetooth
        try
        {
            findBT();
            openBT();
            sendData("Hola!, conecto con Bluetooth");
        }
        catch (IOException ex) { }

        //tomo el TextView con id "tochPanel", ver content_main.xml 
        TextView txt = findViewById(R.id.touchPanel);
        //le asigno evento Swipe
        //TODO: mover lo que hace con cada Swipe a un método para no repetir código
        txt.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "Swipe arriba!", Toast.LENGTH_SHORT).show();
                //TODO: ver si se puede acceder directamente al padre del evento, es decir el textView y no volverlo a buscar con findViewById
                TextView txt = findViewById(R.id.touchPanel);
                //lo cambio de color
                txt.setBackgroundColor(Color.BLUE);
                try {
                    //envío un mensaje al dispositivo Bluetooth al cual me emparejé
            sendData("inicializa conexión");
        }
        catch (IOException ex) { }


        TextView txt = findViewById(R.id.texto);
        txt.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
                TextView txt = findViewById(R.id.texto);
                txt.setBackgroundColor(Color.BLUE);
                try {
                    sendData("Movio arriba");
                }catch (IOException ex) { }
            }

            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "Swipe derecha!", Toast.LENGTH_SHORT).show();
                //TODO: ver si se puede acceder directamente al padre del evento, es decir el textView y no volverlo a buscar con findViewById
                TextView txt = findViewById(R.id.touchPanel);
                //lo cambio de color
                txt.setBackgroundColor(Color.RED);
                //envío un mensaje al dispositivo Bluetooth al cual me emparejé
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                TextView txt = findViewById(R.id.texto);
                txt.setBackgroundColor(Color.RED);
                try {
                    sendData("Movio derecha");
                }catch (IOException ex) { }
            }

            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "Swipe izquierda!", Toast.LENGTH_SHORT).show();
                //TODO: ver si se puede acceder directamente al padre del evento, es decir el textView y no volverlo a buscar con findViewById
                TextView txt = findViewById(R.id.touchPanel);
                //lo cambio de color
                txt.setBackgroundColor(Color.BLACK);
                //envío un mensaje al dispositivo Bluetooth al cual me emparejé
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                TextView txt = findViewById(R.id.texto);
                txt.setBackgroundColor(Color.BLACK);
                try {
                    sendData("Movio izquierda");
                }catch (IOException ex) { }
            }

            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "Swipe abajo!", Toast.LENGTH_SHORT).show();
                //TODO: ver si se puede acceder directamente al padre del evento, es decir el textView y no volverlo a buscar con findViewById
                TextView txt = findViewById(R.id.touchPanel);
                //lo cambio de color
                txt.setBackgroundColor(Color.YELLOW);
                //envío un mensaje al dispositivo Bluetooth al cual me emparejé
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                TextView txt = findViewById(R.id.texto);
                txt.setBackgroundColor(Color.YELLOW);
                try {
                    sendData("Movio abajo");
                }catch (IOException ex) { }
            }
        });
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
        // as you specify a parent activity in Androidst.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

<<<<<<< HEAD
    /**
        Método que se ejecuta cuando el usuario realiza gestos de tocar pantalla 
    */
=======
    // This example shows an Activity, but you would use the same approach if
// you were subclassing a View.
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
<<<<<<< HEAD
            //presionar pantalla
            case (MotionEvent.ACTION_DOWN):
                Toast.makeText(getApplicationContext(), "Action was DOWN", Toast.LENGTH_SHORT).show();
                return true;
            //mover el dedo en la pantalla
            case (MotionEvent.ACTION_MOVE):
                Toast.makeText(getApplicationContext(), "Action was MOVE", Toast.LENGTH_SHORT).show();
                return true;
            //soltar pantalla
            case (MotionEvent.ACTION_UP):
                Toast.makeText(getApplicationContext(), "Action was UP", Toast.LENGTH_SHORT).show();
                return true;
            //cancelar evento, por ejemplo: cuando se superpone un evento más importante
            case (MotionEvent.ACTION_CANCEL):
                Toast.makeText(getApplicationContext(), "Action was CANCEL", Toast.LENGTH_SHORT).show();
                return true;
            //cuando se sale el dedo de la pantalla 
=======
            case (MotionEvent.ACTION_DOWN):
                Toast.makeText(getApplicationContext(), "Action was DOWN", Toast.LENGTH_SHORT).show();
                return true;
            case (MotionEvent.ACTION_MOVE):
                Toast.makeText(getApplicationContext(), "Action was MOVE", Toast.LENGTH_SHORT).show();
                return true;
            case (MotionEvent.ACTION_UP):
                Toast.makeText(getApplicationContext(), "Action was UP", Toast.LENGTH_SHORT).show();
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Toast.makeText(getApplicationContext(), "Action was CANCEL", Toast.LENGTH_SHORT).show();
                return true;
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
            case (MotionEvent.ACTION_OUTSIDE):

                Toast.makeText(getApplicationContext(), "Salio afuera", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onTouchEvent(event);

        }
    }




    void findBT()
    {
<<<<<<< HEAD
        //Obtener un objeto que representa el dispositivo de bluetooth que posee el móvil
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null)
        {
            Toast.makeText(getApplicationContext(),"Bluetooth no disponible",Toast.LENGTH_SHORT).show();
        }
        //Bluetooth está disponible, pero no habilitado
        if(!mBluetoothAdapter.isEnabled())
        {
            //intentar habilitarlo
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); 
            startActivityForResult(enableBluetooth, 0);
        }

        //obtengo los dipositivos emparejados con el móvil
=======
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null)
        {
            Toast.makeText(getApplicationContext(),"No bluetooth adapter available",Toast.LENGTH_SHORT).show();
        }

        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }

>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
<<<<<<< HEAD
                //me conecto a uno específico
                if(device.getName().equals(DEVICE_NAME))
=======
                if(device.getName().equals("Galaxy A30"))
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
                {
                    mmDevice = device;
                    break;
                }
            }
        }
<<<<<<< HEAD
        Toast.makeText(getApplicationContext(),"Se encontró el dispositivo Bluetooth",Toast.LENGTH_SHORT).show();
    }
/**
    Inicia un socket bluetooth de escucha para la comunicación
*/
=======
        Toast.makeText(getApplicationContext(),"Bluetooth Device Found",Toast.LENGTH_SHORT).show();
    }

>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
    void openBT() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standard SerialPortService ID
        mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
        mmSocket.connect();
        mmOutputStream = mmSocket.getOutputStream();
        mmInputStream = mmSocket.getInputStream();

        beginListenForData();

<<<<<<< HEAD
        Toast.makeText(getApplicationContext(),"Bluetooth abierto",Toast.LENGTH_SHORT).show();
    }

/**
    Recibe desde el socket de bluetooth
    ver método openBT() donde inicializa la conexión
*/
    void beginListenForData()
    {
        final Handler handler = new Handler();
        final byte delimiter = 46; //Utilizamos el punto (.) como último caracter para entender que el emisor termina de mandarnos datos
=======
        Toast.makeText(getApplicationContext(),"Bluetooth Opened",Toast.LENGTH_SHORT).show();
    }

    void beginListenForData()
    {
        final Handler handler = new Handler();
        final byte delimiter = 46; //This is the ASCII code for a newline character
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
<<<<<<< HEAD
            //Un hilo nuevo para correr en background
=======
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
<<<<<<< HEAD
                        //tomo los bytes que vienen del socket
=======
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
                        int bytesAvailable = mmInputStream.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
<<<<<<< HEAD
                            //voy leyendo los bytes disponibles
                            mmInputStream.read(packetBytes);
                            //por cada byte que leemos, iteramos
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                //hasta que el emisor no envie un caracter de fin de mensaje, seguimos leyendo (definimos que sea el punto , linea 215)
                                if(b == delimiter)
                                {
                                    //cuando manda un punto, mostramos el mensaje enviado, decodificandolo con US-ASCII
=======
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;
<<<<<<< HEAD
                                    //delegar la presentación del mensaje en pantalla a otro thread para seguir leyendo mientras
=======

>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                            Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });
<<<<<<< HEAD
        //inicia el thread que escucha desde un socket bluetooth y escribe en pantalla 
        workerThread.start();
    }

    /**
        Enviar al socket bluetooth
    */
=======

        workerThread.start();
    }


>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
    void sendData(String text) throws IOException
    {
        String msg = text;
        msg += "\n";
        mmOutputStream.write(msg.getBytes());
<<<<<<< HEAD
        Toast.makeText(getApplicationContext(),"Se envió el mensaje: " msg + " al dispositivo Bluetooth",Toast.LENGTH_SHORT).show();
=======
        Toast.makeText(getApplicationContext(),"Data send",Toast.LENGTH_SHORT).show();
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
    }

    void closeBT() throws IOException
    {
        stopWorker = true;
        mmOutputStream.close();
        mmInputStream.close();
        mmSocket.close();
<<<<<<< HEAD
        Toast.makeText(getApplicationContext(),"Se cerró la conexión Bluetooth",Toast.LENGTH_SHORT).show();
=======
        Toast.makeText(getApplicationContext(),"Bluetooth Closed",Toast.LENGTH_SHORT).show();
>>>>>>> 58bc3ccff573bd082e51f4f698fd4ed147248ce9
    }

}