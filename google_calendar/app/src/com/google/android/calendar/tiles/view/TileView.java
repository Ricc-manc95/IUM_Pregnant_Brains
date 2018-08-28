// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.tiles.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class TileView extends ViewGroup
{

    public View contentView;
    public boolean denseMode;
    private int horizontalIncrement;
    public ImageView icon;
    public int iconSize;
    private boolean indentContent;
    public View rightActionView;
    private boolean treatedAsButton;

    public TileView(Context context)
    {
        super(context);
        setupDefaultValues();
        context = createContentView(LayoutInflater.from(getContext()));
        if (context != null)
        {
            contentView = context;
            addView(context);
            onContentViewSet(context);
        }
    }

    public TileView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        setupDefaultValues();
        if (attributeset == null)
        {
            return;
        }
        context = context.getTheme().obtainStyledAttributes(attributeset, R.styleable.TileView, 0, 0);
        applyAttributes(context);
        context.recycle();
        return;
        attributeset;
        context.recycle();
        throw attributeset;
    }

    public int adjustTileHeight(int i)
    {
        int ai[] = new int[3];
        Tile.Dimensions dimensions = Tile.Dimensions.SMALL_TILE_MIN_HEIGHT;
        ai[0] = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        dimensions = Tile.Dimensions.MEDIUM_TILE_MIN_HEIGHT;
        ai[1] = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        dimensions = Tile.Dimensions.LARGE_TILE_MIN_HEIGHT;
        ai[2] = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        for (int j = 0; j < 3; j++)
        {
            int l = ai[j];
            if (i <= l)
            {
                return l;
            }
        }

        dimensions = Tile.Dimensions.TILE_HEIGHT_INCREMENT;
        int k = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        return (k - (i - ai[2]) % k) + i;
    }

    protected void applyAttributes(TypedArray typedarray)
    {
        int i = typedarray.getResourceId(R.styleable.TileView_tile_content_layout, 0);
        Object obj = LayoutInflater.from(getContext());
        if (i != 0)
        {
            obj = ((LayoutInflater) (obj)).inflate(i, this, false);
        } else
        {
            obj = createContentView(((LayoutInflater) (obj)));
        }
        if (obj != null)
        {
            contentView = ((View) (obj));
            addView(((View) (obj)));
            onContentViewSet(((View) (obj)));
        }
        setIconDrawable(typedarray.getResourceId(R.styleable.TileView_tile_icon, 0));
        if (typedarray.getBoolean(R.styleable.TileView_tile_treat_as_button, treatedAsButton))
        {
            treatAsButton(true);
        }
        if (typedarray.getBoolean(R.styleable.TileView_tile_indent_layout, indentContent))
        {
            indentContent();
        }
        denseMode = typedarray.getBoolean(R.styleable.TileView_tile_dense_mode, denseMode);
        horizontalIncrement = typedarray.getInteger(R.styleable.TileView_tile_horizontal_increment, horizontalIncrement);
        i = typedarray.getResourceId(R.styleable.TileView_tile_right_action_layout, 0);
        if (i != 0)
        {
            rightActionView = LayoutInflater.from(getContext()).inflate(i, this, false);
            addView(rightActionView);
            if (rightActionView != null && !hasOnClickListeners())
            {
                treatAsButton(true);
                class .Lambda._cls1
                    implements android.view.View.OnClickListener
                {

                    private final TileView arg$1;

                    public final void onClick(View view)
                    {
                        arg$1.rightActionView.performClick();
                    }

            public .Lambda._cls1()
            {
                arg$1 = TileView.this;
            }
                }

                setOnClickListener(new .Lambda._cls1());
            }
        }
        iconSize = typedarray.getDimensionPixelSize(R.styleable.TileView_tile_icon_size, iconSize);
    }

    public View createContentView(LayoutInflater layoutinflater)
    {
        return null;
    }

    public CharSequence getAccessibilityClassName()
    {
        if (treatedAsButton)
        {
            return android/widget/Button.getName();
        } else
        {
            return super.getAccessibilityClassName();
        }
    }

    public int getContentEndPadding()
    {
        boolean flag;
        if (rightActionView != null && rightActionView.getVisibility() != 8)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return rightActionView.getMeasuredWidth();
        } else
        {
            int i = horizontalIncrement;
            com.google.android.calendar.material.Material.Dimensions dimensions = com.google.android.calendar.material.Material.Dimensions.INCREMENT;
            return i * getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        }
    }

    public int getContentStartPadding()
    {
        int j = horizontalIncrement;
        com.google.android.calendar.material.Material.Dimensions dimensions = com.google.android.calendar.material.Material.Dimensions.INCREMENT;
        int k = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        int i;
        if (indentContent)
        {
            com.google.android.calendar.material.Material.Dimensions dimensions1 = com.google.android.calendar.material.Material.Dimensions.KEYLINE_2ND_OFFSET;
            i = getContext().getResources().getDimensionPixelOffset(dimensions1.dimenResId);
        } else
        {
            i = 0;
        }
        return i + k * j;
    }

    public final ImageView getIcon()
    {
        if (icon != null)
        {
            return icon;
        } else
        {
            icon = (ImageView)LayoutInflater.from(getContext()).inflate(0x7f050176, this, false);
            addView(icon, 0);
            return icon;
        }
    }

    protected int getSecondaryComponentTopOffset(View view)
    {
        int i;
        if (denseMode)
        {
            Tile.Dimensions dimensions = Tile.Dimensions.MEDIUM_TILE_MIN_HEIGHT;
            i = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        } else
        {
            Tile.Dimensions dimensions1 = Tile.Dimensions.LARGE_TILE_MIN_HEIGHT;
            i = getContext().getResources().getDimensionPixelOffset(dimensions1.dimenResId);
        }
        if (contentView.getMeasuredHeight() > i)
        {
            return (int)((float)i / 2.0F - (float)view.getMeasuredHeight() / 2.0F);
        } else
        {
            return (int)((float)getMeasuredHeight() / 2.0F - (float)view.getMeasuredHeight() / 2.0F);
        }
    }

    public TileView indentContent()
    {
        if (contentView == null)
        {
            return this;
        } else
        {
            indentContent = true;
            return this;
        }
    }

    public void onContentViewSet(View view)
    {
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        boolean flag1 = true;
        l = 0;
        k -= i;
        if (getLayoutDirection() == 1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (contentView != null)
        {
            j = (int)((float)getMeasuredHeight() / 2.0F - (float)contentView.getMeasuredHeight() / 2.0F);
            int i1 = contentView.getMeasuredHeight();
            contentView.layout(0, j, k, i1 + j);
        }
        if (icon != null && icon.getDrawable() != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            int j1 = icon.getMeasuredWidth();
            int k1;
            if (i != 0)
            {
                j = horizontalIncrement;
                com.google.android.calendar.material.Material.Dimensions dimensions = com.google.android.calendar.material.Material.Dimensions.INCREMENT;
                j = k - j * getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId) - j1;
            } else
            {
                j = horizontalIncrement;
                com.google.android.calendar.material.Material.Dimensions dimensions1 = com.google.android.calendar.material.Material.Dimensions.INCREMENT;
                j *= getContext().getResources().getDimensionPixelOffset(dimensions1.dimenResId);
            }
            k1 = getSecondaryComponentTopOffset(icon);
            icon.layout(j, k1, j1 + j, icon.getMeasuredHeight() + k1);
        }
        if (rightActionView != null && rightActionView.getVisibility() != 8)
        {
            j = ((flag1) ? 1 : 0);
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            if (i != 0)
            {
                i = l;
            } else
            {
                i = k - rightActionView.getMeasuredWidth();
            }
            j = getSecondaryComponentTopOffset(rightActionView);
            k = rightActionView.getMeasuredHeight();
            rightActionView.layout(i, j, rightActionView.getMeasuredWidth() + i, k + j);
        }
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag1 = true;
        boolean flag = false;
        int j1 = android.view.View.MeasureSpec.getSize(i);
        if (icon != null && icon.getDrawable() != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            j = android.view.View.MeasureSpec.makeMeasureSpec(iconSize, 0x40000000);
            icon.measure(j, j);
        }
        if (rightActionView != null && rightActionView.getVisibility() != 8)
        {
            j = ((flag1) ? 1 : 0);
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            View view = rightActionView;
            com.google.android.calendar.material.Material.Dimensions dimensions = com.google.android.calendar.material.Material.Dimensions.KEYLINE_1ST;
            j = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
            int l = rightActionView.getPaddingTop();
            int k1 = horizontalIncrement;
            dimensions = com.google.android.calendar.material.Material.Dimensions.INCREMENT;
            view.setPaddingRelative(j, l, k1 * getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId), rightActionView.getPaddingBottom());
            rightActionView.measure(0, 0);
        }
        if (contentView != null)
        {
            j = getContentStartPadding();
            int i1 = getContentEndPadding();
            contentView.setPaddingRelative(j, contentView.getPaddingTop(), i1, contentView.getPaddingBottom());
            contentView.measure(i, 0);
        }
        if (contentView == null)
        {
            j = ((flag) ? 1 : 0);
        } else
        {
            j = contentView.getMeasuredHeight();
        }
        j = adjustTileHeight(Math.max(j, getMinimumHeight()));
        if (contentView != null)
        {
            int k = android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000);
            contentView.measure(i, k);
        }
        setMeasuredDimension(j1, j + (getPaddingTop() + getPaddingBottom()));
    }

    public TileView setHorizontalIncrement(int i)
    {
        if (i == 0)
        {
            i = 0;
        } else
        {
            i = getResources().getInteger(i);
        }
        horizontalIncrement = i;
        return this;
    }

    public TileView setIconDrawable(int i)
    {
        Drawable drawable;
        if (i == 0)
        {
            drawable = null;
        } else
        {
            drawable = getResources().getDrawable(i);
        }
        return setIconDrawable(drawable);
    }

    public TileView setIconDrawable(Drawable drawable)
    {
        boolean flag = false;
        int i;
        if (icon != null && icon.getDrawable() != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 || drawable != null)
        {
            getIcon().setImageDrawable(drawable);
            drawable = getIcon();
            if (icon != null && icon.getDrawable() != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            drawable.setVisibility(i);
        }
        i = ((flag) ? 1 : 0);
        if (icon != null)
        {
            i = ((flag) ? 1 : 0);
            if (icon.getDrawable() != null)
            {
                i = 1;
            }
        }
        if (i != 0)
        {
            indentContent();
        }
        return this;
    }

    public TileView setIconSize(int i)
    {
        if (i != iconSize)
        {
            boolean flag;
            if (icon != null && icon.getDrawable() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                requestLayout();
            }
        }
        iconSize = i;
        return this;
    }

    public final TileView setRightActionView(View view)
    {
        rightActionView = view;
        addView(view);
        if (rightActionView == null || hasOnClickListeners())
        {
            return this;
        } else
        {
            treatAsButton(true);
            setOnClickListener(new .Lambda._cls1());
            return this;
        }
    }

    protected void setupDefaultValues()
    {
        denseMode = true;
        indentContent = false;
        treatedAsButton = false;
        horizontalIncrement = getResources().getInteger(0x7f110021);
        Tile.Dimensions dimensions = Tile.Dimensions.TILE_ICON_SIZE;
        iconSize = getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId);
        dimensions = Tile.Dimensions.SMALL_TILE_MIN_HEIGHT;
        setMinimumHeight(getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId));
    }

    public final TileView treatAsButton(boolean flag)
    {
        TypedArray typedarray;
        if (contentView == null)
        {
            return this;
        }
        treatedAsButton = flag;
        if (!flag)
        {
            contentView.setBackground(null);
            contentView.setOnClickListener(null);
            contentView.setClickable(false);
            return this;
        }
        typedarray = getContext().getTheme().obtainStyledAttributes(new int[] {
            0x7f0100a2
        });
        int i = typedarray.getResourceId(0, 0);
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        if (contentView != null)
        {
            contentView.setBackgroundResource(i);
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final TileView arg$1;

                public final void onClick(View view)
                {
                    arg$1.callOnClick();
                }

            .Lambda._cls0()
            {
                arg$1 = TileView.this;
            }
            }

            contentView.setOnClickListener(new .Lambda._cls0());
        }
        typedarray.recycle();
        return this;
        Exception exception;
        exception;
        typedarray.recycle();
        throw exception;
    }
}
