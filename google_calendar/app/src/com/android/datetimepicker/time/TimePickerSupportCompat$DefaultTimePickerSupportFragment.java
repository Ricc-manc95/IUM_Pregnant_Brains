// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.android.datetimepicker.SupportDialogFragmentWithListener;

public final class  extends SupportDialogFragmentWithListener
{

    public android.app.gment.createDialog timeListener;

    public final Dialog onCreateDialog(Bundle bundle)
    {
        bundle = timeListener;
        Object obj = super.mTarget;
        if (obj instanceof timeListener)
        {
            bundle = new timeListener((timeListener)obj);
        }
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        return timeListener(((android.content.Context) (obj)), getArguments(), bundle);
    }

    public ()
    {
    }
}
