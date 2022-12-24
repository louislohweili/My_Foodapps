package com.example.my_foodapps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class Update_food_item extends AppCompatActivity {

    private ImageView imageViewAddImage;
    private EditText foodname,price;
    private RatingBar rate;
    private Button buttonupdate;

    private Bitmap selectedImage;
    private Bitmap scaledImage;
    private ImageButton Back;
    int item_id;
    private byte [] image;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food_item);


        imageViewAddImage = findViewById(R.id.imageViewAddImage);
        foodname = findViewById(R.id.editTextupdatfood);
        price = findViewById(R.id.editTextupdateprice);
        rate = findViewById(R.id.editupdaterate);

        buttonupdate= findViewById(R.id.buttonupdate);
        Back = findViewById(R.id.backimage);
       // Intent i = getIntent();
        item_id = getIntent().getIntExtra("item_id",0);
        String itemname = getIntent().getStringExtra( "itemname");
        byte[] Image =  getIntent().getByteArrayExtra("imageUrl");
        String Price =getIntent().getStringExtra("price");
        String Rate = getIntent().getStringExtra("rating");
        foodname.setText(itemname);
        price.setText(Price);
        rate.setRating(Float.valueOf(String.valueOf(Rate)));
        image = getIntent().getByteArrayExtra("imageUrl");
        imageViewAddImage.setImageBitmap(BitmapFactory.decodeByteArray(Image,0,Image.length));
        imageViewAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Update_food_item.this
                        , Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Update_food_item.this
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
                Toast.makeText(getApplicationContext(),"Nothing update", Toast.LENGTH_SHORT).show();
                finish ();

            }
        });
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedata();
            }
        });
    }

    private void updatedata() {

        if (item_id == 0)
        {
            Toast.makeText(Update_food_item.this, "There ia a problem!", Toast.LENGTH_SHORT).show();
        }

        else {
            Intent intent = new Intent();
            String UpdateFoodName = foodname.getText().toString();
            String UpdatePrice = price.getText().toString();
            String UpdateRate = String.valueOf(Double.valueOf(rate.getRating()));
            intent.putExtra("item_id",item_id);
            intent.putExtra("UpdateFoodName",UpdateFoodName);
            intent.putExtra("UpdatePrice",UpdatePrice);
            intent.putExtra("UpdateRate",UpdateRate);
            if (selectedImage == null) {
                intent.putExtra("image", image);
            } else {

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                scaledImage = makeSmall(selectedImage, 300);
                scaledImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                byte[] image = outputStream.toByteArray();
                intent.putExtra("image", image);
            }

            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 5 && resultCode == RESULT_OK && data != null)
        {
            try {

                if (Build.VERSION.SDK_INT >= 28)
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