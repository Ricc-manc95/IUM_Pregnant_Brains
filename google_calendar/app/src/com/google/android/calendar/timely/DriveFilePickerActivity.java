// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.common.activity.CalendarSupportActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import java.util.ArrayList;
import java.util.Set;

public class DriveFilePickerActivity extends CalendarSupportActivity
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/DriveFilePickerActivity);
    private String accountName;
    private GoogleApiClient googleApiClient;

    public DriveFilePickerActivity()
    {
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        switch (i)
        {
        default:
            super.onActivityResult(i, j, intent);
            return;

        case 1: // '\001'
            break;
        }
        if (j == -1)
        {
            intent = (DriveId)intent.getParcelableExtra("response_drive_id");
            Intent intent1 = getIntent();
            intent1.putExtra("driveIdExtra", intent.encodeToString());
            setResult(-1, intent1);
        }
        finish();
    }

    public final void onConnected(Bundle bundle)
    {
        bundle = Drive.DriveApi.newOpenFileActivityBuilder().build(googleApiClient);
        try
        {
            startIntentSenderForResult(bundle, 1, null, 0, 0, 0);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Bundle bundle)
        {
            Log.w(TAG, "Unable to send intent", bundle);
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionresult)
    {
        boolean flag2;
        flag2 = true;
        String s = String.valueOf(connectionresult.toString());
        boolean flag;
        if (s.length() != 0)
        {
            "GoogleApiClient connection failed: ".concat(s);
        } else
        {
            new String("GoogleApiClient connection failed: ");
        }
        if (connectionresult.zzaEP != 0 && connectionresult.mPendingIntent != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        GooglePlayServicesUtil.getErrorDialog(connectionresult.zzaEP, this, 0).show();
_L4:
        return;
_L2:
        boolean flag1;
        if (connectionresult.zzaEP != 0 && connectionresult.mPendingIntent != null)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            try
            {
                startIntentSenderForResult(connectionresult.mPendingIntent.getIntentSender(), 1, null, 0, 0, 0);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (ConnectionResult connectionresult)
            {
                Log.e(TAG, "Exception while starting resolution activity", connectionresult);
            }
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onConnectionSuspended(int i)
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        accountName = getIntent().getStringExtra("accountNameExtra");
    }

    protected void onPause()
    {
        if (googleApiClient != null)
        {
            googleApiClient.disconnect();
        }
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
        if (googleApiClient == null)
        {
            com.google.android.gms.common.api.GoogleApiClient.Builder builder = (new com.google.android.gms.common.api.GoogleApiClient.Builder(this)).addApi(Drive.API);
            com.google.android.gms.common.api.Scope scope = Drive.SCOPE_FULL;
            if (scope == null)
            {
                throw new NullPointerException(String.valueOf("Scope must not be null"));
            }
            builder.zzaIZ.add(scope);
            if (this == null)
            {
                throw new NullPointerException(String.valueOf("Listener must not be null"));
            }
            builder.zzaJl.add(this);
            if (this == null)
            {
                throw new NullPointerException(String.valueOf("Listener must not be null"));
            }
            builder.zzaJm.add(this);
            if (accountName != null)
            {
                Object obj = accountName;
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = new Account(((String) (obj)), "com.google");
                }
                builder.zzafe = ((Account) (obj));
            }
            googleApiClient = builder.build();
        }
        googleApiClient.connect();
    }

}
