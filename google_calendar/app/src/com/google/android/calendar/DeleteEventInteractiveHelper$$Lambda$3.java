// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.content.DialogInterface;
import com.google.android.calendar.api.event.Event;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.calendar:
//            DeleteEventInteractiveHelper

final class arg._cls4
    implements android.content.r..Lambda._cls3
{

    private final SettableFuture arg$1;
    private final Context arg$2;
    private final Event arg$3;
    private final AtomicInteger arg$4;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        DeleteEventInteractiveHelper.lambda$showConfirmationDialog$2$DeleteEventInteractiveHelper$51666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T9MAT3KC5H6OPA6ELQ7ASJ57D662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45THM2R35DPI62SHFC5O6IBR5EPIMST1F8LR6ARJK7D66KOBMC4NNAT39DGNM6RRECDQN4SJ5DPQ2UOBKDTMMIOPF85Q6URB9CD4MST35CTIN4EQCC5N68SJFD5I2UORFDPQ6ARJK5T26IOBCDTJKIRJKCLP6COB3CKTKIAAM0(arg$1, arg$2, arg$3, arg$4);
    }

    (SettableFuture settablefuture, Context context, Event event, AtomicInteger atomicinteger)
    {
        arg$1 = settablefuture;
        arg$2 = context;
        arg$3 = event;
        arg$4 = atomicinteger;
    }
}
