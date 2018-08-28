// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.CardViewImpl;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.libraries.hats20.util.LayoutDimensions;

final class DimensionConfigurationHelper
{

    public final boolean bottomSheet;
    public final Dialog dialog;
    public final LayoutDimensions layoutDimensions;
    public final CardView promptCard;

    DimensionConfigurationHelper(CardView cardview, Dialog dialog1, LayoutDimensions layoutdimensions, boolean flag)
    {
        promptCard = cardview;
        dialog = dialog1;
        layoutDimensions = layoutdimensions;
        bottomSheet = flag;
    }

    final void configureDimensions()
    {
        float f;
        float f1;
        Object obj;
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams;
        int i;
        boolean flag;
        if (dialog != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (bottomSheet)
        {
            i = -1;
        } else
        {
            obj = layoutDimensions;
            Display display = ((WindowManager)((LayoutDimensions) (obj)).context.getSystemService("window")).getDefaultDisplay();
            Point point1 = new Point();
            display.getSize(point1);
            if (point1.x < ((LayoutDimensions) (obj)).screenWidth480)
            {
                obj = ((WindowManager)((LayoutDimensions) (obj)).context.getSystemService("window")).getDefaultDisplay();
                Point point = new Point();
                ((Display) (obj)).getSize(point);
                i = point.x;
            } else
            {
                i = ((LayoutDimensions) (obj)).context.getResources().getDimensionPixelSize(0x7f0e0225) + ((LayoutDimensions) (obj)).context.getResources().getDimensionPixelOffset(0x7f0e0212) * 2;
            }
        }
        obj = promptCard;
        if (bottomSheet)
        {
            f = promptCard.getContext().getResources().getDimension(0x7f0e021b);
        } else
        {
            f = promptCard.getContext().getResources().getDimension(0x7f0e021a);
        }
        ((CardView) (obj)).setCardElevation(f);
        obj = promptCard;
        f = 1.5F * CardView.IMPL.getMaxElevation(((CardView) (obj)).mCardViewDelegate);
        obj = promptCard;
        f1 = CardView.IMPL.getMaxElevation(((CardView) (obj)).mCardViewDelegate);
        if (dialog != null)
        {
            obj = layoutDimensions.getMargin(bottomSheet);
        } else
        {
            obj = new RectF(0.0F, 0.0F, 0.0F, 0.0F);
        }
        if (flag)
        {
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.addFlags(32);
            window.clearFlags(2);
            android.view.WindowManager.LayoutParams layoutparams = window.getAttributes();
            layoutparams.x = 0;
            layoutparams.y = 0;
            layoutparams.width = i;
            layoutparams.gravity = 85;
            window.setAttributes(layoutparams);
        }
        try
        {
            marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)promptCard.getLayoutParams();
        }
        catch (ClassCastException classcastexception)
        {
            throw new RuntimeException("HatsShowRequest.insertIntoParent can only be called with a ViewGroup whose LayoutParams extend MarginLayoutParams", classcastexception);
        }
        marginlayoutparams.setMargins(Math.round(((RectF) (obj)).left - f1), Math.round(((RectF) (obj)).top - f), Math.round(((RectF) (obj)).right - f1), Math.round(((RectF) (obj)).bottom - f));
        promptCard.setLayoutParams(marginlayoutparams);
    }
}
