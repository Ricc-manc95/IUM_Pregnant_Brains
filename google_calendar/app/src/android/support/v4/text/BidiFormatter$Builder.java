// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.text;

import java.util.Locale;

// Referenced classes of package android.support.v4.text:
//            BidiFormatter, TextDirectionHeuristicCompat

public final class mFlags
{

    private int mFlags;
    private boolean mIsRtlContext;
    private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

    public final BidiFormatter build()
    {
        if (mFlags == 2 && mTextDirectionHeuristicCompat == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC)
        {
            if (mIsRtlContext)
            {
                return BidiFormatter.DEFAULT_RTL_INSTANCE;
            } else
            {
                return BidiFormatter.DEFAULT_LTR_INSTANCE;
            }
        } else
        {
            return new BidiFormatter(mIsRtlContext, mFlags, mTextDirectionHeuristicCompat);
        }
    }

    public Compat()
    {
        mIsRtlContext = BidiFormatter.isRtlLocale(Locale.getDefault());
        mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
        mFlags = 2;
    }

    public mFlags(Locale locale)
    {
        mIsRtlContext = BidiFormatter.isRtlLocale(locale);
        mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
        mFlags = 2;
    }
}
