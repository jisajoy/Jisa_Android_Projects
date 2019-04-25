package com.mysports.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.mysports.R;
import com.mysports.adapter.HoursRateRecycleView;
import com.mysports.interfacepackage.ChildFragmentChangeListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static android.support.v4.content.ContextCompat.checkSelfPermission;
import static com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage;

public class MyAccountFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    RelativeLayout mProfilePictureLayout;
    CircleImageView mProfileImage;
    LinearLayout mNoImageLayout;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    Dialog mediaAccessDialog;
    Uri photoURI;
    public static int CHOOSE_CAMERA = 501;
    public static int CHOOSE_GALLERY = 502;
    private final int CROP_IMAGE = 100;
    public MyAccountFragment() {
        // Required empty public constructor
    }

    public static MyAccountFragment newInstance(String param1, String param2) {
        MyAccountFragment fragment = new MyAccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProfilePictureLayout = view.findViewById(R.id.profile_picture_layout);
        mProfilePictureLayout.setOnClickListener(this);
        mProfileImage = view.findViewById(R.id.profile_image);
        mNoImageLayout = view.findViewById(R.id.no_image_layout);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.profile_picture_layout) {
            mNoImageLayout.setVisibility(View.GONE);
            mediaAccess();
          /*  *//*if (checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_REQUEST_CODE);
            }*//*
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePicture, 0);
           *//* Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, 1);*/
        }
    }

    public void showNewFragment(Fragment fragment, String TAG) {
        ChildFragmentChangeListener changeListener = (ChildFragmentChangeListener) getActivity();
        changeListener.replaceFragment(fragment, TAG);
    }

   /* public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    System.out.println("Image saved");
                    Uri selectedImage = imageReturnedIntent.getData();//image to set to imageview
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    Uri tempUri = getImageUri(getActivity(), photo);
                    File finalFile = new File(getRealPathFromURI(tempUri));
                    String filePath = finalFile.getPath();
                    System.out.println("imageUrl : " + finalFile);//sample : -   /storage/emulated/0/Pictures/1517292829154.jpg
                    mProfileImage.setImageBitmap(getCameraPhotoOrientation(getActivity(), selectedImage, filePath, photo));
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {//put the same code from camera
                    Uri selectedImage = imageReturnedIntent.getData();
                    mProfileImage.setImageURI(selectedImage);
                }
                break;
        }
    }*/

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
              /*  Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 0);*/
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void mediaAccess() {
        mediaAccessDialog = new Dialog(getActivity(), R.style.Theme_AppCompat_Dialog);
        mediaAccessDialog.setTitle(null);
        Window window = mediaAccessDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        mediaAccessDialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mediaAccessDialog.setContentView(R.layout.media_selection);
        mediaAccessDialog.setCancelable(false);
        mediaAccessDialog.setCanceledOnTouchOutside(false);
        mediaAccessDialog.show();
        RelativeLayout mGalleryIntent = mediaAccessDialog.findViewById(R.id.gallery_intent);
        mGalleryIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryIntent();
                mediaAccessDialog.dismiss();
            }
        });
        RelativeLayout mCameraInent = mediaAccessDialog.findViewById(R.id.camera_intent);
        mCameraInent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntent();
            }
        });
        TextView close = (TextView) mediaAccessDialog.findViewById(R.id.txt_dialog_ok);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaAccessDialog.dismiss();

            }
        });
    }
    private void galleryIntent() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, CHOOSE_GALLERY);
    }
    public Bitmap getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath, Bitmap photo) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    return rotateImage(photo, 270);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    return rotateImage(photo, 180);
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    return rotateImage(photo, 90);
                default:
                    return photo;
            }

           /* Log.i("RotateImage", "Exif orientation: " + orientation);
            Log.i("RotateImage", "Rotate value: " + rotate);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return photo;
    }

    private void cameraIntent() {
        mediaAccessDialog.dismiss();
       /* System.out.println("dialog call");
        values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getActivity().getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);*/

        if (!Permissive.checkPermission(getActivity(), Manifest.permission.CAMERA)) {
            new Permissive.Request(Manifest.permission.CAMERA)
                    .whenPermissionsGranted(new PermissionsGrantedListener() {
                        @Override
                        public void onPermissionsGranted(String[] permissions) throws SecurityException {
                            cameraAction();

                        }
                    })
                    .whenPermissionsRefused(new PermissionsRefusedListener() {
                        @Override
                        public void onPermissionsRefused(String[] permissions) {
                            Toast.makeText(getActivity(), "Please Grant Permission", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .execute(getActivity());
        } else {
            cameraAction();

        }
    }


    public void cameraAction() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            try {
                File photoFile = createImageFileWith();
                String path = photoFile.getAbsolutePath();
                photoURI = FileProvider.getUriForFile(getActivity(),
                        getString(R.string.file_provider_authority),
                        photoFile);
                System.out.println("photofile" + photoURI);

            } catch (IOException ex) {
                Log.e("TakePicture", ex.getMessage());
            }

            if (photoURI != null) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent, CHOOSE_CAMERA);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_CAMERA) {
                System.out.println("return_uri" + photoURI);
                String realPath = getRealPathFromURI(photoURI);
                System.out.println("realpath"+realPath);
            }
            if (requestCode == CHOOSE_GALLERY) {
                galleryImageStoring(data.getData());
            }
            //cropper code
            if (requestCode == 100) {
                // upload the image uri to the server from here
                System.out.println("image"+data.getData());
            }
        }
    }

    private void galleryImageStoring(Uri data) {
        performCropImage(data);
    }

    public File createImageFileWith() throws IOException {
        final String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        final String imageFileName = "JPEG_" + timestamp;
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "pics");
        storageDir.mkdirs();
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }


    private boolean performCropImage(Uri mFinalImageUri) {
        try {
            if (mFinalImageUri != null) {

                //call the standard crop action intent (the user device may not support it)
                Intent cropIntent = new Intent("com.android.camera.action.CROP");
                //indicate image type and Uri
                cropIntent.setDataAndType(mFinalImageUri, "image/*");
                //set crop properties
                cropIntent.putExtra("crop", "true");
                //indicate aspect of desired crop
                cropIntent.putExtra("aspectX", 1);
                cropIntent.putExtra("aspectY", 1);
                cropIntent.putExtra("scale", true);
                //indicate output X and Y
                cropIntent.putExtra("outputX", 500);
                cropIntent.putExtra("outputY", 500);
                //retrieve data on return
                cropIntent.putExtra("return-data", false);

                File f = createNewFile("CROP_");


                try {
                    f.createNewFile();
                } catch (IOException ex) {
                    Log.e("io", ex.getMessage());
                }

                Uri mCropImagedUri = Uri.fromFile(f);
                System.out.println("mCropImagedUri"+mCropImagedUri);
                cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCropImagedUri);
                //start the activity - we handle returning in onActivityResult
                startActivityForResult(cropIntent, CROP_IMAGE);
                return true;
            }
        } catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return false;
    }

    private File createNewFile(String prefix) {
        if (prefix == null || "".equalsIgnoreCase(prefix)) {
            prefix = "IMG_";
        }
        File newDirectory = new File(Environment.getExternalStorageDirectory() + "/mypics/");
        if (!newDirectory.exists()) {
            if (newDirectory.mkdir()) {
                Log.d(this.getClass().getName(), newDirectory.getAbsolutePath() + " directory created");
            }
        }
        File file = new File(newDirectory, (prefix + System.currentTimeMillis() + ".jpg"));
        if (file.exists()) {
            //this wont be executed
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}
