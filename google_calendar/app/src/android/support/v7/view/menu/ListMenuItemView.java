// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

// Referenced classes of package android.support.v7.view.menu:
//            MenuItemImpl, MenuBuilder

public class ListMenuItemView extends LinearLayout
    implements MenuView.ItemView, android.widget.AbsListView.SelectionBoundsAdjuster
{

    private Drawable mBackground;
    private CheckBox mCheckBox;
    private LinearLayout mContent;
    public boolean mForceShowIcon;
    public ImageView mGroupDivider;
    public boolean mHasListDivider;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    public MenuItemImpl mItemData;
    public boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;

    public ListMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100e0);
    }

    public ListMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset);
        Context context1 = getContext();
        attributeset = new TintTypedArray(context1, context1.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.MenuView, i, 0));
        mBackground = attributeset.getDrawable(android.support.v7.appcompat.R.styleable.MenuView_android_itemBackground);
        i = android.support.v7.appcompat.R.styleable.MenuView_android_itemTextAppearance;
        mTextAppearance = ((TintTypedArray) (attributeset)).mWrapped.getResourceId(i, -1);
        i = android.support.v7.appcompat.R.styleable.MenuView_preserveIconSpacing;
        mPreserveIconSpacing = ((TintTypedArray) (attributeset)).mWrapped.getBoolean(i, false);
        mTextAppearanceContext = context;
        mSubMenuArrow = attributeset.getDrawable(android.support.v7.appcompat.R.styleable.MenuView_subMenuArrow);
        context = context.getTheme().obtainStyledAttributes(null, new int[] {
            0x1010129
        }, 0x7f0100b8, 0);
        mHasListDivider = context.hasValue(0);
        ((TintTypedArray) (attributeset)).mWrapped.recycle();
        context.recycle();
    }

    public void adjustListItemSelectionBounds(Rect rect)
    {
        if (mGroupDivider != null && mGroupDivider.getVisibility() == 0)
        {
            android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)mGroupDivider.getLayoutParams();
            int i = rect.top;
            int j = mGroupDivider.getHeight();
            int k = layoutparams.topMargin;
            rect.top = layoutparams.bottomMargin + (j + k) + i;
        }
    }

    public final MenuItemImpl getItemData()
    {
        return mItemData;
    }

    public final void initialize(MenuItemImpl menuitemimpl, int i)
    {
        char c;
        Object obj;
        Object obj1;
        Object obj2;
        boolean flag1 = true;
        boolean flag = false;
        mItemData = menuitemimpl;
        boolean flag2;
        if (menuitemimpl.isVisible())
        {
            i = 0;
        } else
        {
            i = 8;
        }
        setVisibility(i);
        if (this != null && prefersCondensedTitle())
        {
            obj = menuitemimpl.getTitleCondensed();
        } else
        {
            obj = menuitemimpl.getTitle();
        }
        if (obj != null)
        {
            mTitleView.setText(((CharSequence) (obj)));
            if (mTitleView.getVisibility() != 0)
            {
                mTitleView.setVisibility(0);
            }
        } else
        if (mTitleView.getVisibility() != 8)
        {
            mTitleView.setVisibility(8);
        }
        flag2 = menuitemimpl.isCheckable();
        if (flag2 || mRadioButton != null || mCheckBox != null)
        {
            if ((mItemData.mFlags & 4) != 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                if (mRadioButton == null)
                {
                    if (mInflater == null)
                    {
                        mInflater = LayoutInflater.from(getContext());
                    }
                    mRadioButton = (RadioButton)mInflater.inflate(0x7f050011, this, false);
                    obj = mRadioButton;
                    char c1;
                    if (mContent != null)
                    {
                        mContent.addView(((android.view.View) (obj)), -1);
                    } else
                    {
                        addView(((android.view.View) (obj)), -1);
                    }
                }
                obj1 = mRadioButton;
                obj = mCheckBox;
            } else
            {
                if (mCheckBox == null)
                {
                    if (mInflater == null)
                    {
                        mInflater = LayoutInflater.from(getContext());
                    }
                    mCheckBox = (CheckBox)mInflater.inflate(0x7f05000e, this, false);
                    obj = mCheckBox;
                    if (mContent != null)
                    {
                        mContent.addView(((android.view.View) (obj)), -1);
                    } else
                    {
                        addView(((android.view.View) (obj)), -1);
                    }
                }
                obj1 = mCheckBox;
                obj = mRadioButton;
            }
            if (flag2)
            {
                ((CompoundButton) (obj1)).setChecked(mItemData.isChecked());
                if (((CompoundButton) (obj1)).getVisibility() != 0)
                {
                    ((CompoundButton) (obj1)).setVisibility(0);
                }
                if (obj != null && ((CompoundButton) (obj)).getVisibility() != 8)
                {
                    ((CompoundButton) (obj)).setVisibility(8);
                }
            } else
            {
                if (mCheckBox != null)
                {
                    mCheckBox.setVisibility(8);
                }
                if (mRadioButton != null)
                {
                    mRadioButton.setVisibility(8);
                }
            }
        }
        if (!menuitemimpl.mMenu.isShortcutsVisible()) goto _L2; else goto _L1
_L1:
        if (menuitemimpl.mMenu.isQwertyMode())
        {
            i = menuitemimpl.mShortcutAlphabeticChar;
        } else
        {
            i = menuitemimpl.mShortcutNumericChar;
        }
        if (i == 0) goto _L2; else goto _L3
_L3:
        i = 1;
_L14:
        if (menuitemimpl.mMenu.isQwertyMode())
        {
            c1 = menuitemimpl.mShortcutAlphabeticChar;
        } else
        {
            c2 = menuitemimpl.mShortcutNumericChar;
        }
        if (i == 0) goto _L5; else goto _L4
_L4:
        obj = mItemData;
        if (!((MenuItemImpl) (obj)).mMenu.isShortcutsVisible()) goto _L7; else goto _L6
_L6:
        if (((MenuItemImpl) (obj)).mMenu.isQwertyMode())
        {
            i = ((MenuItemImpl) (obj)).mShortcutAlphabeticChar;
        } else
        {
            i = ((MenuItemImpl) (obj)).mShortcutNumericChar;
        }
        if (i == 0) goto _L7; else goto _L8
_L8:
        i = 1;
_L15:
        if (i == 0) goto _L5; else goto _L9
_L9:
        i = 0;
_L16:
        if (i != 0) goto _L11; else goto _L10
_L10:
        obj1 = mShortcutView;
        obj2 = mItemData;
        char c2;
        if (((MenuItemImpl) (obj2)).mMenu.isQwertyMode())
        {
            c = ((MenuItemImpl) (obj2)).mShortcutAlphabeticChar;
        } else
        {
            c = ((MenuItemImpl) (obj2)).mShortcutNumericChar;
        }
        if (c != 0) goto _L13; else goto _L12
_L12:
        obj = "";
_L21:
        ((TextView) (obj1)).setText(((CharSequence) (obj)));
_L11:
        if (mShortcutView.getVisibility() != i)
        {
            mShortcutView.setVisibility(i);
        }
        obj = menuitemimpl.getIcon();
        obj1 = mItemData.mMenu;
        if (mForceShowIcon)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0;
        }
        if ((i != 0 || mPreserveIconSpacing) && (mIconView != null || obj != null || mPreserveIconSpacing))
        {
            if (mIconView == null)
            {
                if (mInflater == null)
                {
                    mInflater = LayoutInflater.from(getContext());
                }
                mIconView = (ImageView)mInflater.inflate(0x7f05000f, this, false);
                obj1 = mIconView;
                StringBuilder stringbuilder;
                int j;
                if (mContent != null)
                {
                    mContent.addView(((android.view.View) (obj1)), 0);
                } else
                {
                    addView(((android.view.View) (obj1)), 0);
                }
            }
            if (obj != null || mPreserveIconSpacing)
            {
                obj1 = mIconView;
                if (i == 0)
                {
                    obj = null;
                }
                ((ImageView) (obj1)).setImageDrawable(((Drawable) (obj)));
                if (mIconView.getVisibility() != 0)
                {
                    mIconView.setVisibility(0);
                }
            } else
            {
                mIconView.setVisibility(8);
            }
        }
        setEnabled(menuitemimpl.isEnabled());
        flag2 = menuitemimpl.hasSubMenu();
        if (mSubMenuArrowView != null)
        {
            obj = mSubMenuArrowView;
            if (flag2)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = 8;
            }
            ((ImageView) (obj)).setVisibility(i);
        }
        setContentDescription(menuitemimpl.getContentDescription());
        return;
