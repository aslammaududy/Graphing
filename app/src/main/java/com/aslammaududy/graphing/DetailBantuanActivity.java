package com.aslammaududy.graphing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailBantuanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bantuan);

        Bundle bundle = getIntent().getExtras();
        byte[] bytes = bundle.getByteArray("gambar");
        String s = bundle.getString("detail");

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ImageView view = (ImageView) findViewById(R.id.gambar);
        view.setImageBitmap(bitmap);

        TextView textView = (TextView) findViewById(R.id.detail);
        textView.setText(s);
    }
}
