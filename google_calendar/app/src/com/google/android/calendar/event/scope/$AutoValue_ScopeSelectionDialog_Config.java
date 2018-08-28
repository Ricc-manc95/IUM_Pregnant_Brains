// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;

import android.os.Bundle;
import java.util.List;

abstract class $AutoValue_ScopeSelectionDialog_Config extends ScopeSelectionDialog.Config
{

    private final Bundle additionalArguments;
    private final int positiveButton;
    private final List scopes;
    private final int title;

    $AutoValue_ScopeSelectionDialog_Config(int i, int j, Bundle bundle, List list)
    {
        title = i;
        positiveButton = j;
        if (bundle == null)
        {
            throw new NullPointerException("Null additionalArguments");
        }
        additionalArguments = bundle;
        if (list == null)
        {
            throw new NullPointerException("Null scopes");
        } else
        {
            scopes = list;
            return;
        }
    }

    public final Bundle additionalArguments()
    {
        return additionalArguments;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ScopeSelectionDialog.Config)
            {
                if (title != ((ScopeSelectionDialog.Config) (obj = (ScopeSelectionDialog.Config)obj)).title() || positiveButton != ((ScopeSelectionDialog.Config) (obj)).positiveButton() || !additionalArguments.equals(((ScopeSelectionDialog.Config) (obj)).additionalArguments()) || !scopes.equals(((ScopeSelectionDialog.Config) (obj)).scopes()))
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

    public int hashCode()
    {
        return (((title ^ 0xf4243) * 0xf4243 ^ positiveButton) * 0xf4243 ^ additionalArguments.hashCode()) * 0xf4243 ^ scopes.hashCode();
    }

    final int positiveButton()
    {
        return positiveButton;
    }

    final List scopes()
    {
        return scopes;
    }

    final int title()
    {
        return title;
    }

    public String toString()
    {
        int i = title;
        int j = positiveButton;
        String s = String.valueOf(additionalArguments);
        String s1 = String.valueOf(scopes);
        return (new StringBuilder(String.valueOf(s).length() + 84 + String.valueOf(s1).length())).append("Config{title=").append(i).append(", positiveButton=").append(j).append(", additionalArguments=").append(s).append(", scopes=").append(s1).append("}").toString();
    }
}
