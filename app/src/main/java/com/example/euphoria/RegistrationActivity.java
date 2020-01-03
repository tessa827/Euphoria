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

import com.example.euphoria.LoginActivity;
import com.example.euphoria.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    Button signUp_btn;
    TextView signIn_text;
    EditText txtusername, txtemail, txtpass;
    String user,email,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signUp_btn = findViewById(R.id.signUp_btn);
        signIn_text = findViewById(R.id.signIn_text);
        txtusername = findViewById(R.id.txtusername);
        txtemail = findViewById(R.id.txtemail);
        txtpass = findViewById(R.id.txtpass);
        mAuth = FirebaseAuth.getInstance();

        TextView signIn_text = findViewById(R.id.signIn_text);
        signIn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = txtusername.getText().toString();
                email = txtemail.getText().toString();
                pass = txtpass.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(getApplicationContext(), "Masukan Username !!", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukan Email!!", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Masukan Password", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegistrationActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //jika daftar sukses maka masuk ke mainact
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);

                                    startActivity(intent);
                                    finish();
                                } else {
                                    //jika daftar gagal berikan pesan
                                    Toast.makeText(RegistrationActivity.this, "Proses Pendaftaran Gagagl!! : "
                                                    + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}



