// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.visualelement;

import java.io.Serializable;
import java.util.Locale;

// Referenced classes of package com.google.android.libraries.social.analytics.visualelement:
//            VisualElement

public final class VisualElementTag
    implements Serializable
{

    public static final long serialVersionUID = 1L;
    public final int id;
    public final boolean isRootPage;

    public VisualElementTag(int i)
    {
        this(i, false, com/google/android/libraries/social/analytics/visualelement/VisualElement);
    }

    public VisualElementTag(int i, boolean flag, Class class1)
    {
        id = i;
        isRootPage = flag;
    }

    public final boolean equals(Object obj)
    {
        return this == obj || (obj instanceof VisualElementTag) && id == ((VisualElementTag)obj).id;
    }

    public final int hashCode()
    {
        return id + 527;
    }

    public final String toString()
    {
        return String.format(Locale.US, "VisualElementTag {id: %d, isRootPage: %b}", new Object[] {
            Integer.valueOf(id), Boolean.valueOf(isRootPage)
        });
    }
}
