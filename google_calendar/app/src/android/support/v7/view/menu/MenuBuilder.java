// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.view.menu:
//            MenuItemImpl, SubMenuBuilder, MenuPresenter

public class MenuBuilder
    implements SupportMenu
{

    private static final int sCategoryToOrder[] = {
        1, 4, 5, 3, 2, 0
    };
    public ArrayList mActionItems;
    public Callback mCallback;
    public final Context mContext;
    public int mDefaultShowAsAction;
    public MenuItemImpl mExpandedItem;
    private boolean mGroupDividerEnabled;
    public Drawable mHeaderIcon;
    public CharSequence mHeaderTitle;
    public View mHeaderView;
    public boolean mIsActionItemsStale;
    private boolean mIsClosing;
    public boolean mIsVisibleItemsStale;
    public ArrayList mItems;
    public boolean mItemsChangedWhileDispatchPrevented;
    public ArrayList mNonActionItems;
    private boolean mOptionalIconsVisible;
    public boolean mOverrideVisibleItems;
    public CopyOnWriteArrayList mPresenters;
    public boolean mPreventDispatchingItemsChanged;
    private boolean mQwertyMode;
    private final Resources mResources;
    private boolean mShortcutsVisible;
    public boolean mStructureChangedWhileDispatchPrevented;
    private ArrayList mTempShortcutItemList;
    private ArrayList mVisibleItems;

    public MenuBuilder(Context context)
    {
        boolean flag = true;
        super();
        mDefaultShowAsAction = 0;
        mPreventDispatchingItemsChanged = false;
        mItemsChangedWhileDispatchPrevented = false;
        mStructureChangedWhileDispatchPrevented = false;
        mOptionalIconsVisible = false;
        mIsClosing = false;
        mTempShortcutItemList = new ArrayList();
        mPresenters = new CopyOnWriteArrayList();
        mGroupDividerEnabled = false;
        mContext = context;
        mResources = context.getResources();
        mItems = new ArrayList();
        mVisibleItems = new ArrayList();
        mIsVisibleItemsStale = true;
        mActionItems = new ArrayList();
        mNonActionItems = new ArrayList();
        mIsActionItemsStale = true;
        if (mResources.getConfiguration().keyboard == 1 || !ViewConfigurationCompat.shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration.get(mContext), mContext))
        {
            flag = false;
        }
        mShortcutsVisible = flag;
    }

    private final MenuItem addInternal(int i, int j, int k, CharSequence charsequence)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        int l;
        l = k >> 16;
        if (l < 0 || l >= sCategoryToOrder.length)
        {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        l = sCategoryToOrder[l] << 16 | 0xffff & k;
        charsequence = new MenuItemImpl(this, i, j, k, l, charsequence, mDefaultShowAsAction);
        arraylist = mItems;
        arraylist1 = mItems;
        i = arraylist1.size() - 1;
_L3:
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_135;
        }
        if (((MenuItemImpl)arraylist1.get(i)).mOrdering > l) goto _L2; else goto _L1
_L1:
        i++;
_L4:
        arraylist.add(i, charsequence);
        onItemsChanged(true);
        return charsequence;
