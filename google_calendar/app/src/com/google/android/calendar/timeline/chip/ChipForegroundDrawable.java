// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import com.android.bitmap.RequestKey;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.common.drawable.DefaultableBitmapDrawable;
import com.google.android.calendar.event.image.cache.contactphoto.ContactPhotoCacheHolder;
import com.google.android.calendar.utils.SpanUtils;
import com.google.android.calendar.utils.datatypes.StringBuilderList;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipConfig, ChipViewModel

class ChipForegroundDrawable extends Drawable
{
    static final class PrimaryText
    {

        public final CharSequence text;
        public final ChipViewModel viewModel;
        public final int width;

        static CharSequence formatText(ChipViewModel chipviewmodel, ChipConfig chipconfig, TextPaint textpaint, int i)
        {
            StringBuilderList stringbuilderlist;
            int k;
            k = 1;
            stringbuilderlist = new StringBuilderList(3);
            List list = chipviewmodel.getPrimaryText();
            if (!list.isEmpty()) goto _L2; else goto _L1
_L1:
            Object obj = "";
_L6:
            stringbuilderlist.add(((CharSequence) (obj)));
            if (chipviewmodel.getStrikeThroughText() != 0) goto _L4; else goto _L3
_L3:
            if (stringbuilderlist.size == 0)
            {
                throw new IndexOutOfBoundsException();
            }
              goto _L5
            chipconfig;
            throw chipconfig;
            chipviewmodel;
_L26:
            int j;
            if (chipconfig != null)
            {
                try
                {
                    stringbuilderlist.close();
                }
                // Misplaced declaration of an exception variable
                catch (TextPaint textpaint)
                {
                    ThrowableExtension.STRATEGY.addSuppressed(chipconfig, textpaint);
                }
            } else
            {
                stringbuilderlist.close();
            }
            throw chipviewmodel;
_L2:
            obj = ChipForegroundDrawable.prepInputString((String)list.get(0), chipviewmodel);
              goto _L6
_L5:
            obj = stringbuilderlist.list[stringbuilderlist.size - 1].string;
            chipconfig = chipconfig.boldSpan;
            if (((Spannable) (obj)).length() != 0)
            {
                ((Spannable) (obj)).setSpan(chipconfig, 0, ((Spannable) (obj)).length(), 33);
            }
_L10:
            if (chipviewmodel.getIcon() != 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j == 0) goto _L8; else goto _L7
_L7:
            chipconfig = new android.text.style.LeadingMarginSpan.Standard(chipviewmodel.getPrimaryTextInsetForIcon(), 0);
            if (stringbuilderlist.size == 0)
            {
                throw new IndexOutOfBoundsException();
            }
              goto _L9
_L4:
            if (chipviewmodel.getStrikeThroughText() == 2)
            {
                j = 34;
            } else
            {
                j = 33;
            }
            if (stringbuilderlist.size == 0)
            {
                throw new IndexOutOfBoundsException();
            }
            obj = stringbuilderlist.list[stringbuilderlist.size - 1].string;
            ((Spannable) (obj)).setSpan(chipconfig.strikeThroughSpan, 0, ((Spannable) (obj)).length(), j);
              goto _L10
_L9:
            obj = stringbuilderlist.list[stringbuilderlist.size - 1].string;
            if (((Spannable) (obj)).length() != 0)
            {
                ((Spannable) (obj)).setSpan(chipconfig, 0, ((Spannable) (obj)).length(), 33);
            }
              goto _L8
_L11:
            if (j >= list.size())
            {
                break MISSING_BLOCK_LABEL_356;
            }
            stringbuilderlist.add(ChipForegroundDrawable.prepInputString((String)list.get(j), chipviewmodel));
            j++;
              goto _L11
            chipviewmodel.getEllipsizeType();
            JVM INSTR tableswitch 1 2: default 597
        //                       1 481
        //                       2 496;
               goto _L12 _L13 _L14
_L12:
            chipconfig = new SpannableStringBuilder();
            j = 0;
_L22:
            if (j >= stringbuilderlist.size()) goto _L16; else goto _L15
_L15:
            if (j <= 0) goto _L18; else goto _L17
_L17:
            chipconfig.append('\n');
_L18:
            if (j != 0) goto _L20; else goto _L19
_L19:
            if (chipviewmodel.getIcon() != 0)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k == 0) goto _L20; else goto _L21
_L21:
            k = chipviewmodel.getPrimaryTextInsetForIcon();
_L25:
            chipconfig.append(TextUtils.ellipsize((SpannableStringBuilder)stringbuilderlist.get(j), textpaint, i - (k + 2), android.text.TextUtils.TruncateAt.END));
            j++;
              goto _L22
_L13:
            chipviewmodel = SpanUtils.join("\n", stringbuilderlist);
            stringbuilderlist.close();
            return chipviewmodel;
_L14:
            if (!chipviewmodel.getIsRtl())
            {
                i = k;
            } else
            {
                i = 0;
            }
            if (i != 0) goto _L24; else goto _L23
_L23:
            throw new IllegalStateException();
_L24:
            chipviewmodel = SpanUtils.join("\n", stringbuilderlist);
            stringbuilderlist.close();
            return chipviewmodel;
_L20:
            k = 0;
              goto _L25
_L16:
            stringbuilderlist.close();
            return chipconfig;
            chipviewmodel;
            chipconfig = null;
              goto _L26
_L8:
            j = 1;
              goto _L11
        }

