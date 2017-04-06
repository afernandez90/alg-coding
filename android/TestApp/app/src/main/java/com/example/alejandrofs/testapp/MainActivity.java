package com.example.alejandrofs.testapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (deviceHasFeature(PackageManager.FEATURE_SENSOR_COMPASS)) {
            display("I have a compass :D");
        } else {
            display("I don't have a compass :(");
        }

        // Print a string generated from C++ code to the console
        System.out.println(stringFromJNI());

        // Print java version
        System.out.println(System.getProperty("java.vm.version"));

        // Send a hello world message
        Intent sendMessageIntent = new Intent();
        sendMessageIntent.setAction(Intent.ACTION_SEND);
        sendMessageIntent.putExtra(Intent.EXTRA_TEXT, "Hello, world!");
        sendMessageIntent.setType("text/plain");

        // Start any activity that can send a text message
        startActivity(sendMessageIntent);

        createAlarm("it's woohoo time!", 4, 19);
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void display(String msg) {
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(msg);
    }

    private boolean deviceHasFeature(String feature) {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(feature);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
