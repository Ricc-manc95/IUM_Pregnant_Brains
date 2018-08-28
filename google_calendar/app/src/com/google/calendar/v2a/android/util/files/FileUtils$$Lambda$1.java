// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.files;

import java.io.File;
import java.util.Comparator;

public final class 
    implements Comparator
{

    public static final Comparator $instance = new <init>();

    public final int compare(Object obj, Object obj1)
    {
        obj = (File)obj;
        return Long.valueOf(((File)obj1).lastModified()).compareTo(Long.valueOf(((File) (obj)).lastModified()));
    }


    private ()
    {
    }
}
