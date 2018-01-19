package com.rishabhrawat.devlight.Account_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.rishabhrawat.devlight.Account_Activity.Dashboard.DashboardFragment;
import com.rishabhrawat.devlight.Account_Activity.Explore.ExploreFragment;
import com.rishabhrawat.devlight.Account_Activity.Profile.ProfileFragment;
import com.rishabhrawat.devlight.R;
import com.rishabhrawat.devlight.Signup.SignupActivity;

public class AccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedfragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //dashboard
                    selectedfragment= DashboardFragment.newInstance();
                    break;
                case R.id.navigation_dashboard:
                    //explore
                    selectedfragment= ExploreFragment.newInstance();
                    break;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_Profile);
                    //profile
                    selectedfragment= ProfileFragment.newInstance();
                    break;

            }
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content,selectedfragment);
            transaction.commit();
            return true;
        }



    };

    //manualy dissplaying the first fragment



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_account);

        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(AccountActivity.this,SignupActivity.class));
                    finish();
                }
            }
        };
       //manualy adding a fragment
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,DashboardFragment.newInstance());
        transaction.commit();


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

   // menue item in the top corner
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);

        return super.onCreateOptionsMenu(menu);



    }
  //action for the options button ie logout about and notifiacations
    public void action(MenuItem mi)
    {
        switch(mi.getItemId())
        {
            case R.id.logout:
                mAuth.signOut();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
