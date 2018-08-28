// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.text;

import android.text.TextUtils;
import java.util.Locale;

public final class TextUtilsCompat
{

    private static final Locale ROOT = new Locale("", "");

    public static int getLayoutDirectionFromLocale(Locale locale)
    {
        return TextUtils.getLayoutDirectionFromLocale(locale);
    }

}
