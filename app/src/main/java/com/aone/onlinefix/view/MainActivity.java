package com.aone.onlinefix.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.aone.onlinefix.R;
import com.aone.onlinefix.utils.ProblemState;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.fragments_container)
//    private View contianer;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return onItemSelected(item.getItemId());
        }

    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setNavigationMenu();
        if (savedInstanceState == null) {
            onItemSelected(R.id.navigation_home);
        }


    }



    /*listener*/

    private void setNavigationMenu() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private boolean onItemSelected(int item) {
        Fragment fragment = null;

        switch (item) {
            case R.id.navigation_home:
                fragment = FixRequestsFragment.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, fragment).commit();
                setTitle(R.string.fix_requests);

                return true;
            case R.id.navigation_dashboard:
                fragment = DashBoardFragment.newInstance(ProblemState.Store_Going_To_Fix.toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, fragment).commit();
                setTitle(R.string.title_dashboard);

                return true;

        }


        return false;
    }



/*Fix requests Fragment*/



}
