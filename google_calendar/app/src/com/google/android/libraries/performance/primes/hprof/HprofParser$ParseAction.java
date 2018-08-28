// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;


public final class  extends Enum
{

    private static final IDENTIFY_JAVA_LANG_CLASS $VALUES[];
    public static final IDENTIFY_JAVA_LANG_CLASS CLASSIFY_REF;
    public static final IDENTIFY_JAVA_LANG_CLASS EXCLUDE_INSTANCE;
    public static final IDENTIFY_JAVA_LANG_CLASS FIND_INSTANCE;
    public static final IDENTIFY_JAVA_LANG_CLASS IDENTIFY_JAVA_LANG_CLASS;
    public static final IDENTIFY_JAVA_LANG_CLASS IDENTIFY_OBJECT_CLASS;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        EXCLUDE_INSTANCE = new <init>("EXCLUDE_INSTANCE", 0);
        FIND_INSTANCE = new <init>("FIND_INSTANCE", 1);
        CLASSIFY_REF = new <init>("CLASSIFY_REF", 2);
        IDENTIFY_OBJECT_CLASS = new <init>("IDENTIFY_OBJECT_CLASS", 3);
        IDENTIFY_JAVA_LANG_CLASS = new <init>("IDENTIFY_JAVA_LANG_CLASS", 4);
        $VALUES = (new .VALUES[] {
            EXCLUDE_INSTANCE, FIND_INSTANCE, CLASSIFY_REF, IDENTIFY_OBJECT_CLASS, IDENTIFY_JAVA_LANG_CLASS
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
