// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import com.google.common.base.Predicate;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            EventResultCreator

final class arg._cls1
    implements Predicate
{

    private final List arg$1;

    public final boolean apply(Object obj)
    {
        return EventResultCreator.lambda$wasConnectorUsed$3$EventResultCreator(arg$1, (AnnotationFragment)obj);
    }

    Q(List list)
    {
        arg$1 = list;
    }
}
