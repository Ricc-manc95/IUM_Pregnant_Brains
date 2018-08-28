// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.rooms.data.Room;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            ErrorViewController

final class arg._cls1
    implements Consumer
{

    private final ErrorViewController arg$1;

    public final void accept(Object obj)
    {
        ErrorViewController errorviewcontroller = arg$1;
        obj = (Room)obj;
        errorviewcontroller.listener.nRoomRemoved(((Room) (obj)));
    }

    (ErrorViewController errorviewcontroller)
    {
        arg$1 = errorviewcontroller;
    }
}
