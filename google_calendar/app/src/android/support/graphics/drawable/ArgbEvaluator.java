// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.animation.TypeEvaluator;

public final class ArgbEvaluator
    implements TypeEvaluator
{

    public static final ArgbEvaluator sInstance = new ArgbEvaluator();

    public ArgbEvaluator()
    {
    }

    public final Object evaluate(float f, Object obj, Object obj1)
    {
        int i = ((Integer)obj).intValue();
        float f1 = (float)(i >>> 24) / 255F;
        float f4 = (float)(i >> 16 & 0xff) / 255F;
        float f5 = (float)(i >> 8 & 0xff) / 255F;
        float f6 = (float)(i & 0xff) / 255F;
        i = ((Integer)obj1).intValue();
        float f2 = (float)(i >>> 24) / 255F;
        float f8 = (float)(i >> 16 & 0xff) / 255F;
        float f7 = (float)(i >> 8 & 0xff) / 255F;
        float f3 = (float)(i & 0xff) / 255F;
        f4 = (float)Math.pow(f4, 2.2000000000000002D);
        f5 = (float)Math.pow(f5, 2.2000000000000002D);
        f6 = (float)Math.pow(f6, 2.2000000000000002D);
        f8 = (float)Math.pow(f8, 2.2000000000000002D);
        f7 = (float)Math.pow(f7, 2.2000000000000002D);
        f3 = (float)Math.pow(f3, 2.2000000000000002D);
        f4 = (float)Math.pow(f4 + (f8 - f4) * f, 0.45454545454545453D);
        f5 = (float)Math.pow(f5 + (f7 - f5) * f, 0.45454545454545453D);
        f3 = (float)Math.pow(f6 + (f3 - f6) * f, 0.45454545454545453D);
        i = Math.round((f1 + (f2 - f1) * f) * 255F);
        int j = Math.round(f4 * 255F);
        int k = Math.round(f5 * 255F);
        return Integer.valueOf(Math.round(f3 * 255F) | (i << 24 | j << 16 | k << 8));
    }

}
