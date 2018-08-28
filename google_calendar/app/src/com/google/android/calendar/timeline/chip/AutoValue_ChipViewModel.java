// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipViewModel

final class AutoValue_ChipViewModel extends ChipViewModel
{

    private final int backgroundColor;
    private final ListenableFuture badgeRequestKey;
    private final int borderColor;
    private final Float borderWidth;
    private final int chipType;
    private final ChipViewModel.ColorStyle colorStyle;
    private final Supplier contentDescription;
    private final int cornerRadius;
    private final Boolean dragHandles;
    private final int ellipsizeType;
    private final int foregroundColor;
    private final int foregroundPaddingBottom;
    private final int foregroundPaddingEnd;
    private final int foregroundPaddingStart;
    private final int foregroundPaddingTop;
    private final int icon;
    private final int iconBadgeSize;
    private final int iconHorizontalCorrection;
    private final ChipViewModel.IconMode iconMode;
    private final int iconSize;
    private final boolean isRtl;
    private final Optional optionalBackgroundImageViewModel;
    private final int outerBorderColor;
    private final Integer primarySwipeIcon;
    private final List primaryText;
    private final int primaryTextInsetForIcon;
    private final Integer rippleColor;
    private final String secondaryActionAction;
    private final String secondaryActionInfo;
    private final Integer secondarySwipeIcon;
    private final int strikeThroughText;
    private final Integer styledCornersCompatibilityMode;
    private final int supportedSwipeDirections;
    private final Integer swipeAccentColor;
    private final int textSize;
    private final int verticalAlignType;

