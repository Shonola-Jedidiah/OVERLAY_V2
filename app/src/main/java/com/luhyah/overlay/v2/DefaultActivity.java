package com.luhyah.overlay.v2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.zip.Inflater;

public class DefaultActivity extends AppCompatActivity {

    private androidx.cardview.widget.CardView cardViewPermission;
    private ImageView infoIcon;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_default);

        initialization();

        cardViewPermission.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {grantPermission ();}
        });

        if(isPermissionGranted ()){cardViewPermission.setVisibility (View.GONE);}
        else{cardViewPermission.setVisibility (View.VISIBLE);}

        infoIcon.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                alert.setTitle ("About Overlay")
                        .setMessage ("Nunc malesuada dignissim aliquam. Maecenas lectus odio, elementum ac dignissim id, sodales quis quam")
                        .setCancelable (true)
                        .setPositiveButton ("Ok" , new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialogInterface , int i) {dialogInterface.cancel ();}
                        }).show ();
            }
        });
    }

    private void initialization(){
        cardViewPermission = findViewById (R.id.card_permission);
        infoIcon = findViewById (R.id.info_icon);
        alert = new AlertDialog.Builder (this);
    }

    @Override
    protected void onResume() {
        super.onResume ();
        if(isPermissionGranted ()){cardViewPermission.setVisibility (View.GONE);}
        else{cardViewPermission.setVisibility (View.VISIBLE);}
    }

    private void grantPermission(){
        Intent intent = new Intent (Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse ("package:" + getPackageName ()));
        startActivityForResult (intent,1);
    }
    private boolean isPermissionGranted(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return Settings.canDrawOverlays (this);
        }
        return true;
    }

}