// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.scope;


abstract class $AutoValue_ScopeSelectionDialog_Scope extends ScopeSelectionDialog.Scope
{

    private final int description;
    private final int value;

    $AutoValue_ScopeSelectionDialog_Scope(int i, int j)
    {
        description = i;
        value = j;
    }

    final int description()
    {
        return description;
    }

    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ScopeSelectionDialog.Scope)
            {
                if (description != ((ScopeSelectionDialog.Scope) (obj = (ScopeSelectionDialog.Scope)obj)).description() || value != ((ScopeSelectionDialog.Scope) (obj)).value())
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
        return (description ^ 0xf4243) * 0xf4243 ^ value;
    }

    public String toString()
    {
        int i = description;
        int j = value;
        return (new StringBuilder(49)).append("Scope{description=").append(i).append(", value=").append(j).append("}").toString();
    }

    public final int value()
    {
        return value;
    }
}
