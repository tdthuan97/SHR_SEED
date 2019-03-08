package com.example.tdthu.shrimp_seed.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.EGLExt;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tdthu.shrimp_seed.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Fragment_Process_1_Step_2 extends Fragment {

    int PERMISSION_CODE = 1;
    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };

    int REQUEST_CODE_CAMERA = 111;
    int REQUEST_CODE_FILE = 112;

    View view;
    ImageView img;
    FloatingActionButton fabCamera;
    FloatingActionButton fabPickImg;
    Button btnCustomClearance;

    Uri imageUri;
    ContentValues values;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_process_1_step_2, container, false);
        init();
        event();
        return view;
    }
    private void event(){
        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasPermissions(getActivity(),PERMISSIONS)){
                    //If permission is already having then showing the toast
                    Toast.makeText(getActivity(),"You already have the permission",Toast.LENGTH_LONG).show();
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "New Picture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                    imageUri = getActivity().getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                    //Existing the method with return
                    return;
                }
                requestPermission();


        }
        });
        fabPickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FILE);
            }
        });
    }

    private void requestPermission() {
        if(!hasPermissions(getActivity(), PERMISSIONS)){
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_CODE);

        }
    }
    private boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (getActivity() != null && PERMISSIONS != null) {
                for (String permission : PERMISSIONS) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 111:
                if (requestCode == REQUEST_CODE_CAMERA) {
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            //Bitmap thumbnail = MediaStore.Images.Media.getBitmap(
                            //       getActivity().getContentResolver(),imageUri );
                            Bitmap thumbnail = ajustOrientImg(getRealPathFromURI(imageUri));
                            img.setImageBitmap(thumbnail);
                            //String file = getRealPathFromURI(imageUri);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
                break;
            case 112:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    InputStream inputStream = null;
                    try {
                        inputStream = getActivity().getContentResolver().openInputStream(uri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    img.setImageBitmap(bitmap);

                }
                break;
        }
        //if (requestCode == REQUEST_CODE_FILE && resultCode == RESULT_OK && data != null)
        //{


        //}
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void init(){
        img = view.findViewById(R.id.imageview_customclearance);
        fabCamera = view.findViewById(R.id.floatingbutton_custom_clearance_camera);
        fabPickImg = view.findViewById(R.id.floatingbutton_custom_clearance_pick_img);
        btnCustomClearance = view.findViewById(R.id.button_hatchery_customclearance);
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private Bitmap ajustOrientImg(String file){
        Bitmap rotatedBitmap = null;
        try {

            BitmapFactory.Options bounds = new BitmapFactory.Options();
            bounds.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file, bounds);

            BitmapFactory.Options opts = new BitmapFactory.Options();
            Bitmap bm = BitmapFactory.decodeFile(file, opts);
            ExifInterface exif = null;
            exif = new ExifInterface(file);
            String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
            int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

            int rotationAngle = 0;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

            Matrix matrix = new Matrix();
            matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
            rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
            rotatedBitmap = MediaStore.Images.Media.getBitmap(
                    getActivity().getContentResolver(), imageUri);
            return rotatedBitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotatedBitmap;
    }

}
