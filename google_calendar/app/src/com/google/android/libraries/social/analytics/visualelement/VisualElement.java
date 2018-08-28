// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.visualelement;

import java.io.Serializable;
import java.util.Locale;

// Referenced classes of package com.google.android.libraries.social.analytics.visualelement:
//            VisualElementTag

public class VisualElement
    implements Serializable
{

    public static final long serialVersionUID = 1L;
    public final VisualElementTag tag;

    public VisualElement(VisualElementTag visualelementtag)
    {
        if (visualelementtag == null)
        {
            throw new NullPointerException();
        } else
        {
            tag = visualelementtag;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != null && getClass() == obj.getClass())
        {
            obj = (VisualElement)obj;
            return tag.equals(((VisualElement) (obj)).tag);
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return tag.hashCode() + 527;
    }

    public String toString()
    {
        return String.format(Locale.US, "VisualElement {tag: %s}", new Object[] {
            tag
        });
    }
}
