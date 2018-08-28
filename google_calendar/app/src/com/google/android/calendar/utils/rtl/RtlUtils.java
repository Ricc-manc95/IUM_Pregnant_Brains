// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.rtl;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.text.TextUtils;

public final class RtlUtils
{

    private static final String LRE = "\u202A";
    private static final String LRM = "\u200E";
    private static final String PDF = "\u202C";
    private static final String RLE = "\u202B";
    private static final String RLM = "\u200F";

    public static CharSequence forceTextAlignment(CharSequence charsequence, boolean flag)
    {
        if (charsequence == null)
        {
            return null;
        }
        String s;
        String s1;
        if (flag)
        {
            s = RLM;
        } else
        {
            s = LRM;
        }
        if (TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR.isRtl(charsequence, 0, charsequence.length()))
        {
            s1 = RLE;
        } else
        {
            s1 = LRE;
        }
        return TextUtils.concat(new CharSequence[] {
            s, TextUtils.concat(new CharSequence[] {
                s1, charsequence, PDF
            }), s
        });
    }

    public static String forceTextAlignment(String s, boolean flag)
    {
        return (String)forceTextAlignment(((CharSequence) (s)), flag);
    }

    public static String forceTextAlignmentOrUseDefault(String s, boolean flag, String s1)
    {
        if (!TextUtils.isEmpty(s))
        {
            s1 = forceTextAlignment(s, flag);
        }
        return (String)s1;
    }

    public static boolean isLayoutDirectionRtl(Context context)
    {
        return context.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    public static boolean isLayoutDirectionRtl(Configuration configuration)
    {
        return configuration.getLayoutDirection() == 1;
    }

}
