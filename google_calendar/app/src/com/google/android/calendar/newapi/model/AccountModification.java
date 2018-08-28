// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model;

import android.accounts.Account;

// Referenced classes of package com.google.android.calendar.newapi.model:
//            AccountHolder

public interface AccountModification
    extends AccountHolder
{

    public abstract void setAccount(Account account);
}
