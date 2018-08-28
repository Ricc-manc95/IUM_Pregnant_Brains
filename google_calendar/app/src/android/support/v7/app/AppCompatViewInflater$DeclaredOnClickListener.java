// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v7.app:
//            AppCompatViewInflater

static final class mMethodName
    implements android.view.kListener
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

    public (View view, String s)
    {
        mHostView = view;
        mMethodName = s;
    }
}
