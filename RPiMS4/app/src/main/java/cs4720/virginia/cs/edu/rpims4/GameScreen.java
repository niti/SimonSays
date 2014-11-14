package cs4720.virginia.cs.edu.rpims4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.Random;


public class GameScreen extends Activity {

    private SensorManager mSensorManager;

    private ShakeEventListener mSensorListener;
    ArrayList<String> userClicks = new ArrayList<String>();
    ArrayList<Light> lightSeenSoFar = new ArrayList<Light>(); //generates array of lights
    int currLevel = 1;
    SimonCounter theTimer;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);


        newPattern();
        theTimer = new SimonCounter(30000,1000);
        theTimer.start();
    }




    public void startTimer()
    {
      CountDownTimer theTimer = new CountDownTimer(30000, 1000) {
            TextView timerView  = (TextView)findViewById(R.id.textView);
            public void onTick(long millisUntilFinished) {

                timerView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {

                timerView.setText("done!");
            }
        }.start();
    }


    public void newPattern()
    {

        ArrayList<Light> allLights = LightColors.makeMyLightArray();
        System.out.println("new pattern");
        for(int i = 0; i < currLevel; i++)
        {
            Random rand = new Random();
            int randNum = rand.nextInt(8-0)+0;

            lightSeenSoFar.add(allLights.get(randNum));//gets a random color form list of light




            System.out.println(allLights.get(randNum).getLightColor());

        }
        sendJSONToPi();
        turnOffLights();

    }




    public void turnOffLights()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String json = LightColors.black;
        String url  = getIntent().getExtras().getString("ip");
        System.out.println("This is my url from the game screen" + url);
        HttpClient httpClient = new DefaultHttpClient();
        Context context = getApplicationContext();
        CharSequence text = "hello world";
        int duration = Toast.LENGTH_SHORT;


        try {
            System.out.println();
            System.out.println("url is" + url);
            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new StringEntity(json));
            HttpResponse response2 = httpClient.execute(httpPost);
            //Toast toast2 = Toast.makeText(context,"it's working", duration);
            //toast2.show();


        } catch (Exception e) {
            System.out.println(e.toString());
            //Toast toast2 = Toast.makeText(context,e.toString(), duration);
            //toast2.show();
            System.err.println("Test is not reachable.");
        }
    }

    public void sendJSONToPi(){
        for(int i = 0; i < lightSeenSoFar.size(); i++){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String json = lightSeenSoFar.get(i).getJSON();
            String url  = getIntent().getExtras().getString("ip");
            System.out.println("This is my url from the game screen" + url);
            HttpClient httpClient = new DefaultHttpClient();
            Context context = getApplicationContext();
            CharSequence text = "hello world";
            int duration = Toast.LENGTH_SHORT;


            try {
                System.out.println();
                System.out.println("url is" + url);
                HttpPost httpPost = new HttpPost(url);

                httpPost.setEntity(new StringEntity(json));
                HttpResponse response2 = httpClient.execute(httpPost);
                //Toast toast2 = Toast.makeText(context,"it's working", duration);
                //toast2.show();


            } catch (Exception e) {
                System.out.println(e.toString());
                //Toast toast2 = Toast.makeText(context,e.toString(), duration);
                //toast2.show();
                System.err.println("Test is not reachable.");
            }
            turnOffLights();
        }
    }

    public int validate(ArrayList<String> userClicks, ArrayList<Light> lightQueue){
        System.out.println("What validates sees in userclicks");
        for(int i =0; i < userClicks.size(); i++){
            System.out.print( userClicks.get(i) + " ");
        }
        System.out.println();
        System.out.println("What validates sees in light seen so far");
        for(int i =0; i < lightQueue.size(); i++){
            System.out.print(lightQueue.get(i).getLightColor()+ " ");
        }
        System.out.println();
        for(int i =0; i < userClicks.size(); i++){
            if(!(userClicks.get(i).equalsIgnoreCase(lightQueue.get(i).getLightColor()))) {
                return 0; //if user is wrong -- GAME OVER
            }
        }

        if(userClicks.size() != lightQueue.size()){
            return 1; //if user is not done -- DO Nothing
        }

        return 2; //if user is correct -- call new pattern


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_screen, menu);
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

    public void dictator(int validateNum){
        System.out.println("what the dictator sees");
        for(int i = 0; i < lightSeenSoFar.size();i++){
            System.out.print(lightSeenSoFar.get(i).getLightColor()  + " ");
        }
        System.out.println();
        if(validateNum == 0){
            theTimer.cancel();
            startActivity(new Intent(getApplicationContext(), GameOver.class));
            //System.out.println("GameOver");
        }
        if(validateNum == 2)
        {

            theTimer.onFinish();
            currLevel++;
            userClicks.clear();
            newPattern();

        }
        if(validateNum ==1)
        {
            System.out.println("more things have to be clicked");
        }


    }

    public void buttonOrange(View v){
        userClicks.add("Orange");
        int i = validate(userClicks,lightSeenSoFar);
        dictator(i);

    }

    public void buttonPurple(View v){
        userClicks.add("purple");
        int i = validate(userClicks,lightSeenSoFar);
        dictator(i);

    }
    public void buttonPink(View v){
        userClicks.add("Pink");
        int i = validate(userClicks,lightSeenSoFar);
        dictator(i);
    }
    public void buttonWhite(View v){
        userClicks.add("White");
        int i = validate(userClicks,lightSeenSoFar);
        dictator(i);
    }
    public void buttonRainbow(View v){
        userClicks.add("Rainbow");
        int i = validate(userClicks,lightSeenSoFar);
        dictator(i);
    }


    public void buttonGreen(View v) {
        userClicks.add("Green");
        int i = validate(userClicks, lightSeenSoFar);
        dictator(i);

    }

    public void buttonRed(View v){
        userClicks.add("Red");
        int i = validate(userClicks,lightSeenSoFar);
        dictator(i);
    }

    public void buttonBlue(View v){
        userClicks.add("Blue");
        int i= validate(userClicks,lightSeenSoFar);
        dictator(i);

    }

    public void buttonYellow(View v){
        userClicks.add("Yellow");
        int i = validate(userClicks,lightSeenSoFar);
        dictator(i);

    }

    public class SimonCounter extends CountDownTimer {

        public SimonCounter (int startTime, int interval){
            super(startTime, interval);
        }

        @Override
        public void onFinish(){
            int i = validate(userClicks,lightSeenSoFar);
            if(i == 2){
                TextView timerView  = (TextView)findViewById(R.id.textView);
                timerView.setText("Time is up");
                theTimer.start();
            }
            else {
                startActivity(new Intent(getApplicationContext(), GameOver.class));
            }
        }

        @Override
        public void onTick(long timeTillDone){
            int time = (int)timeTillDone/1000;
            TextView timerView  = (TextView)findViewById(R.id.textView);
            timerView.setText("Time remaining:" + time);
        }
    }
}
