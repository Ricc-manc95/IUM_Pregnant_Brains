// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.base;


final class tcher extends tcher
{

    public static final SHIFT INSTANCE = new <init>();
    private static final int SHIFT = Integer.numberOfLeadingZeros(31);

    public final boolean matches(char c)
    {
        return "\u2002\u3000\r\205\u200A\u2005\u2000\u3000\u2029\013\u3000\u2008\u2003\u205F\u3000\u1680\t \u2006\u2001\u202F\240\f\u2009\u3000\u2004\u3000\u3000\u2028\n\u2007\u3000".charAt(0x6449bf0a * c >>> SHIFT) == c;
    }


    tcher()
    {
        super("CharMatcher.whitespace()");
    }
}
