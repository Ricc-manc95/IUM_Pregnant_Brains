// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            CategoryInfo, ChainInfo

public interface LocationGroup
    extends Parcelable, Freezable
{

    public abstract CategoryInfo getCategoryInfo();

    public abstract ChainInfo getChainInfo();

    public abstract String getLocationQuery();

    public abstract Integer getLocationQueryType();
}