_L2:
        i = 0;
          goto _L14
_L7:
        i = 0;
          goto _L15
_L5:
        i = 8;
          goto _L16
_L13:
        obj = ((MenuItemImpl) (obj2)).mMenu.mContext.getResources();
        stringbuilder = new StringBuilder();
        if (ViewConfiguration.get(((MenuItemImpl) (obj2)).mMenu.mContext).hasPermanentMenuKey())
        {
            stringbuilder.append(((Resources) (obj)).getString(0x7f13003c));
        }
        if (((MenuItemImpl) (obj2)).mMenu.isQwertyMode())
        {
            j = ((MenuItemImpl) (obj2)).mShortcutAlphabeticModifiers;
        } else
        {
            j = ((MenuItemImpl) (obj2)).mShortcutNumericModifiers;
        }
        obj2 = ((Resources) (obj)).getString(0x7f130038);
        if ((j & 0x10000) == 0x10000)
        {
            stringbuilder.append(((String) (obj2)));
        }
        obj2 = ((Resources) (obj)).getString(0x7f130034);
        if ((j & 0x1000) == 4096)
        {
            stringbuilder.append(((String) (obj2)));
        }
        obj2 = ((Resources) (obj)).getString(0x7f130033);
        if ((j & 2) == 2)
        {
            stringbuilder.append(((String) (obj2)));
        }
        obj2 = ((Resources) (obj)).getString(0x7f130039);
        if ((j & 1) == 1)
        {
            stringbuilder.append(((String) (obj2)));
        }
        obj2 = ((Resources) (obj)).getString(0x7f13003b);
        if ((j & 4) == 4)
        {
            stringbuilder.append(((String) (obj2)));
        }
        obj2 = ((Resources) (obj)).getString(0x7f130037);
        if ((j & 8) == 8)
        {
            stringbuilder.append(((String) (obj2)));
        }
        c;
        JVM INSTR lookupswitch 3: default 1136
    //                   8: 1180
    //                   10: 1163
    //                   32: 1197;
           goto _L17 _L18 _L19 _L20
