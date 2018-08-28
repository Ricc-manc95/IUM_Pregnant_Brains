// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate.api;

import com.google.personalization.assist.annotate.AnnotationType;

final class 
    implements com.google.protobuf.rter
{

    public final Object convert(Object obj)
    {
        AnnotationType annotationtype = AnnotationType.forNumber(((Integer)obj).intValue());
        obj = annotationtype;
        if (annotationtype == null)
        {
            obj = AnnotationType.ACTIVATE_BANK_CARD_ACTION;
        }
        return obj;
    }

    ()
    {
    }
}
