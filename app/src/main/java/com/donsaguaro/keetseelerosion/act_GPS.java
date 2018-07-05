package com.donsaguaro.keetseelerosion;



import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Path;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.donsaguaro.keetseelerosion.R;

import org.osmdroid.api.IGeoPoint;
//import org.osmdroid.config.Configuration;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.views.overlay.Polyline;
//import org.osmdroid.bonuspack.overlays.;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.api.IMapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import org.osmdroid.views.overlay.PathOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.IMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


import java.io.File;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class act_GPS  extends Activity {
    private MapView myMapView = null;

    private ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myGesture;
    private Criteria criteria;
    private PopupWindow pw;
    private View myView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //final View v = getA
        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        final Context ctx = getApplicationContext();

        //filename= "mytiles_cache.xip"
        //File outFile = new File(getExternalFilesDir(null), filename);

        org.osmdroid.config.Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        org.osmdroid.config.Configuration.getInstance().setOsmdroidTileCache(getExternalFilesDir(null));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's tile servers will get you banned based on this string
        //inflate and create the map
        setContentView(R.layout.gps_view);
        System.out.println("Made it here");

        myView= (View) findViewById(R.id.map);
        myMapView = (MapView) findViewById(R.id.map);

        //myMapView.setTileSource(TileSourceFactory.MAPNIK);


        myMapView.setTileSource(new XYTileSource(
                "USGS National Map Satellite",
                0,
                15,
                256,
                ".jpg",
                new String[]{}
        ));
        myMapView.setUseDataConnection(false);




        myMapView.setBuiltInZoomControls(true);
        myMapView.setMultiTouchControls(true);

        IMapController mapController = myMapView.getController();
        double myZoom = 14;
        mapController.setZoom(myZoom);

        LocationManager myLocationManager = (LocationManager) getSystemService(ctx.LOCATION_SERVICE);
        criteria = new Criteria();
        String provider = myLocationManager.getBestProvider(criteria, false);
        String myPerm = android.Manifest.permission.ACCESS_FINE_LOCATION;
        int res = ctx.checkCallingOrSelfPermission(myPerm);
        //Location myLoc2 = new Location("NONE");
        //Location myLoc2 = myLocationManager.getLastKnownLocation(provider);
        if (res == PackageManager.PERMISSION_GRANTED) {
            System.out.println("act_GPS GRANTED!!!!");
        }
        MyLocationNewOverlay mLocationOverlay=new MyLocationNewOverlay(myMapView);
        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(ctx),myMapView);
        //mLocationOverlay.enableMyLocation();
        //mMapView.getOverlays().add(this.mLocationOverlay);
        //myLoc4= IMyLocationProvider.
        //GpsMyLocationProvider gotmyloc = new GpsMyLocationProvider(ctx);
       // System.out.println("my loc lat is here");

        //System.out.println(gotmyloc.getLastKnownLocation().getLatitude());
        System.out.println("my lat2 is here");
//        System.out.println(myLoc2.getLatitude());


       //MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay( myMapView);
        System.out.println("test null");

        System.out.println(mLocationOverlay == null);
        //Location myLat = mLocationOverlay.getMyLocation();




        mLocationOverlay.setDrawAccuracyEnabled(true);
        System.out.println("test null2");
        System.out.println(mLocationOverlay == null);
        GeoPoint myGeoPoint = mLocationOverlay.getMyLocation();
        System.out.println(myGeoPoint == null);
//        Double myLong =myGeoPoint.getLongitude();
//        Double myLat =myGeoPoint.getLatitude();
//        String latString = myLat.toString();
//        System.out.println("Here is lat string");
//
//        System.out.println(latString);


        //GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);

        //GeoPoint p1 = new GeoPoint(-110.501176, 36.691458);

        GeoPoint p1 = new GeoPoint(36.691458, -110.508176);
        GeoPoint p2 = new GeoPoint(36.704659, -110.511069);
        GeoPoint p3 = new GeoPoint(36.734221, -110.507614);
        GeoPoint p4 = new GeoPoint(36.741991, -110.504068);
        GeoPoint p5 = new GeoPoint(36.747399, -110.501529);
        GeoPoint p6 = new GeoPoint(36.751914, -110.496832);
        GeoPoint p7 = new GeoPoint(36.761, -110.497);
        //mapController.setCenter(new GeoPoint(myLoc2.getLatitude(), myLoc2.getLongitude()));
        //mapController.setCenter(p7);
        mLocationOverlay.enableMyLocation();
        mLocationOverlay.enableFollowLocation();




        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem overLayItem1 = new OverlayItem("Location 1", "p1desc", p1);
        overLayItem1.setMarker(getResources().getDrawable(R.drawable.number_1));
        items.add(overLayItem1);

        OverlayItem overLayItem2 = new OverlayItem("Location 2", "p2desc", p2);
        overLayItem2.setMarker(getResources().getDrawable(R.drawable.number_2));
        items.add(overLayItem2);

        OverlayItem overLayItem3 = new OverlayItem("Location 3", "p3desc", p3);
        overLayItem3.setMarker(getResources().getDrawable(R.drawable.number_3));
        items.add(overLayItem3);

        OverlayItem overLayItem4 = new OverlayItem("Location 4", "p4desc", p4);
        overLayItem4.setMarker(getResources().getDrawable(R.drawable.number_4));
        items.add(overLayItem4);

        OverlayItem overLayItem5 = new OverlayItem("Location 5", "p5desc", p5);
        overLayItem5.setMarker(getResources().getDrawable(R.drawable.number_5));
        items.add(overLayItem5);

        OverlayItem overLayItem6 = new OverlayItem("Location 6", "p6desc", p6);
        overLayItem6.setMarker(getResources().getDrawable(R.drawable.number_6));
        items.add(overLayItem6);

        OverlayItem overLayItem7 = new OverlayItem("Keet Seel", "KS", p7);
        overLayItem7.setMarker(getResources().getDrawable(R.drawable.star));
        items.add(overLayItem7);



        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(ctx, items,
                this.myGesture = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {


                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        String myLocNum = item.getTitle();
                        System.out.println("locnum is ");

                        System.out.println(myLocNum);

                        //Toast.makeText(ctx, item.getTitle(), Toast.LENGTH_LONG).show();
                        initiatePopupWindow(myView,myLocNum);
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        return false;
                    }
                }
        );

