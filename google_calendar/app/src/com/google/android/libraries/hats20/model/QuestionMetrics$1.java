// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.model;

import android.os.Parcel;

// Referenced classes of package com.google.android.libraries.hats20.model:
//            QuestionMetrics

final class A
    implements android.os.
{

    public final Object createFromParcel(Parcel parcel)
    {
        return new QuestionMetrics(parcel);
    }

    public final Object[] newArray(int i)
    {
        return new QuestionMetrics[i];
    }

    A()
    {
    }
}
