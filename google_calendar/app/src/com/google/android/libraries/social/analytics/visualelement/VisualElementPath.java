// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.analytics.visualelement;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.View;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.libraries.social.analytics.visualelement:
//            VisualElementProvider, VisualElement, VisualElementTag

public final class VisualElementPath
    implements Serializable
{

    public static final String EXTRA_VISUAL_ELEMENT_PATH = com/google/android/libraries/social/analytics/visualelement/VisualElementPath.getName();
    public static final long serialVersionUID = 1L;
    public final List visualElements = new ArrayList();

    public VisualElementPath()
    {
    }

    public static Intent findIntent(Context context)
    {
        for (; (context instanceof Activity) || (context instanceof ContextWrapper); context = ((ContextWrapper)context).getBaseContext())
        {
            if (context instanceof Activity)
            {
                return ((Activity)context).getIntent();
            }
        }

        return null;
    }

    public final void addVisualElement(View view)
    {
        if (view instanceof VisualElementProvider)
        {
            view = ((VisualElementProvider)view).getVisualElement();
        } else
        if (view instanceof VisualElementProvider)
        {
            view = ((VisualElementProvider)view).getVisualElement();
        } else
        {
            view = (VisualElement)view.getTag(0x7f100006);
        }
        if (view != null)
        {
            visualElements.add(view);
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof VisualElementPath)
        {
            obj = ((VisualElementPath)obj).visualElements;
            List list = visualElements;
            if (obj == null)
            {
                if (list == null)
                {
                    return true;
                }
            } else
            {
                return obj.equals(list);
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return visualElements.hashCode();
    }

    public final String toString()
    {
        List list = visualElements;
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++)
        {
            if (i > 0)
            {
                stringbuilder.append("->");
            }
            stringbuilder.append(((VisualElement)list.get(i)).tag.id);
        }

        return stringbuilder.append(" (leaf->root)").toString();
    }

}
