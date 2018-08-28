// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.app.Activity;
import android.os.Parcelable;
import android.view.View;

public abstract class ViewFinder
    implements Parcelable
{

    public ViewFinder()
    {
    }

    public abstract View find(Activity activity, View view);
}
