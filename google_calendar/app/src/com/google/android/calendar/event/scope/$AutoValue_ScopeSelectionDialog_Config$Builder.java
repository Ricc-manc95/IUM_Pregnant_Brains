// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.os.Bundle;
import java.util.List;

// Referenced classes of package com.google.android.calendar.event.scope:
//            AutoValue_ScopeSelectionDialog_Config

public final class  extends 
{

    private Bundle additionalArguments;
    private Integer positiveButton;
    private List scopes;
    private Integer title;

    public final  additionalArguments(Bundle bundle)
    {
        additionalArguments = bundle;
        return this;
    }

    public final additionalArguments build()
    {
        String s1 = "";
        if (title == null)
        {
            s1 = String.valueOf("").concat(" title");
        }
        String s = s1;
        if (positiveButton == null)
        {
            s = String.valueOf(s1).concat(" positiveButton");
        }
        s1 = s;
        if (additionalArguments == null)
        {
            s1 = String.valueOf(s).concat(" additionalArguments");
        }
        s = s1;
        if (scopes == null)
        {
            s = String.valueOf(s1).concat(" scopes");
        }
        if (!s.isEmpty())
        {
            s = String.valueOf(s);
            if (s.length() != 0)
            {
                s = "Missing required properties:".concat(s);
            } else
            {
                s = new String("Missing required properties:");
            }
            throw new IllegalStateException(s);
        } else
        {
            return new AutoValue_ScopeSelectionDialog_Config(title.intValue(), positiveButton.intValue(), additionalArguments, scopes);
        }
    }

    public final scopes positiveButton(int i)
    {
        positiveButton = Integer.valueOf(i);
        return this;
    }

    public final positiveButton scopes(List list)
    {
        if (list == null)
        {
            throw new NullPointerException("Null scopes");
        } else
        {
            scopes = list;
            return this;
        }
    }

    public final scopes title(int i)
    {
        title = Integer.valueOf(i);
        return this;
    }

    public ()
    {
    }
}
