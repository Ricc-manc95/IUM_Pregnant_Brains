// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class AppCompatViewInflater
{
    static final class DeclaredOnClickListener
        implements android.view.View.OnClickListener
    {

        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public final void onClick(View view)
        {
            if (mResolvedMethod != null) goto _L2; else goto _L1
_L1:
            Context context = mHostView.getContext();
_L7:
            if (context == null)
            {
                break; /* Loop/switch isn't completed */
            }
            if (context.isRestricted()) goto _L4; else goto _L3
_L3:
            Method method = context.getClass().getMethod(mMethodName, new Class[] {
                android/view/View
            });
            if (method == null) goto _L4; else goto _L5
_L5:
            mResolvedMethod = method;
            mResolvedContext = context;
_L2:
            NoSuchMethodException nosuchmethodexception;
            try
            {
                mResolvedMethod.invoke(mResolvedContext, new Object[] {
                    view
                });
                return;
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", view);
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                throw new IllegalStateException("Could not execute method for android:onClick", view);
            }
            nosuchmethodexception;
_L4:
            if (context instanceof ContextWrapper)
            {
                context = ((ContextWrapper)context).getBaseContext();
            } else
            {
                context = null;
            }
            if (true) goto _L7; else goto _L6
_L6:
            int i = mHostView.getId();
            if (i == -1)
            {
                view = "";
            } else
            {
                view = (new StringBuilder(" with id '")).append(mHostView.getContext().getResources().getResourceEntryName(i)).append("'").toString();
            }
            throw new IllegalStateException((new StringBuilder("Could not find method ")).append(mMethodName).append("(View) in a parent or ancestor Context for android:onClick attribute defined on view ").append(mHostView.getClass()).append(view).toString());
        }

        public DeclaredOnClickListener(View view, String s)
        {
            mHostView = view;
            mMethodName = s;
        }
    }


    private static final String sClassPrefixList[] = {
        "android.widget.", "android.view.", "android.webkit."
    };
    private static final Map sConstructorMap = new ArrayMap();
    private static final Class sConstructorSignature[] = {
        android/content/Context, android/util/AttributeSet
    };
    private static final int sOnClickAttrs[] = {
        0x101026f
    };
    private final Object mConstructorArgs[] = new Object[2];

    public AppCompatViewInflater()
    {
    }

    static void checkOnClickListener(View view, AttributeSet attributeset)
    {
        Object obj = view.getContext();
        if (!(obj instanceof ContextWrapper) || !ViewCompat.hasOnClickListeners(view))
        {
            return;
        }
        attributeset = ((Context) (obj)).obtainStyledAttributes(attributeset, sOnClickAttrs);
        obj = attributeset.getString(0);
        if (obj != null)
        {
            view.setOnClickListener(new DeclaredOnClickListener(view, ((String) (obj))));
        }
        attributeset.recycle();
    }

    protected static AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeset)
    {
        return new AppCompatAutoCompleteTextView(context, attributeset);
    }

    protected static AppCompatButton createButton(Context context, AttributeSet attributeset)
    {
        return new AppCompatButton(context, attributeset);
    }

    protected static AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeset)
    {
        return new AppCompatCheckBox(context, attributeset);
    }

    protected static AppCompatCheckedTextView createCheckedTextView(Context context, AttributeSet attributeset)
    {
        return new AppCompatCheckedTextView(context, attributeset);
    }

    protected static AppCompatEditText createEditText(Context context, AttributeSet attributeset)
    {
        return new AppCompatEditText(context, attributeset);
    }

    protected static AppCompatImageButton createImageButton(Context context, AttributeSet attributeset)
    {
        return new AppCompatImageButton(context, attributeset);
    }

    protected static AppCompatImageView createImageView(Context context, AttributeSet attributeset)
    {
        return new AppCompatImageView(context, attributeset);
    }

    protected static AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context, AttributeSet attributeset)
    {
        return new AppCompatMultiAutoCompleteTextView(context, attributeset);
    }

    protected static AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeset)
    {
        return new AppCompatRadioButton(context, attributeset);
    }

    protected static AppCompatRatingBar createRatingBar(Context context, AttributeSet attributeset)
    {
        return new AppCompatRatingBar(context, attributeset);
    }

    protected static AppCompatSeekBar createSeekBar(Context context, AttributeSet attributeset)
    {
        return new AppCompatSeekBar(context, attributeset);
    }

    protected static AppCompatSpinner createSpinner(Context context, AttributeSet attributeset)
    {
        return new AppCompatSpinner(context, attributeset);
    }

    protected static AppCompatTextView createTextView(Context context, AttributeSet attributeset)
    {
        return new AppCompatTextView(context, attributeset);
    }

    private final View createViewByPrefix(Context context, String s, String s1)
        throws ClassNotFoundException, InflateException
    {
        Object obj;
        Constructor constructor;
        constructor = (Constructor)sConstructorMap.get(s);
        obj = constructor;
        if (constructor != null) goto _L2; else goto _L1
_L1:
        try
        {
            obj = context.getClassLoader();
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_104;
        }
        context = (new StringBuilder()).append(s1).append(s).toString();
_L3:
        obj = ((ClassLoader) (obj)).loadClass(context).asSubclass(android/view/View).getConstructor(sConstructorSignature);
        sConstructorMap.put(s, obj);
_L2:
        ((Constructor) (obj)).setAccessible(true);
        context = (View)((Constructor) (obj)).newInstance(mConstructorArgs);
        return context;
        context = s;
          goto _L3
    }

    static Context themifyContext(Context context, AttributeSet attributeset, boolean flag, boolean flag1)
    {
label0:
        {
            attributeset = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.View, 0, 0);
            int i;
            if (flag)
            {
                i = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.View_android_theme, 0);
            } else
            {
                i = 0;
            }
            if (flag1 && i == 0)
            {
                i = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.View_theme, 0);
            }
            attributeset.recycle();
            attributeset = context;
            if (i == 0)
            {
                break label0;
            }
            if (context instanceof ContextThemeWrapper)
            {
                attributeset = context;
                if (((ContextThemeWrapper)context).mThemeResource == i)
                {
                    break label0;
                }
            }
            attributeset = new ContextThemeWrapper(context, i);
        }
        return attributeset;
    }

    final View createViewFromTag(Context context, String s, AttributeSet attributeset)
    {
        String s1;
        s1 = s;
        if (s.equals("view"))
        {
            s1 = attributeset.getAttributeValue(null, "class");
        }
        mConstructorArgs[0] = context;
        mConstructorArgs[1] = attributeset;
        if (-1 == s1.indexOf('.'))
        {
            int i = 0;
            do
            {
                try
                {
                    if (i >= sClassPrefixList.length)
                    {
                        break;
                    }
                    s = createViewByPrefix(context, s1, sClassPrefixList[i]);
                }
                // Misplaced declaration of an exception variable
                catch (Context context)
                {
                    mConstructorArgs[0] = null;
                    mConstructorArgs[1] = null;
                    return null;
                }
                finally
                {
                    mConstructorArgs[0] = null;
                }
                if (s != null)
                {
                    mConstructorArgs[0] = null;
                    mConstructorArgs[1] = null;
                    return s;
                }
                i++;
            } while (true);
            mConstructorArgs[0] = null;
            mConstructorArgs[1] = null;
            return null;
        }
        context = createViewByPrefix(context, s1, null);
        mConstructorArgs[0] = null;
        mConstructorArgs[1] = null;
        return context;
        mConstructorArgs[1] = null;
        throw context;
    }

    final void verifyNotNull(View view, String s)
    {
        if (view == null)
        {
            throw new IllegalStateException((new StringBuilder()).append(getClass().getName()).append(" asked to inflate view for <").append(s).append(">, but returned null").toString());
        } else
        {
            return;
        }
    }

}