//
        Polyline polyline = new Polyline(myMapView);
        List<GeoPoint> geoPoints = new ArrayList<>();

        geoPoints.add (new GeoPoint(36.68306417690	,-110.54176551300));
        geoPoints.add (new GeoPoint(36.68599588650	,-110.53945849600));
        //geoPoints.add (new GeoPoint(36.68695424380	,-110.53072803500));
        geoPoints.add (new GeoPoint(36.68710328220	,-110.53169740100));
        geoPoints.add (new GeoPoint(36.68710328220	,-110.53169740100));
        //geoPoints.add (new GeoPoint(36.68791743700	,-110.51202025600));
        geoPoints.add (new GeoPoint(36.6872	,-110.51202025600));
        //geoPoints.add (new GeoPoint(36.68809932060	,-110.52066849900));
        //geoPoints.add (new GeoPoint(36.68831293010	,-110.51854228700));
        //geoPoints.add (new GeoPoint(36.68951655150	,-110.52251846700));
        //geoPoints.add (new GeoPoint(36.68951655150	,-110.52251846700));
        geoPoints.add (new GeoPoint(36.69453200000	,-110.50699525600));
        geoPoints.add (new GeoPoint(36.69469615670	,-110.50693867300));
        //geoPoints.add (new GeoPoint(36.70153279050	,-110.50886237800));
        geoPoints.add (new GeoPoint(36.71790613940	,-110.51312697600));
        geoPoints.add (new GeoPoint(36.72385584760	,-110.51191824100));
        geoPoints.add (new GeoPoint(36.72910989210	,-110.50965216500));
        geoPoints.add (new GeoPoint(36.73518732220	,-110.50775356200));
        geoPoints.add (new GeoPoint(36.74309466880	,-110.50412883700));
        geoPoints.add (new GeoPoint(36.74961879230	,-110.50013086700));
        geoPoints.add (new GeoPoint(36.75546232120	,-110.49518649700));
        geoPoints.add (new GeoPoint(36.75971464940	,-110.49542753500));





        //geoPoints.add (new GeoPoint(36.691458, -110.508176));
        //geoPoints.add (new GeoPoint(36.704659, -110.511069));
        polyline.setPoints(geoPoints);
        polyline.setColor(Color.rgb(200,20,20));
        System.out.println("Color here");

        System.out.println(polyline.getColor());
//        polyline.setOnClickListener(new Polyline.OnClickListener() {
//            @Override
//            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos) {
//                Toast.makeText(mapView.getContext(), "polyline with " + polyline.getPoints().size() + "pts was tapped", Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });

        //myMapView.getOverlays().clear();
        myMapView.getOverlayManager().add(polyline);
        myMapView.getOverlays().add(mOverlay);
        myMapView.getOverlays().add(mLocationOverlay);
    }

    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        //myMapView.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        //myMapView.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }


    private void initiatePopupWindow(View v, String myLocNum) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) act_GPS.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.popupip2,
                    (ViewGroup) findViewById(R.id.popupIP2));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            // display the popup in the center
            pw.showAtLocation(v, Gravity.CENTER, 0, 0);
            ImageView imageGPS = (ImageView) layout.findViewById(R.id.imageGPS);
            TextView textViewGPS = (TextView) layout.findViewById(R.id.textViewGPS);


            switch (myLocNum) {
                case "Location 1":
                    textViewGPS.setText( R.string.loc1Desc);
                    imageGPS.setImageResource(R.drawable.loc1);
                    break;
                case "Location 2":
                    textViewGPS.setText( R.string.loc2Desc);
                    imageGPS.setImageResource(R.drawable.loc2);
                    break;
                case "Location 3":
                    textViewGPS.setText( R.string.loc3Desc);
                    imageGPS.setImageResource(R.drawable.loc3);
                    break;
                case "Location 4":
                    textViewGPS.setText( R.string.loc4Desc);
                    imageGPS.setImageResource(R.drawable.loc4);
                    break;
                case "Location 5":
                    textViewGPS.setText( R.string.loc5Desc);
                    imageGPS.setImageResource(R.drawable.loc5);
                    break;
                case "Location 6":
                    textViewGPS.setText( R.string.loc6Desc);
                    imageGPS.setImageResource(R.drawable.loc6);
                    break;
                case "Keet Seel":
                    textViewGPS.setText( R.string.locksDesc);
                    imageGPS.setImageResource(R.drawable.star);
                    break;
            }



            Button cancelButton = (Button) layout.findViewById(R.id.okIPGPS);
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


}