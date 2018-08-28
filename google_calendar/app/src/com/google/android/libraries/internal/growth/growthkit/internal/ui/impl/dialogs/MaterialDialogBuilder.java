// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.design.bottomsheet.BottomSheetBehavior;
import android.support.design.bottomsheet.BottomSheetDialog;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.libraries.internal.growth.growthkit.internal.media.ImageCache;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.DialogBuilder;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.PromoDialog;
import com.google.android.libraries.material.dialog.ButtonPaneLayout;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs:
//            DialogUtils

public final class MaterialDialogBuilder
    implements DialogBuilder
{

    public BottomSheetBehavior bottomSheetBehavior;

    public MaterialDialogBuilder()
    {
        bottomSheetBehavior = null;
    }

    public final PromoDialog build(Context context, ImageCache imagecache, com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
        boolean flag = DialogUtils.isAlertDialogUi(promoui);
        boolean flag1 = DialogUtils.isBottomSheetUi(promoui);
        LayoutInflater layoutinflater = LayoutInflater.from(context);
        int j = context.getResources().getConfiguration().orientation;
        ArrayList arraylist = new ArrayList();
        Object obj;
        Object obj1;
        View view;
        ButtonPaneLayout buttonpanelayout;
        Object obj2;
        int i;
        if (DialogUtils.isBottomSheetUi(promoui))
        {
            i = 0x7f0500a8;
        } else
        {
            DialogUtils.isAlertDialogUi(promoui);
            i = 0x7f0500a7;
        }
        view = layoutinflater.inflate(i, null);
        obj2 = (TextView)view.findViewById(0x7f100236);
        obj1 = (TextView)view.findViewById(0x7f100237);
        buttonpanelayout = (ButtonPaneLayout)view.findViewById(0x7f100238);
        if (promoui.uiTemplateCase_ == 2)
        {
            obj = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
        }
        ((TextView) (obj2)).setText(((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (obj)).headlineText_);
        if (promoui.uiTemplateCase_ == 2)
        {
            obj = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
        }
        ((TextView) (obj1)).setText(((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (obj)).bodyText_);
        if (promoui.uiTemplateCase_ == 2)
        {
            obj = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
        }
        obj2 = Lists.reverse(((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (obj)).userAction_).iterator();
        while (((Iterator) (obj2)).hasNext()) 
        {
            com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action action = (com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action)((Iterator) (obj2)).next();
            obj1 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.forNumber(action.actionType_);
            obj = obj1;
            if (obj1 == null)
            {
                obj = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.ACTION_UNKNOWN;
            }
            if (obj == com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.ACTION_POSITIVE && DialogUtils.isBottomSheetUi(promoui))
            {
                obj = (Button)layoutinflater.inflate(0x7f0500aa, null);
            } else
            {
                obj = (Button)layoutinflater.inflate(0x7f0500a9, null);
            }
            ((Button) (obj)).setText(action.buttonText_);
            ((Button) (obj)).setTag(action);
            arraylist.add(obj);
            buttonpanelayout.addView(((View) (obj)));
        }
        if (promoui.uiTemplateCase_ == 2)
        {
            obj = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
        }
        obj1 = ((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (obj)).imageUrl_;
        if (!TextUtils.isEmpty(((CharSequence) (obj1))) && (j != 2 || !flag))
        {
            imagecache.loadImageToView(((String) (obj1)), (ImageView)view.findViewById(0x7f100235));
        }
        if (flag)
        {
            context = new android.support.v7.app.AlertDialog.Builder(context);
            ((android.support.v7.app.AlertDialog.Builder) (context)).P.mCancelable = true;
            ((android.support.v7.app.AlertDialog.Builder) (context)).P.mView = view;
            ((android.support.v7.app.AlertDialog.Builder) (context)).P.mViewLayoutResId = 0;
            ((android.support.v7.app.AlertDialog.Builder) (context)).P.mViewSpacingSpecified = false;
            context = context.create();
        } else
        if (flag1)
        {
            BottomSheetDialog bottomsheetdialog = new BottomSheetDialog(context);
            bottomsheetdialog.setContentView(view);
            if (promoui.uiTemplateCase_ == 2)
            {
                imagecache = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
            } else
            {
                imagecache = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
            }
            if (((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (imagecache)).promotedApp_ == null)
            {
                imagecache = com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.DEFAULT_INSTANCE;
            } else
            {
                imagecache = ((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (imagecache)).promotedApp_;
            }
            promoui = "";
            if (((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier) (imagecache)).appIdCase_ == 4)
            {
                promoui = (String)((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier) (imagecache)).appId_;
            }
            if (TextUtils.isEmpty(((CharSequence) (obj1))) && !TextUtils.isEmpty(promoui))
            {
                imagecache = (ImageView)view.findViewById(0x7f100235);
                class .Lambda._cls0
                    implements android.content.DialogInterface.OnShowListener
                {

                    private final MaterialDialogBuilder arg$1;

                    public final void onShow(DialogInterface dialoginterface)
                    {
                        dialoginterface = arg$1;
                        ((MaterialDialogBuilder) (dialoginterface)).bottomSheetBehavior.setPeekHeight(-1);
                        ((MaterialDialogBuilder) (dialoginterface)).bottomSheetBehavior.setState(3);
                        ((MaterialDialogBuilder) (dialoginterface)).bottomSheetBehavior.skipCollapsed = true;
                        ((MaterialDialogBuilder) (dialoginterface)).bottomSheetBehavior.hideable = true;
                    }

            .Lambda._cls0()
            {
                arg$1 = MaterialDialogBuilder.this;
            }
                }

                try
                {
                    imagecache.setImageDrawable(context.getPackageManager().getApplicationIcon(promoui));
                    imagecache.setVisibility(0);
                }
                // Misplaced declaration of an exception variable
                catch (Context context)
                {
                    imagecache.setVisibility(8);
                }
            }
            bottomSheetBehavior = BottomSheetBehavior.from(bottomsheetdialog.findViewById(0x7f100164));
            bottomsheetdialog.setOnShowListener(new .Lambda._cls0());
            context = bottomsheetdialog;
        } else
        {
            context = null;
        }
        return new PromoDialog(context, arraylist);
    }
}
