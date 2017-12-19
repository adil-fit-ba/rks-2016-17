package ba.fit.app.e_univerzitet.area_student.primjer3_alert_dialoga;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import ba.fit.app.e_univerzitet.R;
import ba.fit.app.e_univerzitet.area_student.primjer1_vise_activity.Home1Activity;

public class Home3Activity extends Home1Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Pitanje? ");
        adb.setMessage("Da li želite downloadovati PDF uputstvo (5 MB)?\n");
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("Da, preuzmi", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Download u toku.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                installNewApp();
            }

        });

        adb.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
               // moveTaskToBack(true);
            }
        });
        adb.setCancelable(false);
        adb.show();

    }

    private void installNewApp()
    {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://earth.androidapksfree.com/AndroidBucket1/com.skype.raider-5.2.0.62296-84079448-Android-4.0.apk"));
        request.setDescription("Otvorite ovaj fajl da biste instalirali");
        request.setTitle("Nesto");
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "uputsvo.pdf");

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        moveTaskToBack(true);
    }


}
