// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.support.design.chip.Chip;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.calendar.utils.animation.QuantumInterpolators;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            BottomBar

public class SmartRsvpBottomBar extends BottomBar
{
    public static interface RemoveProposalListener
    {

        public abstract void removeTimeProposal();
    }

    public static final class RsvpBottomBarMode extends Enum
    {

        private static final RsvpBottomBarMode $VALUES[];
        public static final RsvpBottomBarMode FOLLOW_UP_WITHOUT_PROPOSAL;
        public static final RsvpBottomBarMode FOLLOW_UP_WITH_PROPOSAL;
        public static final RsvpBottomBarMode RSVP_WITHOUT_TOGGLE;
        public static final RsvpBottomBarMode RSVP_WITH_PROPOSAL;
        public static final RsvpBottomBarMode RSVP_WITH_TOGGLE;
        public final boolean canHaveFollowUps;
        public final boolean hasProposal;
        public final boolean isExpanded;

        public static RsvpBottomBarMode[] values()
        {
            return (RsvpBottomBarMode[])$VALUES.clone();
        }

        static 
        {
            RSVP_WITHOUT_TOGGLE = new RsvpBottomBarMode("RSVP_WITHOUT_TOGGLE", 0, false, false, false);
            RSVP_WITH_TOGGLE = new RsvpBottomBarMode("RSVP_WITH_TOGGLE", 1, true, false, false);
            RSVP_WITH_PROPOSAL = new RsvpBottomBarMode("RSVP_WITH_PROPOSAL", 2, true, false, true);
            FOLLOW_UP_WITHOUT_PROPOSAL = new RsvpBottomBarMode("FOLLOW_UP_WITHOUT_PROPOSAL", 3, true, true, false);
            FOLLOW_UP_WITH_PROPOSAL = new RsvpBottomBarMode("FOLLOW_UP_WITH_PROPOSAL", 4, true, true, true);
            $VALUES = (new RsvpBottomBarMode[] {
                RSVP_WITHOUT_TOGGLE, RSVP_WITH_TOGGLE, RSVP_WITH_PROPOSAL, FOLLOW_UP_WITHOUT_PROPOSAL, FOLLOW_UP_WITH_PROPOSAL
            });
        }

        private RsvpBottomBarMode(String s, int i, boolean flag, boolean flag1, boolean flag2)
        {
            super(s, i);
            canHaveFollowUps = flag;
            hasProposal = flag2;
            isExpanded = flag1;
        }
    }


    private ValueAnimator animator;
    public RsvpBottomBarMode currentMode;
    public Chip proposeNewTimeChip;
    public RemoveProposalListener removeProposalListener;
    public View toggle;

    public SmartRsvpBottomBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private final ValueAnimator createAnimation(RsvpBottomBarMode rsvpbottombarmode)
    {
        if (getHeight() == 0)
        {
            measure(0, 0);
        }
        if (animator != null && animator.isStarted())
        {
            animator.cancel();
        }
        int j = getMeasuredHeight();
        int k = (int)((View)getParent()).getTranslationY();
        int i;
        if (rsvpbottombarmode.isExpanded)
        {
            i = getMeasuredHeight();
        } else
        {
            i = getResources().getDimensionPixelSize(0x7f0e0368);
            i = getMeasuredHeight() - i;
        }
        animator = ValueAnimator.ofInt(new int[] {
            j - k, i
        });
        animator.addUpdateListener(new BottomBar.AnimationListener(this));
        return animator;
    }

    public final ValueAnimator createEnterAnimation()
    {
        if (getHeight() == 0)
        {
            measure(0, 0);
        }
        int i = getMeasuredHeight();
        ((View)getParent()).setTranslationY(i - 0);
        super.onHeightChangedListener.onHeightChanged();
        return createAnimation(currentMode);
    }

    protected final int getActionContainerResId()
    {
        return 0x7f100254;
    }

    protected final int getContainerResId()
    {
        return 0x7f100254;
    }

    protected final void initialize(int i, int ai[])
    {
        super.initialize(i, ai);
        proposeNewTimeChip = (Chip)findViewById(0x7f10035f);
        proposeNewTimeChip.setOnClickListener(this);
        toggle = findViewById(0x7f100300);
        ViewCompat.setAccessibilityDelegate(toggle, new _cls1());
        class .Lambda._cls0
            implements android.view.View.OnClickListener
        {

            private final SmartRsvpBottomBar arg$1;

            public final void onClick(View view)
            {
                view = arg$1;
                ((SmartRsvpBottomBar) (view)).currentMode.ordinal();
                JVM INSTR tableswitch 1 4: default 44
            //                           1 65
            //                           2 75
            //                           3 95
            //                           4 85;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                AnalyticsLogger analyticslogger;
                analyticslogger = AnalyticsLoggerHolder.instance;
                if (analyticslogger == null)
                {
                    throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
                }
                break; /* Loop/switch isn't completed */
_L2:
                view.moveBottomBar(RsvpBottomBarMode.FOLLOW_UP_WITHOUT_PROPOSAL);
                continue; /* Loop/switch isn't completed */
_L3:
                view.moveBottomBar(RsvpBottomBarMode.FOLLOW_UP_WITH_PROPOSAL);
                continue; /* Loop/switch isn't completed */
_L5:
                view.moveBottomBar(RsvpBottomBarMode.RSVP_WITH_PROPOSAL);
                continue; /* Loop/switch isn't completed */
_L4:
                view.moveBottomBar(RsvpBottomBarMode.RSVP_WITH_TOGGLE);
                if (true) goto _L1; else goto _L6
_L6:
                analyticslogger = (AnalyticsLogger)analyticslogger;
                Context context = view.getContext();
                if (((SmartRsvpBottomBar) (view)).currentMode.isExpanded)
                {
                    view = "expanded";
                } else
                {
                    view = "collapsed";
                }
                analyticslogger.trackEvent(context, "event_action", "tap_footer_more_options", view, Long.valueOf(0L));
                return;
            }

            .Lambda._cls0()
            {
                arg$1 = SmartRsvpBottomBar.this;
            }
        }

        toggle.setOnClickListener(new .Lambda._cls0());
        currentMode = RsvpBottomBarMode.RSVP_WITHOUT_TOGGLE;
    }

    public final void moveBottomBar(RsvpBottomBarMode rsvpbottombarmode)
    {
        if (!animationsEnabled || getVisibility() != 0)
        {
            int i;
            int j;
            if (rsvpbottombarmode.isExpanded)
            {
                i = getMeasuredHeight();
            } else
            {
                i = getResources().getDimensionPixelSize(0x7f0e0368);
                i = getMeasuredHeight() - i;
            }
            j = getMeasuredHeight();
            ((View)getParent()).setTranslationY(j - i);
            super.onHeightChangedListener.onHeightChanged();
        } else
        {
            createAnimation(rsvpbottombarmode);
            animator.setInterpolator(QuantumInterpolators.FAST_OUT_SLOW_IN);
            animator.setDuration(getResources().getInteger(0x10e0002));
            animator.start();
        }
        currentMode = rsvpbottombarmode;
    }

    private class _cls1 extends AccessibilityDelegateCompat
    {

        private final SmartRsvpBottomBar this$0;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            if (currentMode.isExpanded)
            {
                view = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE;
            } else
            {
                view = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND;
            }
            accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) (view)).mAction);
        }

        _cls1()
        {
            this$0 = SmartRsvpBottomBar.this;
            super();
        }
    }

}
