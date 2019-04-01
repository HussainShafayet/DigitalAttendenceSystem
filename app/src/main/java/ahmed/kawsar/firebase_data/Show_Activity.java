package ahmed.kawsar.firebase_data;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Show_Activity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference db1;
    private List<Student> studentList;
    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        db1 = FirebaseDatabase.getInstance().getReference(uid);

        studentList = new ArrayList<>();
        customAdapter = new CustomAdapter(Show_Activity.this,studentList);



        listView = (ListView)findViewById(R.id.listViewId);
    }

    @Override
    protected void onStart() {

        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                studentList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Student student = dataSnapshot1.getValue(Student.class);
                    studentList.add(student);

                }
                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