    AutoValue_ChipViewModel(boolean flag, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int i2, Optional optional, int j2, int k2, int l2, 
            ListenableFuture listenablefuture, List list, Supplier supplier, String s, String s1, int i3, int j3, 
            int k3, int l3, int i4, int j4, Integer integer, Integer integer1, Integer integer2, 
            Integer integer3, Integer integer4, Boolean boolean1, ChipViewModel.ColorStyle colorstyle, ChipViewModel.IconMode iconmode, int k4, Float float1, 
            int l4)
    {
        isRtl = flag;
        foregroundColor = i;
        backgroundColor = j;
        borderColor = k;
        outerBorderColor = l;
        chipType = i1;
        foregroundPaddingStart = j1;
        foregroundPaddingTop = k1;
        foregroundPaddingEnd = l1;
        foregroundPaddingBottom = i2;
        optionalBackgroundImageViewModel = optional;
        strikeThroughText = j2;
        ellipsizeType = k2;
        verticalAlignType = l2;
        badgeRequestKey = listenablefuture;
        primaryText = list;
        contentDescription = supplier;
        secondaryActionAction = s;
        secondaryActionInfo = s1;
        icon = i3;
        primaryTextInsetForIcon = j3;
        iconHorizontalCorrection = k3;
        textSize = l3;
        iconSize = i4;
        supportedSwipeDirections = j4;
        primarySwipeIcon = integer;
        secondarySwipeIcon = integer1;
        swipeAccentColor = integer2;
        rippleColor = integer3;
        styledCornersCompatibilityMode = integer4;
        dragHandles = boolean1;
        colorStyle = colorstyle;
        iconMode = iconmode;
        iconBadgeSize = k4;
        borderWidth = float1;
        cornerRadius = l4;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof ChipViewModel)
            {
                if (isRtl != ((ChipViewModel) (obj = (ChipViewModel)obj)).getIsRtl() || foregroundColor != ((ChipViewModel) (obj)).getForegroundColor() || backgroundColor != ((ChipViewModel) (obj)).getBackgroundColor() || borderColor != ((ChipViewModel) (obj)).getBorderColor() || outerBorderColor != ((ChipViewModel) (obj)).getOuterBorderColor() || chipType != ((ChipViewModel) (obj)).getChipType() || foregroundPaddingStart != ((ChipViewModel) (obj)).getForegroundPaddingStart() || foregroundPaddingTop != ((ChipViewModel) (obj)).getForegroundPaddingTop() || foregroundPaddingEnd != ((ChipViewModel) (obj)).getForegroundPaddingEnd() || foregroundPaddingBottom != ((ChipViewModel) (obj)).getForegroundPaddingBottom() || !optionalBackgroundImageViewModel.equals(((ChipViewModel) (obj)).getOptionalBackgroundImageViewModel()) || strikeThroughText != ((ChipViewModel) (obj)).getStrikeThroughText() || ellipsizeType != ((ChipViewModel) (obj)).getEllipsizeType() || verticalAlignType != ((ChipViewModel) (obj)).getVerticalAlignType() || !badgeRequestKey.equals(((ChipViewModel) (obj)).getBadgeRequestKey()) || !primaryText.equals(((ChipViewModel) (obj)).getPrimaryText()) || !contentDescription.equals(((ChipViewModel) (obj)).getContentDescription()) || (secondaryActionAction != null ? !secondaryActionAction.equals(((ChipViewModel) (obj)).getSecondaryActionAction()) : ((ChipViewModel) (obj)).getSecondaryActionAction() != null) || (secondaryActionInfo != null ? !secondaryActionInfo.equals(((ChipViewModel) (obj)).getSecondaryActionInfo()) : ((ChipViewModel) (obj)).getSecondaryActionInfo() != null) || (icon != ((ChipViewModel) (obj)).getIcon() || primaryTextInsetForIcon != ((ChipViewModel) (obj)).getPrimaryTextInsetForIcon() || iconHorizontalCorrection != ((ChipViewModel) (obj)).getIconHorizontalCorrection() || textSize != ((ChipViewModel) (obj)).getTextSize() || iconSize != ((ChipViewModel) (obj)).getIconSize() || supportedSwipeDirections != ((ChipViewModel) (obj)).getSupportedSwipeDirections()) || (primarySwipeIcon != null ? !primarySwipeIcon.equals(((ChipViewModel) (obj)).getPrimarySwipeIcon()) : ((ChipViewModel) (obj)).getPrimarySwipeIcon() != null) || (secondarySwipeIcon != null ? !secondarySwipeIcon.equals(((ChipViewModel) (obj)).getSecondarySwipeIcon()) : ((ChipViewModel) (obj)).getSecondarySwipeIcon() != null) || (swipeAccentColor != null ? !swipeAccentColor.equals(((ChipViewModel) (obj)).getSwipeAccentColor()) : ((ChipViewModel) (obj)).getSwipeAccentColor() != null) || (rippleColor != null ? !rippleColor.equals(((ChipViewModel) (obj)).getRippleColor()) : ((ChipViewModel) (obj)).getRippleColor() != null) || (styledCornersCompatibilityMode != null ? !styledCornersCompatibilityMode.equals(((ChipViewModel) (obj)).getStyledCornersCompatibilityMode()) : ((ChipViewModel) (obj)).getStyledCornersCompatibilityMode() != null) || (!dragHandles.equals(((ChipViewModel) (obj)).getDragHandles()) || !colorStyle.equals(((ChipViewModel) (obj)).getColorStyle()) || !iconMode.equals(((ChipViewModel) (obj)).getIconMode()) || iconBadgeSize != ((ChipViewModel) (obj)).getIconBadgeSize()) || (borderWidth != null ? !borderWidth.equals(((ChipViewModel) (obj)).getBorderWidth()) : ((ChipViewModel) (obj)).getBorderWidth() != null) || cornerRadius != ((ChipViewModel) (obj)).getCornerRadius())
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int getBackgroundColor()
    {
        return backgroundColor;
    }

    public final ListenableFuture getBadgeRequestKey()
    {
        return badgeRequestKey;
    }

    public final int getBorderColor()
    {
        return borderColor;
    }

    public final Float getBorderWidth()
    {
        return borderWidth;
    }

    public final int getChipType()
    {
        return chipType;
    }

    public final ChipViewModel.ColorStyle getColorStyle()
    {
        return colorStyle;
    }

    public final Supplier getContentDescription()
    {
        return contentDescription;
    }

    public final int getCornerRadius()
    {
        return cornerRadius;
    }

    public final Boolean getDragHandles()
    {
        return dragHandles;
    }

    public final int getEllipsizeType()
    {
        return ellipsizeType;
    }

    public final int getForegroundColor()
    {
        return foregroundColor;
    }

    public final int getForegroundPaddingBottom()
    {
        return foregroundPaddingBottom;
    }

    public final int getForegroundPaddingEnd()
    {
        return foregroundPaddingEnd;
    }

    public final int getForegroundPaddingStart()
    {
        return foregroundPaddingStart;
    }

    public final int getForegroundPaddingTop()
    {
        return foregroundPaddingTop;
    }

    public final int getIcon()
    {
        return icon;
    }

    public final int getIconBadgeSize()
    {
        return iconBadgeSize;
    }

    public final int getIconHorizontalCorrection()
    {
        return iconHorizontalCorrection;
    }

    public final ChipViewModel.IconMode getIconMode()
    {
        return iconMode;
    }

    public final int getIconSize()
    {
        return iconSize;
    }

    public final boolean getIsRtl()
    {
        return isRtl;
    }

    public final Optional getOptionalBackgroundImageViewModel()
    {
        return optionalBackgroundImageViewModel;
    }

    public final int getOuterBorderColor()
    {
        return outerBorderColor;
    }

    public final Integer getPrimarySwipeIcon()
    {
        return primarySwipeIcon;
    }

    public final List getPrimaryText()
    {
        return primaryText;
    }

    public final int getPrimaryTextInsetForIcon()
    {
        return primaryTextInsetForIcon;
    }

    public final Integer getRippleColor()
    {
        return rippleColor;
    }

    public final String getSecondaryActionAction()
    {
        return secondaryActionAction;
    }

    public final String getSecondaryActionInfo()
    {
        return secondaryActionInfo;
    }

    public final Integer getSecondarySwipeIcon()
    {
        return secondarySwipeIcon;
    }

    public final int getStrikeThroughText()
    {
        return strikeThroughText;
    }

    public final Integer getStyledCornersCompatibilityMode()
    {
        return styledCornersCompatibilityMode;
    }

    public final int getSupportedSwipeDirections()
    {
        return supportedSwipeDirections;
    }

    public final Integer getSwipeAccentColor()
    {
        return swipeAccentColor;
    }

    public final int getTextSize()
    {
        return textSize;
    }

    public final int getVerticalAlignType()
    {
        return verticalAlignType;
    }

    public final int hashCode()
    {
        int l1 = 0;
        char c;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        int j5;
        int k5;
        int l5;
        int i6;
        int j6;
        int k6;
        int l6;
        int i7;
        int j7;
        int k7;
        int l7;
        int i8;
        int j8;
        if (isRtl)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        i2 = foregroundColor;
        j2 = backgroundColor;
        k2 = borderColor;
        l2 = outerBorderColor;
        i3 = chipType;
        j3 = foregroundPaddingStart;
        k3 = foregroundPaddingTop;
        l3 = foregroundPaddingEnd;
        i4 = foregroundPaddingBottom;
        j4 = optionalBackgroundImageViewModel.hashCode();
        k4 = strikeThroughText;
        l4 = ellipsizeType;
        i5 = verticalAlignType;
        j5 = badgeRequestKey.hashCode();
        k5 = primaryText.hashCode();
        l5 = contentDescription.hashCode();
        if (secondaryActionAction == null)
        {
            i = 0;
        } else
        {
            i = secondaryActionAction.hashCode();
        }
        if (secondaryActionInfo == null)
        {
            j = 0;
        } else
        {
            j = secondaryActionInfo.hashCode();
        }
        i6 = icon;
        j6 = primaryTextInsetForIcon;
        k6 = iconHorizontalCorrection;
        l6 = textSize;
        i7 = iconSize;
        j7 = supportedSwipeDirections;
        if (primarySwipeIcon == null)
        {
            k = 0;
        } else
        {
            k = primarySwipeIcon.hashCode();
        }
        if (secondarySwipeIcon == null)
        {
            l = 0;
        } else
        {
            l = secondarySwipeIcon.hashCode();
        }
        if (swipeAccentColor == null)
        {
            i1 = 0;
        } else
        {
            i1 = swipeAccentColor.hashCode();
        }
        if (rippleColor == null)
        {
            j1 = 0;
        } else
        {
            j1 = rippleColor.hashCode();
        }
        if (styledCornersCompatibilityMode == null)
        {
            k1 = 0;
        } else
        {
            k1 = styledCornersCompatibilityMode.hashCode();
        }
        k7 = dragHandles.hashCode();
        l7 = colorStyle.hashCode();
        i8 = iconMode.hashCode();
        j8 = iconBadgeSize;
        if (borderWidth != null)
        {
            l1 = borderWidth.hashCode();
        }
        return ((((((k1 ^ (j1 ^ (i1 ^ (l ^ (k ^ (((((((j ^ (i ^ (((((((((((((((((c ^ 0xf4243) * 0xf4243 ^ i2) * 0xf4243 ^ j2) * 0xf4243 ^ k2) * 0xf4243 ^ l2) * 0xf4243 ^ i3) * 0xf4243 ^ j3) * 0xf4243 ^ k3) * 0xf4243 ^ l3) * 0xf4243 ^ i4) * 0xf4243 ^ j4) * 0xf4243 ^ k4) * 0xf4243 ^ l4) * 0xf4243 ^ i5) * 0xf4243 ^ j5) * 0xf4243 ^ k5) * 0xf4243 ^ l5) * 0xf4243) * 0xf4243) * 0xf4243 ^ i6) * 0xf4243 ^ j6) * 0xf4243 ^ k6) * 0xf4243 ^ l6) * 0xf4243 ^ i7) * 0xf4243 ^ j7) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ k7) * 0xf4243 ^ l7) * 0xf4243 ^ i8) * 0xf4243 ^ j8) * 0xf4243 ^ l1) * 0xf4243 ^ cornerRadius;
    }

    public final ChipViewModel.Builder toBuilder()
    {
        return new Builder(this);
    }

    public final String toString()
    {
        boolean flag = isRtl;
        int i = foregroundColor;
        int j = backgroundColor;
        int k = borderColor;
        int l = outerBorderColor;
        int i1 = chipType;
        int j1 = foregroundPaddingStart;
        int k1 = foregroundPaddingTop;
        int l1 = foregroundPaddingEnd;
        int i2 = foregroundPaddingBottom;
        String s = String.valueOf(optionalBackgroundImageViewModel);
        int j2 = strikeThroughText;
        int k2 = ellipsizeType;
        int l2 = verticalAlignType;
        String s1 = String.valueOf(badgeRequestKey);
        String s2 = String.valueOf(primaryText);
        String s3 = String.valueOf(contentDescription);
        String s4 = secondaryActionAction;
        String s5 = secondaryActionInfo;
        int i3 = icon;
        int j3 = primaryTextInsetForIcon;
        int k3 = iconHorizontalCorrection;
        int l3 = textSize;
        int i4 = iconSize;
        int j4 = supportedSwipeDirections;
        String s6 = String.valueOf(primarySwipeIcon);
        String s7 = String.valueOf(secondarySwipeIcon);
        String s8 = String.valueOf(swipeAccentColor);
        String s9 = String.valueOf(rippleColor);
        String s10 = String.valueOf(styledCornersCompatibilityMode);
        String s11 = String.valueOf(dragHandles);
        String s12 = String.valueOf(colorStyle);
        String s13 = String.valueOf(iconMode);
        int k4 = iconBadgeSize;
        String s14 = String.valueOf(borderWidth);
        int l4 = cornerRadius;
        return (new StringBuilder(String.valueOf(s).length() + 911 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length() + String.valueOf(s9).length() + String.valueOf(s10).length() + String.valueOf(s11).length() + String.valueOf(s12).length() + String.valueOf(s13).length() + String.valueOf(s14).length())).append("ChipViewModel{isRtl=").append(flag).append(", foregroundColor=").append(i).append(", backgroundColor=").append(j).append(", borderColor=").append(k).append(", outerBorderColor=").append(l).append(", chipType=").append(i1).append(", foregroundPaddingStart=").append(j1).append(", foregroundPaddingTop=").append(k1).append(", foregroundPaddingEnd=").append(l1).append(", foregroundPaddingBottom=").append(i2).append(", optionalBackgroundImageViewModel=").append(s).append(", strikeThroughText=").append(j2).append(", ellipsizeType=").append(k2).append(", verticalAlignType=").append(l2).append(", badgeRequestKey=").append(s1).append(", primaryText=").append(s2).append(", contentDescription=").append(s3).append(", secondaryActionAction=").append(s4).append(", secondaryActionInfo=").append(s5).append(", icon=").append(i3).append(", primaryTextInsetForIcon=").append(j3).append(", iconHorizontalCorrection=").append(k3).append(", textSize=").append(l3).append(", iconSize=").append(i4).append(", supportedSwipeDirections=").append(j4).append(", primarySwipeIcon=").append(s6).append(", secondarySwipeIcon=").append(s7).append(", swipeAccentColor=").append(s8).append(", rippleColor=").append(s9).append(", styledCornersCompatibilityMode=").append(s10).append(", dragHandles=").append(s11).append(", colorStyle=").append(s12).append(", iconMode=").append(s13).append(", iconBadgeSize=").append(k4).append(", borderWidth=").append(s14).append(", cornerRadius=").append(l4).append("}").toString();
    }

    private class Builder extends ChipViewModel.Builder
    {

        private Integer backgroundColor;
        private ListenableFuture badgeRequestKey;
        private Integer borderColor;
        private Float borderWidth;
        private Integer chipType;
        private ChipViewModel.ColorStyle colorStyle;
        private Supplier contentDescription;
        private Integer cornerRadius;
        private Boolean dragHandles;
        private Integer ellipsizeType;
        private Integer foregroundColor;
        private Integer foregroundPaddingBottom;
        private Integer foregroundPaddingEnd;
        private Integer foregroundPaddingStart;
        private Integer foregroundPaddingTop;
        private Integer icon;
        private Integer iconBadgeSize;
        private Integer iconHorizontalCorrection;
        private ChipViewModel.IconMode iconMode;
        private Integer iconSize;
        private Boolean isRtl;
        private Optional optionalBackgroundImageViewModel;
        private Integer outerBorderColor;
        private Integer primarySwipeIcon;
        private List primaryText;
        private Integer primaryTextInsetForIcon;
        private Integer rippleColor;
        private String secondaryActionAction;
        private String secondaryActionInfo;
        private Integer secondarySwipeIcon;
        private Integer strikeThroughText;
        private Integer styledCornersCompatibilityMode;
        private Integer supportedSwipeDirections;
        private Integer swipeAccentColor;
        private Integer textSize;
        private Integer verticalAlignType;

        public final ChipViewModel build()
        {
            String s2 = "";
            if (isRtl == null)
            {
                s2 = String.valueOf("").concat(" isRtl");
            }
            String s = s2;
            if (foregroundColor == null)
            {
                s = String.valueOf(s2).concat(" foregroundColor");
            }
            s2 = s;
            if (backgroundColor == null)
            {
                s2 = String.valueOf(s).concat(" backgroundColor");
            }
            s = s2;
            if (borderColor == null)
            {
                s = String.valueOf(s2).concat(" borderColor");
            }
            s2 = s;
            if (outerBorderColor == null)
            {
                s2 = String.valueOf(s).concat(" outerBorderColor");
            }
            s = s2;
            if (chipType == null)
            {
                s = String.valueOf(s2).concat(" chipType");
            }
            s2 = s;
            if (foregroundPaddingStart == null)
            {
                s2 = String.valueOf(s).concat(" foregroundPaddingStart");
            }
            s = s2;
            if (foregroundPaddingTop == null)
            {
                s = String.valueOf(s2).concat(" foregroundPaddingTop");
            }
            s2 = s;
            if (foregroundPaddingEnd == null)
            {
                s2 = String.valueOf(s).concat(" foregroundPaddingEnd");
            }
            s = s2;
            if (foregroundPaddingBottom == null)
            {
                s = String.valueOf(s2).concat(" foregroundPaddingBottom");
            }
            s2 = s;
            if (strikeThroughText == null)
            {
                s2 = String.valueOf(s).concat(" strikeThroughText");
            }
            s = s2;
            if (ellipsizeType == null)
            {
                s = String.valueOf(s2).concat(" ellipsizeType");
            }
            s2 = s;
            if (verticalAlignType == null)
            {
                s2 = String.valueOf(s).concat(" verticalAlignType");
            }
            s = s2;
            if (badgeRequestKey == null)
            {
                s = String.valueOf(s2).concat(" badgeRequestKey");
            }
            s2 = s;
            if (primaryText == null)
            {
                s2 = String.valueOf(s).concat(" primaryText");
            }
            s = s2;
            if (contentDescription == null)
            {
                s = String.valueOf(s2).concat(" contentDescription");
            }
            s2 = s;
            if (icon == null)
            {
                s2 = String.valueOf(s).concat(" icon");
            }
            s = s2;
            if (primaryTextInsetForIcon == null)
            {
                s = String.valueOf(s2).concat(" primaryTextInsetForIcon");
            }
            s2 = s;
            if (iconHorizontalCorrection == null)
            {
                s2 = String.valueOf(s).concat(" iconHorizontalCorrection");
            }
            s = s2;
            if (textSize == null)
            {
                s = String.valueOf(s2).concat(" textSize");
            }
            s2 = s;
            if (iconSize == null)
            {
                s2 = String.valueOf(s).concat(" iconSize");
            }
            s = s2;
            if (supportedSwipeDirections == null)
            {
                s = String.valueOf(s2).concat(" supportedSwipeDirections");
            }
            s2 = s;
            if (dragHandles == null)
            {
                s2 = String.valueOf(s).concat(" dragHandles");
            }
            s = s2;
            if (colorStyle == null)
            {
                s = String.valueOf(s2).concat(" colorStyle");
            }
            s2 = s;
            if (iconMode == null)
            {
                s2 = String.valueOf(s).concat(" iconMode");
            }
            s = s2;
            if (iconBadgeSize == null)
            {
                s = String.valueOf(s2).concat(" iconBadgeSize");
            }
            s2 = s;
            if (cornerRadius == null)
            {
                s2 = String.valueOf(s).concat(" cornerRadius");
            }
            if (!s2.isEmpty())
            {
                String s1 = String.valueOf(s2);
                if (s1.length() != 0)
                {
                    s1 = "Missing required properties:".concat(s1);
                } else
                {
                    s1 = new String("Missing required properties:");
                }
                throw new IllegalStateException(s1);
            } else
            {
                return new AutoValue_ChipViewModel(isRtl.booleanValue(), foregroundColor.intValue(), backgroundColor.intValue(), borderColor.intValue(), outerBorderColor.intValue(), chipType.intValue(), foregroundPaddingStart.intValue(), foregroundPaddingTop.intValue(), foregroundPaddingEnd.intValue(), foregroundPaddingBottom.intValue(), optionalBackgroundImageViewModel, strikeThroughText.intValue(), ellipsizeType.intValue(), verticalAlignType.intValue(), badgeRequestKey, primaryText, contentDescription, secondaryActionAction, secondaryActionInfo, icon.intValue(), primaryTextInsetForIcon.intValue(), iconHorizontalCorrection.intValue(), textSize.intValue(), iconSize.intValue(), supportedSwipeDirections.intValue(), primarySwipeIcon, secondarySwipeIcon, swipeAccentColor, rippleColor, styledCornersCompatibilityMode, dragHandles, colorStyle, iconMode, iconBadgeSize.intValue(), borderWidth, cornerRadius.intValue());
            }
        }

        public final ChipViewModel.Builder setBackgroundColor(int i)
        {
            backgroundColor = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setBadgeRequestKey(ListenableFuture listenablefuture)
        {
            if (listenablefuture == null)
            {
                throw new NullPointerException("Null badgeRequestKey");
            } else
            {
                badgeRequestKey = listenablefuture;
                return this;
            }
        }

        public final ChipViewModel.Builder setBorderColor(int i)
        {
            borderColor = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setChipType(int i)
        {
            chipType = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setColorStyle(ChipViewModel.ColorStyle colorstyle)
        {
            if (colorstyle == null)
            {
                throw new NullPointerException("Null colorStyle");
            } else
            {
                colorStyle = colorstyle;
                return this;
            }
        }

        public final ChipViewModel.Builder setContentDescription(Supplier supplier)
        {
            contentDescription = supplier;
            return this;
        }

        public final ChipViewModel.Builder setCornerRadius(int i)
        {
            cornerRadius = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setDragHandles(Boolean boolean1)
        {
            if (boolean1 == null)
            {
                throw new NullPointerException("Null dragHandles");
            } else
            {
                dragHandles = boolean1;
                return this;
            }
        }

        public final ChipViewModel.Builder setEllipsizeType(int i)
        {
            ellipsizeType = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setForegroundColor(int i)
        {
            foregroundColor = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setForegroundPaddingBottom(int i)
        {
            foregroundPaddingBottom = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setForegroundPaddingEnd(int i)
        {
            foregroundPaddingEnd = Integer.valueOf(0);
            return this;
        }

        public final ChipViewModel.Builder setForegroundPaddingStart(int i)
        {
            foregroundPaddingStart = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setForegroundPaddingTop(int i)
        {
            foregroundPaddingTop = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setIcon(int i)
        {
            icon = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setIconBadgeSize(int i)
        {
            iconBadgeSize = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setIconHorizontalCorrection(int i)
        {
            iconHorizontalCorrection = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setIconMode(ChipViewModel.IconMode iconmode)
        {
            if (iconmode == null)
            {
                throw new NullPointerException("Null iconMode");
            } else
            {
                iconMode = iconmode;
                return this;
            }
        }

        public final ChipViewModel.Builder setIconSize(int i)
        {
            iconSize = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setIsRtl(boolean flag)
        {
            isRtl = Boolean.valueOf(flag);
            return this;
        }

        public final ChipViewModel.Builder setOptionalBackgroundImageViewModel(Optional optional)
        {
            if (optional == null)
            {
                throw new NullPointerException("Null optionalBackgroundImageViewModel");
            } else
            {
                optionalBackgroundImageViewModel = optional;
                return this;
            }
        }

        public final ChipViewModel.Builder setOuterBorderColor(int i)
        {
            outerBorderColor = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setPrimarySwipeIcon(Integer integer)
        {
            primarySwipeIcon = integer;
            return this;
        }

        public final ChipViewModel.Builder setPrimaryText(List list)
        {
            if (list == null)
            {
                throw new NullPointerException("Null primaryText");
            } else
            {
                primaryText = list;
                return this;
            }
        }

        public final ChipViewModel.Builder setPrimaryTextInsetForIcon(int i)
        {
            primaryTextInsetForIcon = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setRippleColor(Integer integer)
        {
            rippleColor = integer;
            return this;
        }

        public final ChipViewModel.Builder setSecondaryActionAction(String s)
        {
            secondaryActionAction = s;
            return this;
        }

        public final ChipViewModel.Builder setSecondaryActionInfo(String s)
        {
            secondaryActionInfo = s;
            return this;
        }

        public final ChipViewModel.Builder setSecondarySwipeIcon(Integer integer)
        {
            secondarySwipeIcon = integer;
            return this;
        }

        public final ChipViewModel.Builder setStrikeThroughText(int i)
        {
            strikeThroughText = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setStyledCornersCompatibilityMode(Integer integer)
        {
            styledCornersCompatibilityMode = integer;
            return this;
        }

        public final ChipViewModel.Builder setSupportedSwipeDirections(int i)
        {
            supportedSwipeDirections = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setSwipeAccentColor(Integer integer)
        {
            swipeAccentColor = integer;
            return this;
        }

        public final ChipViewModel.Builder setTextSize(int i)
        {
            textSize = Integer.valueOf(i);
            return this;
        }

        public final ChipViewModel.Builder setVerticalAlignType(int i)
        {
            verticalAlignType = Integer.valueOf(i);
            return this;
        }

        public Builder()
        {
            optionalBackgroundImageViewModel = Absent.INSTANCE;
        }

        Builder(ChipViewModel chipviewmodel)
        {
            optionalBackgroundImageViewModel = Absent.INSTANCE;
            isRtl = Boolean.valueOf(chipviewmodel.getIsRtl());
            foregroundColor = Integer.valueOf(chipviewmodel.getForegroundColor());
            backgroundColor = Integer.valueOf(chipviewmodel.getBackgroundColor());
            borderColor = Integer.valueOf(chipviewmodel.getBorderColor());
            outerBorderColor = Integer.valueOf(chipviewmodel.getOuterBorderColor());
            chipType = Integer.valueOf(chipviewmodel.getChipType());
            foregroundPaddingStart = Integer.valueOf(chipviewmodel.getForegroundPaddingStart());
            foregroundPaddingTop = Integer.valueOf(chipviewmodel.getForegroundPaddingTop());
            foregroundPaddingEnd = Integer.valueOf(chipviewmodel.getForegroundPaddingEnd());
            foregroundPaddingBottom = Integer.valueOf(chipviewmodel.getForegroundPaddingBottom());
            optionalBackgroundImageViewModel = chipviewmodel.getOptionalBackgroundImageViewModel();
            strikeThroughText = Integer.valueOf(chipviewmodel.getStrikeThroughText());
            ellipsizeType = Integer.valueOf(chipviewmodel.getEllipsizeType());
            verticalAlignType = Integer.valueOf(chipviewmodel.getVerticalAlignType());
            badgeRequestKey = chipviewmodel.getBadgeRequestKey();
            primaryText = chipviewmodel.getPrimaryText();
            contentDescription = chipviewmodel.getContentDescription();
            secondaryActionAction = chipviewmodel.getSecondaryActionAction();
            secondaryActionInfo = chipviewmodel.getSecondaryActionInfo();
            icon = Integer.valueOf(chipviewmodel.getIcon());
            primaryTextInsetForIcon = Integer.valueOf(chipviewmodel.getPrimaryTextInsetForIcon());
            iconHorizontalCorrection = Integer.valueOf(chipviewmodel.getIconHorizontalCorrection());
            textSize = Integer.valueOf(chipviewmodel.getTextSize());
            iconSize = Integer.valueOf(chipviewmodel.getIconSize());
            supportedSwipeDirections = Integer.valueOf(chipviewmodel.getSupportedSwipeDirections());
            primarySwipeIcon = chipviewmodel.getPrimarySwipeIcon();
            secondarySwipeIcon = chipviewmodel.getSecondarySwipeIcon();
            swipeAccentColor = chipviewmodel.getSwipeAccentColor();
            rippleColor = chipviewmodel.getRippleColor();
            styledCornersCompatibilityMode = chipviewmodel.getStyledCornersCompatibilityMode();
            dragHandles = chipviewmodel.getDragHandles();
            colorStyle = chipviewmodel.getColorStyle();
            iconMode = chipviewmodel.getIconMode();
            iconBadgeSize = Integer.valueOf(chipviewmodel.getIconBadgeSize());
            borderWidth = chipviewmodel.getBorderWidth();
            cornerRadius = Integer.valueOf(chipviewmodel.getCornerRadius());
        }
    }

}
