package com.example.creadorpartiturasrober;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonRegister;
    private ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar vistas
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        logoImage = findViewById(R.id.logoImage);

        // Cargar y procesar la imagen del logo
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        Bitmap transparentBitmap = removeWhiteBackground(originalBitmap);
        logoImage.setImageBitmap(transparentBitmap);

        buttonRegister.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(RegisterActivity.this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            } else {
                // Registro exitoso
                Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Bitmap removeWhiteBackground(Bitmap original) {
        int width = original.getWidth();
        int height = original.getHeight();

        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(original, 0, 0, null);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = original.getPixel(x, y);
                if (Color.red(pixel) > 240 && Color.green(pixel) > 240 && Color.blue(pixel) > 240) {
                    result.setPixel(x, y, Color.TRANSPARENT);
                } else {
                    result.setPixel(x, y, pixel);
                }
            }
        }
        return result;
    }
}
