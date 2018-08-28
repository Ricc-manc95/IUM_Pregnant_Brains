// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.visualelement;

import android.view.View;

// Referenced classes of package com.google.android.libraries.social.analytics.visualelement:
//            VisualElementProvider, VisualElement

public final class VisualElementUtil
{

    public static VisualElement attach(View view, VisualElement visualelement)
    {
        if (view instanceof VisualElementProvider)
        {
            throw new IllegalArgumentException(String.valueOf(view.getClass().getName()).concat(" implements VisualElementProvider; this metadata should be added to the result of VisualElementProvider.getVisualElement()."));
        } else
        {
            view.setTag(0x7f100006, visualelement);
            return visualelement;
        }
    }
}
