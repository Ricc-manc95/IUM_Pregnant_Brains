// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.accounts.Account;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.Button;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.calendarlist.CalendarDescriptor;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventClient;
import com.google.android.calendar.api.event.EventDescriptor;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.attendee.AttendeeDescriptor;
import com.google.android.calendar.newapi.overflow.GuestNotificationDialogUtils;
import com.google.common.base.Pair;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.android.calendar:
//            AutoValue_DeleteEventInteractiveHelper_DeleteOptions

public class DeleteEventInteractiveHelper
{
    public static abstract class DeleteOptions
    {

        public abstract EventDescriptor descriptor();

        public abstract com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestNotification();

        public abstract int scope();

        public DeleteOptions()
        {
        }
    }


    private static final int MAX_MODIFICATION_SCOPE = Math.max(0, Math.max(1, 2));
    public static EventClient eventClient;

    public DeleteEventInteractiveHelper()
    {
    }

    public static ListenableFuture deleteEvent(Context context, EventKey eventkey)
    {
        class .Lambda._cls1
            implements AsyncFunction
        {

            private final Context arg$1;

            public final ListenableFuture apply(Object obj)
            {
                return DeleteEventInteractiveHelper.lambda$showConfirmationDialog$0$DeleteEventInteractiveHelper(arg$1, (Event)obj);
            }

            .Lambda._cls1(Context context)
            {
                arg$1 = context;
            }
        }

        class .Lambda._cls0
            implements AsyncFunction
        {

            public static final AsyncFunction $instance = new .Lambda._cls0();

            public final ListenableFuture apply(Object obj)
            {
                obj = (DeleteOptions)obj;
                return DeleteEventInteractiveHelper.eventClient.delete(((DeleteOptions) (obj)).descriptor(), ((DeleteOptions) (obj)).scope(), ((DeleteOptions) (obj)).guestNotification());
            }


            private .Lambda._cls0()
            {
            }
        }

        return AbstractTransformFuture.create(AbstractTransformFuture.create(eventClient.read(eventkey), new .Lambda._cls1(context), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN)), .Lambda._cls0..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    static final ListenableFuture lambda$showConfirmationDialog$0$DeleteEventInteractiveHelper(Context context, Event event)
        throws Exception
    {
        SettableFuture settablefuture = new SettableFuture();
        AtomicReference atomicreference = new AtomicReference();
        class .Lambda._cls7
            implements android.content.DialogInterface.OnCancelListener
        {

            private final SettableFuture arg$1;

            public final void onCancel(DialogInterface dialoginterface)
            {
                DeleteEventInteractiveHelper.lambda$showConfirmationDialog$6$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEP9AO______0(arg$1);
            }

            .Lambda._cls7(SettableFuture settablefuture)
            {
                arg$1 = settablefuture;
            }
        }

        class .Lambda._cls5
            implements android.content.DialogInterface.OnClickListener
        {

            private final SettableFuture arg$1;
            private final Context arg$2;
            private final Event arg$3;

            public final void onClick(DialogInterface dialoginterface, int k)
            {
                DeleteEventInteractiveHelper.lambda$showConfirmationDialog$4$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5O6IBR5EPIMST1F8LR6ARJK7D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(arg$1, arg$2, arg$3);
            }

            .Lambda._cls5(SettableFuture settablefuture, Context context, Event event)
            {
                arg$1 = settablefuture;
                arg$2 = context;
                arg$3 = event;
            }
        }

        class .Lambda._cls6
            implements android.content.DialogInterface.OnClickListener
        {

            private final SettableFuture arg$1;

            public final void onClick(DialogInterface dialoginterface, int k)
            {
                DeleteEventInteractiveHelper.lambda$showConfirmationDialog$5$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(arg$1);
            }

            .Lambda._cls6(SettableFuture settablefuture)
            {
                arg$1 = settablefuture;
            }
        }

        if (event.getDescriptor().isRecurringPhantom())
        {
            String s = event.getOrganizer().email;
            Object obj = event.getCalendar().account.name;
            String as[];
            Object aobj[];
            Object obj1;
            boolean flag;
            boolean flag1;
            if (!TextUtils.isEmpty(s) && s.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag1 = TextUtils.isEmpty(event.getSyncId());
            as = context.getResources().getStringArray(0x7f0b001f);
            aobj = context.getResources().getIntArray(0x7f0b0021);
            obj1 = new int[MAX_MODIFICATION_SCOPE + 1];
            for (int j = 0; j < aobj.length; j++)
            {
                obj1[aobj[j]] = j;
            }

            ArrayList arraylist;
            if (flag)
            {
                as[obj1[2]] = null;
            } else
            {
                as[obj1[1]] = null;
            }
            if (flag1)
            {
                as[obj1[0]] = null;
            }
            obj1 = new ArrayList();
            arraylist = new ArrayList();
            for (int i = 0; i < as.length; i++)
            {
                if (!TextUtils.isEmpty(as[i]))
                {
                    ((List) (obj1)).add(as[i]);
                    arraylist.add(Integer.valueOf(aobj[i]));
                }
            }

            aobj = new Pair((String[])((List) (obj1)).toArray(new String[((List) (obj1)).size()]), (Integer[])arraylist.toArray(new Integer[arraylist.size()]));
            as = (String[])((Pair) (aobj)).first;
            aobj = (Integer[])((Pair) (aobj)).second;
            obj1 = new AtomicInteger();
            class .Lambda._cls2
                implements android.content.DialogInterface.OnClickListener
            {

                private final AtomicReference arg$1;
                private final AtomicInteger arg$2;
                private final Integer arg$3[];

                public final void onClick(DialogInterface dialoginterface, int k)
                {
                    DeleteEventInteractiveHelper.lambda$showConfirmationDialog$1$DeleteEventInteractiveHelper$5166KOBMC4NNAT39DGNM6RRECDQN4SJ5DPQ2UOBKDTMMIOPF85Q6URB9CD96APJ5E9IMSOR57D66KOBMC4NNAT39DGNM6RRECDQN4SJ5DPQ2UOBKDTMMIOPF85Q6URB9CD4MST35CTIN4EQR9HL62TJ15TM62RJ75T4MST35CTIN4EQCC5N68SJFD5I2UORFDPQ6ARJK5T26IOBCDTJKIRJKCLP6COB3CKTKIAAM0(arg$1, arg$2, arg$3, k);
                }

            .Lambda._cls2(AtomicReference atomicreference, AtomicInteger atomicinteger, Integer ainteger[])
            {
                arg$1 = atomicreference;
                arg$2 = atomicinteger;
                arg$3 = ainteger;
            }
            }

            class .Lambda._cls3
                implements android.content.DialogInterface.OnClickListener
            {

                private final SettableFuture arg$1;
                private final Context arg$2;
                private final Event arg$3;
                private final AtomicInteger arg$4;

                public final void onClick(DialogInterface dialoginterface, int k)
                {
                    DeleteEventInteractiveHelper.lambda$showConfirmationDialog$2$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5O6IBR5EPIMST1F8LR6ARJK7D66KOBMC4NNAT39DGNM6RRECDQN4SJ5DPQ2UOBKDTMMIOPF85Q6URB9CD4MST35CTIN4EQCC5N68SJFD5I2UORFDPQ6ARJK5T26IOBCDTJKIRJKCLP6COB3CKTKIAAM0(arg$1, arg$2, arg$3, arg$4);
                }

            .Lambda._cls3(SettableFuture settablefuture, Context context, Event event, AtomicInteger atomicinteger)
            {
                arg$1 = settablefuture;
                arg$2 = context;
                arg$3 = event;
                arg$4 = atomicinteger;
            }
            }

            class .Lambda._cls4
                implements android.content.DialogInterface.OnClickListener
            {

                private final SettableFuture arg$1;

                public final void onClick(DialogInterface dialoginterface, int k)
                {
                    DeleteEventInteractiveHelper.lambda$showConfirmationDialog$3$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(arg$1);
                }

            .Lambda._cls4(SettableFuture settablefuture)
            {
                arg$1 = settablefuture;
            }
            }

            atomicreference.set((new android.app.AlertDialog.Builder(context)).setTitle(context.getString(0x7f13015c, new Object[] {
                event.getSummary()
            })).setSingleChoiceItems(as, -1, new .Lambda._cls2(atomicreference, ((AtomicInteger) (obj1)), ((Integer []) (aobj)))).setPositiveButton(0x104000a, new .Lambda._cls3(settablefuture, context, event, ((AtomicInteger) (obj1)))).setNegativeButton(0x1040000, new .Lambda._cls4(settablefuture)).show());
            ((AlertDialog)atomicreference.get()).getButton(-1).setEnabled(false);
        } else
        {
            atomicreference.set((new android.app.AlertDialog.Builder(context)).setMessage(0x7f13015d).setPositiveButton(0x104000a, new .Lambda._cls5(settablefuture, context, event)).setNegativeButton(0x1040000, new .Lambda._cls6(settablefuture)).show());
        }
        ((AlertDialog)atomicreference.get()).setOnCancelListener(new .Lambda._cls7(settablefuture));
        return settablefuture;
    }

    static final void lambda$showConfirmationDialog$1$DeleteEventInteractiveHelper$5166KOBMC4NNAT39DGNM6RRECDQN4SJ5DPQ2UOBKDTMMIOPF85Q6URB9CD96APJ5E9IMSOR57D66KOBMC4NNAT39DGNM6RRECDQN4SJ5DPQ2UOBKDTMMIOPF85Q6URB9CD4MST35CTIN4EQR9HL62TJ15TM62RJ75T4MST35CTIN4EQCC5N68SJFD5I2UORFDPQ6ARJK5T26IOBCDTJKIRJKCLP6COB3CKTKIAAM0(AtomicReference atomicreference, AtomicInteger atomicinteger, Integer ainteger[], int i)
    {
        ((AlertDialog)atomicreference.get()).getButton(-1).setEnabled(true);
        atomicinteger.set(ainteger[i].intValue());
    }

    static final void lambda$showConfirmationDialog$2$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5O6IBR5EPIMST1F8LR6ARJK7D66KOBMC4NNAT39DGNM6RRECDQN4SJ5DPQ2UOBKDTMMIOPF85Q6URB9CD4MST35CTIN4EQCC5N68SJFD5I2UORFDPQ6ARJK5T26IOBCDTJKIRJKCLP6COB3CKTKIAAM0(SettableFuture settablefuture, Context context, Event event, AtomicInteger atomicinteger)
    {
        settablefuture.setFuture(showGuestNotificationDialog(context, event, atomicinteger.get()));
    }

    static final void lambda$showConfirmationDialog$3$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(SettableFuture settablefuture)
    {
        settablefuture.cancel(false);
    }

    static final void lambda$showConfirmationDialog$4$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5O6IBR5EPIMST1F8LR6ARJK7D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(SettableFuture settablefuture, Context context, Event event)
    {
        settablefuture.setFuture(showGuestNotificationDialog(context, event, 0));
    }

    static final void lambda$showConfirmationDialog$5$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(SettableFuture settablefuture)
    {
        settablefuture.cancel(false);
    }

    static final void lambda$showConfirmationDialog$6$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEP9AO______0(SettableFuture settablefuture)
    {
        settablefuture.cancel(false);
    }

    static final DeleteOptions lambda$showGuestNotificationDialog$7$DeleteEventInteractiveHelper(Event event, int i, com.google.android.calendar.api.event.GooglePrivateData.GuestNotification guestnotification)
    {
        return new AutoValue_DeleteEventInteractiveHelper_DeleteOptions(event.getDescriptor(), i, guestnotification);
    }

    public static ListenableFuture showConfirmationDialog(Context context, EventKey eventkey)
    {
        return AbstractTransformFuture.create(eventClient.read(eventkey), new .Lambda._cls1(context), new com.google.android.apps.calendar.util.concurrent.CalendarExecutor..Lambda._cls0(CalendarExecutor.MAIN));
    }

    private static ListenableFuture showGuestNotificationDialog(Context context, Event event, int i)
    {
        FeatureConfig featureconfig = Features.instance;
        if (featureconfig == null)
        {
            throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
        }
        ((FeatureConfig)featureconfig).notify_guests();
        if (!GuestNotificationDialogUtils.shouldShowDialogForEventDeletion(event))
        {
            context = com.google.android.calendar.api.event.GooglePrivateData.GuestNotification.UNDECIDED;
            class .Lambda._cls8
                implements Function
            {

                private final Event arg$1;
                private final int arg$2;

                public final Object apply(Object obj)
                {
                    return DeleteEventInteractiveHelper.lambda$showGuestNotificationDialog$7$DeleteEventInteractiveHelper(arg$1, arg$2, (com.google.android.calendar.api.event.GooglePrivateData.GuestNotification)obj);
                }

            .Lambda._cls8(Event event, int i)
            {
                arg$1 = event;
                arg$2 = i;
            }
            }

            if (context == null)
            {
                context = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                context = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(context);
            }
        } else
        {
            context = GuestNotificationDialogUtils.showNotificationDialogAsync(event, context, GuestNotificationDialogUtils.getGuestNotificationDialogStringForDeletion(event, context), "delete_swipe");
        }
        return AbstractTransformFuture.create(context, new .Lambda._cls8(event, i), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/DeleteEventInteractiveHelper);
        eventClient = CalendarApi.Events;
    }
}
