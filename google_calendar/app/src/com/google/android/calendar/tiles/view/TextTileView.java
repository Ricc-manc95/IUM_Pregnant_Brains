// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.tiles.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import java.util.Arrays;

// Referenced classes of package com.google.android.calendar.tiles.view:
//            TileView

public class TextTileView extends TileView
{

    public ViewGroup container;
    public TextView primaryLine;
    public TextView secondaryLine;

    public TextTileView(Context context)
    {
        super(context);
    }

    public TextTileView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public static transient CharSequence concatenate(CharSequence acharsequence[])
    {
        if (acharsequence != null)
        {
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
            int j = acharsequence.length;
            for (int i = 0; i < j; i++)
            {
                CharSequence charsequence = acharsequence[i];
                if (TextUtils.isEmpty(charsequence))
                {
                    continue;
                }
                if (!TextUtils.isEmpty(spannablestringbuilder))
                {
                    spannablestringbuilder.append('\n');
                }
                spannablestringbuilder.append(charsequence);
            }

            if (!TextUtils.isEmpty(spannablestringbuilder))
            {
                return spannablestringbuilder;
            }
        }
        return null;
    }

    public int adjustTileHeight(int i)
    {
        int l;
        int j = getExpectedLineCount();
        l = Math.abs(getActualLineCount() - j);
        i = j;
        if (j > 1)
        {
            i = j;
            if (!super.denseMode)
            {
                i = j + 1;
            }
        }
        i;
        JVM INSTR tableswitch 0 3: default 68
    //                   0 136
    //                   1 141
    //                   2 163
    //                   3 185;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Tile.Dimensions dimensions = Tile.Dimensions.LARGE_TEXT_AWARE_TILE_MIN_HEIGHT;
        int k = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        dimensions = Tile.Dimensions.TEXT_AWARE_TILE_HEIGHT_INCREMENT;
        i = (i - 3) * getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId) + k;
_L7:
        Tile.Dimensions dimensions1 = Tile.Dimensions.TEXT_AWARE_TILE_HEIGHT_INCREMENT;
        return i + l * getContext().getResources().getDimensionPixelOffset(dimensions1.dimenResId);
_L2:
        return getMinimumHeight();
_L3:
        Tile.Dimensions dimensions2 = Tile.Dimensions.SMALL_TEXT_AWARE_TILE_MIN_HEIGHT;
        i = getContext().getResources().getDimensionPixelOffset(dimensions2.dimenResId);
        continue; /* Loop/switch isn't completed */
_L4:
        Tile.Dimensions dimensions3 = Tile.Dimensions.MEDIUM_TEXT_AWARE_TILE_MIN_HEIGHT;
        i = getContext().getResources().getDimensionPixelOffset(dimensions3.dimenResId);
        continue; /* Loop/switch isn't completed */
_L5:
        Tile.Dimensions dimensions4 = Tile.Dimensions.LARGE_TEXT_AWARE_TILE_MIN_HEIGHT;
        i = getContext().getResources().getDimensionPixelOffset(dimensions4.dimenResId);
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected final void applyAttributes(TypedArray typedarray)
    {
        super.applyAttributes(typedarray);
        int i = R.styleable.TileView_tile_primary_text;
        String s1 = typedarray.getNonResourceString(i);
        String s = s1;
        if (s1 == null)
        {
            s = typedarray.getString(i);
        }
        setPrimaryText(new CharSequence[] {
            s
        });
        i = R.styleable.TileView_tile_secondary_text;
        s1 = typedarray.getNonResourceString(i);
        s = s1;
        if (s1 == null)
        {
            s = typedarray.getString(i);
        }
        if (!TextUtils.isEmpty(s))
        {
            setSecondaryText(new CharSequence[] {
                s
            });
        }
        if (typedarray.hasValue(R.styleable.TileView_tile_primary_text_color))
        {
            setPrimaryTextColor(typedarray.getColor(R.styleable.TileView_tile_primary_text_color, 0));
        }
        if (typedarray.hasValue(R.styleable.TileView_tile_primary_text_size))
        {
            primaryLine.setTextSize(0, typedarray.getDimensionPixelSize(R.styleable.TileView_tile_primary_text_size, 0));
        }
        if (typedarray.hasValue(R.styleable.TileView_tile_primary_text_maxLines))
        {
            int j = typedarray.getInteger(R.styleable.TileView_tile_primary_text_maxLines, 1);
            TextView textview = primaryLine;
            textview.setEllipsize(android.text.TextUtils.TruncateAt.END);
            textview.setMaxLines(j);
        }
        if (typedarray.hasValue(R.styleable.TileView_tile_secondary_text_maxLines))
        {
            int k = typedarray.getInteger(R.styleable.TileView_tile_secondary_text_maxLines, 1);
            typedarray = getSecondaryTextView();
            typedarray.setEllipsize(android.text.TextUtils.TruncateAt.END);
            typedarray.setMaxLines(k);
        }
    }

