// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newevent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class NewEventTimeChangedListenerHolder
{

    public static final NewEventTimeChangedListenerHolder INSTANCE = new NewEventTimeChangedListenerHolder();
    public long createNewEventTime;
    public final List createNewEventTimeChangedListeners = new ArrayList();

    private NewEventTimeChangedListenerHolder()
    {
        createNewEventTime = -1L;
    }

    public final void setCreateNewEventTime(long l)
    {
        if (l != createNewEventTime)
        {
            createNewEventTime = l;
            Iterator iterator = createNewEventTimeChangedListeners.iterator();
            while (iterator.hasNext()) 
            {
                OnCreateNewEventTimeChangedListener oncreateneweventtimechangedlistener = (OnCreateNewEventTimeChangedListener)iterator.next();
                if (oncreateneweventtimechangedlistener != null)
                {
                    oncreateneweventtimechangedlistener._mth5152ILG_0();
                }
            }
        }
    }


    private class OnCreateNewEventTimeChangedListener
    {

        public abstract void onCreateNewEventTimeChanged$5152ILG_0();
    }

}
