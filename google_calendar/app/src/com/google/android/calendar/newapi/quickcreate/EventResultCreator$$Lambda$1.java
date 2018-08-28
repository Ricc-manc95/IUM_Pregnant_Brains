// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import com.google.personalization.assist.annotate.api.AnnotationFragment;
import java.util.Comparator;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            EventResultCreator

final class Q
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        return EventResultCreator.lambda$stripText$1$EventResultCreator((AnnotationFragment)obj, (AnnotationFragment)obj1);
    }


    private Q()
    {
    }
}