    public View createContentView(LayoutInflater layoutinflater)
    {
        return layoutinflater.inflate(0x7f050174, this, false);
    }

    public int getActualLineCount()
    {
        int i;
        boolean flag = true;
        int j = primaryLine.getLineCount();
        if (secondaryLine != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L2; else goto _L1
_L1:
        CharSequence charsequence;
        if (secondaryLine != null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            charsequence = getSecondaryTextView().getText();
        } else
        {
            charsequence = null;
        }
        if (TextUtils.isEmpty(charsequence)) goto _L2; else goto _L3
_L3:
        i = secondaryLine.getLineCount();
_L5:
        return i + j;
_L2:
        i = 0;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public int getExpectedLineCount()
    {
        boolean flag = false;
        CharSequence charsequence = primaryLine.getText();
        int i;
        int j;
        if (TextUtils.isEmpty(charsequence))
        {
            i = 0;
        } else
        {
            i = CharMatcher.is('\n').countIn(charsequence) + 1;
        }
        if (secondaryLine != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            charsequence = getSecondaryTextView().getText();
        } else
        {
            charsequence = null;
        }
        if (TextUtils.isEmpty(charsequence))
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = CharMatcher.is('\n').countIn(charsequence) + 1;
        }
        return i + j;
    }

    protected final int getSecondaryComponentTopOffset(View view)
    {
        byte byte0 = 1;
        if (getActualLineCount() > 1)
        {
            byte0 = 2;
        }
        Tile.Dimensions dimensions = Tile.Dimensions.TEXT_AWARE_TILE_HEIGHT_INCREMENT;
        int i = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        int j = container.getTop();
        int k = primaryLine.getTop();
        return (int)((float)(byte0 * i) / 2.0F - (float)view.getMeasuredHeight() / 2.0F) + (j + k);
    }

    public final TextView getSecondaryTextView()
    {
        boolean flag;
        if (secondaryLine != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            inflate(getContext(), 0x7f050175, container);
            secondaryLine = (TextView)findViewById(0x7f100346);
        }
        return secondaryLine;
    }

    public void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        container = (ViewGroup)view;
        primaryLine = (TextView)findViewById(0x7f10029e);
    }

    public transient TextTileView setPrimaryText(CharSequence acharsequence[])
    {
        acharsequence = concatenate(acharsequence);
        primaryLine.setText(acharsequence);
        return this;
    }

    public TextTileView setPrimaryTextColor(int i)
    {
        primaryLine.setTextColor(i);
        return this;
    }

    public final transient TextTileView setSecondaryText(CharSequence acharsequence[])
    {
label0:
        {
            boolean flag = true;
            boolean flag1 = false;
            acharsequence = concatenate(acharsequence);
            int i;
            if (TextUtils.isEmpty(acharsequence))
            {
                TextView textview;
                if (secondaryLine != null)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    break label0;
                }
            }
            if (!TextUtils.isEmpty(acharsequence))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (secondaryLine == null)
            {
                flag = false;
            }
            if (flag)
            {
                textview = getSecondaryTextView();
                if (i != 0)
                {
                    i = ((flag1) ? 1 : 0);
                } else
                {
                    i = 8;
                }
                textview.setVisibility(i);
            }
            getSecondaryTextView().setText(acharsequence);
        }
        return this;
    }

    public final TextTileView setTextAdaptively(CharSequence charsequence, CharSequence charsequence1)
    {
        CharSequence charsequence2;
        if (TextUtils.isEmpty(charsequence))
        {
            charsequence2 = charsequence1;
        } else
        {
            charsequence2 = charsequence;
        }
        setPrimaryText(new CharSequence[] {
            charsequence2
        });
        if (TextUtils.isEmpty(charsequence))
        {
            charsequence1 = null;
        }
        setSecondaryText(new CharSequence[] {
            charsequence1
        });
        return this;
    }

    protected final void setupDefaultValues()
    {
        super.setupDefaultValues();
        Tile.Dimensions dimensions = Tile.Dimensions.SMALL_TEXT_AWARE_TILE_MIN_HEIGHT;
        setMinimumHeight(getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId));
    }

    public final void useTextAsContentDescription(int i)
    {
        boolean flag = true;
        String s = getContext().getString(i);
        CharSequence charsequence = primaryLine.getText();
        Object obj;
        Joiner joiner;
        if (secondaryLine != null)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            obj = getSecondaryTextView().getText();
        } else
        {
            obj = null;
        }
        joiner = (new Joiner(String.valueOf('\n'))).skipNulls();
        obj = Arrays.asList(new CharSequence[] {
            s, charsequence, obj
        }).iterator();
        setContentDescription(joiner.appendTo(new StringBuilder(), ((java.util.Iterator) (obj))).toString());
    }
}
