// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import com.google.android.libraries.internal.growth.growthkit.inject.FragmentInjector;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.media.ImageCache;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.DialogBuilder;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.PromoDialog;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.util.ArrayList;
import java.util.Map;
import javax.inject.Provider;

public class PromoUiDialogFragment extends DialogFragment
{
    public static interface PromoUiDialogFragmentSubcomponent
        extends FragmentInjector
    {
    }


    private static final Logger logger = new Logger();
    public Map dialogBuilderMap;
    public ImageCache imageCache;
    private boolean memberInjectionSucceed;
    private PromoContext promoContext;
    private Handler uiHandler;
    public UserActionUtil userActionUtil;

    public PromoUiDialogFragment()
    {
        memberInjectionSucceed = false;
    }

    static final void lambda$createEmptyDialogScheduledForDismissal$1$PromoUiDialogFragment(Dialog dialog)
    {
        dialog.dismiss();
    }

    public final void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            ((FragmentInjector)((Provider)GrowthKit.get(context).internalFragmentInjectors().get(com/google/android/libraries/internal/growth/growthkit/internal/ui/impl/dialogs/PromoUiDialogFragment)).get()).inject(this);
            memberInjectionSucceed = true;
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            logger.w(context, "Failed to inject members.", new Object[0]);
        }
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        super.onCancel(dialoginterface);
        userActionUtil.persistUserChoice(promoContext, com.google.identity.growth.proto.CampaignManagement.UserAction.DISMISSED);
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        uiHandler = new Handler();
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        if (!memberInjectionSucceed) goto _L2; else goto _L1
_L1:
        com.google.identity.growth.proto.Promotion.PromoUi promoui1;
        bundle = getArguments();
        bundle.setClassLoader(com/google/android/libraries/internal/growth/growthkit/internal/common/PromoContext.getClassLoader());
        promoContext = (PromoContext)bundle.getParcelable("promoContext");
        com.google.identity.growth.proto.Promotion.PromoUi promoui = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
        ExtensionRegistryLite extensionregistrylite = ExtensionRegistryLite.getEmptyRegistry();
        promoui1 = (com.google.identity.growth.proto.Promotion.PromoUi)((com.google.protobuf.contrib.android.ProtoParsers.InternalDontUse)bundle.getParcelable("promoui")).getMessageUnsafe(promoui.getDefaultInstanceForType(), extensionregistrylite);
        if (promoui1 == null) goto _L2; else goto _L3
_L3:
        if (super.mHost != null) goto _L5; else goto _L4
_L4:
        bundle = null;
_L9:
        com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype;
        PromoContext promocontext;
        promocontext = promoContext;
        uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui1.uiType_);
        Object obj;
        obj = uitype;
        if (uitype != null)
        {
            break MISSING_BLOCK_LABEL_105;
        }
        obj = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
        obj = (Provider)dialogBuilderMap.get(obj);
        if (obj != null) goto _L7; else goto _L6
_L6:
        logger.e("buildDialog called with a non-dialog uiType: %s", new Object[] {
            promoui1
        });
          goto _L8
_L5:
        bundle = (FragmentActivity)super.mHost.mActivity;
          goto _L9
_L7:
        bundle = ((DialogBuilder)((Provider) (obj)).get()).build(bundle, imageCache, promoui1);
        if (((PromoDialog) (bundle)).dialog != null)
        {
            break MISSING_BLOCK_LABEL_205;
        }
        logger.e("Failed to build dialog.", new Object[0]);
        bundle = null;
          goto _L8
        obj = (ArrayList)((PromoDialog) (bundle)).actionViews;
        j = ((ArrayList) (obj)).size();
        i = 0;
_L11:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = ((ArrayList) (obj)).get(i);
        i++;
        ((View)obj1).setOnClickListener(new .Lambda._cls0(promocontext, bundle));
        if (true) goto _L11; else goto _L10
        bundle;
        logger.w(bundle, "Failed to extract promo id and ui from bundle", new Object[0]);
_L2:
        bundle = null;
          goto _L8
_L10:
        bundle = ((PromoDialog) (bundle)).dialog;
_L8:
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final PromoUiDialogFragment arg$1;
            private final PromoContext arg$2;
            private final PromoDialog arg$3;

            public final void onClick(View view)
            {
                Object obj4 = arg$1;
                Object obj2 = arg$2;
                Object obj5 = arg$3;
                Object obj3 = (com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action)view.getTag();
                ((PromoUiDialogFragment) (obj4)).userActionUtil.persistUserChoice(((PromoContext) (obj2)), UserActionUtil.getUserActionFromAction(((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj3))));
                ((PromoDialog) (obj5)).dialog.dismiss();
                obj4 = ((PromoUiDialogFragment) (obj4)).userActionUtil;
                if (((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj3)).targetCase_ == 8)
                {
                    view = (com.google.identity.growth.proto.Promotion.AndroidIntentTarget)((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj3)).target_;
                } else
                {
                    view = com.google.identity.growth.proto.Promotion.AndroidIntentTarget.DEFAULT_INSTANCE;
                }
                obj5 = ((PromoContext) (obj2)).actionTypeIntentMap();
                obj3 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.forNumber(((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj3)).actionType_);
                obj2 = obj3;
                if (obj3 == null)
                {
                    obj2 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.ACTION_UNKNOWN;
                }
                obj3 = (Intent)((ImmutableMap) (obj5)).get(obj2);
                if (obj3 == null)
                {
                    UserActionUtil.logger.w("Intent could not be loaded, not launching.", new Object[0]);
                    return;
                }
                obj2 = com.google.identity.growth.proto.Promotion.AndroidIntentTarget.IntentType.forNumber(((com.google.identity.growth.proto.Promotion.AndroidIntentTarget) (view)).intentType_);
                view = ((View) (obj2));
                if (obj2 == null)
                {
                    view = com.google.identity.growth.proto.Promotion.AndroidIntentTarget.IntentType.UNKNOWN;
                }
                switch (view.ordinal())
                {
                default:
                    UserActionUtil.logger.w("IntentType %s not yet supported", new Object[] {
                        view
                    });
                    return;

                case 1: // '\001'
                    try
                    {
                        ((UserActionUtil) (obj4)).context.startActivity(((Intent) (obj3)));
                        return;
                    }
                    // Misplaced declaration of an exception variable
                    catch (View view)
                    {
                        UserActionUtil.logger.w(view, "Did not found activity to start", new Object[0]);
                    }
                    return;

                case 3: // '\003'
                    ((UserActionUtil) (obj4)).context.sendBroadcast(((Intent) (obj3)));
                    return;

                case 2: // '\002'
                    ((UserActionUtil) (obj4)).context.startService(((Intent) (obj3)));
                    return;
                }
            }

            .Lambda._cls0(PromoContext promocontext, PromoDialog promodialog)
            {
                arg$1 = PromoUiDialogFragment.this;
                arg$2 = promocontext;
                arg$3 = promodialog;
            }
        }

        Object obj1;
        int i;
        int j;
        for (bundle = null; bundle == null;)
        {
            bundle = (new android.support.v7.app.AlertDialog.Builder(getContext())).create();
            class .Lambda._cls1
                implements Runnable
            {

                private final Dialog arg$1;

                public final void run()
                {
                    PromoUiDialogFragment.lambda$createEmptyDialogScheduledForDismissal$1$PromoUiDialogFragment(arg$1);
                }

            .Lambda._cls1(Dialog dialog)
            {
                arg$1 = dialog;
            }
            }

            uiHandler.post(new .Lambda._cls1(bundle));
            return bundle;
        }

        return bundle;
          goto _L9
    }

}
