package com.example.alejandrofs.testapp;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (deviceHasFeature(PackageManager.FEATURE_SENSOR_COMPASS)) display("I have a compass :D");
        else display("I don't have a compass :(");

        // Print a string generated from C++ code to the console
        System.out.println(stringFromJNI());
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
