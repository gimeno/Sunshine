package com.example.android.sunshine.app;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, new ForecastFragment());
            fragmentTransaction.commit();
            PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent__settings = new Intent(this, SettingsActivity.class);
                startActivity(intent__settings);
                return true;
            case R.id.action_see_location:
                Intent intent_map = new Intent(Intent.ACTION_VIEW);
                SharedPreferences sharedPrefs =
                        PreferenceManager.getDefaultSharedPreferences(this);
                String location = sharedPrefs.getString(
                        getString(R.string.pref_location_key),
                        getString(R.string.pref_location_default));
                Uri builtUri = Uri.parse("geo:0,0?")
                        .buildUpon()
                        .appendQueryParameter("q", location)
                        .build();
                intent_map.setData(builtUri);
                if (intent_map.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent_map);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


