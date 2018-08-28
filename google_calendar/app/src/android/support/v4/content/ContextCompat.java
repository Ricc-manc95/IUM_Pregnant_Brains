// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import java.io.File;

public class ContextCompat
{

    private static final Object sLock = new Object();

    public static int checkSelfPermission(Context context, String s)
    {
        return context.checkPermission(s, Process.myPid(), Process.myUid());
    }

    public static int getColor(Context context, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return context.getColor(i);
        } else
        {
            return context.getResources().getColor(i);
        }
    }

    public static ColorStateList getColorStateList(Context context, int i)
    {
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            return context.getColorStateList(i);
        } else
        {
            return context.getResources().getColorStateList(i);
        }
    }

    public static Drawable getDrawable(Context context, int i)
    {
        return context.getDrawable(i);
    }

    public static File[] getExternalCacheDirs(Context context)
    {
        return context.getExternalCacheDirs();
    }

    public static File[] getExternalFilesDirs(Context context, String s)
    {
        return context.getExternalFilesDirs(null);
    }

    public static boolean startActivities(Context context, Intent aintent[], Bundle bundle)
    {
        context.startActivities(aintent, bundle);
        return true;
    }

    public static void startActivity(Context context, Intent intent, Bundle bundle)
    {
        context.startActivity(intent, bundle);
    }

}
