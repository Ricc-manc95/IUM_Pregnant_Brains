// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;


public interface AutoSizeableTextView
{

    public static final boolean PLATFORM_SUPPORTS_AUTOSIZE = flag;

    
    {
        boolean flag;
        if (android.os.Build.VERSION.SDK_INT >= 27)
        {
            flag = true;
        } else
        {
            flag = false;
        }
    }
}
