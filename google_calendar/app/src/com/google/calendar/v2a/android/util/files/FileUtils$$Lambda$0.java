// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2a.android.util.files;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.io.Files;
import java.io.File;

public final class 
    implements Function
{

    public static final Function $instance = new <init>();

    public final Object apply(Object obj)
    {
        return Files.asCharSource((File)obj, Charsets.UTF_8);
    }


    private ()
    {
    }
}
