// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.support.espresso;


public final class IdleResourceManager
{

    public boolean isMultipleChoiceSelectionAnimating;
    public boolean isThankYouAnimating;

    public IdleResourceManager()
    {
    }

    public final void setIsMultipleChoiceSelectionAnimating(boolean flag)
    {
        boolean flag2 = true;
        boolean flag1;
        if (!isMultipleChoiceSelectionAnimating && !isThankYouAnimating)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        isMultipleChoiceSelectionAnimating = flag;
        if (!flag1)
        {
            if (!isMultipleChoiceSelectionAnimating && !isThankYouAnimating)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (!flag1);
        }
    }
}