_L20:
        break MISSING_BLOCK_LABEL_1197;
_L17:
        stringbuilder.append(c);
_L22:
        obj = stringbuilder.toString();
          goto _L21
_L19:
        stringbuilder.append(((Resources) (obj)).getString(0x7f130036));
          goto _L22
_L18:
        stringbuilder.append(((Resources) (obj)).getString(0x7f130035));
          goto _L22
        stringbuilder.append(((Resources) (obj)).getString(0x7f13003a));
          goto _L22
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        ViewCompat.setBackground(this, mBackground);
        mTitleView = (TextView)findViewById(0x7f100047);
        if (mTextAppearance != -1)
        {
            mTitleView.setTextAppearance(mTextAppearanceContext, mTextAppearance);
        }
        mShortcutView = (TextView)findViewById(0x7f1000e6);
        mSubMenuArrowView = (ImageView)findViewById(0x7f1000e7);
        if (mSubMenuArrowView != null)
        {
            mSubMenuArrowView.setImageDrawable(mSubMenuArrow);
        }
        mGroupDivider = (ImageView)findViewById(0x7f1000e4);
        mContent = (LinearLayout)findViewById(0x7f1000e5);
    }

    protected void onMeasure(int i, int j)
    {
        if (mIconView != null && mPreserveIconSpacing)
        {
            android.view.ViewGroup.LayoutParams layoutparams = getLayoutParams();
            android.widget.LinearLayout.LayoutParams layoutparams1 = (android.widget.LinearLayout.LayoutParams)mIconView.getLayoutParams();
            if (layoutparams.height > 0 && layoutparams1.width <= 0)
            {
                layoutparams1.width = layoutparams.height;
            }
        }
        super.onMeasure(i, j);
    }

    public final boolean prefersCondensedTitle()
    {
        return false;
    }
}
