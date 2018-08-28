// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar;

import android.accounts.Account;
import java.util.Comparator;

final class 
    implements Comparator
{

    public final int compare(Object obj, Object obj1)
    {
        obj = (Account)obj;
        obj1 = (Account)obj1;
        return ((Account) (obj)).name.compareTo(((Account) (obj1)).name);
    }

    ()
    {
    }
}
