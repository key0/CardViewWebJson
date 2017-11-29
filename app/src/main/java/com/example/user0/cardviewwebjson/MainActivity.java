package com.example.user0.cardviewwebjson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private Fragment fragmentContact  = new ContactFragment()  ;
    private Fragment fragmentCardView = new CardViewFragment() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragmentCardView)
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragmentCardView)
                            .commit();
                    return true;

                case R.id.navigation_contact:

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragmentContact)
                            .commit();
                    return true;

            }
            return false;
        }
    };
 }
