// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.calendar.newapi.screen.ics:
//            IcsImporter

final class arg._cls2
    implements Runnable
{

    private final ListenableFuture arg$1;
    private final ompleteListener arg$2;

    public final void run()
    {
        IcsImporter.lambda$addImportListener$4$IcsImporter(arg$1, arg$2);
    }

    ompleteListener(ListenableFuture listenablefuture, ompleteListener ompletelistener)
    {
        arg$1 = listenablefuture;
        arg$2 = ompletelistener;
    }
}
