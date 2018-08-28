// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable21;
import java.util.HashMap;
import java.util.Map;

final class ContactPhotoCache
{

    public final Context context;
    public final Map photoCache = new HashMap();

    ContactPhotoCache(Context context1)
    {
        context = context1;
    }

    static Drawable createRoundDrawable(Resources resources, Bitmap bitmap)
    {
        resources = new RoundedBitmapDrawable21(resources, bitmap);
        ((RoundedBitmapDrawable) (resources)).mPaint.setAntiAlias(true);
        resources.invalidateSelf();
        resources.mIsCircular = true;
        resources.mApplyGravity = true;
        resources.mCornerRadius = Math.min(((RoundedBitmapDrawable) (resources)).mBitmapHeight, ((RoundedBitmapDrawable) (resources)).mBitmapWidth) / 2;
        ((RoundedBitmapDrawable) (resources)).mPaint.setShader(((RoundedBitmapDrawable) (resources)).mBitmapShader);
        resources.invalidateSelf();
        return resources;
    }
}
