// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chipviewmodelfactory;


// Referenced classes of package com.google.android.calendar.timeline.chipviewmodelfactory:
//            ChipFragmentInfo

public static final class contentDescriptionSuffix
{

    public String contentDescriptionPrefix;
    public String contentDescriptionSuffix;
    public boolean shouldShowImages;
    public boolean showMultidayAnnotations;
    public eConfigProvider swipeConfigProvider;
    public Integer viewType;

    public final ChipFragmentInfo build()
    {
        if (viewType == null)
        {
            throw new IllegalStateException("setViewType must be called before build!");
        } else
        {
            return new ChipFragmentInfo(showMultidayAnnotations, shouldShowImages, false, 0, viewType.intValue(), contentDescriptionPrefix, contentDescriptionSuffix, swipeConfigProvider, 0);
        }
    }

    public eConfigProvider()
    {
        contentDescriptionPrefix = "";
        contentDescriptionSuffix = "";
    }
}
