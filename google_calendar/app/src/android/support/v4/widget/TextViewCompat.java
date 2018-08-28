// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.text.TextPaint;
import android.widget.TextView;

public final class TextViewCompat
{

    public static void setFirstBaselineToTopHeight(TextView textview, int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException();
        }
        if (android.os.Build.VERSION.SDK_INT >= 28)
        {
            textview.setFirstBaselineToTopHeight(i);
        } else
        {
            android.graphics.Paint.FontMetricsInt fontmetricsint = textview.getPaint().getFontMetricsInt();
            int j;
            if (textview.getIncludeFontPadding())
            {
                j = fontmetricsint.top;
            } else
            {
                j = fontmetricsint.ascent;
            }
            if (i > Math.abs(j))
            {
                j = -j;
                textview.setPadding(textview.getPaddingLeft(), i - j, textview.getPaddingRight(), textview.getPaddingBottom());
                return;
            }
        }
    }

    public static void setLastBaselineToBottomHeight(TextView textview, int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException();
        }
        android.graphics.Paint.FontMetricsInt fontmetricsint = textview.getPaint().getFontMetricsInt();
        int j;
        if (textview.getIncludeFontPadding())
        {
            j = fontmetricsint.bottom;
        } else
        {
            j = fontmetricsint.descent;
        }
        if (i > Math.abs(j))
        {
            textview.setPadding(textview.getPaddingLeft(), textview.getPaddingTop(), textview.getPaddingRight(), i - j);
        }
    }

    public static void setLineHeight(TextView textview, int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException();
        }
        int j = textview.getPaint().getFontMetricsInt(null);
        if (i != j)
        {
            textview.setLineSpacing(i - j, 1.0F);
        }
    }

    public static android.view.ActionMode.Callback wrapCustomSelectionActionModeCallback(TextView textview, android.view.ActionMode.Callback callback)
    {
        if (android.os.Build.VERSION.SDK_INT < 26 || android.os.Build.VERSION.SDK_INT > 27 || (callback instanceof OreoCallback))
        {
            return callback;
        } else
        {
            return new OreoCallback(callback, textview);
        }
    }

    private class OreoCallback
        implements android.view.ActionMode.Callback
    {

        private final android.view.ActionMode.Callback mCallback;
        private boolean mCanUseMenuBuilderReferences;
        private boolean mInitializedMenuBuilderReferences;
        private Class mMenuBuilderClass;
        private Method mMenuBuilderRemoveItemAtMethod;
        private final TextView mTextView;

        public final boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem)
        {
            return mCallback.onActionItemClicked(actionmode, menuitem);
        }

        public final boolean onCreateActionMode(ActionMode actionmode, Menu menu)
        {
            return mCallback.onCreateActionMode(actionmode, menu);
        }

        public final void onDestroyActionMode(ActionMode actionmode)
        {
            mCallback.onDestroyActionMode(actionmode);
        }

        public final boolean onPrepareActionMode(ActionMode actionmode, Menu menu)
        {
            PackageManager packagemanager;
            Context context;
            context = mTextView.getContext();
            packagemanager = context.getPackageManager();
            if (mInitializedMenuBuilderReferences)
            {
                break MISSING_BLOCK_LABEL_65;
            }
            mInitializedMenuBuilderReferences = true;
            mMenuBuilderClass = Class.forName("com.android.internal.view.menu.MenuBuilder");
            mMenuBuilderRemoveItemAtMethod = mMenuBuilderClass.getDeclaredMethod("removeItemAt", new Class[] {
                Integer.TYPE
            });
            mCanUseMenuBuilderReferences = true;
_L5:
            if (!mCanUseMenuBuilderReferences || !mMenuBuilderClass.isInstance(menu)) goto _L2; else goto _L1
_L1:
            Object obj = mMenuBuilderRemoveItemAtMethod;
_L6:
            int i = menu.size() - 1;
_L4:
            if (i < 0)
            {
                break; /* Loop/switch isn't completed */
            }
            MenuItem menuitem = menu.getItem(i);
            if (menuitem.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(menuitem.getIntent().getAction()))
            {
                ((Method) (obj)).invoke(menu, new Object[] {
                    Integer.valueOf(i)
                });
            }
            i--;
            if (true) goto _L4; else goto _L3
            obj;
_L9:
            mMenuBuilderClass = null;
            mMenuBuilderRemoveItemAtMethod = null;
            mCanUseMenuBuilderReferences = false;
              goto _L5
_L2:
            obj = menu.getClass().getDeclaredMethod("removeItemAt", new Class[] {
                Integer.TYPE
            });
              goto _L6
_L3:
            ArrayList arraylist = new ArrayList();
            if (context instanceof Activity)
            {
                Iterator iterator = packagemanager.queryIntentActivities((new Intent()).setAction("android.intent.action.PROCESS_TEXT").setType("text/plain"), 0).iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    ResolveInfo resolveinfo1 = (ResolveInfo)iterator.next();
                    boolean flag;
                    if (context.getPackageName().equals(resolveinfo1.activityInfo.packageName))
                    {
                        flag = true;
                    } else
                    if (resolveinfo1.activityInfo.exported && (resolveinfo1.activityInfo.permission == null || context.checkSelfPermission(resolveinfo1.activityInfo.permission) == 0))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        arraylist.add(resolveinfo1);
                    }
                } while (true);
            }
            int j = 0;
            while (j < arraylist.size()) 
            {
                ResolveInfo resolveinfo = (ResolveInfo)arraylist.get(j);
                MenuItem menuitem1 = menu.add(0, 0, j + 100, resolveinfo.loadLabel(packagemanager));
                TextView textview = mTextView;
                Intent intent = (new Intent()).setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
                boolean flag1;
                boolean flag2;
                if ((textview instanceof Editable) && textview.onCheckIsTextEditor() && textview.isEnabled())
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                menuitem1.setIntent(intent.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", flag2).setClassName(resolveinfo.activityInfo.packageName, resolveinfo.activityInfo.name)).setShowAsAction(1);
                j++;
            }
            break MISSING_BLOCK_LABEL_539;
            Object obj1;
            obj1;
_L8:
            return mCallback.onPrepareActionMode(actionmode, menu);
            obj1;
            continue; /* Loop/switch isn't completed */
            obj1;
            if (true) goto _L8; else goto _L7
_L7:
            obj1;
              goto _L9
        }

        OreoCallback(android.view.ActionMode.Callback callback, TextView textview)
        {
            mCallback = callback;
            mTextView = textview;
            mInitializedMenuBuilderReferences = false;
        }
    }

}
