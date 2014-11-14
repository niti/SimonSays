package cs4720.virginia.cs.edu.rpims4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class MainScreen extends Activity {

    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
                EditText url  = (EditText)findViewById(R.id.ipAddress);
                Intent intent = new Intent(getApplicationContext(), GameScreen.class);
                intent.putExtra("ip", url.getText().toString());
                startActivity(intent);
            }
        });
        EditText url  = (EditText)findViewById(R.id.ipAddress);
        System.out.println("THE URL:" + url.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void buttonOnClick(View v) {
        Button button = (Button) v;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String json = "{\"lights\": [{\"lightId\": 1, \"red\":0,\"green\":255,\"blue\":0, \"intensity\": 0.7}],\"propagate\": true}";
        EditText url  = (EditText)findViewById(R.id.ipAddress);
        HttpClient httpClient = new DefaultHttpClient();
        Context context = getApplicationContext();
        CharSequence text = "hello world";
        int duration = Toast.LENGTH_SHORT;


        try {
            //HttpPost httpPost = new HttpPost("http://" + url.getText().toString());
            HttpPost httpPost = new HttpPost("http://172.27.99.74/rpi/");
            httpPost.setEntity(new StringEntity(json));
            HttpResponse response2 = httpClient.execute(httpPost);
           // Toast toast2 = Toast.makeText(context,"it's working", duration);
            //toast2.show();


        } catch (Exception e) {
            Toast toast2 = Toast.makeText(context,e.toString(), duration);
            toast2.show();
            //System.err.println("Test is not reachable.");
        }

        Intent intent = new Intent(getApplicationContext(), GameScreen.class);
        intent.putExtra("ip", url.getText().toString());
        startActivity(intent);

    }
}
