// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v7.widget.DrawableUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class SupportMenuInflater extends MenuInflater
{

    public static final Class ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE[];
    public static final Class ACTION_VIEW_CONSTRUCTOR_SIGNATURE[];
    public final Object mActionProviderConstructorArguments[];
    public final Object mActionViewConstructorArguments[];
    public Context mContext;
    public Object mRealOwner;

    public SupportMenuInflater(Context context)
    {
        super(context);
        mContext = context;
        mActionViewConstructorArguments = (new Object[] {
            context
        });
        mActionProviderConstructorArguments = mActionViewConstructorArguments;
    }

    private final void parseMenu(XmlPullParser xmlpullparser, AttributeSet attributeset, Menu menu)
        throws XmlPullParserException, IOException
    {
        MenuState menustate;
        int i;
        boolean flag;
        menustate = new MenuState(menu);
        i = xmlpullparser.getEventType();
        flag = false;
        menu = null;
_L8:
        if (i != 2) goto _L2; else goto _L1
_L1:
        String s = xmlpullparser.getName();
        if (!s.equals("menu")) goto _L4; else goto _L3
_L3:
        int j = xmlpullparser.next();
_L7:
        i = 0;
_L6:
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_1108;
        }
        switch (j)
        {
        default:
            break;

        case 1: // '\001'
            break MISSING_BLOCK_LABEL_1097;

        case 2: // '\002'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
            break;
        }
        break MISSING_BLOCK_LABEL_912;
_L9:
        j = xmlpullparser.next();
        if (true) goto _L6; else goto _L5
_L4:
        throw new RuntimeException((new StringBuilder("Expecting menu, got ")).append(s).toString());
_L2:
        j = xmlpullparser.next();
        i = j;
        if (j != 1) goto _L8; else goto _L7
_L5:
        if (!flag)
        {
            Object obj = xmlpullparser.getName();
            if (((String) (obj)).equals("group"))
            {
                obj = menustate._fld0.mContext.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.MenuGroup);
                menustate.groupId = ((TypedArray) (obj)).getResourceId(android.support.v7.appcompat.R.styleable.MenuGroup_android_id, 0);
                menustate.groupCategory = ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuGroup_android_menuCategory, 0);
                menustate.groupOrder = ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuGroup_android_orderInCategory, 0);
                menustate.groupCheckable = ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuGroup_android_checkableBehavior, 0);
                menustate.groupVisible = ((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.MenuGroup_android_visible, true);
                menustate.groupEnabled = ((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.MenuGroup_android_enabled, true);
                ((TypedArray) (obj)).recycle();
            } else
            if (((String) (obj)).equals("item"))
            {
                obj = menustate._fld0.mContext.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.MenuItem);
                menustate.itemId = ((TypedArray) (obj)).getResourceId(android.support.v7.appcompat.R.styleable.MenuItem_android_id, 0);
                menustate.itemCategoryOrder = ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuItem_android_menuCategory, menustate.groupCategory) & 0xffff0000 | ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuItem_android_orderInCategory, menustate.groupOrder) & 0xffff;
                menustate.itemTitle = ((TypedArray) (obj)).getText(android.support.v7.appcompat.R.styleable.MenuItem_android_title);
                menustate.itemTitleCondensed = ((TypedArray) (obj)).getText(android.support.v7.appcompat.R.styleable.MenuItem_android_titleCondensed);
                menustate.itemIconResId = ((TypedArray) (obj)).getResourceId(android.support.v7.appcompat.R.styleable.MenuItem_android_icon, 0);
                String s2 = ((TypedArray) (obj)).getString(android.support.v7.appcompat.R.styleable.MenuItem_android_alphabeticShortcut);
                char c;
                int k;
                if (s2 == null)
                {
                    c = '\0';
                } else
                {
                    c = s2.charAt(0);
                }
                menustate.itemAlphabeticShortcut = c;
                menustate.itemAlphabeticModifiers = ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuItem_alphabeticModifiers, 4096);
                s2 = ((TypedArray) (obj)).getString(android.support.v7.appcompat.R.styleable.MenuItem_android_numericShortcut);
                if (s2 == null)
                {
                    c = '\0';
                } else
                {
                    c = s2.charAt(0);
                }
                menustate.itemNumericShortcut = c;
                menustate.itemNumericModifiers = ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuItem_numericModifiers, 4096);
                if (((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.MenuItem_android_checkable))
                {
                    if (((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.MenuItem_android_checkable, false))
                    {
                        k = 1;
                    } else
                    {
                        k = 0;
                    }
                    menustate.itemCheckable = k;
                } else
                {
                    menustate.itemCheckable = menustate.groupCheckable;
                }
                menustate.itemChecked = ((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.MenuItem_android_checked, false);
                menustate.itemVisible = ((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.MenuItem_android_visible, menustate.groupVisible);
                menustate.itemEnabled = ((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.MenuItem_android_enabled, menustate.groupEnabled);
                menustate.itemShowAsAction = ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuItem_showAsAction, -1);
                menustate.itemListenerMethodName = ((TypedArray) (obj)).getString(android.support.v7.appcompat.R.styleable.MenuItem_android_onClick);
                menustate.itemActionViewLayout = ((TypedArray) (obj)).getResourceId(android.support.v7.appcompat.R.styleable.MenuItem_actionLayout, 0);
                menustate.itemActionViewClassName = ((TypedArray) (obj)).getString(android.support.v7.appcompat.R.styleable.MenuItem_actionViewClass);
                menustate.itemActionProviderClassName = ((TypedArray) (obj)).getString(android.support.v7.appcompat.R.styleable.MenuItem_actionProviderClass);
                if (menustate.itemActionProviderClassName != null)
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0 && menustate.itemActionViewLayout == 0 && menustate.itemActionViewClassName == null)
                {
                    menustate.itemActionProvider = (ActionProvider)menustate.newInstance(menustate.itemActionProviderClassName, ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, menustate._fld0.mActionProviderConstructorArguments);
                } else
                {
                    if (k != 0)
                    {
                        Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                    }
                    menustate.itemActionProvider = null;
                }
                menustate.itemContentDescription = ((TypedArray) (obj)).getText(android.support.v7.appcompat.R.styleable.MenuItem_contentDescription);
                menustate.itemTooltipText = ((TypedArray) (obj)).getText(android.support.v7.appcompat.R.styleable.MenuItem_tooltipText);
                if (((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.MenuItem_iconTintMode))
                {
                    menustate.itemIconTintMode = DrawableUtils.parseTintMode(((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.MenuItem_iconTintMode, -1), menustate.itemIconTintMode);
                } else
                {
                    menustate.itemIconTintMode = null;
                }
                if (((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.MenuItem_iconTint))
                {
                    menustate.itemIconTintList = ((TypedArray) (obj)).getColorStateList(android.support.v7.appcompat.R.styleable.MenuItem_iconTint);
                } else
                {
                    menustate.itemIconTintList = null;
                }
                ((TypedArray) (obj)).recycle();
                menustate.itemAdded = false;
            } else
            if (((String) (obj)).equals("menu"))
            {
                parseMenu(xmlpullparser, attributeset, ((Menu) (menustate.addSubMenuItem())));
            } else
            {
                flag = true;
                menu = ((Menu) (obj));
            }
        }
          goto _L9
        String s1 = xmlpullparser.getName();
        if (flag && s1.equals(menu))
        {
            flag = false;
            menu = null;
        } else
        if (s1.equals("group"))
        {
            menustate.groupId = 0;
            menustate.groupCategory = 0;
            menustate.groupOrder = 0;
            menustate.groupCheckable = 0;
            menustate.groupVisible = true;
            menustate.groupEnabled = true;
        } else
        if (s1.equals("item"))
        {
            if (!menustate.itemAdded)
            {
                if (menustate.itemActionProvider != null && menustate.itemActionProvider.hasSubMenu())
                {
                    menustate.addSubMenuItem();
                } else
                {
                    menustate.itemAdded = true;
                    menustate.setItem(menustate.menu.add(menustate.groupId, menustate.itemId, menustate.itemCategoryOrder, menustate.itemTitle));
                }
            }
        } else
        if (s1.equals("menu"))
        {
            i = 1;
        }
          goto _L9
        throw new RuntimeException("Unexpected end of document");
    }

    final Object findRealOwner(Object obj)
    {
        while (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) 
        {
            obj = ((ContextWrapper)obj).getBaseContext();
        }
        return obj;
    }

    public final void inflate(int i, Menu menu)
    {
        if (menu instanceof SupportMenu) goto _L2; else goto _L1
_L1:
        super.inflate(i, menu);
_L4:
        return;
_L2:
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        XmlResourceParser xmlresourceparser2;
        xmlresourceparser = null;
        xmlresourceparser2 = null;
        xmlresourceparser1 = null;
        XmlResourceParser xmlresourceparser3 = mContext.getResources().getLayout(i);
        xmlresourceparser1 = xmlresourceparser3;
        xmlresourceparser = xmlresourceparser3;
        xmlresourceparser2 = xmlresourceparser3;
        parseMenu(xmlresourceparser3, Xml.asAttributeSet(xmlresourceparser3), menu);
        if (xmlresourceparser3 != null)
        {
            xmlresourceparser3.close();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        menu;
        xmlresourceparser = xmlresourceparser1;
        throw new InflateException("Error inflating menu XML", menu);
        menu;
        if (xmlresourceparser != null)
        {
            xmlresourceparser.close();
        }
        throw menu;
        menu;
        xmlresourceparser = xmlresourceparser2;
        throw new InflateException("Error inflating menu XML", menu);
    }

    static 
    {
        Class aclass[] = new Class[1];
        aclass[0] = android/content/Context;
        ACTION_VIEW_CONSTRUCTOR_SIGNATURE = aclass;
        ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = aclass;
    }

    private class MenuState
    {

        public int groupCategory;
        public int groupCheckable;
        public boolean groupEnabled;
        public int groupId;
        public int groupOrder;
        public boolean groupVisible;
        public ActionProvider itemActionProvider;
        public String itemActionProviderClassName;
        public String itemActionViewClassName;
        public int itemActionViewLayout;
        public boolean itemAdded;
        public int itemAlphabeticModifiers;
        public char itemAlphabeticShortcut;
        public int itemCategoryOrder;
        public int itemCheckable;
        public boolean itemChecked;
        public CharSequence itemContentDescription;
        public boolean itemEnabled;
        public int itemIconResId;
        public ColorStateList itemIconTintList;
        public android.graphics.PorterDuff.Mode itemIconTintMode;
        public int itemId;
        public String itemListenerMethodName;
        public int itemNumericModifiers;
        public char itemNumericShortcut;
        public int itemShowAsAction;
        public CharSequence itemTitle;
        public CharSequence itemTitleCondensed;
        public CharSequence itemTooltipText;
        public boolean itemVisible;
        public Menu menu;
        public final SupportMenuInflater this$0;

        public final SubMenu addSubMenuItem()
        {
            itemAdded = true;
            SubMenu submenu = menu.addSubMenu(groupId, itemId, itemCategoryOrder, itemTitle);
            setItem(submenu.getItem());
            return submenu;
        }

        final Object newInstance(String s, Class aclass[], Object aobj[])
        {
            try
            {
                aclass = mContext.getClassLoader().loadClass(s).getConstructor(aclass);
                aclass.setAccessible(true);
                aclass = ((Class []) (aclass.newInstance(aobj)));
            }
            // Misplaced declaration of an exception variable
            catch (Class aclass[])
            {
                Log.w("SupportMenuInflater", (new StringBuilder("Cannot instantiate class: ")).append(s).toString(), aclass);
                return null;
            }
            return aclass;
        }

        final void setItem(MenuItem menuitem)
        {
            int i = 1;
            Object obj = menuitem.setChecked(itemChecked).setVisible(itemVisible).setEnabled(itemEnabled);
            boolean flag;
            if (itemCheckable > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            ((MenuItem) (obj)).setCheckable(flag).setTitleCondensed(itemTitleCondensed).setIcon(itemIconResId);
            if (itemShowAsAction >= 0)
            {
                menuitem.setShowAsAction(itemShowAsAction);
            }
            if (itemListenerMethodName != null)
            {
                if (mContext.isRestricted())
                {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                SupportMenuInflater supportmenuinflater = SupportMenuInflater.this;
                if (supportmenuinflater.mRealOwner == null)
                {
                    Context context = supportmenuinflater.mContext;
                    obj = context;
                    if (!(context instanceof Activity))
                    {
                        obj = context;
                        if (context instanceof ContextWrapper)
                        {
                            context = ((ContextWrapper)context).getBaseContext();
                            obj = context;
                            if (!(context instanceof Activity))
                            {
                                obj = context;
                                if (context instanceof ContextWrapper)
                                {
                                    obj = supportmenuinflater.findRealOwner(((ContextWrapper)context).getBaseContext());
                                }
                            }
                        }
                    }
                    supportmenuinflater.mRealOwner = obj;
                }
                menuitem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(supportmenuinflater.mRealOwner, itemListenerMethodName));
            }
            char c;
            if (itemCheckable >= 2)
            {
                if (menuitem instanceof MenuItemImpl)
                {
                    obj = (MenuItemImpl)menuitem;
                    obj.mFlags = ((MenuItemImpl) (obj)).mFlags & -5 | 4;
                } else
                if (menuitem instanceof MenuItemWrapperICS)
                {
                    obj = (MenuItemWrapperICS)menuitem;
                    try
                    {
                        if (((MenuItemWrapperICS) (obj)).mSetExclusiveCheckableMethod == null)
                        {
                            obj.mSetExclusiveCheckableMethod = ((SupportMenuItem)((MenuItemWrapperICS) (obj)).mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[] {
                                Boolean.TYPE
                            });
                        }
                        ((MenuItemWrapperICS) (obj)).mSetExclusiveCheckableMethod.invoke(((MenuItemWrapperICS) (obj)).mWrappedObject, new Object[] {
                            Boolean.valueOf(true)
                        });
                    }
                    // Misplaced declaration of an exception variable
                    catch (Object obj)
                    {
                        Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", ((Throwable) (obj)));
                    }
                }
            }
            if (itemActionViewClassName != null)
            {
                menuitem.setActionView((View)newInstance(itemActionViewClassName, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, mActionViewConstructorArguments));
            } else
            {
                i = 0;
            }
            if (itemActionViewLayout > 0)
            {
                if (i == 0)
                {
                    menuitem.setActionView(itemActionViewLayout);
                } else
                {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            if (itemActionProvider != null)
            {
                obj = itemActionProvider;
                if (menuitem instanceof SupportMenuItem)
                {
                    ((SupportMenuItem)menuitem).setSupportActionProvider(((ActionProvider) (obj)));
                } else
                {
                    Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
                }
            }
            obj = itemContentDescription;
            if (menuitem instanceof SupportMenuItem)
            {
                ((SupportMenuItem)menuitem).setContentDescription(((CharSequence) (obj)));
            } else
            if (android.os.Build.VERSION.SDK_INT >= 26)
            {
                menuitem.setContentDescription(((CharSequence) (obj)));
            }
            obj = itemTooltipText;
            if (menuitem instanceof SupportMenuItem)
            {
                ((SupportMenuItem)menuitem).setTooltipText(((CharSequence) (obj)));
            } else
            if (android.os.Build.VERSION.SDK_INT >= 26)
            {
                menuitem.setTooltipText(((CharSequence) (obj)));
            }
            c = itemAlphabeticShortcut;
            i = itemAlphabeticModifiers;
            if (menuitem instanceof SupportMenuItem)
            {
                ((SupportMenuItem)menuitem).setAlphabeticShortcut(c, i);
            } else
            if (android.os.Build.VERSION.SDK_INT >= 26)
            {
                menuitem.setAlphabeticShortcut(c, i);
            }
            c = itemNumericShortcut;
            i = itemNumericModifiers;
            if (menuitem instanceof SupportMenuItem)
            {
                ((SupportMenuItem)menuitem).setNumericShortcut(c, i);
            } else
            if (android.os.Build.VERSION.SDK_INT >= 26)
            {
                menuitem.setNumericShortcut(c, i);
            }
            if (itemIconTintMode != null)
            {
                obj = itemIconTintMode;
                if (menuitem instanceof SupportMenuItem)
                {
                    ((SupportMenuItem)menuitem).setIconTintMode(((android.graphics.PorterDuff.Mode) (obj)));
                } else
                if (android.os.Build.VERSION.SDK_INT >= 26)
                {
                    menuitem.setIconTintMode(((android.graphics.PorterDuff.Mode) (obj)));
                }
            }
            if (itemIconTintList == null) goto _L2; else goto _L1
_L1:
            obj = itemIconTintList;
            if (!(menuitem instanceof SupportMenuItem)) goto _L4; else goto _L3
_L3:
            ((SupportMenuItem)menuitem).setIconTintList(((ColorStateList) (obj)));
_L2:
            return;
_L4:
            if (android.os.Build.VERSION.SDK_INT < 26) goto _L2; else goto _L5
_L5:
            menuitem.setIconTintList(((ColorStateList) (obj)));
            return;
            if (true) goto _L7; else goto _L6
_L7:
            break MISSING_BLOCK_LABEL_384;
_L6:
        }

        public MenuState(Menu menu1)
        {
            this$0 = SupportMenuInflater.this;
            super();
            itemIconTintList = null;
            itemIconTintMode = null;
            menu = menu1;
            groupId = 0;
            groupCategory = 0;
            groupOrder = 0;
            groupCheckable = 0;
            groupVisible = true;
            groupEnabled = true;
        }

        private class InflatedOnMenuItemClickListener
            implements android.view.MenuItem.OnMenuItemClickListener
        {

            private static final Class PARAM_TYPES[] = {
                android/view/MenuItem
            };
            private Method mMethod;
            private Object mRealOwner;

            public final boolean onMenuItemClick(MenuItem menuitem)
            {
                try
                {
                    if (mMethod.getReturnType() == Boolean.TYPE)
                    {
                        return ((Boolean)mMethod.invoke(mRealOwner, new Object[] {
                            menuitem
                        })).booleanValue();
                    }
                    mMethod.invoke(mRealOwner, new Object[] {
                        menuitem
                    });
                }
                // Misplaced declaration of an exception variable
                catch (MenuItem menuitem)
                {
                    throw new RuntimeException(menuitem);
                }
                return true;
            }


            public InflatedOnMenuItemClickListener(Object obj, String s)
            {
                mRealOwner = obj;
                Class class1 = obj.getClass();
                try
                {
                    mMethod = class1.getMethod(s, PARAM_TYPES);
                    return;
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    s = new InflateException((new StringBuilder("Couldn't resolve menu item onClick handler ")).append(s).append(" in class ").append(class1.getName()).toString());
                }
                s.initCause(((Throwable) (obj)));
                throw s;
            }
        }

    }

}
