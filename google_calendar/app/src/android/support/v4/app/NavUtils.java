// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public final class NavUtils
{

    public static Intent getParentActivityIntent(Activity activity)
    {
        Object obj = activity.getParentActivityIntent();
        if (obj != null)
        {
            return ((Intent) (obj));
        }
        obj = getParentActivityName(activity);
        if (obj == null)
        {
            return null;
        }
        ComponentName componentname = new ComponentName(activity, ((String) (obj)));
        try
        {
            if (getParentActivityName(activity, componentname) == null)
            {
                return Intent.makeMainActivity(componentname);
            }
            activity = (new Intent()).setComponent(componentname);
        }
        // Misplaced declaration of an exception variable
        catch (Activity activity)
        {
            Log.e("NavUtils", (new StringBuilder("getParentActivityIntent: bad parentActivityName '")).append(((String) (obj))).append("' in manifest").toString());
            return null;
        }
        return activity;
    }

    public static Intent getParentActivityIntent(Context context, ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        String s = getParentActivityName(context, componentname);
        if (s == null)
        {
            return null;
        }
        componentname = new ComponentName(componentname.getPackageName(), s);
        if (getParentActivityName(context, componentname) == null)
        {
            return Intent.makeMainActivity(componentname);
        } else
        {
            return (new Intent()).setComponent(componentname);
        }
    }

    public static String getParentActivityName(Activity activity)
    {
        try
        {
            activity = getParentActivityName(((Context) (activity)), activity.getComponentName());
        }
        // Misplaced declaration of an exception variable
        catch (Activity activity)
        {
            throw new IllegalArgumentException(activity);
        }
        return activity;
    }

    private static String getParentActivityName(Context context, ComponentName componentname)
        throws android.content.pm.PackageManager.NameNotFoundException
    {
        Object obj = context.getPackageManager().getActivityInfo(componentname, 128);
        componentname = ((ActivityInfo) (obj)).parentActivityName;
        if (componentname == null)
        {
            if (((ActivityInfo) (obj)).metaData == null)
            {
                return null;
            }
            obj = ((ActivityInfo) (obj)).metaData.getString("android.support.PARENT_ACTIVITY");
            if (obj == null)
            {
                return null;
            }
            componentname = ((ComponentName) (obj));
            if (((String) (obj)).charAt(0) == '.')
            {
                return (new StringBuilder()).append(context.getPackageName()).append(((String) (obj))).toString();
            }
        }
        return componentname;
    }
}
