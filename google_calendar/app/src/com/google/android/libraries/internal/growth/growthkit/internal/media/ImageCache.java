// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.media;

import android.widget.ImageView;
import com.google.common.util.concurrent.ListenableFuture;

public interface ImageCache
{

    public abstract ListenableFuture downloadImage(String s, int i, int j);

    public abstract void loadImageToView(String s, ImageView imageview);
}
