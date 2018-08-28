// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.CpEventDescriptor;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventModifications;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventOperation

public class ICalDeleteFragment extends DialogFragment
{

    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/ical/ICalDeleteFragment);

    public ICalDeleteFragment()
    {
    }

    static final ListenableFuture lambda$onDeleteEventClicked$2$ICalDeleteFragment(EventModifications eventmodifications)
    {
        byte byte0 = 0;
        Object obj1 = eventmodifications.getDescriptor();
        Object obj = obj1;
        if (((EventDescriptor) (obj1)).isRecurringPhantom())
        {
            obj = obj1;
            if (!eventmodifications.isRecurring())
            {
                if (obj1 instanceof CpEventDescriptor)
                {
                    obj = ((CpEventDescriptor)obj1).derivePhantomDescriptor(eventmodifications.getStartMillis());
                } else
                {
                    LogUtils.wtf(TAG, "Unexpected event descriptor.", new Object[0]);
                    obj = obj1;
                }
            }
        }
        obj1 = CalendarApi.Events;
        if (eventmodifications.isRecurring())
        {
            byte0 = 2;
        }
        return ((EventClient) (obj1)).delete(((EventDescriptor) (obj)), byte0, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED);
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        ICalEventOperation icaleventoperation = (ICalEventOperation)getArguments().getParcelable("operation");
        String s = ((EventModifications)icaleventoperation.events().get(0)).getSummary();
        class .Lambda._cls0
            implements android.content.DialogInterface.OnClickListener
        {

            private final ICalDeleteFragment arg$1;
            private final ICalEventOperation arg$2;

            public final void onClick(final DialogInterface importFragment, int i)
            {
                Object obj = arg$1;
                Object obj1 = arg$2;
                importFragment = (ICalImportFragment)((Fragment) (obj)).mFragmentManager.findFragmentByTag(ICalImportFragment.TAG);
                if (importFragment == null)
                {
                    LogUtils.e(ICalDeleteFragment.TAG, "Can't find ICalImportFragment (tag: %s)", new Object[] {
                        ICalImportFragment.TAG
                    });
                    if (((Fragment) (obj)).mHost == null)
                    {
                        importFragment = null;
                    } else
                    {
                        importFragment = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                    }
                    importFragment.finish();
                    return;
                }
                obj = ((ICalEventOperation) (obj1)).events();
                class .Lambda._cls2
                    implements Function
                {

                    public static final Function $instance = new .Lambda._cls2();

                    public final Object apply(Object obj2)
                    {
                        return ICalDeleteFragment.lambda$onDeleteEventClicked$2$ICalDeleteFragment((EventModifications)obj2);
                    }


                        private .Lambda._cls2()
                        {
                        }
                }

                obj1 = .Lambda._cls2..instance;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                if (obj1 == null)
                {
                    throw new NullPointerException();
                }
                obj = new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(new com.google.common.collect.Iterables._cls5(((Iterable) (obj)), ((Function) (obj1)))), true);
                importFragment = new _cls1();
                obj1 = new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN);
                if (importFragment == null)
                {
                    throw new NullPointerException();
                } else
                {
                    ((ListenableFuture) (obj)).addListener(new com.google.common.util.concurrent.Futures.CallbackListener(((java.util.concurrent.Future) (obj)), importFragment), ((java.util.concurrent.Executor) (obj1)));
                    return;
                }
            }

            .Lambda._cls0(ICalEventOperation icaleventoperation)
            {
                arg$1 = ICalDeleteFragment.this;
                arg$2 = icaleventoperation;
            }

            private class _cls1
                implements FutureCallback
            {

                private final ICalImportFragment val$importFragment;

                public final void onFailure(Throwable throwable)
                {
                    throwable = importFragment;
                    if (((Fragment) (throwable)).mFragmentManager.findFragmentByTag(ICalEventListFragment.TAG) != null)
                    {
                        throwable.scheduleRefresh();
                        return;
                    }
                    if (((Fragment) (throwable)).mHost == null)
                    {
                        throwable = null;
                    } else
                    {
                        throwable = (FragmentActivity)((Fragment) (throwable)).mHost.mActivity;
                    }
                    throwable.finish();
                }

                public final void onSuccess(Object obj)
                {
                    obj = importFragment;
                    if (((Fragment) (obj)).mFragmentManager.findFragmentByTag(ICalEventListFragment.TAG) != null)
                    {
                        ((ICalImportFragment) (obj)).scheduleRefresh();
                        return;
                    }
                    if (((Fragment) (obj)).mHost == null)
                    {
                        obj = null;
                    } else
                    {
                        obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                    }
                    ((FragmentActivity) (obj)).finish();
                }

                _cls1()
                {
                    importFragment = icalimportfragment;
                    super();
                }
            }

        }

        class .Lambda._cls1
            implements android.content.DialogInterface.OnClickListener
        {

            private final ICalDeleteFragment arg$1;

            public final void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface = arg$1;
                if (((Fragment) (dialoginterface)).mHost == null)
                {
                    dialoginterface = null;
                } else
                {
                    dialoginterface = (FragmentActivity)((Fragment) (dialoginterface)).mHost.mActivity;
                }
                dialoginterface.finish();
            }

            .Lambda._cls1()
            {
                arg$1 = ICalDeleteFragment.this;
            }
        }

        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return (new android.app.AlertDialog.Builder(bundle)).setMessage(requireContext().getResources().getString(0x7f1302f7, new Object[] {
            s
        })).setPositiveButton(0x104000a, new .Lambda._cls0(icaleventoperation)).setNegativeButton(0x1040000, new .Lambda._cls1()).create();
    }

}
