// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.common.drawable.CircledScalingStringDrawable;
import com.google.android.calendar.common.drawable.DefaultableBitmapDrawable;

// Referenced classes of package com.google.android.calendar.avatar:
//            LetterTileDrawableFactory

final class arg._cls4
    implements com.google.android.calendar.common.drawable.istener
{

    private final Consumer arg$1;
    private final Context arg$2;
    private final String arg$3;
    private final String arg$4;

    public final void onEmptyBitmapSet(DefaultableBitmapDrawable defaultablebitmapdrawable)
    {
        Consumer consumer;
        Context context;
        String s;
        String s1;
        consumer = arg$1;
        context = arg$2;
        s1 = arg$3;
        s = arg$4;
        defaultablebitmapdrawable.unbind();
        boolean flag;
        if (!TextUtils.isEmpty(s1))
        {
label0:
            {
                if (s1 == null)
                {
                    throw new NullPointerException();
                }
                char c = ((String)s1).charAt(0);
                if (Character.isLetterOrDigit(c) && (' ' <= c && c <= '\u024F' || '\u0370' <= c && c <= '\u058F' || '\u10A0' <= c && c <= '\u10FF' || '\u0590' <= c && c <= '\u06FF' || '\u13A0' <= c && c <= '\u13FF'))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    flag = true;
                    break label0;
                }
            }
        }
        flag = false;
        if (true) goto _L2; else goto _L1
_L2:
        int i;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_446;
        }
        defaultablebitmapdrawable = context.getResources();
        if (LetterTileDrawableFactory.colors == null)
        {
            LetterTileDrawableFactory.colors = defaultablebitmapdrawable.obtainTypedArray(0x7f0b0031);
            LetterTileDrawableFactory.defaultColor = defaultablebitmapdrawable.getColor(0x7f0d013a);
            LetterTileDrawableFactory.tileFontColor = defaultablebitmapdrawable.getColor(0x7f0d013b);
        }
        if (TextUtils.isEmpty(s1))
        {
            break; /* Loop/switch isn't completed */
        }
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        char c1 = ((String)s1).charAt(0);
        if (Character.isLetterOrDigit(c1) && (' ' <= c1 && c1 <= '\u024F' || '\u0370' <= c1 && c1 <= '\u058F' || '\u10A0' <= c1 && c1 <= '\u10FF' || '\u0590' <= c1 && c1 <= '\u06FF' || '\u13A0' <= c1 && c1 <= '\u13FF'))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L1; else goto _L3
_L3:
        i = 1;
_L4:
        if (i != 0)
        {
            defaultablebitmapdrawable = String.valueOf(Character.toUpperCase(s1.charAt(0)));
        } else
        {
            defaultablebitmapdrawable = null;
        }
        if (TextUtils.isEmpty(s))
        {
            i = LetterTileDrawableFactory.defaultColor;
        } else
        {
            if (s == null)
            {
                throw new NullPointerException();
            }
            i = Math.abs(((String)s).hashCode());
            int j = LetterTileDrawableFactory.colors.length();
            i = LetterTileDrawableFactory.colors.getColor(i % j, LetterTileDrawableFactory.defaultColor);
        }
        defaultablebitmapdrawable = new CircledScalingStringDrawable(context, defaultablebitmapdrawable, i, LetterTileDrawableFactory.tileFontColor, 0.6666667F);
_L5:
        consumer.accept(defaultablebitmapdrawable);
        return;
_L1:
        i = 0;
          goto _L4
        defaultablebitmapdrawable = ContextCompat.getDrawable(context, 0x7f020280);
          goto _L5
    }

    rawable(Consumer consumer, Context context, String s, String s1)
    {
        arg$1 = consumer;
        arg$2 = context;
        arg$3 = s;
        arg$4 = s1;
    }
}
