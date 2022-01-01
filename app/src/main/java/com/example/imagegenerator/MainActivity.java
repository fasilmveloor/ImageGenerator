package com.example.imagegenerator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText xBox, yBox, fontBox, textBox;
    Button generate;
    ImageView genimage;
    int X;
    int Y;
    int fontsize;
    String textdisp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xBox = findViewById(R.id.X);
        yBox = findViewById(R.id.Y);
        fontBox = findViewById(R.id.font);
        textBox = findViewById(R.id.textdisplay);
        generate = findViewById(R.id.generate);
        genimage = findViewById(R.id.genimage);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                X = Integer.parseInt(xBox.getText().toString());
                Y = Integer.parseInt(yBox.getText().toString());
                fontsize = Integer.parseInt(fontBox.getText().toString());
                textdisp = textBox.getText().toString();
                BitmapDrawable img = generateCertificate(textdisp, X, Y, fontsize);
                genimage.setImageDrawable(img);
            }
        });

    }

    private BitmapDrawable generateCertificate(String text, int X, int Y, int fontsize) {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.cartificate)
                .copy(Bitmap.Config.ARGB_8888, true);

        Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(convertToPixels( fontsize));

        Rect textRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), textRect);

        Canvas canvas = new Canvas(bm);

        if(textRect.width() >= (canvas.getWidth() - 4)) {
            paint.setTextSize(convertToPixels( fontsize/2));
        }

        canvas.drawText(text, X, Y, paint);


        return new BitmapDrawable(getResources(), bm);


    }

    public int convertToPixels(int nDP)
    {
        final float conversionScale = getResources().getDisplayMetrics().density;

        return (int) ((nDP * conversionScale) + 0.5f) ;

    }
}