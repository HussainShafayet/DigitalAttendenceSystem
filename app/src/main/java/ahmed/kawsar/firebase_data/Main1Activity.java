package ahmed.kawsar.firebase_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main1Activity extends AppCompatActivity {
    public Button button;
    public EditText editText1,editText2,editText3,editText4;
    String[] dept_session;
    Spinner spinner;

    public static final String string_number1 = "registration_strat_from";
    public static final String string_number2 = "total_student";
    public static final String string_subject = "set_subject";
    public static final String string_dept_session = "dept_session";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        button = (Button)findViewById(R.id.buttonid1);
        editText1 = (EditText) findViewById(R.id.msg);
        editText2 = (EditText) findViewById(R.id.msg1);
        editText3 = (EditText) findViewById(R.id.dateid);
       editText4 = (EditText) findViewById(R.id.dept_sessionid);
//         dept_session= getResources().getStringArray(R.array.spinner_value);
//         spinner=(Spinner)findViewById(R.id.referenceid);
//        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this,R.layout.sample_value,R.id.textviewsampleid,dept_session);
//        spinner.setAdapter(adapter);

        // final

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(editText1.getText().toString()) && !TextUtils.isEmpty(editText2.getText().toString()) && !TextUtils.isEmpty(editText3.getText().toString()) && !TextUtils.isEmpty(editText4.getText().toString()) ){
                    int number1 = Integer.parseInt(editText1.getText().toString());
                    int number2 = Integer.parseInt(editText2.getText().toString());
                    String setdate= editText3.getText().toString().trim();
                    String dept_session = editText4.getText().toString().trim();
                    //String reference_id= spinner.getSelectedItem().toString().trim();
                    Intent intent = new Intent(Main1Activity.this,Main2Activity.class);
                    intent.putExtra(string_number1,number1);
                    intent.putExtra(string_number2,number2);
                    intent.putExtra(string_subject,setdate);
                    intent.putExtra(string_dept_session,dept_session);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}
