// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.chips;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.chips:
//            AutocompletionEventsListener

final class isSelectionSessionOpen
    implements AutocompletionEventsListener
{

    private final List autocompletionEventsListeners = new ArrayList();
    private boolean isSelectionSessionOpen;

    public final void queryUpdated(String s)
    {
        if (!isSelectionSessionOpen)
        {
            selectionSessionStarted();
        }
        for (Iterator iterator = autocompletionEventsListeners.iterator(); iterator.hasNext(); ((AutocompletionEventsListener)iterator.next()).queryUpdated(s)) { }
    }

    public final void resultsReceived(int i, boolean flag)
    {
        for (Iterator iterator = autocompletionEventsListeners.iterator(); iterator.hasNext(); ((AutocompletionEventsListener)iterator.next()).resultsReceived(i, flag)) { }
    }

    public final void selectionSessionStarted()
    {
        isSelectionSessionOpen = true;
        for (Iterator iterator = autocompletionEventsListeners.iterator(); iterator.hasNext(); ((AutocompletionEventsListener)iterator.next()).selectionSessionStarted()) { }
    }

    ()
    {
        isSelectionSessionOpen = false;
    }
}
