package com.aslammaududy.graphing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class BantuanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);
    }

    public void listOnClick(View view) {
        Bitmap bitmap;
        ByteArrayOutputStream stream;
        byte[] bytes;
        Intent intent;
        switch (view.getId()) {
            case R.id.fungsilinier:
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fungsilinier);
                stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                bytes = stream.toByteArray();

                intent = new Intent(this, DetailBantuanActivity.class);
                intent.putExtra("gambar", bytes);
                intent.putExtra("detail", R.string.fungsi_linier);
                startActivity(intent);
                break;
            case R.id.fungsikuadrat:
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fungsikuadrat);
                stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                bytes = stream.toByteArray();

                intent = new Intent(this, DetailBantuanActivity.class);
                intent.putExtra("gambar", bytes);
                intent.putExtra("detail", R.string.fungsi_kuadrat);
                startActivity(intent);
                break;
            case R.id.trigonometri:
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.trigonometri);
                stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                bytes = stream.toByteArray();

                intent = new Intent(this, DetailBantuanActivity.class);
                intent.putExtra("gambar", bytes);
                intent.putExtra("detail", R.string.trigonometri);
                startActivity(intent);
                break;
        }
    }
}
