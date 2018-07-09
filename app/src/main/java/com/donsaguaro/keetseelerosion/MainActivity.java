
package com.donsaguaro.keetseelerosion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

//import com.donsaguaro.keetseelerosion.R;

import java.io.InputStream;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE = 100;
    private final int myLocUpload = 0;
    public static final String EXTRA_MESSAGE = "com.donsaguaro.keetseelerosion.MainActivity.MESSAGE";


    private ImageView check1;
    private ImageView check2;
    private ImageView check3;
    private ImageView check4;
    private ImageView check5;
    private ImageView check6;
//    private int imageLength;
    private String dateStringFinal;
    private EditText dateInput;
    private TextView dateError;
    private TextView uploading;
    private Button instructions;
    private Button privacyButton;
    private Button showGPS;
    private boolean setDateBool = false;

    private Button selectImageButton1;
    private Button selectImageButton2;
    private Button selectImageButton3;
    private Button selectImageButton4;
    private Button selectImageButton5;
    private Button selectImageButton6;

    private Button uploadImageButton1;
    private Button uploadImageButton2;
    private Button uploadImageButton3;
    private Button uploadImageButton4;
    private Button uploadImageButton5;
    private Button uploadImageButton6;

    private Button setDateButton;
    //private String dateStringFinal;
    private PopupWindow pw;
    HashMap<Integer, Uri> locDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//        AssetManager am = getApplicationContext().getAssets();
//        AssetFileDescriptor afd = null;
//        try {
//            afd = am.openFd( "KSTiles.sqlite");
//
//            // Create new file to copy into.
//            File file = new File(Environment.getExternalStorageDirectory() + java.io.File.separator + "KSTilessd.sqlite");
//            file.createNewFile();
//            System.out.println("second sd try");
//
//            System.out.println(Environment.getExternalStorageDirectory().toString());
//            copyFdToFile(afd.getFileDescriptor(), file);
//
//        } catch(IOException e) {
//            e.printStackTrace();
//        }






        this.check1= (ImageView) findViewById(R.id.check1);
        this.check2= (ImageView) findViewById(R.id.check2);
        this.check3= (ImageView) findViewById(R.id.check3);
        this.check4= (ImageView) findViewById(R.id.check4);
        this.check5= (ImageView) findViewById(R.id.check5);
        this.check6= (ImageView) findViewById(R.id.check6);
        //copyAssets();
        this.dateInput= (EditText) findViewById(R.id.dateInput);
        this.instructions = (Button) findViewById(R.id.instructions);
        this.privacyButton = (Button) findViewById(R.id.privacyButton);
        this.showGPS = (Button) findViewById(R.id.gps);
        this.dateError = (TextView) findViewById(R.id.dateError);
        this.uploading = (TextView) findViewById(R.id.uploading);

