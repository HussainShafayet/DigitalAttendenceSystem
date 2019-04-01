package ahmed.kawsar.firebase_data;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private EditText signInEmailEdittext,signInPasswordEdittext;
  private TextView signUpTextView;
  private Button signInButton;
  private ProgressBar progressBar1;
  private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signInEmailEdittext = (EditText)findViewById(R.id.signInEmailEditTextId);
        signInPasswordEdittext = (EditText)findViewById(R.id.signInPasswordEditTextId);
        signInButton =(Button)findViewById(R.id.signInButtonId);
        signUpTextView = (TextView)findViewById(R.id.signUpTextViewId);
        progressBar1 = (ProgressBar)findViewById(R.id.progressbarId1);

        signInButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){

           case R.id.signInButtonId:
                userLogIn();

           break;

           case R.id.signUpTextViewId:
              Intent intent = new Intent(getApplicationContext(),Sign_Up.class);
              startActivity(intent);
               break;
       }
    }

    private void userLogIn() {
        String email = signInEmailEdittext.getText().toString().trim();
        String password = signInPasswordEdittext.getText().toString().trim();

        if(email.isEmpty()){
            signInEmailEdittext.setError("Enter an Email Address");
            signInEmailEdittext.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signInEmailEdittext.setError("Enter a Valid Email Address");
            signInEmailEdittext.requestFocus();
            return;
        }

        if(password.isEmpty()){
            signInPasswordEdittext.setError("Enter a Password");
            signInPasswordEdittext.requestFocus();
            return;
        }

        if(password.length()<6){

            signInPasswordEdittext.setError("Minimum length of a password should be 6");
            signInPasswordEdittext.requestFocus();
            return;
        }
        progressBar1.setVisibility(View.VISIBLE);

         mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 progressBar1.setVisibility(View.GONE);
                  if(task.isSuccessful()){
                      Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      startActivity(intent);
                      finish();
                  }else {
                      Toast.makeText(getApplicationContext(),"LogIn Unsuccessfull",Toast.LENGTH_LONG).show();
                  }
             }
         });
    }
}
