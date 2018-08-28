// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.bitmap;


// Referenced classes of package com.android.bitmap:
//            DecodeTask, RequestKey, ReusableBitmap

public static interface 
{

    public abstract void onDecodeBegin(RequestKey requestkey);

    public abstract void onDecodeCancel(RequestKey requestkey);

    public abstract void onDecodeComplete(RequestKey requestkey, ReusableBitmap reusablebitmap);
}