//        dateError.setVisibility(View.INVISIBLE);
//        uploading.setVisibility(View.INVISIBLE);
        String instructionsText = getString(R.string.instructionsText);
        final String storageSig = getString(R.string.storageSignature);
        String privacyText = getString(R.string.privacyText);
        //String mapText = "MAP";


        this.selectImageButton1 = (Button) findViewById(R.id.select1);
        this.selectImageButton2 = (Button) findViewById(R.id.select2);
        this.selectImageButton3 = (Button) findViewById(R.id.select3);
        this.selectImageButton4 = (Button) findViewById(R.id.select4);
        this.selectImageButton5 = (Button) findViewById(R.id.select5);
        this.selectImageButton6 = (Button) findViewById(R.id.select6);

        this.uploadImageButton1 = (Button) findViewById(R.id.upload1);
        this.uploadImageButton2 = (Button) findViewById(R.id.upload2);
        this.uploadImageButton3 = (Button) findViewById(R.id.upload3);
        this.uploadImageButton4 = (Button) findViewById(R.id.upload4);
        this.uploadImageButton5 = (Button) findViewById(R.id.upload5);
        this.uploadImageButton6 = (Button) findViewById(R.id.upload6);


        uploadImageButton1.setEnabled(false);
        uploadImageButton2.setEnabled(false);
        uploadImageButton3.setEnabled(false);
        uploadImageButton4.setEnabled(false);
        uploadImageButton5.setEnabled(false);
        uploadImageButton6.setEnabled(false);
        selectImageButton1.setEnabled(false);
        selectImageButton2.setEnabled(false);
        selectImageButton3.setEnabled(false);
        selectImageButton4.setEnabled(false);
        selectImageButton5.setEnabled(false);
        selectImageButton6.setEnabled(false);

        this.selectImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SelectImageFromGallery(1); }
        });
        this.selectImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SelectImageFromGallery(2); }
        });
        this.selectImageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SelectImageFromGallery(3); }
        });
        this.selectImageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SelectImageFromGallery(4); }
        });
        this.selectImageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SelectImageFromGallery(5); }
        });
        this.selectImageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SelectImageFromGallery(6); }
        });

        this.uploadImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploading.setVisibility(View.VISIBLE);
                uploadImageButton1.setEnabled(false);
                UploadImage(1,storageSig); }
        });
        this.uploadImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploading.setVisibility(View.VISIBLE);
                uploadImageButton2.setEnabled(false);
                UploadImage(2,storageSig); }
        });
        this.uploadImageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploading.setVisibility(View.VISIBLE);
                uploading.setVisibility(View.VISIBLE);
                uploadImageButton3.setEnabled(false);
                UploadImage(3,storageSig); }
        });
        this.uploadImageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageButton4.setEnabled(false);
                UploadImage(4,storageSig); }
        });
        this.uploadImageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploading.setVisibility(View.VISIBLE);
                uploadImageButton5.setEnabled(false);
                UploadImage(5,storageSig); }
        });
        this.uploadImageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploading.setVisibility(View.VISIBLE);
                uploadImageButton6.setEnabled(false);
                UploadImage(6, storageSig); }
        });



        this.setDateButton = (Button) findViewById(R.id.setDateButton);
        //this.previewImageButton1 = (Button) findViewById(R.id.preview1);
        locDictionary = new HashMap<Integer, Uri>();

        this.setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateString = dateInput.getText().toString();
                String regex = "[0-9]+";

                if (dateString.matches(regex) && (dateString.length()==8)){
                    Boolean yearMin= (Integer.parseInt(dateString.substring(4,8))>2017);
                    Boolean yearMax= (Integer.parseInt(dateString.substring(4,8))<2100);
                    if (yearMin && yearMax) {
                        dateInput.setBackgroundColor(Color.GREEN);
                        setDateBool = true;
                        dateError.setVisibility(View.INVISIBLE);
                        String dateStringYear = dateString.substring(4, 8);
                        String dateStringMonth = dateString.substring(0, 2);
                        String dateStringDay = dateString.substring(2, 4);
                        dateStringFinal = dateStringYear + dateStringMonth + dateStringDay;
                        selectImageButton1.setEnabled(true);
                        selectImageButton2.setEnabled(true);
                        selectImageButton3.setEnabled(true);
                        selectImageButton4.setEnabled(true);
                        selectImageButton5.setEnabled(true);
                        selectImageButton6.setEnabled(true);
                    }else{
                        dateError.setVisibility(View.VISIBLE);
                        //dateError.setBackgroundColor(Color.RED);
                    }
                } else {
                    dateError.setVisibility(View.VISIBLE);
                    //dateError.setBackgroundColor(Color.RED);
                }
            }
        });


        setOnClick(instructions,instructionsText);
        setOnClick(privacyButton,privacyText);
        setOnClickGPS(showGPS);

        Context ctx = getApplicationContext();

        String myPerm= android.Manifest.permission.ACCESS_FINE_LOCATION;
        int res = ctx.checkCallingOrSelfPermission( myPerm);
        if (res == PackageManager.PERMISSION_GRANTED){
            System.out.println("GRANTED!!!!");
        }
        else {
            Integer goodGPSPermission = 99;
            System.out.println("NOT GRANTED!!!!");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    goodGPSPermission
            );

        }
        if (res == PackageManager.PERMISSION_GRANTED){
            System.out.println("Post GRANTED!!!!");
        }


//
//
//        String myPermsd= Manifest.permission.WRITE_EXTERNAL_STORAGE;
//        int ressd = ctx.checkCallingOrSelfPermission( myPermsd);
//        if (ressd == PackageManager.PERMISSION_GRANTED){
//            System.out.println("SDGRANTED!!!!");
//        }
//        else {
//            Integer goodsdPermission = 98;
//            System.out.println("SDNOT GRANTED!!!!");
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    goodsdPermission
//            );
//
//        }




    }
    private void setOnClickGPS(final Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7 = new Intent(getBaseContext(), act_GPS.class);
                startActivity(intent7);
            }
        });
    }
    private void setOnClick(final Button btn, final String writeText){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiatePopupWindow(view, writeText);
            }
        });
    }

    private void initiatePopupWindow(View v, String writeText) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.popupip,
                    (ViewGroup) findViewById(R.id.popupIP));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            // display the popup in the center
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);
            //ImageView imageViewMap = (ImageView) layout.findViewById(R.id.imageViewMap);
//            if (writeText=="MAP"){
//                imageViewMap.setVisibility(View.VISIBLE);
//
//            }else {
//                ViewGroup.LayoutParams paramss=imageViewMap.getLayoutParams();
//                paramss.height=1;
//                paramss.width=1;
//
//                imageViewMap.setLayoutParams(paramss);
//                imageViewMap.setVisibility(View.INVISIBLE);

            TextView textViewIP = (TextView) layout.findViewById(R.id.textViewIP);
            textViewIP.setText(writeText);
            //}
            Button cancelButton = (Button) layout.findViewById(R.id.okIP);
            cancelButton.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
    };

