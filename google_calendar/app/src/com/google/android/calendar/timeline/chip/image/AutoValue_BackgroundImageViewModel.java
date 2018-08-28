// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip.image;


// Referenced classes of package com.google.android.calendar.timeline.chip.image:
//            BackgroundImageViewModel, ImageViewModel

public final class AutoValue_BackgroundImageViewModel extends BackgroundImageViewModel
{

    private final BackgroundImageViewModel.BottomLineStyle bottomLineStyle;
    private final ImageViewModel imageViewModel;

    public AutoValue_BackgroundImageViewModel(ImageViewModel imageviewmodel, BackgroundImageViewModel.BottomLineStyle bottomlinestyle)
    {
        if (imageviewmodel == null)
        {
            throw new NullPointerException("Null imageViewModel");
        }
        imageViewModel = imageviewmodel;
        if (bottomlinestyle == null)
        {
            throw new NullPointerException("Null bottomLineStyle");
        } else
        {
            bottomLineStyle = bottomlinestyle;
            return;
        }
    }

    public final BackgroundImageViewModel.BottomLineStyle bottomLineStyle()
    {
        return bottomLineStyle;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof BackgroundImageViewModel)
            {
                if (!imageViewModel.equals(((BackgroundImageViewModel) (obj = (BackgroundImageViewModel)obj)).imageViewModel()) || !bottomLineStyle.equals(((BackgroundImageViewModel) (obj)).bottomLineStyle()))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return (imageViewModel.hashCode() ^ 0xf4243) * 0xf4243 ^ bottomLineStyle.hashCode();
    }

    public final ImageViewModel imageViewModel()
    {
        return imageViewModel;
    }

    public final String toString()
    {
        String s = String.valueOf(imageViewModel);
        String s1 = String.valueOf(bottomLineStyle);
        return (new StringBuilder(String.valueOf(s).length() + 59 + String.valueOf(s1).length())).append("BackgroundImageViewModel{imageViewModel=").append(s).append(", bottomLineStyle=").append(s1).append("}").toString();
    }
}