_L2:
        i--;
          goto _L3
        i = 0;
          goto _L4
    }

    private final MenuItemImpl findItemWithShortcutForKey(int i, KeyEvent keyevent)
    {
        ArrayList arraylist;
        arraylist = mTempShortcutItemList;
        arraylist.clear();
        findItemsWithShortcutForKey(arraylist, i, keyevent);
        if (!arraylist.isEmpty()) goto _L2; else goto _L1
_L1:
        keyevent = null;
_L6:
        return keyevent;
_L2:
        android.view.KeyCharacterMap.KeyData keydata;
        int j;
        int k;
        int l;
        boolean flag;
        k = keyevent.getMetaState();
        keydata = new android.view.KeyCharacterMap.KeyData();
        keyevent.getKeyData(keydata);
        l = arraylist.size();
        if (l == 1)
        {
            return (MenuItemImpl)arraylist.get(0);
        }
        flag = isQwertyMode();
        j = 0;
_L9:
        if (j >= l) goto _L4; else goto _L3
_L3:
        MenuItemImpl menuitemimpl = (MenuItemImpl)arraylist.get(j);
        char c;
        if (flag)
        {
            c = menuitemimpl.getAlphabeticShortcut();
        } else
        {
            c = menuitemimpl.getNumericShortcut();
        }
        if (c != keydata.meta[0])
        {
            break; /* Loop/switch isn't completed */
        }
        keyevent = menuitemimpl;
        if ((k & 2) == 0) goto _L6; else goto _L5
_L5:
        if (c != keydata.meta[2])
        {
            break; /* Loop/switch isn't completed */
        }
        keyevent = menuitemimpl;
        if ((k & 2) != 0) goto _L6; else goto _L7
_L7:
        if (!flag || c != '\b')
        {
            continue; /* Loop/switch isn't completed */
        }
        keyevent = menuitemimpl;
        if (i == 67) goto _L6; else goto _L8
_L8:
        j++;
          goto _L9
_L4:
        return null;
    }

    private final void findItemsWithShortcutForKey(List list, int i, KeyEvent keyevent)
    {
        boolean flag = isQwertyMode();
        int l = keyevent.getModifiers();
        android.view.KeyCharacterMap.KeyData keydata = new android.view.KeyCharacterMap.KeyData();
        if (keyevent.getKeyData(keydata) || i == 67)
        {
            int i1 = mItems.size();
            int j = 0;
            while (j < i1) 
            {
                MenuItemImpl menuitemimpl = (MenuItemImpl)mItems.get(j);
                if (menuitemimpl.hasSubMenu())
                {
                    ((MenuBuilder)menuitemimpl.getSubMenu()).findItemsWithShortcutForKey(list, i, keyevent);
                }
                char c;
                int k;
                if (flag)
                {
                    c = menuitemimpl.getAlphabeticShortcut();
                } else
                {
                    c = menuitemimpl.getNumericShortcut();
                }
                if (flag)
                {
                    k = menuitemimpl.getAlphabeticModifiers();
                } else
                {
                    k = menuitemimpl.getNumericModifiers();
                }
                if ((l & 0x1100f) == (k & 0x1100f))
                {
                    k = 1;
                } else
                {
                    k = 0;
                }
                if (k != 0 && c != 0 && (c == keydata.meta[0] || c == keydata.meta[2] || flag && c == '\b' && i == 67) && menuitemimpl.isEnabled())
                {
                    list.add(menuitemimpl);
                }
                j++;
            }
        }
    }

    private final void removeItemAtInt(int i, boolean flag)
    {
        if (i >= 0 && i < mItems.size())
        {
            mItems.remove(i);
            if (flag)
            {
                onItemsChanged(true);
                return;
            }
        }
    }

    public MenuItem add(int i)
    {
        return addInternal(0, 0, 0, mResources.getString(i));
    }

    public MenuItem add(int i, int j, int k, int l)
    {
        return addInternal(i, j, k, mResources.getString(l));
    }

    public MenuItem add(int i, int j, int k, CharSequence charsequence)
    {
        return addInternal(i, j, k, charsequence);
    }

    public MenuItem add(CharSequence charsequence)
    {
        return addInternal(0, 0, 0, charsequence);
    }

    public int addIntentOptions(int i, int j, int k, ComponentName componentname, Intent aintent[], Intent intent, int l, 
            MenuItem amenuitem[])
    {
        PackageManager packagemanager = mContext.getPackageManager();
        List list = packagemanager.queryIntentActivityOptions(componentname, aintent, intent, 0);
        int i1;
        if (list != null)
        {
            i1 = list.size();
        } else
        {
            i1 = 0;
        }
        if ((l & 1) == 0)
        {
            removeGroup(i);
        }
        l = 0;
        while (l < i1) 
        {
            ResolveInfo resolveinfo = (ResolveInfo)list.get(l);
            if (resolveinfo.specificIndex < 0)
            {
                componentname = intent;
            } else
            {
                componentname = aintent[resolveinfo.specificIndex];
            }
            componentname = new Intent(componentname);
            componentname.setComponent(new ComponentName(resolveinfo.activityInfo.applicationInfo.packageName, resolveinfo.activityInfo.name));
            componentname = add(i, j, k, resolveinfo.loadLabel(packagemanager)).setIcon(resolveinfo.loadIcon(packagemanager)).setIntent(componentname);
            if (amenuitem != null && resolveinfo.specificIndex >= 0)
            {
                amenuitem[resolveinfo.specificIndex] = componentname;
            }
            l++;
        }
        return i1;
    }

    public SubMenu addSubMenu(int i)
    {
        return addSubMenu(0, 0, 0, ((CharSequence) (mResources.getString(i))));
    }

    public SubMenu addSubMenu(int i, int j, int k, int l)
    {
        return addSubMenu(i, j, k, ((CharSequence) (mResources.getString(l))));
    }

    public SubMenu addSubMenu(int i, int j, int k, CharSequence charsequence)
    {
        charsequence = (MenuItemImpl)addInternal(i, j, k, charsequence);
        SubMenuBuilder submenubuilder = new SubMenuBuilder(mContext, this, charsequence);
        charsequence.mSubMenu = submenubuilder;
        submenubuilder.setHeaderTitle(charsequence.getTitle());
        return submenubuilder;
    }

    public SubMenu addSubMenu(CharSequence charsequence)
    {
        return addSubMenu(0, 0, 0, charsequence);
    }

    public void clear()
    {
        if (mExpandedItem != null)
        {
            collapseItemActionView(mExpandedItem);
        }
        mItems.clear();
        onItemsChanged(true);
    }

    public void clearHeader()
    {
        mHeaderIcon = null;
        mHeaderTitle = null;
        mHeaderView = null;
        onItemsChanged(false);
    }

    public void close()
    {
        close(true);
    }

    public final void close(boolean flag)
    {
        if (mIsClosing)
        {
            return;
        }
        mIsClosing = true;
        for (Iterator iterator = mPresenters.iterator(); iterator.hasNext();)
        {
            WeakReference weakreference = (WeakReference)iterator.next();
            MenuPresenter menupresenter = (MenuPresenter)weakreference.get();
            if (menupresenter == null)
            {
                mPresenters.remove(weakreference);
            } else
            {
                menupresenter.onCloseMenu(this, flag);
            }
        }

        mIsClosing = false;
    }

    public boolean collapseItemActionView(MenuItemImpl menuitemimpl)
    {
        boolean flag1;
        if (mPresenters.isEmpty() || mExpandedItem != menuitemimpl)
        {
            flag1 = false;
        } else
        {
            if (!mPreventDispatchingItemsChanged)
            {
                mPreventDispatchingItemsChanged = true;
                mItemsChangedWhileDispatchPrevented = false;
                mStructureChangedWhileDispatchPrevented = false;
            }
            Iterator iterator = mPresenters.iterator();
            boolean flag = false;
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                WeakReference weakreference = (WeakReference)iterator.next();
                MenuPresenter menupresenter = (MenuPresenter)weakreference.get();
                if (menupresenter == null)
                {
                    mPresenters.remove(weakreference);
                    continue;
                }
                flag1 = menupresenter.collapseItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(menuitemimpl);
                flag = flag1;
                if (flag1)
                {
                    break;
                }
                flag = flag1;
            } while (true);
            mPreventDispatchingItemsChanged = false;
            if (mItemsChangedWhileDispatchPrevented)
            {
                mItemsChangedWhileDispatchPrevented = false;
                onItemsChanged(mStructureChangedWhileDispatchPrevented);
            }
            flag1 = flag;
            if (flag)
            {
                mExpandedItem = null;
                return flag;
            }
        }
        return flag1;
    }

    boolean dispatchMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        return mCallback != null && mCallback.onMenuItemSelected(menubuilder, menuitem);
    }

    public boolean expandItemActionView(MenuItemImpl menuitemimpl)
    {
        boolean flag1;
label0:
        {
            if (mPresenters.isEmpty())
            {
                return false;
            }
            if (!mPreventDispatchingItemsChanged)
            {
                mPreventDispatchingItemsChanged = true;
                mItemsChangedWhileDispatchPrevented = false;
                mStructureChangedWhileDispatchPrevented = false;
            }
            Iterator iterator = mPresenters.iterator();
            boolean flag = false;
            while (iterator.hasNext()) 
            {
                WeakReference weakreference = (WeakReference)iterator.next();
                MenuPresenter menupresenter = (MenuPresenter)weakreference.get();
                if (menupresenter == null)
                {
                    mPresenters.remove(weakreference);
                } else
                {
                    flag = menupresenter.expandItemActionView$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5TMMARJL5T6MARJL89QMIR34CLP3MJ31DPI74RR9CGNN6TBGE1NN4T1FEORIUTJ9CLRIURB5DPQIUJB5DPQKIT35DL4MQS3C7CKLK___0(menuitemimpl);
                    flag1 = flag;
                    if (flag)
                    {
                        break label0;
                    }
                }
            }
            flag1 = flag;
        }
        mPreventDispatchingItemsChanged = false;
        if (mItemsChangedWhileDispatchPrevented)
        {
            mItemsChangedWhileDispatchPrevented = false;
            onItemsChanged(mStructureChangedWhileDispatchPrevented);
        }
        if (flag1)
        {
            mExpandedItem = menuitemimpl;
        }
        return flag1;
    }

    public MenuItem findItem(int i)
    {
        int j;
        int k;
        k = size();
        j = 0;
_L6:
        if (j >= k) goto _L2; else goto _L1
_L1:
        Object obj = (MenuItemImpl)mItems.get(j);
        if (((MenuItemImpl) (obj)).getItemId() != i) goto _L4; else goto _L3
_L3:
        return ((MenuItem) (obj));
_L4:
        MenuItem menuitem;
        if (!((MenuItemImpl) (obj)).hasSubMenu())
        {
            continue; /* Loop/switch isn't completed */
        }
        menuitem = ((MenuItemImpl) (obj)).getSubMenu().findItem(i);
        obj = menuitem;
        if (menuitem != null) goto _L3; else goto _L5
_L5:
        j++;
          goto _L6
_L2:
        return null;
    }

    public final void flagActionItems()
    {
        ArrayList arraylist = getVisibleItems();
        if (!mIsActionItemsStale)
        {
            return;
        }
        Iterator iterator = mPresenters.iterator();
        boolean flag = false;
        while (iterator.hasNext()) 
        {
            WeakReference weakreference = (WeakReference)iterator.next();
            MenuPresenter menupresenter = (MenuPresenter)weakreference.get();
            if (menupresenter == null)
            {
                mPresenters.remove(weakreference);
            } else
            {
                flag = menupresenter.flagActionItems() | flag;
            }
        }
        if (flag)
        {
            mActionItems.clear();
            mNonActionItems.clear();
            int j = arraylist.size();
            int i = 0;
            while (i < j) 
            {
                MenuItemImpl menuitemimpl = (MenuItemImpl)arraylist.get(i);
                if ((menuitemimpl.mFlags & 0x20) == 32)
                {
                    mActionItems.add(menuitemimpl);
                } else
                {
                    mNonActionItems.add(menuitemimpl);
                }
                i++;
            }
        } else
        {
            mActionItems.clear();
            mNonActionItems.clear();
            mNonActionItems.addAll(getVisibleItems());
        }
        mIsActionItemsStale = false;
    }

    protected String getActionViewStatesKey()
    {
        return "android:menu:actionviewstates";
    }

    public MenuItem getItem(int i)
    {
        return (MenuItem)mItems.get(i);
    }

    public MenuBuilder getRootMenu()
    {
        return this;
    }

    public final ArrayList getVisibleItems()
    {
        if (!mIsVisibleItemsStale)
        {
            return mVisibleItems;
        }
        mVisibleItems.clear();
        int j = mItems.size();
        for (int i = 0; i < j; i++)
        {
            MenuItemImpl menuitemimpl = (MenuItemImpl)mItems.get(i);
            if (menuitemimpl.isVisible())
            {
                mVisibleItems.add(menuitemimpl);
            }
        }

        mIsVisibleItemsStale = false;
        mIsActionItemsStale = true;
        return mVisibleItems;
    }

    public boolean hasVisibleItems()
    {
        if (mOverrideVisibleItems)
        {
            return true;
        }
        int j = size();
        for (int i = 0; i < j; i++)
        {
            if (((MenuItemImpl)mItems.get(i)).isVisible())
            {
                return true;
            }
        }

        return false;
    }

    public boolean isGroupDividerEnabled()
    {
        return mGroupDividerEnabled;
    }

    boolean isQwertyMode()
    {
        return mQwertyMode;
    }

    public boolean isShortcutKey(int i, KeyEvent keyevent)
    {
        return findItemWithShortcutForKey(i, keyevent) != null;
    }

    public boolean isShortcutsVisible()
    {
        return mShortcutsVisible;
    }

    public final void onItemsChanged(boolean flag)
    {
        if (!mPreventDispatchingItemsChanged)
        {
            if (flag)
            {
                mIsVisibleItemsStale = true;
                mIsActionItemsStale = true;
            }
            if (!mPresenters.isEmpty())
            {
                if (!mPreventDispatchingItemsChanged)
                {
                    mPreventDispatchingItemsChanged = true;
                    mItemsChangedWhileDispatchPrevented = false;
                    mStructureChangedWhileDispatchPrevented = false;
                }
                for (Iterator iterator = mPresenters.iterator(); iterator.hasNext();)
                {
                    WeakReference weakreference = (WeakReference)iterator.next();
                    MenuPresenter menupresenter = (MenuPresenter)weakreference.get();
                    if (menupresenter == null)
                    {
                        mPresenters.remove(weakreference);
                    } else
                    {
                        menupresenter.updateMenuView(flag);
                    }
                }

                mPreventDispatchingItemsChanged = false;
                if (mItemsChangedWhileDispatchPrevented)
                {
                    mItemsChangedWhileDispatchPrevented = false;
                    onItemsChanged(mStructureChangedWhileDispatchPrevented);
                }
            }
        } else
        {
            mItemsChangedWhileDispatchPrevented = true;
            if (flag)
            {
                mStructureChangedWhileDispatchPrevented = true;
                return;
            }
        }
    }

    public boolean performIdentifierAction(int i, int j)
    {
        return performItemAction(findItem(i), null, j);
    }

    public final boolean performItemAction(MenuItem menuitem, MenuPresenter menupresenter, int i)
    {
        boolean flag1;
        boolean flag2;
        flag2 = false;
        flag1 = false;
        menuitem = (MenuItemImpl)menuitem;
        if (menuitem != null && menuitem.isEnabled()) goto _L2; else goto _L1
_L1:
        flag1 = false;
_L4:
        return flag1;
_L2:
        ActionProvider actionprovider;
        boolean flag;
        boolean flag3;
        flag3 = menuitem.invoke();
        actionprovider = ((MenuItemImpl) (menuitem)).mActionProvider;
        if (actionprovider != null && actionprovider.hasSubMenu())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!menuitem.hasCollapsibleActionView())
        {
            break; /* Loop/switch isn't completed */
        }
        flag2 = menuitem.expandActionView() | flag3;
        flag1 = flag2;
        if (flag2)
        {
            close(true);
            return flag2;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (menuitem.hasSubMenu() || flag)
        {
            if ((i & 4) == 0)
            {
                close(false);
            }
            if (!menuitem.hasSubMenu())
            {
                SubMenuBuilder submenubuilder = new SubMenuBuilder(mContext, this, menuitem);
                menuitem.mSubMenu = submenubuilder;
                submenubuilder.setHeaderTitle(menuitem.getTitle());
            }
            menuitem = (SubMenuBuilder)menuitem.getSubMenu();
            if (flag)
            {
                actionprovider.onPrepareSubMenu(menuitem);
            }
            if (!mPresenters.isEmpty())
            {
                flag1 = flag2;
                if (menupresenter != null)
                {
                    flag1 = menupresenter.onSubMenuSelected(menuitem);
                }
                menupresenter = mPresenters.iterator();
                while (menupresenter.hasNext()) 
                {
                    WeakReference weakreference = (WeakReference)menupresenter.next();
                    MenuPresenter menupresenter1 = (MenuPresenter)weakreference.get();
                    if (menupresenter1 == null)
                    {
                        mPresenters.remove(weakreference);
                    } else
                    if (!flag1)
                    {
                        flag1 = menupresenter1.onSubMenuSelected(menuitem);
                    }
                }
            }
            flag2 = flag3 | flag1;
            flag1 = flag2;
            if (!flag2)
            {
                close(true);
                return flag2;
            }
        } else
        {
            if ((i & 1) == 0)
            {
                close(true);
            }
            return flag3;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public boolean performShortcut(int i, KeyEvent keyevent, int j)
    {
        keyevent = findItemWithShortcutForKey(i, keyevent);
        boolean flag = false;
        if (keyevent != null)
        {
            flag = performItemAction(keyevent, null, j);
        }
        if ((j & 2) != 0)
        {
            close(true);
        }
        return flag;
    }

    public void removeGroup(int i)
    {
        int j;
        int k;
        k = size();
        j = 0;
_L7:
        if (j >= k) goto _L2; else goto _L1
_L1:
        if (((MenuItemImpl)mItems.get(j)).getGroupId() != i) goto _L4; else goto _L3
_L3:
        if (j < 0)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        int i1 = mItems.size();
        for (int l = 0; l < i1 - j && ((MenuItemImpl)mItems.get(j)).getGroupId() == i; l++)
        {
            removeItemAtInt(j, false);
        }

        break; /* Loop/switch isn't completed */
_L4:
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        j = -1;
        if (true) goto _L3; else goto _L5
_L5:
        onItemsChanged(true);
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void removeItem(int i)
    {
        int j;
        int k;
        k = size();
        j = 0;
_L3:
        if (j >= k)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        if (((MenuItemImpl)mItems.get(j)).getItemId() != i) goto _L2; else goto _L1
_L1:
        removeItemAtInt(j, true);
        return;
_L2:
        j++;
          goto _L3
        j = -1;
          goto _L1
    }

    public final void removeMenuPresenter(MenuPresenter menupresenter)
    {
        Iterator iterator = mPresenters.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            WeakReference weakreference = (WeakReference)iterator.next();
            MenuPresenter menupresenter1 = (MenuPresenter)weakreference.get();
            if (menupresenter1 == null || menupresenter1 == menupresenter)
            {
                mPresenters.remove(weakreference);
            }
        } while (true);
    }

    public final void restoreActionViewStates(Bundle bundle)
    {
        if (bundle != null)
        {
            SparseArray sparsearray = bundle.getSparseParcelableArray(getActionViewStatesKey());
            int k = size();
            for (int i = 0; i < k; i++)
            {
                MenuItem menuitem = getItem(i);
                View view = menuitem.getActionView();
                if (view != null && view.getId() != -1)
                {
                    view.restoreHierarchyState(sparsearray);
                }
                if (menuitem.hasSubMenu())
                {
                    ((SubMenuBuilder)menuitem.getSubMenu()).restoreActionViewStates(bundle);
                }
            }

            int j = bundle.getInt("android:menu:expandedactionview");
            if (j > 0)
            {
                bundle = findItem(j);
                if (bundle != null)
                {
                    bundle.expandActionView();
                    return;
                }
            }
        }
    }

    public final void saveActionViewStates(Bundle bundle)
    {
        int j = size();
        int i = 0;
        SparseArray sparsearray;
        SparseArray sparsearray2;
        for (sparsearray = null; i < j; sparsearray = sparsearray2)
        {
            MenuItem menuitem = getItem(i);
            View view = menuitem.getActionView();
            sparsearray2 = sparsearray;
            if (view != null)
            {
                sparsearray2 = sparsearray;
                if (view.getId() != -1)
                {
                    SparseArray sparsearray1 = sparsearray;
                    if (sparsearray == null)
                    {
                        sparsearray1 = new SparseArray();
                    }
                    view.saveHierarchyState(sparsearray1);
                    sparsearray2 = sparsearray1;
                    if (menuitem.isActionViewExpanded())
                    {
                        bundle.putInt("android:menu:expandedactionview", menuitem.getItemId());
                        sparsearray2 = sparsearray1;
                    }
                }
            }
            if (menuitem.hasSubMenu())
            {
                ((SubMenuBuilder)menuitem.getSubMenu()).saveActionViewStates(bundle);
            }
            i++;
        }

        if (sparsearray != null)
        {
            bundle.putSparseParcelableArray(getActionViewStatesKey(), sparsearray);
        }
    }

    public void setCallback(Callback callback)
    {
        mCallback = callback;
    }

    public void setGroupCheckable(int i, boolean flag, boolean flag1)
    {
        int k = mItems.size();
        int j = 0;
        while (j < k) 
        {
            MenuItemImpl menuitemimpl = (MenuItemImpl)mItems.get(j);
            if (menuitemimpl.getGroupId() == i)
            {
                int l = menuitemimpl.mFlags;
                byte byte0;
                if (flag1)
                {
                    byte0 = 4;
                } else
                {
                    byte0 = 0;
                }
                menuitemimpl.mFlags = byte0 | l & -5;
                menuitemimpl.setCheckable(flag);
            }
            j++;
        }
    }

    public void setGroupDividerEnabled(boolean flag)
    {
        mGroupDividerEnabled = flag;
    }

    public void setGroupEnabled(int i, boolean flag)
    {
        int k = mItems.size();
        for (int j = 0; j < k; j++)
        {
            MenuItemImpl menuitemimpl = (MenuItemImpl)mItems.get(j);
            if (menuitemimpl.getGroupId() == i)
            {
                menuitemimpl.setEnabled(flag);
            }
        }

    }

    public void setGroupVisible(int i, boolean flag)
    {
        int k = mItems.size();
        int j = 0;
        boolean flag1 = false;
        for (; j < k; j++)
        {
            MenuItemImpl menuitemimpl = (MenuItemImpl)mItems.get(j);
            if (menuitemimpl.getGroupId() == i && menuitemimpl.setVisibleInt(flag))
            {
                flag1 = true;
            }
        }

        if (flag1)
        {
            onItemsChanged(true);
        }
    }

    final void setHeaderInternal(int i, CharSequence charsequence, int j, Drawable drawable, View view)
    {
        Resources resources = mResources;
        if (view == null) goto _L2; else goto _L1
_L1:
        mHeaderView = view;
        mHeaderTitle = null;
        mHeaderIcon = null;
_L4:
        onItemsChanged(false);
        return;
_L2:
        if (i > 0)
        {
            mHeaderTitle = resources.getText(i);
        } else
        if (charsequence != null)
        {
            mHeaderTitle = charsequence;
        }
        if (j <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        mHeaderIcon = ContextCompat.getDrawable(mContext, j);
_L6:
        mHeaderView = null;
        if (true) goto _L4; else goto _L3
_L3:
        if (drawable == null) goto _L6; else goto _L5
_L5:
        mHeaderIcon = drawable;
          goto _L6
    }

    public void setQwertyMode(boolean flag)
    {
        mQwertyMode = flag;
        onItemsChanged(false);
    }

    public int size()
    {
        return mItems.size();
    }


    private class Callback
    {

        public abstract boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem);

        public abstract void onMenuModeChange(MenuBuilder menubuilder);
    }

}
