/**----------------------------------------------------------------------------------
* Microsoft Developer & Platform Evangelism
*
* Copyright (c) Microsoft Corporation. All rights reserved.
*
* THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, 
* EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES 
* OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
*----------------------------------------------------------------------------------
* The example companies, organizations, products, domain names,	
* e-mail addresses, logos, people, places, and events depicted
* herein are fictitious.  No association with any real company,
* organization, product, domain name, email address, logo, person,
* places, or events is intended or should be inferred.
*----------------------------------------------------------------------------------
**/

package com.donsaguaro.keetseelerosion;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.donsaguaro.keetseelerosion.R;

public class ListImagesActivity99 extends AppCompatActivity {

    //String[] images;
    private Button showDescription;
    private Button cancelButton;
    private TextView textView;
    private TextView textViewDesc;
    private ImageView imageView;
    private PopupWindow pw;
    //private String siteDescribe1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example1);
        this.imageView = (ImageView) findViewById(R.id.imageView);
      this.showDescription = (Button) findViewById(R.id.showDescription);
//        this.cancelButton = (Button) findViewById(R.id.okdesc);
        Intent intent = getIntent();
        String mymessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String siteDescribe = new String();



        switch (mymessage) {
            case "1":
                siteDescribe = "This is the description of site one";
                imageView.setImageResource(R.drawable.map1);
                break;
            case "2":
                siteDescribe = "This is the description of site two";
                imageView.setImageResource(R.drawable.map1);
                break;
            case "3":
                siteDescribe = "This is the description of site three";
                imageView.setImageResource(R.drawable.map1);
                break;
            case "4":
                siteDescribe = "This is the description of site four";
                imageView.setImageResource(R.drawable.map1);
                break;
            case "5":
                siteDescribe = "This is the description of site five";
                imageView.setImageResource(R.drawable.map1);
                break;
            case "6":
                siteDescribe = "This is the description of site six";
                imageView.setImageResource(R.drawable.map1);
                break;
        }

        setOnClick(showDescription,siteDescribe);


//        this.showDescription.setOnClickListener(new View.OnClickListener(siteDescribe1) {
//            @Override
//            String siteDescribe = siteDescribe1
//            public void onClick(View v, siteDescribe) {
//                initiatePopupWindow(v, siteDescribe1);
//            }
//        });

    }
    private void setOnClick(final Button btn, final String siteDescribe){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initiatePopupWindow(view, siteDescribe);
            }
        });
    }
        private void initiatePopupWindow (View v, String siteDescribe){
            try {
                //We need to get the instance of the LayoutInflater, use the context of this activity
                LayoutInflater inflater = (LayoutInflater) ListImagesActivity99.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //Inflate the view from a predefined XML layout
                View layout = inflater.inflate(R.layout.popupdesc,
                        (ViewGroup) findViewById(R.id.popupDesc));
                // create a 300px width and 470px height PopupWindow
                pw = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
                // display the popup in the center
                pw.showAtLocation(v, Gravity.CENTER, 0, 0);

                TextView textViewDesc = (TextView) layout.findViewById(R.id.textViewDesc);
                textViewDesc.setText(siteDescribe);

                Button cancelButton = (Button) layout.findViewById(R.id.okdesc);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw.dismiss();}
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
