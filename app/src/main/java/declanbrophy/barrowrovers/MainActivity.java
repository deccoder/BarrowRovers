package declanbrophy.barrowrovers;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    //Declaring variables
    Button register,signIn;
    EditText emailAddress, password;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Tracks to see if user has signed in or signed out
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (emailAddress != null) {
                    Toast.makeText(MainActivity.this, "Email address Entered", Toast.LENGTH_LONG).show();

                }else {
                    if (password != null) {
                        Toast.makeText(MainActivity.this, "Password Entered", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };

        //Creating and Initializing user interface
        register = (Button) findViewById(R.id.register);
        signIn = (Button) findViewById(R.id.signIn);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password);
        //Listens for sign in button to be pressed
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString().trim();
                String pWord = password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter email address", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(pWord)){
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_LONG).show();
                }
                mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null){
                            Toast.makeText(getApplicationContext(),"User is signed in"+user.getUid(), Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "User is signed out", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                mAuth.signInWithEmailAndPassword(email, pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(), "Sign In with Email and Password Complete"+task.isSuccessful(), Toast.LENGTH_LONG).show();

                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Sign In with Email and Password Please", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        //Listens for register button to be pressed
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Coverts the text entered in email address to a String in variable email
                String email = emailAddress.getText().toString().trim();
                //Converts the text entered in password to a String in variable pWord.
                final String pWord = password.getText().toString().trim();
                //Checks if user has signed in with email address.
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Checks if user has entered a password
                if (TextUtils.isEmpty(pWord)){
                    Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                //Selection statement to check password and email
                mAuth.createUserWithEmailAndPassword(email, pWord);
                mAuth.signInWithEmailAndPassword(email,pWord).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            if (password.length() < 6) {
                                password.setError(getString(R.string.minimum_password));
                            }else if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this,getString(R.string.auth_failed),Toast.LENGTH_SHORT).show();
                            }else {
                                //Intent to go from Log in to Main Menu
                                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
                //Toast message to display successfull registration
                mAuth.signInWithEmailAndPassword(email,pWord).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this,"Registered Successfully"+task.isSuccessful(),Toast.LENGTH_LONG).show();
                        //Toast message to display failed registration
                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Authentication Failed"+task.getException(),Toast.LENGTH_SHORT).show();
                        }else {
                            //Intent to go from Log In to Main Menu
                            startActivity(new Intent(MainActivity.this, MainMenu.class));
                            finish();
                        }
                    }
                });
            }
        });

        }
    //On Start method to check for existing users
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);


    }
    //On Stop method to stop check for existing users
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
