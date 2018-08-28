// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import com.google.personalization.assist.annotate.api.AnnotationFragment;
import java.util.Comparator;

public final class Q
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        obj = (AnnotationFragment)obj;
        obj1 = (AnnotationFragment)obj1;
        return ((AnnotationFragment) (obj)).beginPos_ - ((AnnotationFragment) (obj1)).beginPos_;
    }


    private Q()
    {
    }
}
