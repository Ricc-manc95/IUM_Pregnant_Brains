// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.google.protobuf.FloatValue;
import com.google.type.Color;
import java.util.Iterator;
import java.util.List;

public final class UiUtils
{

    public static View getDialogRootViewIfExists(Activity activity)
    {
        Object obj;
label0:
        {
label1:
            {
                if (!(activity instanceof FragmentActivity))
                {
                    break label1;
                }
                obj = ((FragmentActivity)activity).mFragments.mHost.mFragmentManager.getFragments().iterator();
                do
                {
                    if (!((Iterator) (obj)).hasNext())
                    {
                        break label1;
                    }
                    activity = (Fragment)((Iterator) (obj)).next();
                } while (!(activity instanceof DialogFragment));
                obj = ((Fragment) (activity)).mView;
                if (obj == null)
                {
                    activity = ((DialogFragment)activity).getDialog().getWindow();
                    if (activity != null)
                    {
                        return activity.getDecorView();
                    }
                }
                break label0;
            }
            return null;
        }
        return ((View) (obj));
    }

    public static int protoColorToArgbInt(Color color)
    {
        float f;
        boolean flag;
        if (color.alpha_ != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            FloatValue floatvalue;
            if (color.alpha_ == null)
            {
                floatvalue = FloatValue.DEFAULT_INSTANCE;
            } else
            {
                floatvalue = color.alpha_;
            }
            f = floatvalue.value_;
        } else
        {
            f = 1.0F;
        }
        return android.graphics.Color.argb((int)(f * 255F) & 0xff, (int)(color.red_ * 255F) & 0xff, (int)(color.green_ * 255F) & 0xff, (int)(color.blue_ * 255F) & 0xff);
    }

    public static void setTextAndToggleVisibility(TextView textview, CharSequence charsequence)
    {
        if (TextUtils.isEmpty(charsequence))
        {
            textview.setVisibility(8);
            return;
        } else
        {
            textview.setVisibility(0);
            textview.setText(charsequence);
            return;
        }
    }
}
