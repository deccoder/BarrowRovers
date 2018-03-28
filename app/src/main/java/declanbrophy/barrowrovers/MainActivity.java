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

    Button register,signIn;
    EditText emailAddress, password;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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



        register = (Button) findViewById(R.id.register);
        signIn = (Button) findViewById(R.id.signIn);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String pWord = password.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pWord)){
                    Toast.makeText(getApplicationContext(),"Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, pWord);
                mAuth.signInWithEmailAndPassword(email,pWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            if (password.length() < 6) {
                                password.setError("");
                            }else if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Test Response", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString().trim();
                final String pWord = password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pWord)){
                    Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
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
                                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
                mAuth.signInWithEmailAndPassword(email,pWord).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this,"Registered Successfully"+task.isSuccessful(),Toast.LENGTH_LONG).show();

                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Authentication Failed"+task.getException(),Toast.LENGTH_SHORT).show();
                        }else {
                            startActivity(new Intent(MainActivity.this, MainMenu.class));
                            finish();
                        }
                    }
                });
            }
        });

        }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);


    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
