package com.example.my_foodapps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Add_Food_Menu extends AppCompatActivity {
    private ImageView imageViewAddImage;
    private EditText foodname,price;
    private RatingBar rate;
    private Button buttonSave;

    private Bitmap selectedImage;
    private Bitmap scaledImage;
    private ImageButton Back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_menu);


        imageViewAddImage = findViewById(R.id.imageViewAddImage);
        foodname = findViewById(R.id.editTextfood);
        price = findViewById(R.id.editTextprice);
        rate = findViewById(R.id.editrate);

        buttonSave = findViewById(R.id.buttonSave);
        Back = findViewById(R.id.backimage);

        imageViewAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Add_Food_Menu.this
                        , Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Add_Food_Menu.this
                            ,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                else
                {
                    Intent imageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(imageIntent,2);
                }

            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nothing added", Toast.LENGTH_SHORT).show();
                finish ();

            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Adddata();


                String Foodname =foodname.getText().toString();
                String Price = price.getText().toString();
                String Rating = String.valueOf(rate.getRating());

                if (selectedImage == null)
                {
                    Toast.makeText(getApplicationContext(), "Please select an image!", Toast.LENGTH_SHORT).show();
                }else if(Price == null){
                    Toast.makeText(getApplicationContext(), "Please kindly add price!", Toast.LENGTH_SHORT).show();
                }else {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    scaledImage = makeSmall(selectedImage,300);
                    scaledImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
                    byte [] image = outputStream.toByteArray();

                    Intent i = new Intent();
                    i.putExtra("itemname",Foodname);
                    i.putExtra("price" ,Price);
                    i.putExtra("rating",Rating);
                    i.putExtra("imageUrl",image);

                    setResult(RESULT_OK,i);
                    finish ();

                }

            }

        });

    }
/**
    private void Adddata() {



        String Foodname =foodname.getText().toString();
        Double Price = Double.valueOf(price.getText().toString());
        Double Rating = Double.valueOf(rate.getRating());
        if (selectedImage == null)
        {
            Toast.makeText(getApplicationContext(), "Please select an image!", Toast.LENGTH_SHORT).show();
        }else if(price == null){
            Toast.makeText(getApplicationContext(), "Please kindly add price!", Toast.LENGTH_SHORT).show();
        }else {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            scaledImage = makeSmall(selectedImage,300);
            scaledImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
            byte [] image = outputStream.toByteArray();

            Intent i = new Intent();
            i.putExtra("itemname",Foodname);
            i.putExtra("price" ,Price);
            i.putExtra("rating",Rating);
            i.putExtra("imageUrl",image);

            setResult(RESULT_OK,i);
            finish ();

        }


    }
 **/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Intent imageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imageIntent,2);
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null)
        {
            try {

                if (Build.VERSION.SDK_INT >= 29)
                {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),data.getData());
                    selectedImage = ImageDecoder.decodeBitmap(source);
                }

                else
                {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
                }

                imageViewAddImage.setImageBitmap(selectedImage);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public Bitmap makeSmall(Bitmap image, int maxSize)
    {
        int width = image.getWidth();
        int height = image.getHeight();

        float ratio = (float) width / (float)height;

        if (ratio > 1)
        {
            width = maxSize;
            height = (int)(width / ratio);

        }
        else
        {
            height = maxSize;
            width = (int)(height * ratio);
        }

        return Bitmap.createScaledBitmap(image,width,height,true);
    }
}