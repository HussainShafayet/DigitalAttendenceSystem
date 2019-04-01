package ahmed.kawsar.firebase_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button savebutton;
    ListView simpleList;
    DatabaseReference db;
    int s,k;
    String name1,setsubject, set_dept_session;

    Calendar calendar = Calendar.getInstance();
    String currendate = DateFormat.getDateInstance().format(calendar.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();
        final int a = intent.getIntExtra(Main1Activity.string_number1,0);
        int a1 = intent.getIntExtra(Main1Activity.string_number2,1);
        String subject = intent.getStringExtra(Main1Activity.string_subject);
        final String dept_session= intent.getStringExtra(Main1Activity.string_dept_session);
        setsubject = subject;
        set_dept_session = dept_session;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        db = FirebaseDatabase.getInstance().getReference(uid);
        savebutton=(Button) findViewById(R.id.buttonid);
        String[] strings = new String[100];
        for(k=0,s=a;k<=a1;k++,s++){
            strings[k] = Integer.toString(s);
        }

        String[] m = new String[a1];
        System.arraycopy(strings, 0, m, 0, a1);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter1 customAdapter = new CustomAdapter1(getApplicationContext(),m);
        simpleList.setAdapter(customAdapter);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "";
                int o=a;
                for (int i = 0; i < CustomAdapter1.selectedAnswers.size(); i++,o++) {
                    message = message + "\n" + o + "       " + CustomAdapter1.selectedAnswers.get(i);
                    name1 = message;
                }
                saveDate();
            }
        });
    }
    public void saveDate() {
        String attendance_list = name1;
      String key = db.push().getKey();
        Student student = new Student(currendate,setsubject,set_dept_session,attendance_list);
        db.child(key).setValue(student);
        Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_LONG).show();
    }
}

