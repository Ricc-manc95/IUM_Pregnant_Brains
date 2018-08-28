// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalImportFragment, ICalDeleteFragment, ICalEventOperation

final class arg._cls2
    implements android.content.er
{

    private final ICalDeleteFragment arg$1;
    private final ICalEventOperation arg$2;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        Object obj = arg$1;
        Object obj1 = arg$2;
        dialoginterface = (ICalImportFragment)((Fragment) (obj)).mFragmentManager.findFragmentByTag(ICalImportFragment.TAG);
        if (dialoginterface == null)
        {
            LogUtils.e(ICalDeleteFragment.TAG, "Can't find ICalImportFragment (tag: %s)", new Object[] {
                ICalImportFragment.TAG
            });
            if (((Fragment) (obj)).mHost == null)
            {
                dialoginterface = null;
            } else
            {
                dialoginterface = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
            }
            dialoginterface.finish();
            return;
        }
        obj = ((ICalEventOperation) (obj1)).events();
        obj1 = .instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        obj = new com.google.common.util.concurrent.init>(ImmutableList.copyOf(new com.google.common.collect.Future(((Iterable) (obj)), ((com.google.common.base.Function) (obj1)))), true);
        dialoginterface = new <init>(dialoginterface);
        obj1 = new com.google.android.apps.calendar.util.concurrent.nit>(CalendarExecutor.MAIN);
        if (dialoginterface == null)
        {
            throw new NullPointerException();
        } else
        {
            ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.t>(((java.util.concurrent.Future) (obj)), dialoginterface), ((java.util.concurrent.Executor) (obj1)));
            return;
        }
    }

    tor(ICalDeleteFragment icaldeletefragment, ICalEventOperation icaleventoperation)
    {
        arg$1 = icaldeletefragment;
        arg$2 = icaleventoperation;
    }
}
