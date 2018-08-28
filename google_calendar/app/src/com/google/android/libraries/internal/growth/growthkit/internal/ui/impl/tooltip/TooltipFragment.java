// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.libraries.internal.growth.growthkit.inject.FragmentInjector;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKit;
import com.google.android.libraries.internal.growth.growthkit.inject.GrowthKitComponent;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UiUtils;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;
import com.google.common.base.Optional;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.type.Color;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            TooltipViewFinder, TooltipManager, TooltipModel, Tooltip, 
//            ViewPositionTracker, ViewPosition

public class TooltipFragment extends Fragment
{
    public static interface TooltipFragmentSubcomponent
        extends FragmentInjector
    {
    }


    private static final Logger logger = new Logger();
    private boolean isBeingRestored;
    public PromoContext logContext;
    private boolean savedState;
    private boolean showing;
    private TooltipManager tooltipManager;
    public TooltipViewFinder tooltipViewFinder;
    public UserActionUtil userActionUtil;
    public boolean userDismissed;
    public boolean userTouched;

    public TooltipFragment()
    {
        showing = false;
        isBeingRestored = false;
        userDismissed = true;
        userTouched = false;
    }

    final void init()
    {
        Object obj = getArguments();
        ((Bundle) (obj)).setClassLoader(com/google/android/libraries/internal/growth/growthkit/internal/common/PromoContext.getClassLoader());
        logContext = (PromoContext)((Bundle) (obj)).getParcelable("promoContext");
        com.google.identity.growth.proto.Promotion.PromoUi promoui = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
        ExtensionRegistryLite extensionregistrylite = ExtensionRegistryLite.getEmptyRegistry();
        obj = (com.google.identity.growth.proto.Promotion.PromoUi)((com.google.protobuf.contrib.android.ProtoParsers.InternalDontUse)((Bundle) (obj)).getParcelable("promoui")).getMessageUnsafe(promoui.getDefaultInstanceForType(), extensionregistrylite);
        if (((com.google.identity.growth.proto.Promotion.PromoUi) (obj)).uiTemplateCase_ != 5) goto _L2; else goto _L1
_L1:
        obj = (com.google.identity.growth.proto.Promotion.TooltipUi)((com.google.identity.growth.proto.Promotion.PromoUi) (obj)).uiTemplate_;
_L3:
        Object obj2;
        obj2 = tooltipViewFinder;
        if (super.mHost != null)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        Object obj1 = null;
_L4:
        obj1 = ((TooltipViewFinder) (obj2)).findView(((FragmentActivity) (obj1)), ((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)));
        Object obj3;
        Object obj4;
        Optional optional;
        android.view.View.OnClickListener onclicklistener;
        int i;
        boolean flag;
        if (obj1 == null)
        {
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final TooltipFragment arg$1;
                private final com.google.identity.growth.proto.Promotion.TooltipUi arg$2;

                public final void onClick(View view)
                {
                    Object obj5 = arg$1;
                    view = arg$2;
                    obj5.userDismissed = false;
                    UserActionUtil useractionutil = ((TooltipFragment) (obj5)).userActionUtil;
                    obj5 = ((TooltipFragment) (obj5)).logContext;
                    if (((com.google.identity.growth.proto.Promotion.TooltipUi) (view)).action_ == null)
                    {
                        view = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.DEFAULT_INSTANCE;
                    } else
                    {
                        view = ((com.google.identity.growth.proto.Promotion.TooltipUi) (view)).action_;
                    }
                    useractionutil.persistUserChoice(((PromoContext) (obj5)), UserActionUtil.getUserActionFromAction(view));
                }

            .Lambda._cls0(com.google.identity.growth.proto.Promotion.TooltipUi tooltipui)
            {
                arg$1 = TooltipFragment.this;
                arg$2 = tooltipui;
            }
            }

            class .Lambda._cls1
                implements TooltipModel.OnDismissListener
            {

                private final TooltipFragment arg$1;

                public final void onDismiss()
                {
                    TooltipFragment tooltipfragment = arg$1;
                    if (tooltipfragment.userTouched && tooltipfragment.userDismissed)
                    {
                        tooltipfragment.userActionUtil.persistUserChoice(tooltipfragment.logContext, com.google.identity.growth.proto.CampaignManagement.UserAction.DISMISSED);
                    }
                    tooltipfragment.removeFragment();
                }

            .Lambda._cls1()
            {
                arg$1 = TooltipFragment.this;
            }
            }

            class .Lambda._cls2
                implements android.view.View.OnClickListener
            {

                private final TooltipFragment arg$1;

                public final void onClick(View view)
                {
                    arg$1.userTouched = true;
                }

            .Lambda._cls2()
            {
                arg$1 = TooltipFragment.this;
            }
            }

            class .Lambda._cls3
                implements android.view.View.OnClickListener
            {

                private final TooltipFragment arg$1;

                public final void onClick(View view)
                {
                    view = arg$1;
                    view.userDismissed = false;
                    ((TooltipFragment) (view)).userActionUtil.persistUserChoice(((TooltipFragment) (view)).logContext, com.google.identity.growth.proto.CampaignManagement.UserAction.POSITIVE_RESPONSE);
                }

            .Lambda._cls3()
            {
                arg$1 = TooltipFragment.this;
            }
            }

            float f;
            try
            {
                removeFragment();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                logger.w(((Throwable) (obj)), "Failed to parse proto from arguments", new Object[0]);
            }
            return;
        }
        break MISSING_BLOCK_LABEL_129;
_L2:
        obj = com.google.identity.growth.proto.Promotion.TooltipUi.DEFAULT_INSTANCE;
          goto _L3
        obj1 = (FragmentActivity)super.mHost.mActivity;
          goto _L4
        obj3 = (new AutoValue_TooltipModel.Builder()).setTargetView(((View) (obj1))).setTitleText(((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).headlineText_).setDetailText(((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).bodyText_).setAlignment(TooltipModel.Alignment.CENTER);
        obj2 = com.google.identity.growth.proto.Promotion.TooltipUi.Placement.forNumber(((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).placement_);
        obj1 = obj2;
        if (obj2 != null)
        {
            break MISSING_BLOCK_LABEL_183;
        }
        obj1 = com.google.identity.growth.proto.Promotion.TooltipUi.Placement.UNKNOWN;
        ((com.google.identity.growth.proto.Promotion.TooltipUi.Placement) (obj1)).ordinal();
        JVM INSTR tableswitch 1 1: default 1414
    //                   1 1296;
           goto _L5 _L6
_L5:
        obj1 = TooltipModel.Placement.BELOW;
_L32:
        obj2 = ((TooltipModel.Builder) (obj3)).setPlacement(((TooltipModel.Placement) (obj1))).setMaxWidthPercentage(0.95F).setTapDismissalType(TooltipModel.TapDismissalType.ANYWHERE);
        if ((((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).bitField0_ & 8) != 8) goto _L8; else goto _L7
_L7:
        if (((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).backgroundColor_ != null) goto _L10; else goto _L9
_L9:
        obj1 = Color.DEFAULT_INSTANCE;
_L33:
        ((TooltipModel.Builder) (obj2)).setBackgroundColor(Integer.valueOf(UiUtils.protoColorToArgbInt(((Color) (obj1)))));
_L8:
        if ((((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).bitField0_ & 0x10) != 16) goto _L12; else goto _L11
_L11:
        if (((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).textColor_ != null) goto _L14; else goto _L13
_L13:
        obj1 = Color.DEFAULT_INSTANCE;
_L34:
        ((TooltipModel.Builder) (obj2)).setTextColor(Integer.valueOf(UiUtils.protoColorToArgbInt(((Color) (obj1)))));
_L12:
        if ((((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).bitField0_ & 0x80) != 128) goto _L16; else goto _L15
_L15:
        if (((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).action_ != null) goto _L18; else goto _L17
_L17:
        obj1 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.DEFAULT_INSTANCE;
_L35:
        ((TooltipModel.Builder) (obj2)).setActionText(((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).buttonText_);
        if (((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).action_ != null) goto _L20; else goto _L19
_L19:
        obj1 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.DEFAULT_INSTANCE;
_L36:
        if ((((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).bitField0_ & 4) != 4) goto _L22; else goto _L21
_L21:
        if (((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).action_ != null) goto _L24; else goto _L23
_L23:
        obj1 = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.DEFAULT_INSTANCE;
_L37:
        if (((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).textColor_ != null) goto _L26; else goto _L25
_L25:
        obj1 = Color.DEFAULT_INSTANCE;
_L38:
        ((TooltipModel.Builder) (obj2)).setActionTextColor(Integer.valueOf(UiUtils.protoColorToArgbInt(((Color) (obj1)))));
_L22:
        ((TooltipModel.Builder) (obj2)).setActionListener(new .Lambda._cls0(((com.google.identity.growth.proto.Promotion.TooltipUi) (obj))));
_L16:
        ((TooltipModel.Builder) (obj2)).setDismissListener(new .Lambda._cls1());
        ((TooltipModel.Builder) (obj2)).setUserClickedListener(new .Lambda._cls2());
        ((TooltipModel.Builder) (obj2)).setTargetViewClickListener(new .Lambda._cls3());
        tooltipManager = new TooltipManager(((TooltipModel.Builder) (obj2)).build());
        obj = tooltipManager;
        obj1 = ((TooltipManager) (obj)).model.targetView();
        obj.viewTreeRoot = ((View) (obj1)).getRootView();
        obj2 = View.inflate(((View) (obj1)).getContext(), 0x7f050089, null);
        obj4 = (TextView)((View) (obj2)).findViewById(0x7f1001fa);
        obj3 = (TextView)((View) (obj2)).findViewById(0x7f1001fb);
        if (((TooltipManager) (obj)).model.textColor().isPresent())
        {
            ((TextView) (obj4)).setTextColor(((Integer)((TooltipManager) (obj)).model.textColor().get()).intValue());
            ((TextView) (obj3)).setTextColor(((Integer)((TooltipManager) (obj)).model.textColor().get()).intValue());
        }
        UiUtils.setTextAndToggleVisibility(((TextView) (obj4)), ((TooltipManager) (obj)).model.titleText());
        UiUtils.setTextAndToggleVisibility(((TextView) (obj3)), ((TooltipManager) (obj)).model.detailText());
        if (((TextView) (obj4)).getVisibility() == 8)
        {
            obj4 = (android.view.ViewGroup.MarginLayoutParams)((TextView) (obj3)).getLayoutParams();
            obj4.topMargin = 0;
            ((TextView) (obj3)).setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj4)));
        }
        obj.tooltip = new Tooltip(((View) (obj2)), ((View) (obj1)), ((TooltipManager) (obj)).model.placement(), ((TooltipManager) (obj)).model.alignment());
        if (((TooltipManager) (obj)).model.backgroundColor().isPresent())
        {
            obj3 = ((TooltipManager) (obj)).tooltip;
            i = ((Integer)((TooltipManager) (obj)).model.backgroundColor().get()).intValue();
            ((Tooltip) (obj3)).tooltipView.setContainerBackgroundColor(i);
        }
        obj2 = (TextView)((View) (obj2)).findViewById(0x7f1001fc);
        obj3 = ((TooltipManager) (obj)).tooltip;
        obj4 = ((TooltipManager) (obj)).model.actionText();
        optional = ((TooltipManager) (obj)).model.actionTextColor();
        onclicklistener = ((TooltipManager) (obj)).model.actionListener();
        if (!TextUtils.isEmpty(((CharSequence) (obj4)))) goto _L28; else goto _L27
_L27:
        ((TextView) (obj2)).setVisibility(8);
        i = 0;
_L39:
        obj2 = ((TooltipManager) (obj)).tooltip;
        if (((TooltipManager) (obj)).model.tapDismissalType() == TooltipModel.TapDismissalType.ANYWHERE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ((Tooltip) (obj2)).tooltipView.dismissWhenTouchedOutside = flag;
        obj2 = ((TooltipManager) (obj)).tooltip;
        f = ((TooltipManager) (obj)).model.maxWidthPercentage();
        ((Tooltip) (obj2)).tooltipView.setSuggestedMaxWidthPercentage(f);
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_860;
        }
        obj2 = ((TooltipManager) (obj)).tooltip;
        obj3 = new TooltipManager..Lambda._cls0(((TooltipManager) (obj)));
        ((Tooltip) (obj2)).tooltipView.setOnClickListener(((android.view.View.OnClickListener) (obj3)));
        obj3 = ((TooltipManager) (obj)).tooltip;
        obj2 = new TooltipManager..Lambda._cls1(((TooltipManager) (obj)));
        obj3 = ((Tooltip) (obj3)).tooltipView;
        if (((Tooltip.TooltipView) (obj3)).popupWindow != null)
        {
            ((Tooltip.TooltipView) (obj3)).popupWindow.setOnDismissListener(((android.widget.PopupWindow.OnDismissListener) (obj2)));
        }
        obj2 = ((TooltipManager) (obj)).tooltip;
        obj3 = new TooltipManager..Lambda._cls2(((TooltipManager) (obj)));
        ((Tooltip) (obj2)).tooltipView.userClickedListener = ((android.view.View.OnClickListener) (obj3));
        obj2 = ((TooltipManager) (obj)).tooltip;
        obj3 = ((TooltipManager) (obj)).model.targetViewClickListener();
        ((Tooltip) (obj2)).tooltipView.targetViewOnClickListener = ((android.view.View.OnClickListener) (obj3));
        obj.targetViewTracker = new ViewPositionTracker(((TooltipManager) (obj)).viewTreeRoot);
        ((TooltipManager) (obj)).targetViewTracker.onViewPositionChangedListener = ((ViewPositionTracker.OnViewPositionChangedListener) (obj));
        ((TooltipManager) (obj)).targetViewTracker.onTrackingViewChangedListener = ((ViewPositionTracker.OnTrackingViewChangedListener) (obj));
        obj2 = ((TooltipManager) (obj)).targetViewTracker;
        if (((ViewPositionTracker) (obj2)).trackingViewReference == null)
        {
            break MISSING_BLOCK_LABEL_1423;
        }
        obj = (View)((ViewPositionTracker) (obj2)).trackingViewReference.get();
_L41:
        if (obj1 == obj) goto _L30; else goto _L29
_L29:
        obj2.trackingViewReference = new WeakReference(obj1);
        if (((ViewPositionTracker) (obj2)).onTrackingViewChangedListener != null)
        {
            ((ViewPositionTracker) (obj2)).onTrackingViewChangedListener.onTrackingViewChanged(((View) (obj1)));
        }
        obj = ((ViewPositionTracker) (obj2)).containerView.getViewTreeObserver();
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1082;
        }
        if (!((ViewPositionTracker) (obj2)).isPreDrawListenerAdded)
        {
            ((ViewTreeObserver) (obj)).addOnPreDrawListener(((ViewPositionTracker) (obj2)).preDrawListener);
            obj2.isPreDrawListenerAdded = true;
        }
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_1109;
        }
        if (((ViewPositionTracker) (obj2)).isPreDrawListenerAdded)
        {
            ((ViewTreeObserver) (obj)).removeOnPreDrawListener(((ViewPositionTracker) (obj2)).preDrawListener);
            obj2.isPreDrawListenerAdded = false;
        }
        if (obj1 != null) goto _L30; else goto _L31
_L31:
        obj = ((ViewPositionTracker) (obj2)).currentViewPosition;
        i = Math.max(((ViewPosition) (obj)).viewRect.left, ((ViewPosition) (obj)).clipRect.left);
        if (Math.max(Math.min(((ViewPosition) (obj)).viewRect.right, ((ViewPosition) (obj)).clipRect.right) - i, 0) <= 0)
        {
            break MISSING_BLOCK_LABEL_1428;
        }
        i = Math.max(((ViewPosition) (obj)).viewRect.top, ((ViewPosition) (obj)).clipRect.top);
        if (Math.max(Math.min(((ViewPosition) (obj)).viewRect.bottom, ((ViewPosition) (obj)).clipRect.bottom) - i, 0) <= 0)
        {
            break MISSING_BLOCK_LABEL_1428;
        }
        for (i = 1; i == 0; i = 0)
        {
            break MISSING_BLOCK_LABEL_1274;
        }

        obj = ((ViewPositionTracker) (obj2)).currentViewPosition;
        ((ViewPosition) (obj)).viewRect.set(0, 0, 0, 0);
        ((ViewPosition) (obj)).clipRect.set(0, 0, 0x3fffffff, 0x3fffffff);
        if (((ViewPositionTracker) (obj2)).onViewPositionChangedListener != null)
        {
            ((ViewPositionTracker) (obj2)).onViewPositionChangedListener.onViewPositionChanged(((ViewPositionTracker) (obj2)).currentViewPosition);
        }
_L40:
        showing = true;
        return;
_L6:
        obj1 = TooltipModel.Placement.ABOVE;
          goto _L32
_L10:
        obj1 = ((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).backgroundColor_;
          goto _L33
_L14:
        obj1 = ((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).textColor_;
          goto _L34
_L18:
        obj1 = ((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).action_;
          goto _L35
_L20:
        obj1 = ((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).action_;
          goto _L36
_L24:
        obj1 = ((com.google.identity.growth.proto.Promotion.TooltipUi) (obj)).action_;
          goto _L37
_L26:
        obj1 = ((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action) (obj1)).textColor_;
          goto _L38
_L28:
        ((TextView) (obj2)).setText(((CharSequence) (obj4)));
        if (optional.isPresent())
        {
            ((TextView) (obj2)).setTextColor(((Integer)optional.get()).intValue());
        }
        ((TextView) (obj2)).setOnClickListener(new TooltipManager..Lambda._cls3(onclicklistener, ((TextView) (obj2)), ((Tooltip) (obj3))));
        i = 1;
          goto _L39
_L30:
        ((ViewPositionTracker) (obj2)).calculateViewPosition();
          goto _L40
        obj = null;
          goto _L41
    }

    public final void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        boolean flag;
        if (bundle != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        isBeingRestored = flag;
        if (isBeingRestored && !showing)
        {
            removeFragment();
        }
    }

    public final void onAttach(Context context)
    {
        super.onAttach(context);
        try
        {
            ((FragmentInjector)((Provider)GrowthKit.get(context).internalFragmentInjectors().get(com/google/android/libraries/internal/growth/growthkit/internal/ui/impl/tooltip/TooltipFragment)).get()).inject(this);
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
        return layoutinflater.inflate(0x7f05008a, viewgroup);
    }

    public final void onDestroy()
    {
        if (tooltipManager != null)
        {
            TooltipManager tooltipmanager = tooltipManager;
            Tooltip tooltip = tooltipmanager.tooltip;
            if (tooltip != null && tooltip.tooltipView.isShown())
            {
                Tooltip.TooltipView tooltipview = tooltip.tooltipView;
                if (tooltipview.popupWindow != null)
                {
                    tooltipview.popupWindow.dismiss();
                }
                if (tooltip == tooltipmanager.tooltip)
                {
                    tooltipmanager.tooltip = null;
                    tooltipmanager.model = null;
                }
            }
            if (!userTouched && !savedState)
            {
                userActionUtil.persistUserChoice(logContext, com.google.identity.growth.proto.CampaignManagement.UserAction.DISMISSED);
            }
        }
        super.onDestroy();
    }

    public final void onResume()
    {
        super.onResume();
        final Object activityRoot;
        if (super.mHost == null)
        {
            activityRoot = null;
        } else
        {
            activityRoot = (FragmentActivity)super.mHost.mActivity;
        }
        activityRoot = ((FragmentActivity) (activityRoot)).findViewById(0x1020002);
        ((View) (activityRoot)).getViewTreeObserver().addOnGlobalLayoutListener(new _cls1());
        ((View) (activityRoot)).requestLayout();
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("showing", showing);
        savedState = true;
    }

    final void removeFragment()
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj != null)
        {
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (!((FragmentActivity) (obj)).isFinishing())
            {
                boolean flag;
                if (super.mHost != null && super.mAdded)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && !super.mRemoving)
                {
                    if ((obj = super.mFragmentManager) != null)
                    {
                        ((FragmentManager) (obj)).beginTransaction().remove(this).commitAllowingStateLoss();
                        return;
                    }
                }
            }
        }
    }


    private class _cls1
        implements android.view.ViewTreeObserver.OnGlobalLayoutListener
    {

        public final TooltipFragment this$0;
        private final View val$activityRoot;

        public final void onGlobalLayout()
        {
            activityRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            class .Lambda._cls0
                implements Runnable
            {

                private final _cls1 arg$1;

                public final void run()
                {
                    arg$1._fld0.init();
                }

                .Lambda._cls0()
                {
                    arg$1 = _cls1.this;
                }
            }

            ViewCompat.postOnAnimation(activityRoot.getRootView(), new .Lambda._cls0());
        }

        _cls1()
        {
            this$0 = TooltipFragment.this;
            activityRoot = view;
            super();
        }
    }

}
