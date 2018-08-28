// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.zzzk;

public class GoogleApiActivity extends Activity
    implements android.content.DialogInterface.OnCancelListener
{

    private int zzaIX;

    public GoogleApiActivity()
    {
        zzaIX = 0;
    }

    public static Intent zzb(Context context, PendingIntent pendingintent, int i, boolean flag)
    {
        context = new Intent(context, com/google/android/gms/common/api/GoogleApiActivity);
        context.putExtra("pending_intent", pendingintent);
        context.putExtra("failing_client_id", i);
        context.putExtra("notify_manager", flag);
        return context;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (i != 1) goto _L2; else goto _L1
_L1:
        boolean flag;
        flag = getIntent().getBooleanExtra("notify_manager", true);
        zzaIX = 0;
        setResult(j);
        if (!flag) goto _L4; else goto _L3
_L3:
        intent = zzzk.zzaz(this);
        j;
        JVM INSTR tableswitch -1 0: default 68
    //                   -1 129
    //                   0 73;
           goto _L4 _L5 _L6
_L4:
        finish();
        return;
_L6:
        ConnectionResult connectionresult = new ConnectionResult(13, null);
        i = getIntent().getIntExtra("failing_client_id", -1);
        if (!intent.zzc(connectionresult, i))
        {
            ((zzzk) (intent)).mHandler.sendMessage(((zzzk) (intent)).mHandler.obtainMessage(4, i, 0, connectionresult));
        }
        continue; /* Loop/switch isn't completed */
_L5:
        ((zzzk) (intent)).mHandler.sendMessage(((zzzk) (intent)).mHandler.obtainMessage(2));
        continue; /* Loop/switch isn't completed */
_L2:
        if (i == 2)
        {
            zzaIX = 0;
            setResult(j);
        }
        if (true) goto _L4; else goto _L7
_L7:
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        zzaIX = 0;
        setResult(0);
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        Object obj;
label0:
        {
            super.onCreate(bundle);
            if (bundle != null)
            {
                zzaIX = bundle.getInt("resolution");
            }
            if (zzaIX != 1)
            {
                obj = getIntent().getExtras();
                if (obj != null)
                {
                    break label0;
                }
                Log.e("GoogleApiActivity", "Activity started without extras");
                finish();
            }
            return;
        }
        bundle = (PendingIntent)((Bundle) (obj)).get("pending_intent");
        obj = (Integer)((Bundle) (obj)).get("error_code");
        if (bundle == null && obj == null)
        {
            Log.e("GoogleApiActivity", "Activity started without resolution");
            finish();
            return;
        }
        if (bundle != null)
        {
            try
            {
                startIntentSenderForResult(bundle.getIntentSender(), 1, null, 0, 0, 0);
                zzaIX = 1;
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Bundle bundle)
            {
                Log.e("GoogleApiActivity", "Failed to launch pendingIntent", bundle);
            }
            finish();
            return;
        } else
        {
            GoogleApiAvailability.zzaIm.showErrorDialogFragment(this, ((Integer) (obj)).intValue(), 2, this);
            zzaIX = 1;
            return;
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        bundle.putInt("resolution", zzaIX);
        super.onSaveInstanceState(bundle);
    }
}