//    private void ListImages(Integer myLoc) {
//        switch (myLoc) {
//            case 1:
//                Intent intent1 = new Intent(getBaseContext(), ListImagesActivity99.class);
//                String messageid1 ="1";
//                intent1.putExtra(EXTRA_MESSAGE, messageid1);
//                startActivity(intent1);
//                break;
//            case 2:
//                Intent intent2 = new Intent(getBaseContext(), ListImagesActivity99.class);
//                String messageid2 ="2";
//                intent2.putExtra(EXTRA_MESSAGE, messageid2);
//                startActivity(intent2);
//                break;
//            case 3:
//                Intent intent3 = new Intent(getBaseContext(), ListImagesActivity99.class);
//                String messageid3 ="3";
//                intent3.putExtra(EXTRA_MESSAGE, messageid3);
//                startActivity(intent3);
//                break;
//            case 4:
//                Intent intent4 = new Intent(getBaseContext(), ListImagesActivity99.class);
//                String messageid4 ="4";
//                intent4.putExtra(EXTRA_MESSAGE, messageid4);
//                startActivity(intent4);
//                break;
//            case 5:
//                Intent intent5 = new Intent(getBaseContext(), ListImagesActivity99.class);
//                String messageid5 ="5";
//                intent5.putExtra(EXTRA_MESSAGE, messageid5);
//                startActivity(intent5);
//                break;
//            case 6:
//                Intent intent6 = new Intent(getBaseContext(), ListImagesActivity99.class);
//                System.out.println("Hitting6");
//                String messageid6 ="6";
//                intent6.putExtra(EXTRA_MESSAGE, messageid6);
//                startActivity(intent6);
//                break;
//        }
//    }




    private class myRunnable implements Runnable{
        public Integer myLoc;
        public String storageSig;
        public myRunnable(Integer myLoc, String storageSig){
            this.myLoc=myLoc;
            this.storageSig= storageSig;
        }
        public void run(){

        }
    }


    private void UploadImage(final Integer myLoc, final String storageSig)
    {
        try {
            System.out.println("Upload thinks uri is "+locDictionary.get(myLoc).toString());

            final InputStream imageStream = getContentResolver().openInputStream(locDictionary.get(myLoc));
            final int imageLength = imageStream.available();
            final Handler handler = new Handler();

            Thread th = new Thread(new myRunnable(myLoc, storageSig) {
                public void run() {
                    try {
                        System.out.println("About to upload"+myLoc);

                        final String imageName = ImageManager.UploadImage(imageStream, imageLength, dateStringFinal, myLoc,storageSig);
                        System.out.println("About to uploaded!!!"+myLoc);
                        System.out.println("Returned is "+imageName);
                        uploading.setVisibility(View.INVISIBLE);

                        switch (myLoc) {
                            case 1:
                                ImageView currentCheck1 = (ImageView) findViewById(R.id.check1);
                                currentCheck1.setVisibility(View.VISIBLE);
                                currentCheck1.setImageResource(R.drawable.success);
                                break;
                            case 2:
                                ImageView currentCheck2 = (ImageView) findViewById(R.id.check2);
                                currentCheck2.setVisibility(View.VISIBLE);
                                currentCheck2.setImageResource(R.drawable.success);
                                break;
                            case 3:
                                ImageView currentCheck3 = (ImageView) findViewById(R.id.check3);
                                currentCheck3.setVisibility(View.VISIBLE);
                                currentCheck3.setImageResource(R.drawable.success);
                                break;
                            case 4:
                                ImageView currentCheck4 = (ImageView) findViewById(R.id.check4);
                                currentCheck4.setVisibility(View.VISIBLE);
                                currentCheck4.setImageResource(R.drawable.success);
                                break;
                            case 5:
                                ImageView currentCheck5 = (ImageView) findViewById(R.id.check5);
                                currentCheck5.setVisibility(View.VISIBLE);
                                currentCheck5.setImageResource(R.drawable.success);
                                break;
                            case 6:
                                ImageView currentCheck6 = (ImageView) findViewById(R.id.check6);
                                currentCheck6.setVisibility(View.VISIBLE);
                                currentCheck6.setImageResource(R.drawable.success);
                                break;
                        }





                        handler.post(new Runnable() {

                            public void run() {
                                Toast.makeText(MainActivity.this, "Image Uploaded Successfully. Name = " + imageName, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    catch(Exception ex) {
                        final String exceptionMessage = ex.getMessage();
                        uploading.setVisibility(View.INVISIBLE);

                        runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                switch (myLoc) {
                                    case 1:
                                        ImageView currentCheck1 = (ImageView) findViewById(R.id.check1);
                                        currentCheck1.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        ImageView currentCheck2 = (ImageView) findViewById(R.id.check2);
                                        currentCheck2.setVisibility(View.VISIBLE);
                                        break;
                                    case 3:
                                        ImageView currentCheck3 = (ImageView) findViewById(R.id.check3);
                                        currentCheck3.setVisibility(View.VISIBLE);
                                        break;
                                    case 4:
                                        ImageView currentCheck4 = (ImageView) findViewById(R.id.check4);
                                        currentCheck4.setVisibility(View.VISIBLE);
                                        break;
                                    case 5:
                                        ImageView currentCheck5 = (ImageView) findViewById(R.id.check5);
                                        currentCheck5.setVisibility(View.VISIBLE);
                                        break;
                                    case 6:
                                        ImageView currentCheck6 = (ImageView) findViewById(R.id.check6);
                                        currentCheck6.setVisibility(View.VISIBLE);
                                        break;
                                }
                            }
                        });




                        System.out.println("Catch statement triggered!!");
                                                handler.post(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, exceptionMessage, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }});
            th.start();
        }
        catch(Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void SelectImageFromGallery(Integer myLoc)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra("loc", myLoc);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), myLoc);
    }





//
//    private void copyAssets() {
//        AssetManager assetManager = getAssets();
//        String[] files = null;
//        try {
//            files = assetManager.list("tiles");
//        } catch (IOException e) {
//            Log.e("tag", "Failed to get asset file list.", e);
//        }
//        if (files != null) for (String filename : files) {
//            InputStream in = null;
//            OutputStream out = null;
//            try {
//                in = assetManager.open("tiles/"+filename);
//                File outFile = new File(getExternalFilesDir(null), filename);
//                System.out.println("First sd try");
//
//                //File outFile = new File("/SDCARD/osmdroid/", filename);
//
//                System.out.println(getExternalFilesDir(null).toString());
//                out = new FileOutputStream(outFile);
//                copyFile(in, out);
//            } catch(IOException e) {
//                Log.e("tag", "Failed to copy asset file: " + filename, e);
//            }
//            finally {
//                if (in != null) {
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        // NOOP
//                    }
//                }
//                if (out != null) {
//                    try {
//                        out.close();
//                    } catch (IOException e) {
//                        // NOOP
//                    }
//                }
//            }
//        }
//    }
//    private void copyFile(InputStream in, OutputStream out) throws IOException {
//        System.out.println("Copyfile hit");
//
//        byte[] buffer = new byte[1024];
//        int read;
//        while((read = in.read(buffer)) != -1){
//            out.write(buffer, 0, read);
//        }
//    }
//
//
//
//
//    public static void copyFdToFile(FileDescriptor src, File dst) throws IOException {
//        FileChannel inChannel = new FileInputStream(src).getChannel();
//        FileChannel outChannel = new FileOutputStream(dst).getChannel();
//        try {
//            inChannel.transferTo(0, inChannel.size(), outChannel);
//        } finally {
//            if (inChannel != null)
//                inChannel.close();
//            if (outChannel != null)
//                outChannel.close();
//        }
//    }
//










    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    this.uploadImageButton1.setEnabled(true);
                    this.selectImageButton1.setBackgroundColor(Color.GREEN);
                    locDictionary.put(requestCode,imageReturnedIntent.getData());
                                    }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    this.uploadImageButton2.setEnabled(true);
                    this.selectImageButton2.setBackgroundColor(Color.GREEN);
                    locDictionary.put(requestCode,imageReturnedIntent.getData());
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    this.uploadImageButton3.setEnabled(true);
                    this.selectImageButton3.setBackgroundColor(Color.GREEN);
                    locDictionary.put(requestCode,imageReturnedIntent.getData());
                }
                break;
            case 4:
                if (resultCode == RESULT_OK) {
                    this.uploadImageButton4.setEnabled(true);
                    this.selectImageButton4.setBackgroundColor(Color.GREEN);
                    locDictionary.put(requestCode,imageReturnedIntent.getData());
                }
                break;
            case 5:
                if (resultCode == RESULT_OK) {
                    this.uploadImageButton5.setEnabled(true);
                    this.selectImageButton5.setBackgroundColor(Color.GREEN);
                    locDictionary.put(requestCode,imageReturnedIntent.getData());
                }
                break;
            case 6:
                if (resultCode == RESULT_OK) {
                    this.uploadImageButton6.setEnabled(true);
                    this.selectImageButton6.setBackgroundColor(Color.GREEN);
                    locDictionary.put(requestCode,imageReturnedIntent.getData());
                }
                break;

        }
        System.out.println("select thinks uri is "+locDictionary.get(requestCode).toString());

    }
}
