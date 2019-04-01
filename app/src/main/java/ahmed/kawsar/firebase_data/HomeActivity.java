package ahmed.kawsar.firebase_data;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    private ImageButton imageButton;
    private Button viewbutton;
    public static final String string_reference_home = "reference";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        if(menuItem.getItemId()==R.id.nav_pro)
                        {
                            Intent intent=new Intent(getApplicationContext(),Show_Activity.class);
                            startActivity(intent);
                        }
                        if(menuItem.getItemId()==R.id.nav_logout){
                            onBackPressed();
                        }
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return false;
                    }
                }
        );
        imageButton=findViewById(R.id.attendenceId);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.attendenceId){
                    Intent intent=new Intent(getApplicationContext(),Main1Activity.class);

                    startActivity(intent);
                }
            }
        });
        viewbutton = (Button)findViewById(R.id.storeid);
        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Show_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit ?")
                .setMessage("Do you want to exit ?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        HomeActivity.super.onBackPressed();
                    }
                }).create().show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
