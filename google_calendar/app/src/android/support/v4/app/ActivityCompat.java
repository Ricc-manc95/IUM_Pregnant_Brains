// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;

public final class ActivityCompat extends ContextCompat
{

    public static void finishAffinity(Activity activity)
    {
        activity.finishAffinity();
    }

    public static PermissionCompatDelegate getPermissionCompatDelegate()
    {
        return null;
    }

    public static void requestPermissions(final Activity activity, final String permissions[], final int requestCode)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            if (activity instanceof RequestPermissionsRequestCodeValidator)
            {
                ((RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(requestCode);
            }
            activity.requestPermissions(permissions, requestCode);
        } else
        if (activity instanceof OnRequestPermissionsResultCallback)
        {
            (new Handler(Looper.getMainLooper())).post(new _cls1());
            return;
        }
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String s)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return activity.shouldShowRequestPermissionRationale(s);
        } else
        {
            return false;
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle)
    {
        activity.startActivityForResult(intent, i, bundle);
    }

    private class RequestPermissionsRequestCodeValidator
    {

        public abstract void validateRequestPermissionsRequestCode(int i);
    }


    private class OnRequestPermissionsResultCallback
    {

        public abstract void onRequestPermissionsResult(int i, String as[], int ai[]);
    }


    private class _cls1
        implements Runnable
    {

        private final Activity val$activity;
        private final String val$permissions[];
        private final int val$requestCode;

        public final void run()
        {
            int ai[] = new int[permissions.length];
            PackageManager packagemanager = activity.getPackageManager();
            String s = activity.getPackageName();
            int j = permissions.length;
            for (int i = 0; i < j; i++)
            {
                ai[i] = packagemanager.checkPermission(permissions[i], s);
            }

            ((OnRequestPermissionsResultCallback)activity).onRequestPermissionsResult(requestCode, permissions, ai);
        }

        _cls1()
        {
            permissions = as;
            activity = activity1;
            requestCode = i;
            super();
        }
    }

}
