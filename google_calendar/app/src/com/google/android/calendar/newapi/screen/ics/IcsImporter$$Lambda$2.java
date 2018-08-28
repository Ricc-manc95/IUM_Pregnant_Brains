// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.ics;

import com.google.common.base.Predicate;

// Referenced classes of package com.google.android.calendar.newapi.screen.ics:
//            IcsImporter

final class lt
    implements Predicate
{

    public static final Predicate $instance = new <init>();

    public final boolean apply(Object obj)
    {
        return IcsImporter.lambda$addImportListener$3$IcsImporter((lt)obj);
    }


    private lt()
    {
    }
}
