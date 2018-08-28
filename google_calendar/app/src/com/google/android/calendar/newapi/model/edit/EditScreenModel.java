// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.model.edit;

import android.accounts.Account;
import android.os.Parcelable;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.newapi.model.AccountHolder;
import com.google.android.calendar.newapi.model.ViewTypeHolder;

public abstract class EditScreenModel
    implements Parcelable, AccountHolder, ViewTypeHolder
{

    public EditScreenModel()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public abstract Account getAccount();

    public abstract EntityColor getColor();

    public abstract boolean isModified();

    public abstract boolean isNew();

    public abstract void mergeModel(EditScreenModel editscreenmodel);

    public abstract void mergeModel(Object obj);

    public boolean readOnly()
    {
        return false;
    }
}
