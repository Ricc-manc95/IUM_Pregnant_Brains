// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.firebase.jobdispatcher;

import android.text.TextUtils;
import java.util.List;

public final class  extends RuntimeException
{

    public (String s, List list)
    {
        list = TextUtils.join("\n  - ", list);
        super((new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(list).length())).append(s).append(": ").append(list).toString());
    }
}
