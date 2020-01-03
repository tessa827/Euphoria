package com.example.euphoria;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.euphoria.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button signIn_btn;
    TextView signUp_text;
    EditText txtusername,txtpass;
    String user,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signIn_btn = findViewById(R.id.signIn_btn);
        signUp_text = findViewById(R.id.signUp_text);
        txtusername = findViewById(R.id.txtusername);
        txtpass = findViewById(R.id.txtpass);
        mAuth = FirebaseAuth.getInstance();

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = txtusername.getText().toString();
                pass = txtpass.getText().toString();
                if (TextUtils.isEmpty(user)){
                    Toast.makeText(getApplicationContext(),"Masukan Email Anda !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Masukan Password !!",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(user,pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //kalo login sukses masuk ke MainAct
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    //jika login gagal maka...
                                    Toast.makeText(LoginActivity.this, "Proses Login Gagal!! : " + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


        TextView signUp_text = findViewById(R.id.signUp_text);
        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });


    }


}
