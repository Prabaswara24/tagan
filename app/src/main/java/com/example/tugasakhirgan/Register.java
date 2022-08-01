package com.example.tugasakhirgan;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Register extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private static final String URL_FOR_REGISTRATION = "https://ta-mifpolije.com/E31192259/UserController/registrasi";
    String emailPattern = "[a-zA-Z0-9._-]+@gmail+\\.+[a-z]+";

    ProgressDialog progressDialog;

    private EditText signupInputName, signupInputEmail, signupInputPassword, signupInputAge, signupInputNo;
    private Button btnSignUp;
    private Button btnLinkLogin;
    ImageView image;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        signupInputName = findViewById(R.id.signup_input_name);
        signupInputEmail = findViewById(R.id.signup_input_email);
        signupInputPassword = findViewById(R.id.signup_input_password);
        signupInputAge = findViewById(R.id.signup_input_age);
        signupInputNo = findViewById(R.id.signup_input_no);
        image = (ImageView)findViewById(R.id.image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }

        btnSignUp = findViewById(R.id.btn_signup);
        btnLinkLogin = findViewById(R.id.btn_link_login);

        genderRadioGroup = findViewById(R.id.gender_radio_group);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signupInputName.getText().toString().trim().isEmpty()) {
                    signupInputName.setError("Massukkan Nama");
                    signupInputName.requestFocus();
                    return;
                }
                if (signupInputEmail.getText().toString().trim().isEmpty()) {
                    signupInputEmail.setError("Massukkan Email");
                    signupInputEmail.requestFocus();
                    return;
                }
                if (!signupInputEmail.getText().toString().trim().matches(emailPattern)) {
                    signupInputEmail.setError("Harap Gunakan Gmail");
                    signupInputEmail.requestFocus();
                    return;
                }
                if (signupInputPassword.getText().toString().trim().isEmpty()) {
                    signupInputPassword.setError("Massukkan Password");
                    signupInputPassword.requestFocus();
                    return;
                }
                if (signupInputAge.getText().toString().trim().isEmpty()) {
                    signupInputAge.setError("Massukkan Alamat");
                    signupInputAge.requestFocus();
                    return;
                }
                if (signupInputNo.getText().toString().trim().isEmpty()) {
                    signupInputNo.setError("Massukkan No.HP");
                    signupInputNo.requestFocus();
                    return;
                }

                //if everything is ok we will open image chooser
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });
        btnLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            //getting the image Uri
            Uri imageUri = data.getData();
            try {
                //getting bitmap object from uri
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                //displaying selected image to imageview
                image.setImageBitmap(bitmap);

                //calling the method uploadBitmap to upload image
                uploadBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadBitmap(final Bitmap bitmap) {

        //getting the tag from the edittext
        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        String gender;
        if (selectedId == R.id.female_radio_btn)
            gender = "Female";
        else
            gender = "Male";

        String nama = signupInputName.getText().toString();
        String email = signupInputEmail.getText().toString();
        String pass = signupInputPassword.getText().toString();
        String alamat = signupInputAge.getText().toString();
        String no = signupInputNo.getText().toString();

        progressDialog.setMessage("Adding you ...");
        showDialog();

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, URL_FOR_REGISTRATION,
                new Response.Listener<NetworkResponse>() {

                    @Override
                    public void onResponse(NetworkResponse response) {
                        System.out.println(response);
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            System.out.println(response.data);
                            String user = obj.getJSONObject("data").getString("name");
                            Toast.makeText(getApplicationContext(), "Hi " + user + ", You are successfully Added!", Toast.LENGTH_SHORT).show();

                            // Launch login activity
                            Intent intent = new Intent(Register.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        Log.e(TAG, "Registration Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(), "You are successfully Register!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }) {

            /*
             * If you want to add more parameters with the image
             * you can do it here
             * here we have only one parameter with the image
             * which is tags
             * */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama", nama);
                params.put("email", email);
                params.put("no", no);
                params.put("password", pass);
                params.put("gender", gender);
                params.put("address", alamat);
                return params;
            }

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("foto_ktp", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}