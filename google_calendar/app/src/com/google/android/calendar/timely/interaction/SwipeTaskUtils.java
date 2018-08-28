// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.interaction;

import android.content.Context;
import android.content.res.Resources;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.calendar.task.TaskDataFactory;
import com.google.android.calendar.task.TaskUtils;
import com.google.android.calendar.timely.interaction.swipe.SwipeUtils;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Set;

public final class SwipeTaskUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/interaction/swipe/SwipeUtils);

    static final void lambda$showMarkTaskBundleDoneDialog$0$SwipeTaskUtils$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(SettableFuture settablefuture)
    {
        settablefuture.cancel(false);
    }

    static final void lambda$showMarkTaskBundleDoneDialog$1$SwipeTaskUtils$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(SettableFuture settablefuture)
    {
        settablefuture.set(new Object());
    }

    static final void lambda$showMarkTaskBundleDoneDialog$2$SwipeTaskUtils$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEP9AO______0(SettableFuture settablefuture)
    {
        settablefuture.cancel(false);
    }

    static final void lambda$updateTaskDoneAsync$3$SwipeTaskUtils(Context context, Set set, Optional optional)
    {
        TaskUtils.showReminderToast(context, set.size(), true, (Long)optional.orNull());
    }

    public static ListenableFuture showMarkTaskBundleDoneDialog(Context context, int i)
    {
        SettableFuture settablefuture = new SettableFuture();
        String s = context.getResources().getQuantityString(0x7f120027, i, new Object[] {
            Integer.valueOf(i)
        });
        class .Lambda._cls0
            implements android.content.DialogInterface.OnClickListener
        {

            private final SettableFuture arg$1;

            public final void onClick(DialogInterface dialoginterface, int j)
            {
                SwipeTaskUtils.lambda$showMarkTaskBundleDoneDialog$0$SwipeTaskUtils$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(arg$1);
            }

            .Lambda._cls0(SettableFuture settablefuture)
            {
                arg$1 = settablefuture;
            }
        }

        class .Lambda._cls1
            implements android.content.DialogInterface.OnClickListener
        {

            private final SettableFuture arg$1;

            public final void onClick(DialogInterface dialoginterface, int j)
            {
                SwipeTaskUtils.lambda$showMarkTaskBundleDoneDialog$1$SwipeTaskUtils$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEQ955B0____0(arg$1);
            }

            .Lambda._cls1(SettableFuture settablefuture)
            {
                arg$1 = settablefuture;
            }
        }

        class .Lambda._cls2
            implements android.content.DialogInterface.OnCancelListener
        {

            private final SettableFuture arg$1;

            public final void onCancel(DialogInterface dialoginterface)
            {
                SwipeTaskUtils.lambda$showMarkTaskBundleDoneDialog$2$SwipeTaskUtils$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UH39C5M6UPQ9DPQ6ASJ6C5HMAEP9AO______0(arg$1);
            }

            .Lambda._cls2(SettableFuture settablefuture)
            {
                arg$1 = settablefuture;
            }
        }

        (new android.app.AlertDialog.Builder(context)).setMessage(s).setNegativeButton(0x1040000, new .Lambda._cls0(settablefuture)).setPositiveButton(0x104000a, new .Lambda._cls1(settablefuture)).setOnCancelListener(new .Lambda._cls2(settablefuture)).show();
        return settablefuture;
    }

    public static ListenableFuture updateTaskDoneAsync(Context context, String s, Set set)
    {
        if (TaskDataFactory.instance == null)
        {
            TaskDataFactory.instance = new TaskDataFactory();
        }
        s = TaskDataFactory.instance.getTaskConnection().updateTasksDoneStatus(context, s, true, set);
        class .Lambda._cls3
            implements Consumer
        {

            private final Context arg$1;
            private final Set arg$2;

            public final void accept(Object obj)
            {
                SwipeTaskUtils.lambda$updateTaskDoneAsync$3$SwipeTaskUtils(arg$1, arg$2, (Optional)obj);
            }

            .Lambda._cls3(Context context, Set set)
            {
                arg$1 = context;
                arg$2 = set;
            }
        }

        context = LogUtils.newFailureLoggingCallback(new .Lambda._cls3(context, set), TAG, "Failed to update reminder done status", new Object[0]);
        set = CalendarExecutor.MAIN;
        if (context == null)
        {
            throw new NullPointerException();
        } else
        {
            s.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(s, context), set);
            return s;
        }
    }

}
