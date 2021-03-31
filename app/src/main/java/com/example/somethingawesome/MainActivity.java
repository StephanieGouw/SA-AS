package com.example.somethingawesome;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Intent;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCamera = (Button) findViewById(R.id.btnCamera);
        imageView = (ImageView) findViewById(R.id.imageView);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        Log.d("DEBUG", "about to set bit map image");
        imageView.setImageBitmap(bitmap);
        btnCamera.setBackgroundColor(getDominantColor(bitmap));
        Log.d("DEBUG", "dominant colour is "+getDominantColor(bitmap));
    }

    public int getDominantColor(Bitmap bitmap) {
        Log.d("DEBUG", "function called");
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        final int color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
        return color;
    }

//    // Generate palette synchronously and return it
//    public Palette createPaletteSync (Bitmap bitmap){
//        Palette p = Palette.from(bitmap).generate();
//        return p;
//    }
//
//    public void setButtonColor(Bitmap bitmap) {
//        Log.d("DEBUG", "function was called");
//        // Generate the palette and get the vibrant swatch
//        // See the createPaletteSync() method
//        // from the code snippet above
//        Palette p = createPaletteSync(bitmap);
//        Palette.Swatch vibrantSwatch = p.getVibrantSwatch();
//        Log.d("DEBUG", "created palettes");
//        // Load default colors
//        int backgroundColor = 17170455;
////                ContextCompat.getColor(this, R.color.holo_red_dark);
//        int textColor = 17170458;
////                ContextCompat.getColor(this, R.color.red);
//        Log.d("DEBUG", "before, background colour was " + backgroundColor);
//        Log.d("DEBUG", "before, text colour was " + textColor);
//        // Check that the Vibrant swatch is available
//        if(vibrantSwatch != null){
//            backgroundColor = vibrantSwatch.getRgb();
//            textColor = vibrantSwatch.getTitleTextColor();
////            System.out.println("background colour is " + backgroundColor);
////            System.out.println("text color is " + textColor);
//            Log.d("DEBUG", "background colour is " + backgroundColor);
//            Log.d("DEBUG", "text colour is " + textColor);
//        }
//
//        Log.d("DEBUG", "after, background colour is " + backgroundColor);
//        Log.d("DEBUG", "after, text colour is " + textColor);
//        // Set the toolbar background and text colors
//        btnCamera.setBackgroundColor(backgroundColor);
//        Log.d("DEBUG", "background color was set");
//        //btnCamera.setTitleTextColor(textColor);
//    }
}
