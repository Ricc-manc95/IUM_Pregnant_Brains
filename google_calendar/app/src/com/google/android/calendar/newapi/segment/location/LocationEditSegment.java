// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.android.bitmap.UnrefedBitmapCache;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;
import com.google.android.calendar.event.image.PlacePageOrMapRequestKey;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.location:
//            PlacePageOrMapRequestKeyFactory

public class LocationEditSegment extends EditSegment
    implements android.view.View.OnClickListener, PlacePageOrMapRequestKeyFactory
{
    public static interface Listener
    {

        public abstract void onLocationTileClicked();

        public abstract void onMapPreviewClicked();
    }


    public ExtendedBitmapDrawable drawable;
    public ImageView imagePreview;
    public int previewImageHeight;
    public int previewImageWidth;
    public PlacePageOrMapRequestKeyFactory requestKeyFactory;
    public TextTileView tile;

    public LocationEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        requestKeyFactory = this;
    }

    public final Object createRequestKey(String s, String s1, String s2, String s3, int i, int j)
    {
        return new PlacePageOrMapRequestKey(s, s1, s2, s3, i, j);
    }

    public void onClick(View view)
    {
        if (view.getId() == 0x7f100282)
        {
            ((Listener)super.mListener).onMapPreviewClicked();
            return;
        } else
        {
            ((Listener)super.mListener).onLocationTileClicked();
            return;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        tile = (TextTileView)findViewById(0x7f100175);
        tile.setOnClickListener(this);
        imagePreview = (ImageView)findViewById(0x7f100282);
        com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions extendedoptions = new com.android.bitmap.drawable.ExtendedBitmapDrawable.ExtendedOptions(4);
        extendedoptions.decodeVerticalCenter = 0.5F;
        extendedoptions.backgroundColor = 0xff888888;
        drawable = new ExtendedBitmapDrawable(getResources(), new UnrefedBitmapCache(1024, 0.0F, 0), false, extendedoptions);
        drawable.setCallback(this);
        imagePreview.setImageDrawable(drawable);
        imagePreview.setOnClickListener(this);
        previewImageWidth = getResources().getDimensionPixelSize(0x7f0e0318);
        previewImageHeight = getResources().getDimensionPixelSize(0x7f0e0317);
    }
}
