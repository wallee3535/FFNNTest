package com.example.walle.ffnntest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Bitmap imageBitmap;
    public static int colors[][] = new int[204][153];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] arr = {1, 2, 3};
        FFNN net = new FFNN(arr);

        double[] feed = {1};
        net.feedforward(feed);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void onButtonClicked(View view){
        dispatchTakePictureIntent();
    }

    //https://developer.android.com/training/camera/photobasics.html
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            Log.i("walter's tag", Integer.toString(imageBitmap.getHeight()));
            Log.i("walter's tag", Integer.toString(imageBitmap.getWidth()));
            int color = imageBitmap.getPixel(0, 0);
            Log.i("walter's tag", "red: "+ Color.red(color)+", green: "+ Color.green(color)+", blue: "+ Color.blue(color));
            color = imageBitmap.getPixel(152, 152);
            Log.i("walter's tag", "red: "+Color.red(color)+", green: "+ Color.green(color)+", blue: "+ Color.blue(color));
            //mImageView.setImageBitmap(imageBitmap);
            //dispatchTakePictureIntent();
        }
    }
}
