package ahmed.kawsar.firebase_data;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_Up extends AppCompatActivity implements View.OnClickListener {
    private EditText signUpEmailEdittext,signUpPasswordEdittext;
    private TextView signInTextView;
    private Button signUpButton;
    private FirebaseAuth mAuth;
    private DatabaseReference teacherDatabase;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        this.setTitle("Sign Up Activity");

        mAuth = FirebaseAuth.getInstance();

        signUpEmailEdittext = (EditText)findViewById(R.id.signUpEmailEditTextId);
        signUpPasswordEdittext = (EditText)findViewById(R.id.signUpPasswordEditTextId);
        signUpButton =(Button)findViewById(R.id.signUpButtonId);
        signInTextView = (TextView)findViewById(R.id.signInTextViewId);
        progressBar = (ProgressBar)findViewById(R.id.progressbarId);

        teacherDatabase= FirebaseDatabase.getInstance().getReference().child("teachers");

        signUpButton.setOnClickListener(this);
        signInTextView.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.signUpButtonId:
                userRegister();

                break;

            case R.id.signInTextViewId:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegister() {

        final String email = signUpEmailEdittext.getText().toString().trim();
        String password = signUpPasswordEdittext.getText().toString().trim();

        if(email.isEmpty()){
            signUpEmailEdittext.setError("Enter an Email Address");
            signUpEmailEdittext.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpEmailEdittext.setError("Enter a Valid Email Address");
            signUpEmailEdittext.requestFocus();
            return;
        }

        if(password.isEmpty()){
            signUpPasswordEdittext.setError("Enter a Password");
            signUpPasswordEdittext.requestFocus();
            return;
        }

        if(password.length()<6){

            signUpPasswordEdittext.setError("Minimum length of a password should be 6");
            signUpPasswordEdittext.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Register is succesfull",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                } else {
                     if(task.getException() instanceof FirebaseAuthUserCollisionException){
                         Toast.makeText(getApplicationContext(),"User is already registered",Toast.LENGTH_LONG).show();
                     }else {

                         Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                     }
                }

            }
        });


    }
}
