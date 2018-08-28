// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import com.google.common.base.Predicate;
import com.google.personalization.assist.annotate.AnnotationType;
import com.google.personalization.assist.annotate.api.AnnotationFragment;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            EventResultCreator

final class arg._cls1
    implements Predicate
{

    private final AnnotationType arg$1;

    public final boolean apply(Object obj)
    {
        return EventResultCreator.lambda$findFragment$2$EventResultCreator(arg$1, (AnnotationFragment)obj);
    }

    Q(AnnotationType annotationtype)
    {
        arg$1 = annotationtype;
    }
}