        PrimaryText(ChipViewModel chipviewmodel, int i, CharSequence charsequence)
        {
            viewModel = chipviewmodel;
            width = i;
            text = charsequence;
        }
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timeline/chip/ChipForegroundDrawable);
    private int alpha;
    public final Paint badgeBackgroundPaint = new Paint();
    public final DefaultableBitmapDrawable badgeDrawable;
    public final android.graphics.drawable.Drawable.Callback callback = new _cls1();
    private final ChipConfig config;
    public Drawable icon;
    public int iconSize;
    public final Rect padding = new Rect();
    private PrimaryText primaryText;
    private final Rect primaryTextArea = new Rect();
    public StaticLayout primaryTextLayout;
    private final SpannableStringBuilder secondaryText = new SpannableStringBuilder();
    public final Rect secondaryTextArea = new Rect();
    private StaticLayout secondaryTextLayout;
    public final TextPaint textPaint = new TextPaint(1);
    public boolean valid;
    public ChipViewModel viewModel;

    ChipForegroundDrawable(ChipConfig chipconfig, Resources resources)
    {
        alpha = 255;
        valid = true;
        config = chipconfig;
        textPaint.setStyle(android.graphics.Paint.Style.FILL);
        badgeBackgroundPaint.setStyle(android.graphics.Paint.Style.FILL);
        badgeBackgroundPaint.setAntiAlias(true);
        class .Lambda._cls0
            implements com.google.android.calendar.common.drawable.DefaultableBitmapDrawable.Listener
        {

            public static final com.google.android.calendar.common.drawable.DefaultableBitmapDrawable.Listener $instance = new .Lambda._cls0();

            public final void onEmptyBitmapSet(DefaultableBitmapDrawable defaultablebitmapdrawable)
            {
                ((BasicBitmapDrawable)defaultablebitmapdrawable).unbind();
            }


            private .Lambda._cls0()
            {
            }
        }

        badgeDrawable = new DefaultableBitmapDrawable(resources, ContactPhotoCacheHolder.getContactPhotoCache(), false, .Lambda._cls0..instance, 0);
        badgeDrawable.setDecodeDimensions(config.contactPhotoActualWidth, config.contactPhotoActualHeight);
        badgeDrawable.setBounds(0, 0, config.badgeWidth, config.badgeHeight);
        badgeDrawable.setCallback(callback);
    }

    static String prepInputString(String s, ChipViewModel chipviewmodel)
    {
        if (s.length() > 300)
        {
            s = s.substring(0, 300);
        }
        return RtlUtils.forceTextAlignment(s, chipviewmodel.getIsRtl());
    }

    public void draw(Canvas canvas)
    {
        boolean flag = true;
        if (viewModel != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        if (getBounds().isEmpty())
        {
            LogUtils.w(TAG, "draw called but bounds are empty. Forgot to call setBounds()?", new Object[0]);
        }
        Rect rect;
        Object obj1;
        ChipViewModel chipviewmodel;
        int k;
        if (getIntrinsicWidth() >= config.minimumWidthToShowContents)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (valid) goto _L4; else goto _L3
_L3:
        i = getBounds().width();
        k = getBounds().height();
        primaryTextArea.set(padding.left, padding.top, i - padding.right, k - padding.bottom);
        if (i <= config.minimumWidthRightPadding)
        {
            if (viewModel.getIsRtl())
            {
                primaryTextArea.left = 0;
            } else
            {
                primaryTextArea.right = i;
            }
        }
        if (((BasicBitmapDrawable) (badgeDrawable)).mCurrKey != null && badgeDrawable.getBitmap() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = badgeDrawable.getBounds().width();
            if (viewModel.getIsRtl())
            {
                rect = primaryTextArea;
                k = rect.left;
                rect.left = i + config.badgePadding + k;
            } else
            {
                obj = primaryTextArea;
                obj.right = ((Rect) (obj)).right - (i + config.badgePadding);
            }
        }
        if (primaryTextArea.width() > 0 && primaryTextArea.height() > 0) goto _L6; else goto _L5
_L5:
        primaryTextArea.setEmpty();
        primaryTextLayout = null;
_L11:
        secondaryText.clear();
        secondaryText.clearSpans();
        Object obj;
        if (!TextUtils.isEmpty(viewModel.getSecondaryActionAction()))
        {
            obj1 = secondaryText;
            obj = viewModel.getSecondaryActionAction();
            chipviewmodel = viewModel;
            Object obj2;
            ChipViewModel chipviewmodel1;
            ChipConfig chipconfig;
            TextPaint textpaint;
            int i1;
            int j1;
            if (((String) (obj)).length() > 300)
            {
                obj = ((String) (obj)).substring(0, 300);
            }
            ((SpannableStringBuilder) (obj1)).append(RtlUtils.forceTextAlignment(((String) (obj)), chipviewmodel.getIsRtl()));
            obj = secondaryText;
            obj1 = config.mediumSpan;
            if (((Spannable) (obj)).length() != 0)
            {
                ((Spannable) (obj)).setSpan(obj1, 0, ((Spannable) (obj)).length(), 33);
            }
            if (!TextUtils.isEmpty(viewModel.getSecondaryActionInfo()))
            {
                secondaryText.append(' ');
            }
        }
        if (!TextUtils.isEmpty(viewModel.getSecondaryActionInfo()))
        {
            obj1 = secondaryText;
            obj = viewModel.getSecondaryActionInfo();
            chipviewmodel = viewModel;
            if (((String) (obj)).length() > 300)
            {
                obj = ((String) (obj)).substring(0, 300);
            }
            ((SpannableStringBuilder) (obj1)).append(RtlUtils.forceTextAlignment(((String) (obj)), chipviewmodel.getIsRtl()));
        }
        if (viewModel.getVerticalAlignType() != 0 || TextUtils.isEmpty(secondaryText))
        {
            secondaryTextArea.setEmpty();
            secondaryTextLayout = null;
        } else
        {
            secondaryTextArea.bottom = getBounds().height() - padding.bottom;
            secondaryTextArea.left = padding.left;
            secondaryTextArea.right = getBounds().width() - padding.right;
            if (!secondaryTextArea.isEmpty())
            {
                i = secondaryTextArea.width();
                secondaryTextLayout = new StaticLayout(TextUtils.ellipsize(secondaryText, textPaint, i, android.text.TextUtils.TruncateAt.END), textPaint, i, android.text.Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
                secondaryTextArea.top = secondaryTextArea.bottom - secondaryTextLayout.getLineBottom(0);
            }
        }
        valid = true;
_L4:
        canvas.clipRect(getBounds());
        if (((BasicBitmapDrawable) (badgeDrawable)).mCurrKey != null && badgeDrawable.getBitmap() != null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            int l = config.badgePadding;
            if (viewModel.getIsRtl())
            {
                i = l;
            } else
            {
                i = getBounds().width() - l - badgeDrawable.getBounds().width();
            }
            canvas.save();
            canvas.translate(i, l);
            badgeDrawable.draw(canvas);
            canvas.restore();
        }
        if (!primaryTextArea.isEmpty() && primaryTextLayout != null && primaryTextLayout.getLineCount() != 0)
        {
            canvas.save();
            int j = viewModel.getIconHorizontalCorrection();
            if (icon != null && j < 0)
            {
                if (viewModel.getIsRtl())
                {
                    canvas.clipRect(primaryTextArea.left, primaryTextArea.top, primaryTextArea.right - j, primaryTextArea.bottom);
                } else
                {
                    canvas.clipRect(primaryTextArea.left + j, primaryTextArea.top, primaryTextArea.right, primaryTextArea.bottom);
                }
            } else
            {
                canvas.clipRect(primaryTextArea);
            }
            canvas.translate(primaryTextArea.left, primaryTextArea.top);
            primaryTextLayout.draw(canvas);
            if (icon != null)
            {
                icon.setAlpha(alpha);
                float f;
                float f1;
                if (viewModel.getIsRtl())
                {
                    f = primaryTextArea.width() - iconSize - j;
                } else
                {
                    f = j;
                }
                f1 = (float)(primaryTextLayout.getLineBottom(0) - iconSize) / 2.0F;
                if (viewModel.getIconMode() == ChipViewModel.IconMode.BADGED)
                {
                    f += (float)iconSize / 2.0F;
                    f1 = (float)iconSize / 2.0F + f1;
                    canvas.drawCircle(f, f1, (float)iconSize / 2.0F, badgeBackgroundPaint);
                    icon.setBounds(0, 0, viewModel.getIconBadgeSize(), viewModel.getIconBadgeSize());
                    f -= (float)viewModel.getIconBadgeSize() / 2.0F;
                    f1 -= (float)viewModel.getIconBadgeSize() / 2.0F;
                } else
                {
                    icon.setBounds(0, 0, iconSize, iconSize);
                }
                canvas.translate(f, f1);
                icon.draw(canvas);
            }
            canvas.restore();
        }
        if (!secondaryTextArea.isEmpty() && secondaryTextLayout != null)
        {
            canvas.save();
            canvas.clipRect(secondaryTextArea);
            canvas.translate(secondaryTextArea.left, secondaryTextArea.top);
            secondaryTextLayout.draw(canvas);
            canvas.restore();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L6:
label0:
        {
            obj2 = primaryText;
            chipviewmodel1 = viewModel;
            chipconfig = config;
            textpaint = textPaint;
            l = primaryTextArea.width();
            if (chipviewmodel1.getEllipsizeType() == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (obj2 != null && ((PrimaryText) (obj2)).viewModel == chipviewmodel1)
            {
                obj = obj2;
                if (i == 0)
                {
                    break label0;
                }
                obj = obj2;
                if (l == ((PrimaryText) (obj2)).width)
                {
                    break label0;
                }
            }
            obj = new PrimaryText(chipviewmodel1, l, PrimaryText.formatText(chipviewmodel1, chipconfig, textpaint, l));
        }
        primaryText = ((PrimaryText) (obj));
        obj = primaryText.text;
        obj2 = textPaint;
        if (viewModel.getEllipsizeType() == 2)
        {
            i = 0x7fffffff;
        } else
        {
            i = primaryTextArea.width();
        }
        primaryTextLayout = new StaticLayout(((CharSequence) (obj)), ((TextPaint) (obj2)), i, android.text.Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        obj = primaryTextLayout;
        j1 = primaryTextArea.height();
        l = ((Layout) (obj)).getLineBottom(0);
        i = l;
        i1 = 1;
_L9:
        if (i1 >= ((Layout) (obj)).getLineCount())
        {
            break MISSING_BLOCK_LABEL_1094;
        }
        l = ((Layout) (obj)).getLineBottom(i1);
        if ((float)(l - i) * 0.9F + (float)i <= (float)j1) goto _L8; else goto _L7
_L7:
        switch (viewModel.getVerticalAlignType())
        {
        case 1: // '\001'
        default:
            l = primaryTextArea.top;
            i1 = primaryTextArea.bottom;
            primaryTextArea.top = ((l + i1) - i) / 2;
            primaryTextArea.bottom = i + primaryTextArea.top;
            break;

        case 0: // '\0'
            primaryTextArea.bottom = i + primaryTextArea.top;
            break;

        case 2: // '\002'
            primaryTextArea.top = primaryTextArea.bottom - i;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        i1++;
        i = l;
          goto _L9
        i = l;
          goto _L7
        if (true) goto _L11; else goto _L10
_L10:
        if (true) goto _L1; else goto _L12
_L12:
    }

    public int getIntrinsicHeight()
    {
        return getBounds().height();
    }

    public int getIntrinsicWidth()
    {
        return getBounds().width();
    }

    public int getOpacity()
    {
        return -3;
    }

    protected void onBoundsChange(Rect rect)
    {
        valid = false;
        invalidateSelf();
    }

    public void setAlpha(int i)
    {
        if (i != alpha)
        {
            alpha = i;
            textPaint.setAlpha(alpha);
            badgeDrawable.setAlpha(alpha);
            invalidateSelf();
        }
    }

    final void setBadge(RequestKey requestkey)
    {
label0:
        {
            boolean flag1 = true;
            RequestKey requestkey1 = ((BasicBitmapDrawable) (badgeDrawable)).mCurrKey;
            boolean flag;
            if (requestkey1 == requestkey || requestkey1 != null && requestkey1.equals(requestkey))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                if (requestkey == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (((BasicBitmapDrawable) (badgeDrawable)).mCurrKey != null)
                {
                    flag1 = false;
                }
                if (requestkey == null)
                {
                    badgeDrawable.unbind();
                } else
                {
                    badgeDrawable.bind(requestkey);
                }
                if (!(flag ^ flag1))
                {
                    break label0;
                }
                valid = false;
                invalidateSelf();
            }
            return;
        }
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }


    private class _cls1
        implements android.graphics.drawable.Drawable.Callback
    {

        private final ChipForegroundDrawable this$0;

        public final void invalidateDrawable(Drawable drawable)
        {
            if (drawable == badgeDrawable)
            {
                drawable = ChipForegroundDrawable.this;
                boolean flag;
                if (((BasicBitmapDrawable) (((ChipForegroundDrawable) (drawable)).badgeDrawable)).mCurrKey != null && ((ChipForegroundDrawable) (drawable)).badgeDrawable.getBitmap() != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    drawable = ChipForegroundDrawable.this;
                    drawable.valid = false;
                    drawable.invalidateSelf();
                    return;
                }
            }
            invalidateSelf();
        }

        public final void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
        {
            scheduleSelf(runnable, l);
        }

        public final void unscheduleDrawable(Drawable drawable, Runnable runnable)
        {
            unscheduleSelf(runnable);
        }

        _cls1()
        {
            this$0 = ChipForegroundDrawable.this;
            super();
        }
    }

}
