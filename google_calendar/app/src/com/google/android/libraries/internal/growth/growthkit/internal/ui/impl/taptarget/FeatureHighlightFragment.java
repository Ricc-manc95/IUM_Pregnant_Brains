// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.internal.growth.growthkit.inject.FragmentInjector;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UiUtils;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;
import com.google.android.libraries.material.featurehighlight.FeatureHighlight;
import com.google.android.libraries.material.featurehighlight.FeatureHighlightCallback;
import com.google.android.libraries.material.featurehighlight.FeatureHighlightCallbackProvider;
import com.google.android.libraries.material.featurehighlight.FeatureHighlightView;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.type.Color;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget:
//            FeatureHighlightController, FeatureHighlightControllerFactory, FeatureHighlightViewFinderFactory

public class FeatureHighlightFragment extends Fragment
    implements FeatureHighlightCallbackProvider
{
    public static interface FeatureHighlightFragmentSubcomponent
        extends FragmentInjector
    {
    }


    private static final Logger logger = new Logger();
    private FeatureHighlightController controller;
    public FeatureHighlightControllerFactory controllerFactory;
    public FeatureHighlightViewFinderFactory featureHighlightViewFinderFactory;
    public boolean savedState;
    private boolean showing;

    public FeatureHighlightFragment()
    {
        controller = null;
    }

    public final FeatureHighlightCallback getFeatureHighlightCallback(String s)
    {
        if (controller != null)
        {
            return controller.callback;
        } else
        {
            return null;
        }
    }

    public final void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            ((FragmentInjector)((Provider)GrowthKit.get(context).internalFragmentInjectors().get(com/google/android/libraries/internal/growth/growthkit/internal/ui/impl/taptarget/FeatureHighlightFragment)).get()).inject(this);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            logger.w(context, "Failed to inject members.", new Object[0]);
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        boolean flag;
        if (bundle != null && bundle.getBoolean("showing"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        showing = flag;
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        return layoutinflater.inflate(0x7f050063, viewgroup);
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("showing", showing);
        savedState = true;
    }

    public final void onStart()
    {
        Object obj;
        super.onStart();
        obj = getArguments();
        Object obj1;
        ((Bundle) (obj)).setClassLoader(com/google/android/libraries/internal/growth/growthkit/internal/common/PromoContext.getClassLoader());
        obj1 = (PromoContext)((Bundle) (obj)).getParcelable("promoContext");
        FeatureHighlightControllerFactory featurehighlightcontrollerfactory = controllerFactory;
        controller = new FeatureHighlightController((PromoContext)FeatureHighlightControllerFactory.checkNotNull(obj1, 1), (UserActionUtil)FeatureHighlightControllerFactory.checkNotNull((UserActionUtil)featurehighlightcontrollerfactory.userActionUtilProvider.get(), 2));
        obj1 = controller;
        if (!(this instanceof FeatureHighlightFragment)) goto _L2; else goto _L1
_L1:
        obj1.featureHighlightFragment = (FeatureHighlightFragment)this;
        obj1.callback = new FeatureHighlightController._cls1(((FeatureHighlightController) (obj1)));
_L38:
        if (showing) goto _L4; else goto _L3
_L3:
        obj1 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
        ExtensionRegistryLite extensionregistrylite = ExtensionRegistryLite.getEmptyRegistry();
        obj = (com.google.identity.growth.proto.Promotion.PromoUi)((com.google.protobuf.contrib.android.ProtoParsers.InternalDontUse)((Bundle) (obj)).getParcelable("promoui")).getMessageUnsafe(((MessageLite) (obj1)).getDefaultInstanceForType(), extensionregistrylite);
        if (((com.google.identity.growth.proto.Promotion.PromoUi) (obj)).uiTemplateCase_ != 3) goto _L6; else goto _L5
_L5:
        obj = (com.google.identity.growth.proto.Promotion.TapTargetUi)((com.google.identity.growth.proto.Promotion.PromoUi) (obj)).uiTemplate_;
_L39:
        Object obj4;
        obj4 = new com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder(featureHighlightViewFinderFactory.create(((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj))));
        obj4.headerText = ((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).headlineText_;
        obj4.bodyText = ((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).bodyText_;
        if ((((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).bitField0_ & 8) != 8) goto _L8; else goto _L7
_L7:
        if (((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).backgroundColor_ != null) goto _L10; else goto _L9
_L9:
        obj1 = Color.DEFAULT_INSTANCE;
_L40:
        obj4.outerColor = UiUtils.protoColorToArgbInt(((Color) (obj1)));
        if (!((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).overrideElementColor_) goto _L8; else goto _L11
_L11:
        if (((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).backgroundColor_ != null) goto _L13; else goto _L12
_L12:
        obj1 = Color.DEFAULT_INSTANCE;
_L41:
        obj4.targetViewTintColor = UiUtils.protoColorToArgbInt(((Color) (obj1)));
_L8:
        if ((((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).bitField0_ & 0x10) != 16) goto _L15; else goto _L14
_L14:
        if (((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).innerColor_ != null) goto _L17; else goto _L16
_L16:
        obj1 = Color.DEFAULT_INSTANCE;
_L42:
        obj4.innerColor = UiUtils.protoColorToArgbInt(((Color) (obj1)));
_L15:
        if ((((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).bitField0_ & 0x20) != 32) goto _L19; else goto _L18
_L18:
        if (((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).textColor_ != null) goto _L21; else goto _L20
_L20:
        obj1 = Color.DEFAULT_INSTANCE;
_L43:
        UiUtils.protoColorToArgbInt(((Color) (obj1)));
        JVM INSTR lookupswitch 2: default 1465
    //                   -16777216: 639
    //                   -1: 658;
           goto _L19 _L22 _L23
_L19:
        if ((((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).bitField0_ & 0x200) != 512) goto _L25; else goto _L24
_L24:
        if (((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).action_ != null) goto _L27; else goto _L26
_L26:
        obj1 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.DEFAULT_INSTANCE;
_L44:
        Object obj3 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.forNumber(((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).actionType_);
        Object obj2;
        obj2 = obj3;
        if (obj3 != null)
        {
            break MISSING_BLOCK_LABEL_382;
        }
        obj2 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.ACTION_UNKNOWN;
        if (!((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType) (obj2)).equals(com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.ACTION_DISMISS)) goto _L25; else goto _L28
_L28:
        obj4.dismissActionText = ((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).buttonText_;
        Object obj5;
        int i;
        if ((((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).bitField0_ & 4) == 4)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L30; else goto _L29
_L29:
        if ((((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).bitField0_ & 0x20) != 32) goto _L25; else goto _L30
_L30:
        if ((((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).bitField0_ & 4) != 4) goto _L32; else goto _L31
_L31:
        if (((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).textColor_ != null) goto _L34; else goto _L33
_L33:
        obj = Color.DEFAULT_INSTANCE;
_L45:
        i = UiUtils.protoColorToArgbInt(((Color) (obj)));
          goto _L35
_L25:
        obj5 = new FeatureHighlight(((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).targetViewFinder, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).targetViewTintColor, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).confiningViewId, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).headerText, 0, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).headerTextAppearance, 0, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).bodyText, 0, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).bodyTextAppearance, 0, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).dismissActionText, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).dismissActionTextAppearance, 0, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).outerColor, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).innerColor, 0, 0, 0, null, null, 0, 0, 0, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).taskCompleteOnTap, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).durationInMillis, false, ((com.google.android.libraries.material.featurehighlight.FeatureHighlight.Builder) (obj4)).swipeToDismissEnabled, 0, null);
        if (this != null) goto _L37; else goto _L36
_L36:
        try
        {
            throw new NullPointerException();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            logger.w(((Throwable) (obj)), "Failed to parse proto from arguments", new Object[0]);
        }
_L4:
        return;
_L2:
        FeatureHighlightController.logger.e("FeatureHighlightController instantiated with wrong promo component", new Object[0]);
          goto _L38
_L6:
        obj = com.google.identity.growth.proto.Promotion.TapTargetUi.DEFAULT_INSTANCE;
          goto _L39
_L10:
        obj1 = ((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).backgroundColor_;
          goto _L40
_L13:
        obj1 = ((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).backgroundColor_;
          goto _L41
_L17:
        obj1 = ((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).innerColor_;
          goto _L42
_L21:
        obj1 = ((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).textColor_;
          goto _L43
_L22:
        obj4.headerTextAppearance = 0x7f1403f2;
        obj4.bodyTextAppearance = 0x7f1403f2;
          goto _L19
_L23:
        obj4.headerTextAppearance = 0x7f1403f3;
        obj4.bodyTextAppearance = 0x7f1403f3;
          goto _L19
_L27:
        obj1 = ((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).action_;
          goto _L44
_L34:
        obj = ((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).textColor_;
          goto _L45
_L32:
        if (((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).textColor_ != null) goto _L47; else goto _L46
_L46:
        obj = Color.DEFAULT_INSTANCE;
_L48:
        i = UiUtils.protoColorToArgbInt(((Color) (obj)));
          goto _L35
_L47:
        obj = ((com.google.identity.growth.proto.Promotion.TapTargetUi) (obj)).textColor_;
          goto _L48
_L57:
        obj4.dismissActionTextAppearance = 0x7f1403f2;
          goto _L25
_L58:
        obj4.dismissActionTextAppearance = 0x7f1403f3;
          goto _L25
_L37:
        if (super.mHost != null && super.mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L50; else goto _L49
_L49:
        if (!super.mRemoving) goto _L51; else goto _L50
_L50:
        showing = true;
        return;
_L51:
        obj = ((FeatureHighlight) (obj5)).viewFinder;
        i = ((FeatureHighlight) (obj5)).targetViewTintColor;
        int j = ((FeatureHighlight) (obj5)).confiningViewId;
        obj1 = ((FeatureHighlight) (obj5)).headerText;
        int k = ((FeatureHighlight) (obj5)).headerTextSizeRes;
        int l = ((FeatureHighlight) (obj5)).headerTextAppearance;
        int i1 = ((FeatureHighlight) (obj5)).headerTextAlignment;
        obj2 = ((FeatureHighlight) (obj5)).bodyText;
        int j1 = ((FeatureHighlight) (obj5)).bodyTextSizeRes;
        int k1 = ((FeatureHighlight) (obj5)).bodyTextAppearance;
        int l1 = ((FeatureHighlight) (obj5)).bodyTextAlignment;
        obj3 = ((FeatureHighlight) (obj5)).dismissActionText;
        int i2 = ((FeatureHighlight) (obj5)).dismissActionTextAppearance;
        int j2 = ((FeatureHighlight) (obj5)).dismissActionTextAlignment;
        int k2 = ((FeatureHighlight) (obj5)).outerColor;
        int l2 = ((FeatureHighlight) (obj5)).innerColor;
        int i3 = ((FeatureHighlight) (obj5)).targetTextColor;
        int j3 = ((FeatureHighlight) (obj5)).targetDrawableId;
        int k3 = ((FeatureHighlight) (obj5)).targetDrawableColor;
        obj4 = ((FeatureHighlight) (obj5)).callbackId;
        String s = ((FeatureHighlight) (obj5)).taskTag;
        int l3 = ((FeatureHighlight) (obj5)).verticalOffsetRes;
        int i4 = ((FeatureHighlight) (obj5)).horizontalOffsetRes;
        int j4 = ((FeatureHighlight) (obj5)).centerThresholdRes;
        boolean flag = ((FeatureHighlight) (obj5)).taskCompleteOnTap;
        long l4 = ((FeatureHighlight) (obj5)).durationInMillis;
        boolean flag1 = ((FeatureHighlight) (obj5)).pinToClosestVerticalEdge;
        boolean flag2 = ((FeatureHighlight) (obj5)).swipeToDismissEnabled;
        int k4 = ((FeatureHighlight) (obj5)).textVerticalGravityHint;
        CharSequence charsequence = ((FeatureHighlight) (obj5)).contentDescription;
        obj5 = new Bundle();
        ((Bundle) (obj5)).putParcelable("fh_view_finder", ((android.os.Parcelable) (obj)));
        ((Bundle) (obj5)).putInt("fh_target_view_tint_color", i);
        ((Bundle) (obj5)).putInt("fh_confining_view_id", j);
        ((Bundle) (obj5)).putCharSequence("fh_header_text", ((CharSequence) (obj1)));
        ((Bundle) (obj5)).putInt("fh_header_text_size_res", k);
        ((Bundle) (obj5)).putInt("fh_header_text_appearance", l);
        ((Bundle) (obj5)).putInt("fh_header_text_alignment", i1);
        ((Bundle) (obj5)).putCharSequence("fh_body_text", ((CharSequence) (obj2)));
        ((Bundle) (obj5)).putInt("fh_body_text_size_res", j1);
        ((Bundle) (obj5)).putInt("fh_body_text_appearance", k1);
        ((Bundle) (obj5)).putInt("fh_body_text_alignment", l1);
        ((Bundle) (obj5)).putCharSequence("fh_dismiss_action_text", ((CharSequence) (obj3)));
        ((Bundle) (obj5)).putInt("fh_dismiss_action_text_appearance", i2);
        ((Bundle) (obj5)).putInt("fh_dismiss_action_text_alignment", j2);
        ((Bundle) (obj5)).putInt("fh_outer_color", k2);
        ((Bundle) (obj5)).putInt("fh_inner_color", l2);
        ((Bundle) (obj5)).putInt("fh_target_text_color", i3);
        ((Bundle) (obj5)).putInt("fh_target_drawable", j3);
        ((Bundle) (obj5)).putInt("fh_target_drawable_color", k3);
        ((Bundle) (obj5)).putString("fh_callback_id", ((String) (obj4)));
        ((Bundle) (obj5)).putString("fh_task_tag", s);
        ((Bundle) (obj5)).putInt("fh_vertical_offset_res", l3);
        ((Bundle) (obj5)).putInt("fh_horizontal_offset_res", i4);
        ((Bundle) (obj5)).putInt("fh_center_threshold_res", j4);
        ((Bundle) (obj5)).putBoolean("fh_task_complete_on_tap", flag);
        ((Bundle) (obj5)).putLong("fh_duration", l4);
        ((Bundle) (obj5)).putBoolean("fh_pin_to_closest_vertical_edge", flag1);
        ((Bundle) (obj5)).putBoolean("fh_swipe_to_dismiss_enabled", flag2);
        ((Bundle) (obj5)).putInt("fh_text_vertical_gravity_hint", k4);
        ((Bundle) (obj5)).putCharSequence("fh_content_description", charsequence);
        obj1 = new com.google.android.libraries.material.featurehighlight.FeatureHighlightFragment();
        ((Fragment) (obj1)).setArguments(((Bundle) (obj5)));
        if (super.mHost != null)
        {
            break MISSING_BLOCK_LABEL_1364;
        }
        obj = null;
_L53:
        obj2 = getChildFragmentManager();
        if (((Fragment) (obj1)).mHost != null && ((Fragment) (obj1)).mAdded)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L50; else goto _L52
_L52:
        obj1.showState = 1;
        obj3 = ((FragmentManager) (obj2)).beginTransaction();
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_1378;
        }
        throw new NullPointerException();
        obj = (FragmentActivity)super.mHost.mActivity;
          goto _L53
        obj = ((FragmentActivity) (obj)).findViewById(0x7f100019);
        if (!(obj instanceof FeatureHighlightView)) goto _L55; else goto _L54
_L54:
        obj = (com.google.android.libraries.material.featurehighlight.FeatureHighlightFragment)((View) (obj)).getTag(0x7f10001a);
_L59:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_1427;
        }
        obj4 = ((Fragment) (obj)).mFragmentManager;
        if (obj4 != obj2)
        {
            break MISSING_BLOCK_LABEL_1443;
        }
        ((FragmentTransaction) (obj3)).remove(((Fragment) (obj)));
_L56:
        ((FragmentTransaction) (obj3)).add(((Fragment) (obj1)), "com.google.android.libraries.material.featurehighlight.FeatureHighlightFragment").commitAllowingStateLoss();
          goto _L50
        ((FragmentManager) (obj4)).beginTransaction().remove(((Fragment) (obj))).commit();
        ((FragmentManager) (obj4)).executePendingTransactions();
          goto _L56
_L35:
        i;
        JVM INSTR lookupswitch 2: default 1496
    //                   -16777216: 721
    //                   -1: 732;
           goto _L25 _L57 _L58
_L55:
        obj = null;
          goto _L59
    }

}
