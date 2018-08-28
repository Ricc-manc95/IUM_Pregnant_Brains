// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.util.LongSparseArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.view.StandaloneActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.ExpandedMenuView;
import android.support.v7.view.menu.ListMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegate, AppCompatViewInflater, ActionBar, WindowDecorActionBar, 
//            TwilightManager, ResourcesFlusher, ToolbarActionBar, AppCompatCallback

public final class AppCompatDelegateImpl extends AppCompatDelegate
    implements android.support.v7.view.menu.MenuBuilder.Callback, android.view.LayoutInflater.Factory2
{

    private static final boolean IS_PRE_LOLLIPOP = false;
    private static final int sWindowBackgroundStyleable[] = {
        0x1010054
    };
    private ActionBar mActionBar;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    public ActionMode mActionMode;
    public PopupWindow mActionModePopup;
    public ActionBarContextView mActionModeView;
    public final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    private final android.view.Window.Callback mAppCompatWindowCallback;
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mClosingActionMenu;
    public final Context mContext;
    public DecorContentParent mDecorContentParent;
    private boolean mEnableDefaultActionBarUp;
    public ViewPropertyAnimatorCompat mFadeAnim;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    public boolean mHandleNativeActionModes;
    public boolean mHasActionBar;
    public int mInvalidatePanelMenuFeatures;
    public boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable = new _cls2();
    public boolean mIsDestroyed;
    private boolean mIsFloating;
    private int mLocalNightMode;
    private boolean mLongPressBackDown;
    private MenuInflater mMenuInflater;
    private final android.view.Window.Callback mOriginalWindowCallback;
    private boolean mOverlayActionBar;
    private boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState mPanels[];
    public PanelFeatureState mPreparedPanel;
    public Runnable mShowActionModePopup;
    private View mStatusGuard;
    public ViewGroup mSubDecor;
    public boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private CharSequence mTitle;
    private TextView mTitleView;
    public final Window mWindow;
    private boolean mWindowNoTitle;

    public AppCompatDelegateImpl(Context context, Window window, AppCompatCallback appcompatcallback)
    {
        mFadeAnim = null;
        mHandleNativeActionModes = true;
        mLocalNightMode = -100;
        mContext = context;
        mWindow = window;
        mAppCompatCallback = appcompatcallback;
        mOriginalWindowCallback = mWindow.getCallback();
        if (mOriginalWindowCallback instanceof AppCompatWindowCallback)
        {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        mAppCompatWindowCallback = new AppCompatWindowCallback(mOriginalWindowCallback);
        mWindow.setCallback(mAppCompatWindowCallback);
        context = new TintTypedArray(context, context.obtainStyledAttributes(null, sWindowBackgroundStyleable));
        window = context.getDrawableIfKnown(0);
        if (window != null)
        {
            mWindow.setBackgroundDrawable(window);
        }
        ((TintTypedArray) (context)).mWrapped.recycle();
    }

    private final View createView(View view, String s, Context context, AttributeSet attributeset)
    {
        Object obj;
        Context context1;
        byte byte0;
        byte0 = 0;
        if (mAppCompatViewInflater == null)
        {
            view = mContext.obtainStyledAttributes(android.support.v7.appcompat.R.styleable.AppCompatTheme).getString(android.support.v7.appcompat.R.styleable.AppCompatTheme_viewInflaterClass);
            if (view == null || android/support/v7/app/AppCompatViewInflater.getName().equals(view))
            {
                mAppCompatViewInflater = new AppCompatViewInflater();
            } else
            {
                try
                {
                    mAppCompatViewInflater = (AppCompatViewInflater)Class.forName(view).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    (new StringBuilder("Failed to instantiate custom view inflater ")).append(view).append(". Falling back to default.");
                    mAppCompatViewInflater = new AppCompatViewInflater();
                }
            }
        }
        obj = mAppCompatViewInflater;
        context1 = AppCompatViewInflater.themifyContext(context, attributeset, false, true);
        s.hashCode();
        JVM INSTR lookupswitch 13: default 188
    //                   -1946472170: 530
    //                   -1455429095: 479
    //                   -1346021293: 513
    //                   -938935918: 358
    //                   -937446323: 430
    //                   -658531749: 547
    //                   -339785223: 415
    //                   776382189: 462
    //                   1125864064: 370
    //                   1413872058: 496
    //                   1601505219: 445
    //                   1666676343: 400
    //                   2001146706: 385;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L1:
        byte0 = -1;
_L29:
        byte0;
        JVM INSTR tableswitch 0 12: default 260
    //                   0 564
    //                   1 582
    //                   2 600
    //                   3 618
    //                   4 636
    //                   5 654
    //                   6 672
    //                   7 690
    //                   8 708
    //                   9 726
    //                   10 744
    //                   11 762
    //                   12 780;
           goto _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28
_L15:
        view = null;
_L42:
        if (view == null && context != context1)
        {
            view = ((AppCompatViewInflater) (obj)).createViewFromTag(context1, s, attributeset);
        }
        if (view != null)
        {
            AppCompatViewInflater.checkOnClickListener(view, attributeset);
        }
        return view;
_L5:
        if (!s.equals("TextView")) goto _L1; else goto _L29
_L10:
        if (!s.equals("ImageView")) goto _L1; else goto _L30
_L30:
        byte0 = 1;
          goto _L29
_L14:
        if (!s.equals("Button")) goto _L1; else goto _L31
_L31:
        byte0 = 2;
          goto _L29
_L13:
        if (!s.equals("EditText")) goto _L1; else goto _L32
_L32:
        byte0 = 3;
          goto _L29
_L8:
        if (!s.equals("Spinner")) goto _L1; else goto _L33
_L33:
        byte0 = 4;
          goto _L29
_L6:
        if (!s.equals("ImageButton")) goto _L1; else goto _L34
_L34:
        byte0 = 5;
          goto _L29
_L12:
        if (!s.equals("CheckBox")) goto _L1; else goto _L35
_L35:
        byte0 = 6;
          goto _L29
_L9:
        if (!s.equals("RadioButton")) goto _L1; else goto _L36
_L36:
        byte0 = 7;
          goto _L29
_L3:
        if (!s.equals("CheckedTextView")) goto _L1; else goto _L37
_L37:
        byte0 = 8;
          goto _L29
_L11:
        if (!s.equals("AutoCompleteTextView")) goto _L1; else goto _L38
_L38:
        byte0 = 9;
          goto _L29
_L4:
        if (!s.equals("MultiAutoCompleteTextView")) goto _L1; else goto _L39
_L39:
        byte0 = 10;
          goto _L29
_L2:
        if (!s.equals("RatingBar")) goto _L1; else goto _L40
_L40:
        byte0 = 11;
          goto _L29
_L7:
        if (!s.equals("SeekBar")) goto _L1; else goto _L41
_L41:
        byte0 = 12;
          goto _L29
_L16:
        view = AppCompatViewInflater.createTextView(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L17:
        view = AppCompatViewInflater.createImageView(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L18:
        view = AppCompatViewInflater.createButton(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L19:
        view = AppCompatViewInflater.createEditText(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L20:
        view = AppCompatViewInflater.createSpinner(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L21:
        view = AppCompatViewInflater.createImageButton(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L22:
        view = AppCompatViewInflater.createCheckBox(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L23:
        view = AppCompatViewInflater.createRadioButton(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L24:
        view = AppCompatViewInflater.createCheckedTextView(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L25:
        view = AppCompatViewInflater.createAutoCompleteTextView(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L26:
        view = AppCompatViewInflater.createMultiAutoCompleteTextView(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L27:
        view = AppCompatViewInflater.createRatingBar(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
_L28:
        view = AppCompatViewInflater.createSeekBar(context1, attributeset);
        ((AppCompatViewInflater) (obj)).verifyNotNull(view, s);
          goto _L42
    }

    private final void ensureSubDecor()
    {
        if (mSubDecorInstalled) goto _L2; else goto _L1
_L1:
        Object obj;
        obj = mContext.obtainStyledAttributes(android.support.v7.appcompat.R.styleable.AppCompatTheme);
        if (!((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowActionBar))
        {
            ((TypedArray) (obj)).recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowNoTitle, false))
        {
            requestWindowFeature(1);
        } else
        if (((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowActionBar, false))
        {
            requestWindowFeature(108);
        }
        if (((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowActionBarOverlay, false))
        {
            requestWindowFeature(109);
        }
        if (((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowActionModeOverlay, false))
        {
            requestWindowFeature(10);
        }
        mIsFloating = ((TypedArray) (obj)).getBoolean(android.support.v7.appcompat.R.styleable.AppCompatTheme_android_windowIsFloating, false);
        ((TypedArray) (obj)).recycle();
        mWindow.getDecorView();
        obj = LayoutInflater.from(mContext);
        if (!mWindowNoTitle)
        {
            if (mIsFloating)
            {
                obj = (ViewGroup)((LayoutInflater) (obj)).inflate(0x7f05000c, null);
                mOverlayActionBar = false;
                mHasActionBar = false;
                break MISSING_BLOCK_LABEL_164;
            }
            if (mHasActionBar)
            {
                obj = new TypedValue();
                mContext.getTheme().resolveAttribute(0x7f010080, ((TypedValue) (obj)), true);
                if (((TypedValue) (obj)).resourceId != 0)
                {
                    obj = new ContextThemeWrapper(mContext, ((TypedValue) (obj)).resourceId);
                } else
                {
                    obj = mContext;
                }
                obj = (ViewGroup)LayoutInflater.from(((Context) (obj))).inflate(0x7f050017, null);
                mDecorContentParent = (DecorContentParent)((ViewGroup) (obj)).findViewById(0x7f1000ee);
                mDecorContentParent.setWindowCallback(mWindow.getCallback());
                if (mOverlayActionBar)
                {
                    mDecorContentParent.initFeature(109);
                }
                if (mFeatureProgress)
                {
                    mDecorContentParent.initFeature(2);
                }
                if (mFeatureIndeterminateProgress)
                {
                    mDecorContentParent.initFeature(5);
                }
                continue; /* Loop/switch isn't completed */
            }
        } else
        {
            if (mOverlayActionMode)
            {
                obj = (ViewGroup)((LayoutInflater) (obj)).inflate(0x7f050016, null);
            } else
            {
                obj = (ViewGroup)((LayoutInflater) (obj)).inflate(0x7f050015, null);
            }
            ViewCompat.setOnApplyWindowInsetsListener(((View) (obj)), new _cls3());
            continue; /* Loop/switch isn't completed */
        }
          goto _L3
_L9:
        if (obj == null)
        {
            throw new IllegalArgumentException((new StringBuilder("AppCompat does not support the current theme features: { windowActionBar: ")).append(mHasActionBar).append(", windowActionBarOverlay: ").append(mOverlayActionBar).append(", android:windowIsFloating: ").append(mIsFloating).append(", windowActionModeOverlay: ").append(mOverlayActionMode).append(", windowNoTitle: ").append(mWindowNoTitle).append(" }").toString());
        }
        if (mDecorContentParent == null)
        {
            mTitleView = (TextView)((ViewGroup) (obj)).findViewById(0x7f100047);
        }
        ViewUtils.makeOptionalFitsSystemWindows(((View) (obj)));
        Object obj1 = (ContentFrameLayout)((ViewGroup) (obj)).findViewById(0x7f100000);
        ViewGroup viewgroup = (ViewGroup)mWindow.findViewById(0x1020002);
        if (viewgroup != null)
        {
            View view;
            for (; viewgroup.getChildCount() > 0; ((ContentFrameLayout) (obj1)).addView(view))
            {
                view = viewgroup.getChildAt(0);
                viewgroup.removeViewAt(0);
            }

            viewgroup.setId(-1);
            ((ContentFrameLayout) (obj1)).setId(0x1020002);
            if (viewgroup instanceof FrameLayout)
            {
                ((FrameLayout)viewgroup).setForeground(null);
            }
        }
        mWindow.setContentView(((View) (obj)));
        obj1.mAttachListener = new _cls5();
        mSubDecor = ((ViewGroup) (obj));
        int i;
        int j;
        int k;
        int l;
        if (mOriginalWindowCallback instanceof Activity)
        {
            obj = ((Activity)mOriginalWindowCallback).getTitle();
        } else
        {
            obj = mTitle;
        }
        if (TextUtils.isEmpty(((CharSequence) (obj)))) goto _L5; else goto _L4
_L4:
        if (mDecorContentParent == null) goto _L7; else goto _L6
_L6:
        mDecorContentParent.setWindowTitle(((CharSequence) (obj)));
_L5:
        obj = (ContentFrameLayout)mSubDecor.findViewById(0x1020002);
        obj1 = mWindow.getDecorView();
        i = ((View) (obj1)).getPaddingLeft();
        j = ((View) (obj1)).getPaddingTop();
        k = ((View) (obj1)).getPaddingRight();
        l = ((View) (obj1)).getPaddingBottom();
        ((ContentFrameLayout) (obj)).mDecorPadding.set(i, j, k, l);
        if (ViewCompat.isLaidOut(((View) (obj))))
        {
            ((ContentFrameLayout) (obj)).requestLayout();
        }
        obj1 = mContext.obtainStyledAttributes(android.support.v7.appcompat.R.styleable.AppCompatTheme);
        i = android.support.v7.appcompat.R.styleable.AppCompatTheme_windowMinWidthMajor;
        if (((ContentFrameLayout) (obj)).mMinWidthMajor == null)
        {
            obj.mMinWidthMajor = new TypedValue();
        }
        ((TypedArray) (obj1)).getValue(i, ((ContentFrameLayout) (obj)).mMinWidthMajor);
        i = android.support.v7.appcompat.R.styleable.AppCompatTheme_windowMinWidthMinor;
        if (((ContentFrameLayout) (obj)).mMinWidthMinor == null)
        {
            obj.mMinWidthMinor = new TypedValue();
        }
        ((TypedArray) (obj1)).getValue(i, ((ContentFrameLayout) (obj)).mMinWidthMinor);
        if (((TypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor))
        {
            i = android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMajor;
            if (((ContentFrameLayout) (obj)).mFixedWidthMajor == null)
            {
                obj.mFixedWidthMajor = new TypedValue();
            }
            ((TypedArray) (obj1)).getValue(i, ((ContentFrameLayout) (obj)).mFixedWidthMajor);
        }
        if (((TypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor))
        {
            i = android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedWidthMinor;
            if (((ContentFrameLayout) (obj)).mFixedWidthMinor == null)
            {
                obj.mFixedWidthMinor = new TypedValue();
            }
            ((TypedArray) (obj1)).getValue(i, ((ContentFrameLayout) (obj)).mFixedWidthMinor);
        }
        if (((TypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor))
        {
            i = android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMajor;
            if (((ContentFrameLayout) (obj)).mFixedHeightMajor == null)
            {
                obj.mFixedHeightMajor = new TypedValue();
            }
            ((TypedArray) (obj1)).getValue(i, ((ContentFrameLayout) (obj)).mFixedHeightMajor);
        }
        if (((TypedArray) (obj1)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor))
        {
            i = android.support.v7.appcompat.R.styleable.AppCompatTheme_windowFixedHeightMinor;
            if (((ContentFrameLayout) (obj)).mFixedHeightMinor == null)
            {
                obj.mFixedHeightMinor = new TypedValue();
            }
            ((TypedArray) (obj1)).getValue(i, ((ContentFrameLayout) (obj)).mFixedHeightMinor);
        }
        ((TypedArray) (obj1)).recycle();
        ((ContentFrameLayout) (obj)).requestLayout();
        mSubDecorInstalled = true;
        obj = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
        if (!mIsDestroyed && (obj == null || ((PanelFeatureState) (obj)).menu == null))
        {
            invalidatePanelMenu(108);
        }
_L2:
        return;
_L7:
        if (mActionBar != null)
        {
            mActionBar.setWindowTitle(((CharSequence) (obj)));
        } else
        if (mTitleView != null)
        {
            mTitleView.setText(((CharSequence) (obj)));
        }
        if (true) goto _L5; else goto _L3
_L3:
        obj = null;
        if (true) goto _L9; else goto _L8
_L8:
    }

    private final void initWindowDecorActionBar()
    {
        ensureSubDecor();
        if (mHasActionBar && mActionBar == null)
        {
            if (mOriginalWindowCallback instanceof Activity)
            {
                mActionBar = new WindowDecorActionBar((Activity)mOriginalWindowCallback, mOverlayActionBar);
            } else
            if (mOriginalWindowCallback instanceof Dialog)
            {
                mActionBar = new WindowDecorActionBar((Dialog)mOriginalWindowCallback);
            }
            if (mActionBar != null)
            {
                mActionBar.setDefaultDisplayHomeAsUpEnabled(mEnableDefaultActionBarUp);
                return;
            }
        }
    }

    private final void invalidatePanelMenu(int i)
    {
        mInvalidatePanelMenuFeatures = mInvalidatePanelMenuFeatures | 1 << i;
        if (!mInvalidatePanelMenuPosted)
        {
            ViewCompat.postOnAnimation(mWindow.getDecorView(), mInvalidatePanelMenuRunnable);
            mInvalidatePanelMenuPosted = true;
        }
    }

    private final void openPanel(PanelFeatureState panelfeaturestate, KeyEvent keyevent)
    {
        if (!panelfeaturestate.isOpen && !mIsDestroyed) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (panelfeaturestate.featureId != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        android.view.Window.Callback callback;
        boolean flag;
        if ((mContext.getResources().getConfiguration().screenLayout & 0xf) == 4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        WindowManager windowmanager;
        callback = mWindow.getCallback();
        if (callback != null && !callback.onMenuOpened(panelfeaturestate.featureId, panelfeaturestate.menu))
        {
            closePanel(panelfeaturestate, true);
            return;
        }
        windowmanager = (WindowManager)mContext.getSystemService("window");
        if (windowmanager == null || !preparePanel(panelfeaturestate, keyevent)) goto _L1; else goto _L4
_L4:
        if (panelfeaturestate.decorView != null && !panelfeaturestate.refreshDecorView) goto _L6; else goto _L5
_L5:
        if (panelfeaturestate.decorView != null) goto _L8; else goto _L7
_L7:
        keyevent = getSupportActionBar();
        Object obj;
        Object obj1;
        int i;
        if (keyevent != null)
        {
            keyevent = keyevent.getThemedContext();
        } else
        {
            keyevent = null;
        }
        obj = keyevent;
        if (keyevent == null)
        {
            obj = mContext;
        }
        obj1 = new TypedValue();
        keyevent = ((Context) (obj)).getResources().newTheme();
        keyevent.setTo(((Context) (obj)).getTheme());
        keyevent.resolveAttribute(0x7f01007d, ((TypedValue) (obj1)), true);
        if (((TypedValue) (obj1)).resourceId != 0)
        {
            keyevent.applyStyle(((TypedValue) (obj1)).resourceId, true);
        }
        keyevent.resolveAttribute(0x7f0100bf, ((TypedValue) (obj1)), true);
        if (((TypedValue) (obj1)).resourceId != 0)
        {
            keyevent.applyStyle(((TypedValue) (obj1)).resourceId, true);
        } else
        {
            keyevent.applyStyle(0x7f1402a4, true);
        }
        obj = new ContextThemeWrapper(((Context) (obj)), 0);
        ((Context) (obj)).getTheme().setTo(keyevent);
        panelfeaturestate.listPresenterContext = ((Context) (obj));
        keyevent = ((Context) (obj)).obtainStyledAttributes(android.support.v7.appcompat.R.styleable.AppCompatTheme);
        panelfeaturestate.background = keyevent.getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTheme_panelBackground, 0);
        panelfeaturestate.windowAnimations = keyevent.getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTheme_android_windowAnimationStyle, 0);
        keyevent.recycle();
        panelfeaturestate.decorView = new ListMenuDecorView(panelfeaturestate.listPresenterContext);
        panelfeaturestate.gravity = 81;
        if (panelfeaturestate.decorView == null) goto _L1; else goto _L9
_L9:
        if (panelfeaturestate.createdPanelView != null)
        {
            panelfeaturestate.shownPanelView = panelfeaturestate.createdPanelView;
            i = 1;
        } else
        {
label0:
            {
                if (panelfeaturestate.menu == null)
                {
                    break label0;
                }
                if (mPanelMenuPresenterCallback == null)
                {
                    mPanelMenuPresenterCallback = new PanelMenuPresenterCallback();
                }
                keyevent = mPanelMenuPresenterCallback;
                if (panelfeaturestate.menu == null)
                {
                    keyevent = null;
                } else
                {
                    if (panelfeaturestate.listMenuPresenter == null)
                    {
                        panelfeaturestate.listMenuPresenter = new ListMenuPresenter(panelfeaturestate.listPresenterContext, 0x7f050010);
                        panelfeaturestate.listMenuPresenter.mCallback = keyevent;
                        keyevent = panelfeaturestate.menu;
                        obj = panelfeaturestate.listMenuPresenter;
                        obj1 = ((MenuBuilder) (keyevent)).mContext;
                        ((MenuBuilder) (keyevent)).mPresenters.add(new WeakReference(obj));
                        ((MenuPresenter) (obj)).initForMenu(((Context) (obj1)), keyevent);
                        keyevent.mIsActionItemsStale = true;
                    }
                    keyevent = panelfeaturestate.listMenuPresenter;
                    obj = panelfeaturestate.decorView;
                    if (((ListMenuPresenter) (keyevent)).mMenuView == null)
                    {
                        keyevent.mMenuView = (ExpandedMenuView)((ListMenuPresenter) (keyevent)).mInflater.inflate(0x7f05000d, ((ViewGroup) (obj)), false);
                        if (((ListMenuPresenter) (keyevent)).mAdapter == null)
                        {
                            keyevent.mAdapter = new android.support.v7.view.menu.ListMenuPresenter.MenuAdapter(keyevent);
                        }
                        ((ListMenuPresenter) (keyevent)).mMenuView.setAdapter(((ListMenuPresenter) (keyevent)).mAdapter);
                        ((ListMenuPresenter) (keyevent)).mMenuView.setOnItemClickListener(keyevent);
                    }
                    keyevent = ((ListMenuPresenter) (keyevent)).mMenuView;
                }
                panelfeaturestate.shownPanelView = (View)keyevent;
                if (panelfeaturestate.shownPanelView == null)
                {
                    break label0;
                }
                i = 1;
            }
        }
_L16:
        if (i == 0) goto _L1; else goto _L10
_L10:
        if (panelfeaturestate.shownPanelView == null) goto _L12; else goto _L11
_L11:
        if (panelfeaturestate.createdPanelView == null) goto _L14; else goto _L13
_L13:
        i = 1;
_L18:
        if (i == 0) goto _L1; else goto _L15
_L15:
        keyevent = panelfeaturestate.shownPanelView.getLayoutParams();
        if (keyevent == null)
        {
            keyevent = new android.view.ViewGroup.LayoutParams(-2, -2);
        }
        i = panelfeaturestate.background;
        panelfeaturestate.decorView.setBackgroundResource(i);
        obj = panelfeaturestate.shownPanelView.getParent();
        if (obj != null && (obj instanceof ViewGroup))
        {
            ((ViewGroup)obj).removeView(panelfeaturestate.shownPanelView);
        }
        panelfeaturestate.decorView.addView(panelfeaturestate.shownPanelView, keyevent);
        if (!panelfeaturestate.shownPanelView.hasFocus())
        {
            panelfeaturestate.shownPanelView.requestFocus();
        }
        i = -2;
_L19:
        panelfeaturestate.isHandled = false;
        keyevent = new android.view.WindowManager.LayoutParams(i, -2, 0, 0, 1002, 0x820000, -3);
        keyevent.gravity = panelfeaturestate.gravity;
        keyevent.windowAnimations = panelfeaturestate.windowAnimations;
        windowmanager.addView(panelfeaturestate.decorView, keyevent);
        panelfeaturestate.isOpen = true;
        return;
_L8:
        if (panelfeaturestate.refreshDecorView && panelfeaturestate.decorView.getChildCount() > 0)
        {
            panelfeaturestate.decorView.removeAllViews();
        }
          goto _L9
        i = 0;
          goto _L16
_L14:
        keyevent = panelfeaturestate.listMenuPresenter;
        if (((ListMenuPresenter) (keyevent)).mAdapter == null)
        {
            keyevent.mAdapter = new android.support.v7.view.menu.ListMenuPresenter.MenuAdapter(keyevent);
        }
        if (((ListMenuPresenter) (keyevent)).mAdapter.getCount() <= 0) goto _L12; else goto _L17
_L17:
        i = 1;
          goto _L18
_L12:
        i = 0;
          goto _L18
_L6:
label1:
        {
            if (panelfeaturestate.createdPanelView == null)
            {
                break label1;
            }
            keyevent = panelfeaturestate.createdPanelView.getLayoutParams();
            if (keyevent == null || ((android.view.ViewGroup.LayoutParams) (keyevent)).width != -1)
            {
                break label1;
            }
            i = -1;
        }
          goto _L19
        i = -2;
          goto _L19
    }

    private final boolean shouldRecreateOnNightModeChange()
    {
        if (mApplyDayNightCalled && (mContext instanceof Activity))
        {
            PackageManager packagemanager = mContext.getPackageManager();
            int i;
            try
            {
                i = packagemanager.getActivityInfo(new ComponentName(mContext, mContext.getClass()), 0).configChanges;
            }
            catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                return true;
            }
            return (i & 0x200) == 0;
        } else
        {
            return false;
        }
    }

    public final void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        ensureSubDecor();
        ((ViewGroup)mSubDecor.findViewById(0x1020002)).addView(view, layoutparams);
        mOriginalWindowCallback.onContentChanged();
    }

    public final boolean applyDayNight()
    {
        int i;
        boolean flag;
        flag = false;
        class AutoNightModeManager._cls1 extends BroadcastReceiver
        {

            private final AutoNightModeManager this$1;

            public final void onReceive(Context context, Intent intent)
            {
                context = AutoNightModeManager.this;
                boolean flag1 = ((AutoNightModeManager) (context)).mTwilightManager.isNight();
                if (flag1 != ((AutoNightModeManager) (context)).mIsNight)
                {
                    context.mIsNight = flag1;
                    ((AutoNightModeManager) (context))._fld0.applyDayNight();
                }
            }

            AutoNightModeManager._cls1()
            {
                this$1 = AutoNightModeManager.this;
                super();
            }
        }

        int j;
        int k;
        if (mLocalNightMode != -100)
        {
            j = mLocalNightMode;
        } else
        {
            j = AppCompatDelegate.sDefaultNightMode;
        }
        j;
        JVM INSTR lookupswitch 2: default 48
    //                   -100: 441
    //                   0: 314;
           goto _L1 _L2 _L3
_L2:
        break MISSING_BLOCK_LABEL_441;
_L1:
        i = j;
_L4:
        if (i != -1)
        {
            Object obj = mContext.getResources();
            Configuration configuration = ((Resources) (obj)).getConfiguration();
            k = configuration.uiMode;
            if (i == 2)
            {
                i = 32;
            } else
            {
                i = 16;
            }
            if ((k & 0x30) != i)
            {
                if (shouldRecreateOnNightModeChange())
                {
                    ((Activity)mContext).recreate();
                } else
                {
                    configuration = new Configuration(configuration);
                    android.util.DisplayMetrics displaymetrics = ((Resources) (obj)).getDisplayMetrics();
                    configuration.uiMode = i | configuration.uiMode & 0xffffffcf;
                    ((Resources) (obj)).updateConfiguration(configuration, displaymetrics);
                    if (android.os.Build.VERSION.SDK_INT < 26 && android.os.Build.VERSION.SDK_INT < 28)
                    {
                        if (android.os.Build.VERSION.SDK_INT >= 24)
                        {
                            ResourcesFlusher.flushNougats(((Resources) (obj)));
                        } else
                        if (android.os.Build.VERSION.SDK_INT >= 23)
                        {
                            ResourcesFlusher.flushMarshmallows(((Resources) (obj)));
                        } else
                        {
                            ResourcesFlusher.flushLollipops(((Resources) (obj)));
                        }
                    }
                }
                flag = true;
            } else
            {
                flag = false;
            }
        }
        if (j == 0)
        {
            if (mAutoNightModeManager == null)
            {
                obj = mContext;
                if (TwilightManager.sInstance == null)
                {
                    obj = ((Context) (obj)).getApplicationContext();
                    TwilightManager.sInstance = new TwilightManager(((Context) (obj)), (LocationManager)((Context) (obj)).getSystemService("location"));
                }
                mAutoNightModeManager = new AutoNightModeManager(TwilightManager.sInstance);
            }
            obj = mAutoNightModeManager;
            if (((AutoNightModeManager) (obj)).mAutoTimeChangeReceiver != null)
            {
                ((AutoNightModeManager) (obj))._fld0.mContext.unregisterReceiver(((AutoNightModeManager) (obj)).mAutoTimeChangeReceiver);
                obj.mAutoTimeChangeReceiver = null;
            }
            if (((AutoNightModeManager) (obj)).mAutoTimeChangeReceiver == null)
            {
                obj.mAutoTimeChangeReceiver = ((AutoNightModeManager._cls1) (obj)). new AutoNightModeManager._cls1();
            }
            if (((AutoNightModeManager) (obj)).mAutoTimeChangeReceiverFilter == null)
            {
                obj.mAutoTimeChangeReceiverFilter = new IntentFilter();
                ((AutoNightModeManager) (obj)).mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
                ((AutoNightModeManager) (obj)).mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                ((AutoNightModeManager) (obj)).mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }
            ((AutoNightModeManager) (obj))._fld0.mContext.registerReceiver(((AutoNightModeManager) (obj)).mAutoTimeChangeReceiver, ((AutoNightModeManager) (obj)).mAutoTimeChangeReceiverFilter);
        }
        mApplyDayNightCalled = true;
        return flag;
_L3:
        if (android.os.Build.VERSION.SDK_INT >= 23 && ((UiModeManager)mContext.getSystemService(android/app/UiModeManager)).getNightMode() == 0)
        {
            i = -1;
        } else
        {
            if (mAutoNightModeManager == null)
            {
                obj = mContext;
                if (TwilightManager.sInstance == null)
                {
                    obj = ((Context) (obj)).getApplicationContext();
                    TwilightManager.sInstance = new TwilightManager(((Context) (obj)), (LocationManager)((Context) (obj)).getSystemService("location"));
                }
                mAutoNightModeManager = new AutoNightModeManager(TwilightManager.sInstance);
            }
            obj = mAutoNightModeManager;
            obj.mIsNight = ((AutoNightModeManager) (obj)).mTwilightManager.isNight();
            if (((AutoNightModeManager) (obj)).mIsNight)
            {
                i = 2;
            } else
            {
                i = 1;
            }
        }
          goto _L4
        i = -1;
          goto _L4
    }

    final void callOnPanelClosed(int i, PanelFeatureState panelfeaturestate, Menu menu)
    {
        PanelFeatureState panelfeaturestate2 = panelfeaturestate;
        Object obj = menu;
        if (menu == null)
        {
            PanelFeatureState panelfeaturestate1 = panelfeaturestate;
            if (panelfeaturestate == null)
            {
                panelfeaturestate1 = panelfeaturestate;
                if (i >= 0)
                {
                    panelfeaturestate1 = panelfeaturestate;
                    if (i < mPanels.length)
                    {
                        panelfeaturestate1 = mPanels[i];
                    }
                }
            }
            panelfeaturestate2 = panelfeaturestate1;
            obj = menu;
            if (panelfeaturestate1 != null)
            {
                obj = panelfeaturestate1.menu;
                panelfeaturestate2 = panelfeaturestate1;
            }
        }
        while (panelfeaturestate2 != null && !panelfeaturestate2.isOpen || mIsDestroyed) 
        {
            return;
        }
        mOriginalWindowCallback.onPanelClosed(i, ((Menu) (obj)));
    }

    final void checkCloseActionMenu(MenuBuilder menubuilder)
    {
        if (mClosingActionMenu)
        {
            return;
        }
        mClosingActionMenu = true;
        mDecorContentParent.dismissPopups();
        android.view.Window.Callback callback = mWindow.getCallback();
        if (callback != null && !mIsDestroyed)
        {
            callback.onPanelClosed(108, menubuilder);
        }
        mClosingActionMenu = false;
    }

    final void closePanel(PanelFeatureState panelfeaturestate, boolean flag)
    {
        if (flag && panelfeaturestate.featureId == 0 && mDecorContentParent != null && mDecorContentParent.isOverflowMenuShowing())
        {
            checkCloseActionMenu(panelfeaturestate.menu);
        } else
        {
            WindowManager windowmanager = (WindowManager)mContext.getSystemService("window");
            if (windowmanager != null && panelfeaturestate.isOpen && panelfeaturestate.decorView != null)
            {
                windowmanager.removeView(panelfeaturestate.decorView);
                if (flag)
                {
                    callOnPanelClosed(panelfeaturestate.featureId, panelfeaturestate, null);
                }
            }
            panelfeaturestate.isPrepared = false;
            panelfeaturestate.isHandled = false;
            panelfeaturestate.isOpen = false;
            panelfeaturestate.shownPanelView = null;
            panelfeaturestate.refreshDecorView = true;
            if (mPreparedPanel == panelfeaturestate)
            {
                mPreparedPanel = null;
                return;
            }
        }
    }

    final boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        View view;
        boolean flag1;
        boolean flag3;
        flag3 = false;
        flag1 = false;
        view = mWindow.getDecorView();
        break MISSING_BLOCK_LABEL_14;
_L15:
        boolean flag;
        int i;
        do
        {
            return true;
        } while (ViewCompat.dispatchUnhandledKeyEventPre(view, keyevent) || keyevent.getKeyCode() == 82 && mOriginalWindowCallback.dispatchKeyEvent(keyevent));
        i = keyevent.getKeyCode();
        if (keyevent.getAction() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR lookupswitch 2: default 96
    //                   4: 150
    //                   82: 117;
           goto _L3 _L4 _L5
_L3:
        flag = flag1;
_L6:
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
_L10:
        return ViewCompat.dispatchUnhandledKeyEventPost(view, keyevent);
_L5:
        if (keyevent.getRepeatCount() == 0)
        {
            PanelFeatureState panelfeaturestate = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
            if (!panelfeaturestate.isOpen)
            {
                preparePanel(panelfeaturestate, keyevent);
            }
        }
        flag = true;
          goto _L6
_L4:
        boolean flag4;
        if ((keyevent.getFlags() & 0x80) != 0)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        mLongPressBackDown = flag4;
        flag = flag1;
          goto _L6
_L2:
        i;
        JVM INSTR lookupswitch 2: default 212
    //                   4: 444
    //                   82: 223;
           goto _L7 _L8 _L9
_L8:
        break MISSING_BLOCK_LABEL_444;
_L7:
        flag = flag3;
_L13:
        if (flag)
        {
            return true;
        }
          goto _L10
_L9:
        if (mActionMode == null) goto _L12; else goto _L11
_L11:
        flag = true;
          goto _L13
_L12:
        Object obj = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
        boolean flag5;
        if (mDecorContentParent != null && mDecorContentParent.canShowOverflowMenu() && !ViewConfiguration.get(mContext).hasPermanentMenuKey())
        {
            if (!mDecorContentParent.isOverflowMenuShowing())
            {
                if (mIsDestroyed || !preparePanel(((PanelFeatureState) (obj)), keyevent))
                {
                    break MISSING_BLOCK_LABEL_549;
                }
                flag5 = mDecorContentParent.showOverflowMenu();
            } else
            {
                flag5 = mDecorContentParent.hideOverflowMenu();
            }
        } else
        if (((PanelFeatureState) (obj)).isOpen || ((PanelFeatureState) (obj)).isHandled)
        {
            flag5 = ((PanelFeatureState) (obj)).isOpen;
            closePanel(((PanelFeatureState) (obj)), true);
        } else
        {
            if (!((PanelFeatureState) (obj)).isPrepared)
            {
                break MISSING_BLOCK_LABEL_549;
            }
            boolean flag2;
            if (((PanelFeatureState) (obj)).refreshMenuContent)
            {
                obj.isPrepared = false;
                flag5 = preparePanel(((PanelFeatureState) (obj)), keyevent);
            } else
            {
                flag5 = true;
            }
            if (!flag5)
            {
                break MISSING_BLOCK_LABEL_549;
            }
            openPanel(((PanelFeatureState) (obj)), keyevent);
            flag5 = true;
        }
        if (flag5)
        {
            obj = (AudioManager)mContext.getSystemService("audio");
            if (obj != null)
            {
                ((AudioManager) (obj)).playSoundEffect(0);
            } else
            {
                Log.w("AppCompatDelegate", "Couldn't get audio manager");
            }
        }
          goto _L11
        flag5 = mLongPressBackDown;
        mLongPressBackDown = false;
        obj = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
        if (obj != null && ((PanelFeatureState) (obj)).isOpen)
        {
            if (!flag5)
            {
                closePanel(((PanelFeatureState) (obj)), true);
            }
            flag = true;
        } else
        {
            if (mActionMode != null)
            {
                mActionMode.finish();
                flag2 = true;
            } else
            {
                obj = getSupportActionBar();
                if (obj != null && ((ActionBar) (obj)).collapseActionView())
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
            }
            flag = flag3;
            if (flag2)
            {
                flag = true;
            }
        }
          goto _L13
        flag5 = false;
        break MISSING_BLOCK_LABEL_313;
        if (true) goto _L15; else goto _L14
_L14:
    }

    final void doInvalidatePanelMenu(int i)
    {
        PanelFeatureState panelfeaturestate = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(i);
        if (panelfeaturestate.menu != null)
        {
            Object obj = new Bundle();
            panelfeaturestate.menu.saveActionViewStates(((Bundle) (obj)));
            if (((Bundle) (obj)).size() > 0)
            {
                panelfeaturestate.frozenActionViewState = ((Bundle) (obj));
            }
            obj = panelfeaturestate.menu;
            if (!((MenuBuilder) (obj)).mPreventDispatchingItemsChanged)
            {
                obj.mPreventDispatchingItemsChanged = true;
                obj.mItemsChangedWhileDispatchPrevented = false;
                obj.mStructureChangedWhileDispatchPrevented = false;
            }
            panelfeaturestate.menu.clear();
        }
        panelfeaturestate.refreshMenuContent = true;
        panelfeaturestate.refreshDecorView = true;
        if ((i == 108 || i == 0) && mDecorContentParent != null)
        {
            PanelFeatureState panelfeaturestate1 = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
            if (panelfeaturestate1 != null)
            {
                panelfeaturestate1.isPrepared = false;
                preparePanel(panelfeaturestate1, null);
            }
        }
    }

    final void endOnGoingFadeAnimation()
    {
        if (mFadeAnim != null)
        {
            View view = (View)mFadeAnim.mView.get();
            if (view != null)
            {
                view.animate().cancel();
            }
        }
    }

    final PanelFeatureState findMenuPanel(Menu menu)
    {
        PanelFeatureState apanelfeaturestate[] = mPanels;
        int i;
        int j;
        if (apanelfeaturestate != null)
        {
            i = apanelfeaturestate.length;
        } else
        {
            i = 0;
        }
        for (j = 0; j < i; j++)
        {
            PanelFeatureState panelfeaturestate = apanelfeaturestate[j];
            if (panelfeaturestate != null && panelfeaturestate.menu == menu)
            {
                return panelfeaturestate;
            }
        }

        return null;
    }

    public final View findViewById(int i)
    {
        ensureSubDecor();
        return mWindow.findViewById(i);
    }

    public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate()
    {
        return new ActionBarDrawableToggleImpl();
    }

    public final MenuInflater getMenuInflater()
    {
        if (mMenuInflater == null)
        {
            initWindowDecorActionBar();
            Context context;
            if (mActionBar != null)
            {
                context = mActionBar.getThemedContext();
            } else
            {
                context = mContext;
            }
            mMenuInflater = new SupportMenuInflater(context);
        }
        return mMenuInflater;
    }

    protected final PanelFeatureState getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(int i)
    {
        PanelFeatureState apanelfeaturestate[];
label0:
        {
            PanelFeatureState apanelfeaturestate1[] = mPanels;
            if (apanelfeaturestate1 != null)
            {
                apanelfeaturestate = apanelfeaturestate1;
                if (apanelfeaturestate1.length > i)
                {
                    break label0;
                }
            }
            apanelfeaturestate = new PanelFeatureState[i + 1];
            if (apanelfeaturestate1 != null)
            {
                System.arraycopy(apanelfeaturestate1, 0, apanelfeaturestate, 0, apanelfeaturestate1.length);
            }
            mPanels = apanelfeaturestate;
        }
        PanelFeatureState panelfeaturestate = apanelfeaturestate[i];
        if (panelfeaturestate == null)
        {
            panelfeaturestate = new PanelFeatureState(i);
            apanelfeaturestate[i] = panelfeaturestate;
            return panelfeaturestate;
        } else
        {
            return panelfeaturestate;
        }
    }

    public final ActionBar getSupportActionBar()
    {
        initWindowDecorActionBar();
        return mActionBar;
    }

    public final void installViewFactory()
    {
        LayoutInflater layoutinflater = LayoutInflater.from(mContext);
        if (layoutinflater.getFactory() == null)
        {
            layoutinflater.setFactory2(this);
            return;
        } else
        {
            layoutinflater.getFactory2();
            return;
        }
    }

    public final void invalidateOptionsMenu()
    {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null && actionbar.invalidateOptionsMenu())
        {
            return;
        } else
        {
            invalidatePanelMenu(0);
            return;
        }
    }

    public final void onConfigurationChanged(Configuration configuration)
    {
        Object obj;
        Context context;
        if (mHasActionBar && mSubDecorInstalled)
        {
            ActionBar actionbar = getSupportActionBar();
            if (actionbar != null)
            {
                actionbar.onConfigurationChanged(configuration);
            }
        }
        if (AppCompatDrawableManager.INSTANCE == null)
        {
            configuration = new AppCompatDrawableManager();
            AppCompatDrawableManager.INSTANCE = configuration;
            AppCompatDrawableManager.installDefaultInflateDelegates(configuration);
        }
        obj = AppCompatDrawableManager.INSTANCE;
        context = mContext;
        configuration = ((Configuration) (((AppCompatDrawableManager) (obj)).mDrawableCacheLock));
        configuration;
        JVM INSTR monitorenter ;
        obj = (LongSparseArray)((AppCompatDrawableManager) (obj)).mDrawableCaches.get(context);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        ((LongSparseArray) (obj)).clear();
        configuration;
        JVM INSTR monitorexit ;
        applyDayNight();
        return;
        Exception exception;
        exception;
        configuration;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void onCreate(Bundle bundle)
    {
        if (mOriginalWindowCallback instanceof Activity)
        {
            Object obj;
            try
            {
                obj = NavUtils.getParentActivityName((Activity)mOriginalWindowCallback);
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                obj = null;
            }
            if (obj != null)
            {
                obj = mActionBar;
                if (obj == null)
                {
                    mEnableDefaultActionBarUp = true;
                } else
                {
                    ((ActionBar) (obj)).setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (bundle != null && mLocalNightMode == -100)
        {
            mLocalNightMode = bundle.getInt("appcompat:local_night_mode", -100);
        }
    }

    public final View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        return createView(view, s, context, attributeset);
    }

    public final View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        return onCreateView(null, s, context, attributeset);
    }

    public final void onDestroy()
    {
        if (mInvalidatePanelMenuPosted)
        {
            mWindow.getDecorView().removeCallbacks(mInvalidatePanelMenuRunnable);
        }
        mIsDestroyed = true;
        if (mActionBar != null)
        {
            mActionBar.onDestroy();
        }
        if (mAutoNightModeManager != null)
        {
            AutoNightModeManager autonightmodemanager = mAutoNightModeManager;
            if (autonightmodemanager.mAutoTimeChangeReceiver != null)
            {
                autonightmodemanager._fld0.mContext.unregisterReceiver(autonightmodemanager.mAutoTimeChangeReceiver);
                autonightmodemanager.mAutoTimeChangeReceiver = null;
            }
        }
    }

    public final boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        android.view.Window.Callback callback = mWindow.getCallback();
        if (callback != null && !mIsDestroyed)
        {
            menubuilder = findMenuPanel(menubuilder.getRootMenu());
            if (menubuilder != null)
            {
                return callback.onMenuItemSelected(((PanelFeatureState) (menubuilder)).featureId, menuitem);
            }
        }
        return false;
    }

    public final void onMenuModeChange(MenuBuilder menubuilder)
    {
        if (mDecorContentParent == null || !mDecorContentParent.canShowOverflowMenu() || ViewConfiguration.get(mContext).hasPermanentMenuKey() && !mDecorContentParent.isOverflowMenuShowPending())
        {
            break MISSING_BLOCK_LABEL_211;
        }
        menubuilder = mWindow.getCallback();
        if (mDecorContentParent.isOverflowMenuShowing()) goto _L2; else goto _L1
_L1:
        if (menubuilder != null && !mIsDestroyed)
        {
            if (mInvalidatePanelMenuPosted && (mInvalidatePanelMenuFeatures & 1) != 0)
            {
                mWindow.getDecorView().removeCallbacks(mInvalidatePanelMenuRunnable);
                mInvalidatePanelMenuRunnable.run();
            }
            PanelFeatureState panelfeaturestate = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
            if (panelfeaturestate.menu != null && !panelfeaturestate.refreshMenuContent && menubuilder.onPreparePanel(0, panelfeaturestate.createdPanelView, panelfeaturestate.menu))
            {
                menubuilder.onMenuOpened(108, panelfeaturestate.menu);
                mDecorContentParent.showOverflowMenu();
            }
        }
_L4:
        return;
_L2:
        mDecorContentParent.hideOverflowMenu();
        if (mIsDestroyed) goto _L4; else goto _L3
_L3:
        menubuilder.onPanelClosed(108, getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0).menu);
        return;
        menubuilder = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
        menubuilder.refreshDecorView = true;
        closePanel(menubuilder, false);
        openPanel(menubuilder, null);
        return;
    }

    public final void onPostCreate$51662RJ4E9NMIP1FDTPIUGJLDPI6OP9R55B0____0()
    {
        ensureSubDecor();
    }

    public final void onPostResume()
    {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
        {
            actionbar.setShowHideAnimationEnabled(true);
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        if (mLocalNightMode != -100)
        {
            bundle.putInt("appcompat:local_night_mode", mLocalNightMode);
        }
    }

    public final void onStart()
    {
        applyDayNight();
    }

    public final void onStop()
    {
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
        {
            actionbar.setShowHideAnimationEnabled(false);
        }
        if (mAutoNightModeManager != null)
        {
            AutoNightModeManager autonightmodemanager = mAutoNightModeManager;
            if (autonightmodemanager.mAutoTimeChangeReceiver != null)
            {
                autonightmodemanager._fld0.mContext.unregisterReceiver(autonightmodemanager.mAutoTimeChangeReceiver);
                autonightmodemanager.mAutoTimeChangeReceiver = null;
            }
        }
    }

    final boolean performPanelShortcut(PanelFeatureState panelfeaturestate, int i, KeyEvent keyevent, int j)
    {
        while (keyevent.isSystem() || !panelfeaturestate.isPrepared && !preparePanel(panelfeaturestate, keyevent) || panelfeaturestate.menu == null) 
        {
            return false;
        }
        return panelfeaturestate.menu.performShortcut(i, keyevent, 1);
    }

    final boolean preparePanel(PanelFeatureState panelfeaturestate, KeyEvent keyevent)
    {
        if (!mIsDestroyed) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        Object obj;
        Context context;
        android.view.Window.Callback callback;
        boolean flag;
        if (panelfeaturestate.isPrepared)
        {
            return true;
        }
        if (mPreparedPanel != null && mPreparedPanel != panelfeaturestate)
        {
            closePanel(mPreparedPanel, false);
        }
        callback = mWindow.getCallback();
        if (callback != null)
        {
            panelfeaturestate.createdPanelView = callback.onCreatePanelView(panelfeaturestate.featureId);
        }
        android.content.res.Resources.Theme theme;
        if (panelfeaturestate.featureId == 0 || panelfeaturestate.featureId == 108)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && mDecorContentParent != null)
        {
            mDecorContentParent.setMenuPrepared();
        }
        if (panelfeaturestate.createdPanelView != null || flag && (mActionBar instanceof ToolbarActionBar)) goto _L4; else goto _L3
_L3:
        if (panelfeaturestate.menu != null && !panelfeaturestate.refreshMenuContent) goto _L6; else goto _L5
_L5:
        if (panelfeaturestate.menu != null)
        {
            break; /* Loop/switch isn't completed */
        }
        context = mContext;
        if (panelfeaturestate.featureId != 0 && panelfeaturestate.featureId != 108 || mDecorContentParent == null)
        {
            break MISSING_BLOCK_LABEL_734;
        }
        TypedValue typedvalue = new TypedValue();
        android.content.res.Resources.Theme theme1 = context.getTheme();
        theme1.resolveAttribute(0x7f010080, typedvalue, true);
        if (typedvalue.resourceId != 0)
        {
            obj = context.getResources().newTheme();
            ((android.content.res.Resources.Theme) (obj)).setTo(theme1);
            ((android.content.res.Resources.Theme) (obj)).applyStyle(typedvalue.resourceId, true);
            ((android.content.res.Resources.Theme) (obj)).resolveAttribute(0x7f010081, typedvalue, true);
        } else
        {
            theme1.resolveAttribute(0x7f010081, typedvalue, true);
            obj = null;
        }
        theme = ((android.content.res.Resources.Theme) (obj));
        if (typedvalue.resourceId != 0)
        {
            theme = ((android.content.res.Resources.Theme) (obj));
            if (obj == null)
            {
                theme = context.getResources().newTheme();
                theme.setTo(theme1);
            }
            theme.applyStyle(typedvalue.resourceId, true);
        }
        if (theme == null)
        {
            break MISSING_BLOCK_LABEL_734;
        }
        obj = new ContextThemeWrapper(context, 0);
        ((Context) (obj)).getTheme().setTo(theme);
_L8:
        obj = new MenuBuilder(((Context) (obj)));
        ((MenuBuilder) (obj)).setCallback(this);
        panelfeaturestate.setMenu(((MenuBuilder) (obj)));
        if (panelfeaturestate.menu == null) goto _L1; else goto _L7
_L7:
label0:
        {
            if (flag && mDecorContentParent != null)
            {
                if (mActionMenuPresenterCallback == null)
                {
                    mActionMenuPresenterCallback = new ActionMenuPresenterCallback();
                }
                mDecorContentParent.setMenu(panelfeaturestate.menu, mActionMenuPresenterCallback);
            }
            obj = panelfeaturestate.menu;
            if (!((MenuBuilder) (obj)).mPreventDispatchingItemsChanged)
            {
                obj.mPreventDispatchingItemsChanged = true;
                obj.mItemsChangedWhileDispatchPrevented = false;
                obj.mStructureChangedWhileDispatchPrevented = false;
            }
            if (callback.onCreatePanelMenu(panelfeaturestate.featureId, panelfeaturestate.menu))
            {
                break label0;
            }
            panelfeaturestate.setMenu(null);
            if (flag && mDecorContentParent != null)
            {
                mDecorContentParent.setMenu(null, mActionMenuPresenterCallback);
                return false;
            }
        }
          goto _L1
        panelfeaturestate.refreshMenuContent = false;
_L6:
label1:
        {
            obj = panelfeaturestate.menu;
            if (!((MenuBuilder) (obj)).mPreventDispatchingItemsChanged)
            {
                obj.mPreventDispatchingItemsChanged = true;
                obj.mItemsChangedWhileDispatchPrevented = false;
                obj.mStructureChangedWhileDispatchPrevented = false;
            }
            if (panelfeaturestate.frozenActionViewState != null)
            {
                panelfeaturestate.menu.restoreActionViewStates(panelfeaturestate.frozenActionViewState);
                panelfeaturestate.frozenActionViewState = null;
            }
            if (callback.onPreparePanel(0, panelfeaturestate.createdPanelView, panelfeaturestate.menu))
            {
                break label1;
            }
            if (flag && mDecorContentParent != null)
            {
                mDecorContentParent.setMenu(null, mActionMenuPresenterCallback);
            }
            panelfeaturestate = panelfeaturestate.menu;
            panelfeaturestate.mPreventDispatchingItemsChanged = false;
            if (((MenuBuilder) (panelfeaturestate)).mItemsChangedWhileDispatchPrevented)
            {
                panelfeaturestate.mItemsChangedWhileDispatchPrevented = false;
                panelfeaturestate.onItemsChanged(((MenuBuilder) (panelfeaturestate)).mStructureChangedWhileDispatchPrevented);
                return false;
            }
        }
          goto _L1
        int i;
        boolean flag1;
        if (keyevent != null)
        {
            i = keyevent.getDeviceId();
        } else
        {
            i = -1;
        }
        if (KeyCharacterMap.load(i).getKeyboardType() != 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        panelfeaturestate.qwertyMode = flag1;
        panelfeaturestate.menu.setQwertyMode(panelfeaturestate.qwertyMode);
        keyevent = panelfeaturestate.menu;
        keyevent.mPreventDispatchingItemsChanged = false;
        if (((MenuBuilder) (keyevent)).mItemsChangedWhileDispatchPrevented)
        {
            keyevent.mItemsChangedWhileDispatchPrevented = false;
            keyevent.onItemsChanged(((MenuBuilder) (keyevent)).mStructureChangedWhileDispatchPrevented);
        }
_L4:
        panelfeaturestate.isPrepared = true;
        panelfeaturestate.isHandled = false;
        mPreparedPanel = panelfeaturestate;
        return true;
        obj = context;
          goto _L8
    }

    public final boolean requestWindowFeature(int i)
    {
        if (i != 8) goto _L2; else goto _L1
_L1:
        int j = 108;
_L4:
        if (mWindowNoTitle && j == 108)
        {
            return false;
        }
        break; /* Loop/switch isn't completed */
_L2:
        j = i;
        if (i == 9)
        {
            j = 109;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (mHasActionBar && j == 1)
        {
            mHasActionBar = false;
        }
        switch (j)
        {
        default:
            return mWindow.requestFeature(j);

        case 108: // 'l'
            if (mSubDecorInstalled)
            {
                throw new AndroidRuntimeException("Window feature must be requested before adding content");
            } else
            {
                mHasActionBar = true;
                return true;
            }

        case 109: // 'm'
            if (mSubDecorInstalled)
            {
                throw new AndroidRuntimeException("Window feature must be requested before adding content");
            } else
            {
                mOverlayActionBar = true;
                return true;
            }

        case 10: // '\n'
            if (mSubDecorInstalled)
            {
                throw new AndroidRuntimeException("Window feature must be requested before adding content");
            } else
            {
                mOverlayActionMode = true;
                return true;
            }

        case 2: // '\002'
            if (mSubDecorInstalled)
            {
                throw new AndroidRuntimeException("Window feature must be requested before adding content");
            } else
            {
                mFeatureProgress = true;
                return true;
            }

        case 5: // '\005'
            if (mSubDecorInstalled)
            {
                throw new AndroidRuntimeException("Window feature must be requested before adding content");
            } else
            {
                mFeatureIndeterminateProgress = true;
                return true;
            }

        case 1: // '\001'
            break;
        }
        if (mSubDecorInstalled)
        {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        } else
        {
            mWindowNoTitle = true;
            return true;
        }
    }

    public final void setContentView(int i)
    {
        ensureSubDecor();
        ViewGroup viewgroup = (ViewGroup)mSubDecor.findViewById(0x1020002);
        viewgroup.removeAllViews();
        LayoutInflater.from(mContext).inflate(i, viewgroup);
        mOriginalWindowCallback.onContentChanged();
    }

    public final void setContentView(View view)
    {
        ensureSubDecor();
        ViewGroup viewgroup = (ViewGroup)mSubDecor.findViewById(0x1020002);
        viewgroup.removeAllViews();
        viewgroup.addView(view);
        mOriginalWindowCallback.onContentChanged();
    }

    public final void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        ensureSubDecor();
        ViewGroup viewgroup = (ViewGroup)mSubDecor.findViewById(0x1020002);
        viewgroup.removeAllViews();
        viewgroup.addView(view, layoutparams);
        mOriginalWindowCallback.onContentChanged();
    }

    public final void setSupportActionBar(Toolbar toolbar)
    {
        if (!(mOriginalWindowCallback instanceof Activity))
        {
            return;
        }
        ActionBar actionbar = getSupportActionBar();
        if (actionbar instanceof WindowDecorActionBar)
        {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
        mMenuInflater = null;
        if (actionbar != null)
        {
            actionbar.onDestroy();
        }
        if (toolbar != null)
        {
            toolbar = new ToolbarActionBar(toolbar, ((Activity)mOriginalWindowCallback).getTitle(), mAppCompatWindowCallback);
            mActionBar = toolbar;
            mWindow.setCallback(((ToolbarActionBar) (toolbar)).mWindowCallback);
        } else
        {
            mActionBar = null;
            mWindow.setCallback(mAppCompatWindowCallback);
        }
        invalidateOptionsMenu();
    }

    public final void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        if (mDecorContentParent != null)
        {
            mDecorContentParent.setWindowTitle(charsequence);
        } else
        {
            if (mActionBar != null)
            {
                mActionBar.setWindowTitle(charsequence);
                return;
            }
            if (mTitleView != null)
            {
                mTitleView.setText(charsequence);
                return;
            }
        }
    }

    final ActionMode startSupportActionModeFromWindow(android.support.v7.view.ActionMode.Callback callback)
    {
        boolean flag = true;
        if (mFadeAnim != null)
        {
            View view = (View)mFadeAnim.mView.get();
            if (view != null)
            {
                view.animate().cancel();
            }
        }
        if (mActionMode != null)
        {
            mActionMode.finish();
        }
        if (mAppCompatCallback != null && !mIsDestroyed)
        {
            try
            {
                mAppCompatCallback.onWindowStartingSupportActionMode$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKI46OBCDHH62ORB7CKKOOBECHP6UQB45TPNAS3GDTP78BRM6SNNCQB5ESNK2ORKD5NMSJBFCHIJM___0();
            }
            // Misplaced declaration of an exception variable
            catch (Object obj1) { }
        }
        if (mActionModeView == null)
        {
            if (mIsFloating)
            {
                Object obj2 = new TypedValue();
                Object obj = mContext.getTheme();
                ((android.content.res.Resources.Theme) (obj)).resolveAttribute(0x7f010080, ((TypedValue) (obj2)), true);
                int i;
                if (((TypedValue) (obj2)).resourceId != 0)
                {
                    android.content.res.Resources.Theme theme = mContext.getResources().newTheme();
                    theme.setTo(((android.content.res.Resources.Theme) (obj)));
                    theme.applyStyle(((TypedValue) (obj2)).resourceId, true);
                    obj = new ContextThemeWrapper(mContext, 0);
                    ((Context) (obj)).getTheme().setTo(theme);
                } else
                {
                    obj = mContext;
                }
                mActionModeView = new ActionBarContextView(((Context) (obj)));
                mActionModePopup = new PopupWindow(((Context) (obj)), null, 0x7f010093);
                PopupWindowCompat.setWindowLayoutType(mActionModePopup, 2);
                mActionModePopup.setContentView(mActionModeView);
                mActionModePopup.setWidth(-1);
                ((Context) (obj)).getTheme().resolveAttribute(0x7f010082, ((TypedValue) (obj2)), true);
                i = TypedValue.complexToDimensionPixelSize(((TypedValue) (obj2)).data, ((Context) (obj)).getResources().getDisplayMetrics());
                mActionModeView.setContentHeight(i);
                mActionModePopup.setHeight(-2);
                mShowActionModePopup = new _cls6();
            } else
            {
                ViewStubCompat viewstubcompat = (ViewStubCompat)mSubDecor.findViewById(0x7f1000ec);
                if (viewstubcompat != null)
                {
                    Object obj1 = getSupportActionBar();
                    Object obj3;
                    if (obj1 != null)
                    {
                        obj1 = ((ActionBar) (obj1)).getThemedContext();
                    } else
                    {
                        obj1 = null;
                    }
                    obj3 = obj1;
                    if (obj1 == null)
                    {
                        obj3 = mContext;
                    }
                    viewstubcompat.mInflater = LayoutInflater.from(((Context) (obj3)));
                    mActionModeView = (ActionBarContextView)viewstubcompat.inflate();
                }
            }
        }
        if (mActionModeView != null)
        {
            if (mFadeAnim != null)
            {
                obj = (View)mFadeAnim.mView.get();
                if (obj != null)
                {
                    ((View) (obj)).animate().cancel();
                }
            }
            obj = mActionModeView;
            ((ActionBarContextView) (obj)).removeAllViews();
            obj.mCustomView = null;
            obj.mMenuView = null;
            obj = mActionModeView.getContext();
            obj2 = mActionModeView;
            boolean flag1;
            if (mActionModePopup == null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            obj = new StandaloneActionMode(((Context) (obj)), ((ActionBarContextView) (obj2)), callback, flag1);
            if (callback.onCreateActionMode(((ActionMode) (obj)), ((ActionMode) (obj)).getMenu()))
            {
                ((ActionMode) (obj)).invalidate();
                mActionModeView.initForMode(((ActionMode) (obj)));
                mActionMode = ((ActionMode) (obj));
                if (!mSubDecorInstalled || mSubDecor == null || !ViewCompat.isLaidOut(mSubDecor))
                {
                    flag = false;
                }
                if (flag)
                {
                    mActionModeView.setAlpha(0.0F);
                    callback = ViewCompat.animate(mActionModeView);
                    obj = (View)((ViewPropertyAnimatorCompat) (callback)).mView.get();
                    if (obj != null)
                    {
                        ((View) (obj)).animate().alpha(1.0F);
                    }
                    mFadeAnim = callback;
                    mFadeAnim.setListener(new _cls7());
                } else
                {
                    mActionModeView.setAlpha(1.0F);
                    mActionModeView.setVisibility(0);
                    mActionModeView.sendAccessibilityEvent(32);
                    if (mActionModeView.getParent() instanceof View)
                    {
                        ViewCompat.requestApplyInsets((View)mActionModeView.getParent());
                    }
                }
                if (mActionModePopup != null)
                {
                    mWindow.getDecorView().post(mShowActionModePopup);
                }
            } else
            {
                mActionMode = null;
            }
        }
        if (mActionMode != null && mAppCompatCallback != null)
        {
            mAppCompatCallback.onSupportActionModeStarted$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKTIILG_0();
        }
        return mActionMode;
    }

    final int updateStatusGuard(int i)
    {
        int k = 1;
        boolean flag = true;
        boolean flag1 = false;
        int j;
        if (mActionModeView != null && (mActionModeView.getLayoutParams() instanceof android.view.ViewGroup.MarginLayoutParams))
        {
            Object obj = (android.view.ViewGroup.MarginLayoutParams)mActionModeView.getLayoutParams();
            if (mActionModeView.isShown())
            {
                if (mTempRect1 == null)
                {
                    mTempRect1 = new Rect();
                    mTempRect2 = new Rect();
                }
                Rect rect1 = mTempRect1;
                Rect rect = mTempRect2;
                rect1.set(0, i, 0, 0);
                ViewGroup viewgroup = mSubDecor;
                if (ViewUtils.sComputeFitSystemWindowsMethod != null)
                {
                    try
                    {
                        ViewUtils.sComputeFitSystemWindowsMethod.invoke(viewgroup, new Object[] {
                            rect1, rect
                        });
                    }
                    catch (Exception exception) { }
                }
                if (rect.top == 0)
                {
                    j = i;
                } else
                {
                    j = 0;
                }
                if (((android.view.ViewGroup.MarginLayoutParams) (obj)).topMargin != j)
                {
                    obj.topMargin = i;
                    if (mStatusGuard == null)
                    {
                        mStatusGuard = new View(mContext);
                        mStatusGuard.setBackgroundColor(mContext.getResources().getColor(0x7f0d0000));
                        mSubDecor.addView(mStatusGuard, -1, new android.view.ViewGroup.LayoutParams(-1, i));
                        j = 1;
                    } else
                    {
                        android.view.ViewGroup.LayoutParams layoutparams = mStatusGuard.getLayoutParams();
                        if (layoutparams.height != i)
                        {
                            layoutparams.height = i;
                            mStatusGuard.setLayoutParams(layoutparams);
                        }
                        j = 1;
                    }
                } else
                {
                    j = 0;
                }
                if (mStatusGuard == null)
                {
                    flag = false;
                }
                k = i;
                if (!mOverlayActionMode)
                {
                    k = i;
                    if (flag)
                    {
                        k = 0;
                    }
                }
                i = k;
                k = j;
                j = ((flag) ? 1 : 0);
            } else
            if (((android.view.ViewGroup.MarginLayoutParams) (obj)).topMargin != 0)
            {
                obj.topMargin = 0;
                j = 0;
            } else
            {
                k = 0;
                j = 0;
            }
            if (k != 0)
            {
                mActionModeView.setLayoutParams(((android.view.ViewGroup.LayoutParams) (obj)));
            }
        } else
        {
            j = 0;
        }
        if (mStatusGuard != null)
        {
            obj = mStatusGuard;
            if (j != 0)
            {
                j = ((flag1) ? 1 : 0);
            } else
            {
                j = 8;
            }
            ((View) (obj)).setVisibility(j);
        }
        return i;
    }


    private class _cls2
        implements Runnable
    {

        private final AppCompatDelegateImpl this$0;

        public final void run()
        {
            if ((mInvalidatePanelMenuFeatures & 1) != 0)
            {
                doInvalidatePanelMenu(0);
            }
            if ((mInvalidatePanelMenuFeatures & 0x1000) != 0)
            {
                doInvalidatePanelMenu(108);
            }
            mInvalidatePanelMenuPosted = false;
            mInvalidatePanelMenuFeatures = 0;
        }

        _cls2()
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
        }
    }


    private class AppCompatWindowCallback extends WindowCallbackWrapper
    {

        private final AppCompatDelegateImpl this$0;

        private final android.view.ActionMode startAsSupportActionMode(android.view.ActionMode.Callback callback)
        {
            callback = new android.support.v7.view.SupportActionModeWrapper.CallbackWrapper(mContext, callback);
            Object obj = AppCompatDelegateImpl.this;
            if (((AppCompatDelegateImpl) (obj)).mActionMode != null)
            {
                ((AppCompatDelegateImpl) (obj)).mActionMode.finish();
            }
            ActionModeCallbackWrapperV9 actionmodecallbackwrapperv9 = ((ActionModeCallbackWrapperV9) (obj)). new ActionModeCallbackWrapperV9(callback);
            ActionBar actionbar = ((AppCompatDelegate) (obj)).getSupportActionBar();
            if (actionbar != null)
            {
                obj.mActionMode = actionbar.startActionMode(actionmodecallbackwrapperv9);
                if (((AppCompatDelegateImpl) (obj)).mActionMode != null && ((AppCompatDelegateImpl) (obj)).mAppCompatCallback != null)
                {
                    ((AppCompatDelegateImpl) (obj)).mAppCompatCallback.onSupportActionModeStarted$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKTIILG_0();
                }
            }
            if (((AppCompatDelegateImpl) (obj)).mActionMode == null)
            {
                obj.mActionMode = ((AppCompatDelegateImpl) (obj)).startSupportActionModeFromWindow(actionmodecallbackwrapperv9);
            }
            obj = ((AppCompatDelegateImpl) (obj)).mActionMode;
            if (obj != null)
            {
                return callback.getActionModeWrapper(((ActionMode) (obj)));
            } else
            {
                return null;
            }
        }

        public final boolean dispatchKeyEvent(KeyEvent keyevent)
        {
            return AppCompatDelegateImpl.this.dispatchKeyEvent(keyevent) || super.dispatchKeyEvent(keyevent);
        }

        public final boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
        {
            boolean flag = false;
            if (super.dispatchKeyShortcutEvent(keyevent)) goto _L2; else goto _L1
_L1:
            AppCompatDelegateImpl appcompatdelegateimpl;
            ActionBar actionbar;
            int i;
            appcompatdelegateimpl = AppCompatDelegateImpl.this;
            i = keyevent.getKeyCode();
            actionbar = appcompatdelegateimpl.getSupportActionBar();
            if (actionbar == null || !actionbar.onKeyShortcut(i, keyevent)) goto _L4; else goto _L3
_L3:
            i = 1;
_L7:
            if (!i) goto _L5; else goto _L2
_L2:
            flag = true;
_L5:
            return flag;
_L4:
            if (appcompatdelegateimpl.mPreparedPanel != null && appcompatdelegateimpl.performPanelShortcut(appcompatdelegateimpl.mPreparedPanel, keyevent.getKeyCode(), keyevent, 1))
            {
                if (appcompatdelegateimpl.mPreparedPanel != null)
                {
                    appcompatdelegateimpl.mPreparedPanel.isHandled = true;
                }
                i = 1;
                continue; /* Loop/switch isn't completed */
            }
            if (appcompatdelegateimpl.mPreparedPanel == null)
            {
                PanelFeatureState panelfeaturestate = appcompatdelegateimpl.getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
                appcompatdelegateimpl.preparePanel(panelfeaturestate, keyevent);
                boolean flag1 = appcompatdelegateimpl.performPanelShortcut(panelfeaturestate, keyevent.getKeyCode(), keyevent, 1);
                panelfeaturestate.isPrepared = false;
                if (flag1)
                {
                    i = 1;
                    continue; /* Loop/switch isn't completed */
                }
            }
            i = 0;
            if (true) goto _L7; else goto _L6
_L6:
        }

        public final void onContentChanged()
        {
        }

        public final boolean onCreatePanelMenu(int i, Menu menu)
        {
            if (i == 0 && !(menu instanceof MenuBuilder))
            {
                return false;
            } else
            {
                return super.onCreatePanelMenu(i, menu);
            }
        }

        public final boolean onMenuOpened(int i, Menu menu)
        {
            super.onMenuOpened(i, menu);
            menu = AppCompatDelegateImpl.this;
            if (i == 108)
            {
                menu = menu.getSupportActionBar();
                if (menu != null)
                {
                    menu.dispatchMenuVisibilityChanged(true);
                }
            }
            return true;
        }

        public final void onPanelClosed(int i, Menu menu)
        {
            super.onPanelClosed(i, menu);
            menu = AppCompatDelegateImpl.this;
            if (i == 108)
            {
                menu = menu.getSupportActionBar();
                if (menu != null)
                {
                    menu.dispatchMenuVisibilityChanged(false);
                }
            } else
            if (i == 0)
            {
                PanelFeatureState panelfeaturestate = menu.getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(i);
                if (panelfeaturestate.isOpen)
                {
                    menu.closePanel(panelfeaturestate, false);
                    return;
                }
            }
        }

        public final boolean onPreparePanel(int i, View view, Menu menu)
        {
            MenuBuilder menubuilder;
            boolean flag;
            if (menu instanceof MenuBuilder)
            {
                menubuilder = (MenuBuilder)menu;
            } else
            {
                menubuilder = null;
            }
            if (i == 0 && menubuilder == null)
            {
                flag = false;
            } else
            {
                if (menubuilder != null)
                {
                    menubuilder.mOverrideVisibleItems = true;
                }
                boolean flag1 = super.onPreparePanel(i, view, menu);
                flag = flag1;
                if (menubuilder != null)
                {
                    menubuilder.mOverrideVisibleItems = false;
                    return flag1;
                }
            }
            return flag;
        }

        public final void onProvideKeyboardShortcuts(List list, Menu menu, int i)
        {
            PanelFeatureState panelfeaturestate = getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
            if (panelfeaturestate != null && panelfeaturestate.menu != null)
            {
                super.onProvideKeyboardShortcuts(list, panelfeaturestate.menu, i);
                return;
            } else
            {
                super.onProvideKeyboardShortcuts(list, menu, i);
                return;
            }
        }

        public final android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback)
        {
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                return null;
            }
            if (mHandleNativeActionModes)
            {
                return startAsSupportActionMode(callback);
            } else
            {
                return super.onWindowStartingActionMode(callback);
            }
        }

        public final android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int i)
        {
            if (!mHandleNativeActionModes) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR tableswitch 0 0: default 28
        //                       0 35;
               goto _L2 _L3
_L2:
            return super.onWindowStartingActionMode(callback, i);
_L3:
            return startAsSupportActionMode(callback);
        }

        AppCompatWindowCallback(android.view.Window.Callback callback)
        {
            this$0 = AppCompatDelegateImpl.this;
            super(callback);
        }

        private class ActionModeCallbackWrapperV9
            implements android.support.v7.view.ActionMode.Callback
        {

            private android.support.v7.view.ActionMode.Callback mWrapped;
            public final AppCompatDelegateImpl this$0;

            public final boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem)
            {
                return mWrapped.onActionItemClicked(actionmode, menuitem);
            }

            public final boolean onCreateActionMode(ActionMode actionmode, Menu menu)
            {
                return mWrapped.onCreateActionMode(actionmode, menu);
            }

            public final void onDestroyActionMode(ActionMode actionmode)
            {
                mWrapped.onDestroyActionMode(actionmode);
                if (mActionModePopup != null)
                {
                    mWindow.getDecorView().removeCallbacks(mShowActionModePopup);
                }
                if (mActionModeView != null)
                {
                    endOnGoingFadeAnimation();
                    actionmode = AppCompatDelegateImpl.this;
                    ViewPropertyAnimatorCompat viewpropertyanimatorcompat = ViewCompat.animate(mActionModeView);
                    View view = (View)viewpropertyanimatorcompat.mView.get();
                    if (view != null)
                    {
                        view.animate().alpha(0.0F);
                    }
                    actionmode.mFadeAnim = viewpropertyanimatorcompat;
                    class _cls1 extends ViewPropertyAnimatorListenerAdapter
                    {

                        private final ActionModeCallbackWrapperV9 this$1;

                        public final void onAnimationEnd(View view1)
                        {
                            mActionModeView.setVisibility(8);
                            if (mActionModePopup == null) goto _L2; else goto _L1
_L1:
                            mActionModePopup.dismiss();
_L4:
                            mActionModeView.removeAllViews();
                            mFadeAnim.setListener(null);
                            mFadeAnim = null;
                            return;
_L2:
                            if (mActionModeView.getParent() instanceof View)
                            {
                                ViewCompat.requestApplyInsets((View)mActionModeView.getParent());
                            }
                            if (true) goto _L4; else goto _L3
_L3:
                        }

                    _cls1()
                    {
                        this$1 = ActionModeCallbackWrapperV9.this;
                        super();
                    }
                    }

                    mFadeAnim.setListener(new _cls1());
                }
                if (mAppCompatCallback != null)
                {
                    actionmode = mAppCompatCallback;
                    ActionMode actionmode1 = mActionMode;
                    actionmode.onSupportActionModeFinished$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKTIILG_0();
                }
                mActionMode = null;
            }

            public final boolean onPrepareActionMode(ActionMode actionmode, Menu menu)
            {
                return mWrapped.onPrepareActionMode(actionmode, menu);
            }

            public ActionModeCallbackWrapperV9(android.support.v7.view.ActionMode.Callback callback)
            {
                this$0 = AppCompatDelegateImpl.this;
                super();
                mWrapped = callback;
            }
        }

    }


    private class _cls3
        implements OnApplyWindowInsetsListener
    {

        private final AppCompatDelegateImpl this$0;

        public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
        {
            int j = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetTop();
            int i = updateStatusGuard(j);
            WindowInsetsCompat windowinsetscompat1 = windowinsetscompat;
            if (j != i)
            {
                int k = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetLeft();
                int l = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetRight();
                int i1 = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetBottom();
                windowinsetscompat1 = new WindowInsetsCompat(((WindowInsets)windowinsetscompat.mInsets).replaceSystemWindowInsets(k, i, l, i1));
            }
            return ViewCompat.onApplyWindowInsets(view, windowinsetscompat1);
        }

        _cls3()
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
        }
    }


    private class _cls5
        implements android.support.v7.widget.ContentFrameLayout.OnAttachListener
    {

        private final AppCompatDelegateImpl this$0;

        public final void onAttachedFromWindow()
        {
        }

        public final void onDetachedFromWindow()
        {
            Object obj = AppCompatDelegateImpl.this;
            if (((AppCompatDelegateImpl) (obj)).mDecorContentParent != null)
            {
                ((AppCompatDelegateImpl) (obj)).mDecorContentParent.dismissPopups();
            }
            if (((AppCompatDelegateImpl) (obj)).mActionModePopup != null)
            {
                ((AppCompatDelegateImpl) (obj)).mWindow.getDecorView().removeCallbacks(((AppCompatDelegateImpl) (obj)).mShowActionModePopup);
                if (((AppCompatDelegateImpl) (obj)).mActionModePopup.isShowing())
                {
                    try
                    {
                        ((AppCompatDelegateImpl) (obj)).mActionModePopup.dismiss();
                    }
                    catch (IllegalArgumentException illegalargumentexception) { }
                }
                obj.mActionModePopup = null;
            }
            ((AppCompatDelegateImpl) (obj)).endOnGoingFadeAnimation();
            obj = ((AppCompatDelegateImpl) (obj)).getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0);
            if (obj != null && ((PanelFeatureState) (obj)).menu != null)
            {
                ((PanelFeatureState) (obj)).menu.close();
            }
        }

        _cls5()
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
        }
    }


    private class PanelFeatureState
    {

        public int background;
        public View createdPanelView;
        public ViewGroup decorView;
        public int featureId;
        public Bundle frozenActionViewState;
        public int gravity;
        public boolean isHandled;
        public boolean isOpen;
        public boolean isPrepared;
        public ListMenuPresenter listMenuPresenter;
        public Context listPresenterContext;
        public MenuBuilder menu;
        public boolean qwertyMode;
        public boolean refreshDecorView;
        public boolean refreshMenuContent;
        public View shownPanelView;
        public int windowAnimations;

        final void setMenu(MenuBuilder menubuilder)
        {
            if (menubuilder != menu)
            {
                if (menu != null)
                {
                    menu.removeMenuPresenter(listMenuPresenter);
                }
                menu = menubuilder;
                if (menubuilder != null && listMenuPresenter != null)
                {
                    ListMenuPresenter listmenupresenter = listMenuPresenter;
                    Context context = menubuilder.mContext;
                    menubuilder.mPresenters.add(new WeakReference(listmenupresenter));
                    listmenupresenter.initForMenu(context, menubuilder);
                    menubuilder.mIsActionItemsStale = true;
                    return;
                }
            }
        }

        PanelFeatureState(int i)
        {
            featureId = i;
            refreshDecorView = false;
        }
    }


    private class ListMenuDecorView extends ContentFrameLayout
    {

        private final AppCompatDelegateImpl this$0;

        public final boolean dispatchKeyEvent(KeyEvent keyevent)
        {
            return AppCompatDelegateImpl.this.dispatchKeyEvent(keyevent) || super.dispatchKeyEvent(keyevent);
        }

        public final boolean onInterceptTouchEvent(MotionEvent motionevent)
        {
            if (motionevent.getAction() == 0)
            {
                int i = (int)motionevent.getX();
                int j = (int)motionevent.getY();
                boolean flag;
                if (i < -5 || j < -5 || i > getWidth() + 5 || j > getHeight() + 5)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    motionevent = AppCompatDelegateImpl.this;
                    motionevent.closePanel(motionevent.getPanelState$514LKAACC5N68SJFD5I2USRLE1O6USJK5TR3EBR1E1O2UGBGE11MURBGC5Q48PBCCLJM2T3595MN0R14A1GMSPBC8PIM2T3LE9IL6T31EHIJM___0(0), true);
                    return true;
                }
            }
            return super.onInterceptTouchEvent(motionevent);
        }

        public final void setBackgroundResource(int i)
        {
            setBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
        }

        public ListMenuDecorView(Context context)
        {
            this$0 = AppCompatDelegateImpl.this;
            super(context);
        }
    }


    private class PanelMenuPresenterCallback
        implements android.support.v7.view.menu.MenuPresenter.Callback
    {

        private final AppCompatDelegateImpl this$0;

        public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
label0:
            {
                MenuBuilder menubuilder1 = menubuilder.getRootMenu();
                AppCompatDelegateImpl appcompatdelegateimpl;
                boolean flag1;
                if (menubuilder1 != menubuilder)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                appcompatdelegateimpl = AppCompatDelegateImpl.this;
                if (flag1)
                {
                    menubuilder = menubuilder1;
                }
                menubuilder = appcompatdelegateimpl.findMenuPanel(menubuilder);
                if (menubuilder != null)
                {
                    if (!flag1)
                    {
                        break label0;
                    }
                    callOnPanelClosed(((PanelFeatureState) (menubuilder)).featureId, menubuilder, menubuilder1);
                    closePanel(menubuilder, true);
                }
                return;
            }
            closePanel(menubuilder, flag);
        }

        public final boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            if (menubuilder == null && mHasActionBar)
            {
                android.view.Window.Callback callback = mWindow.getCallback();
                if (callback != null && !mIsDestroyed)
                {
                    callback.onMenuOpened(108, menubuilder);
                }
            }
            return true;
        }

        PanelMenuPresenterCallback()
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
        }
    }


    private class AutoNightModeManager
    {

        public BroadcastReceiver mAutoTimeChangeReceiver;
        public IntentFilter mAutoTimeChangeReceiverFilter;
        public boolean mIsNight;
        public TwilightManager mTwilightManager;
        public final AppCompatDelegateImpl this$0;

        AutoNightModeManager(TwilightManager twilightmanager)
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
            mTwilightManager = twilightmanager;
            mIsNight = twilightmanager.isNight();
        }
    }


    private class ActionBarDrawableToggleImpl
        implements ActionBarDrawerToggle.Delegate
    {

        private final AppCompatDelegateImpl this$0;

        public final Context getActionBarThemedContext()
        {
            AppCompatDelegateImpl appcompatdelegateimpl = AppCompatDelegateImpl.this;
            Context context = null;
            Object obj = appcompatdelegateimpl.getSupportActionBar();
            if (obj != null)
            {
                context = ((ActionBar) (obj)).getThemedContext();
            }
            obj = context;
            if (context == null)
            {
                obj = appcompatdelegateimpl.mContext;
            }
            return ((Context) (obj));
        }

        public final Drawable getThemeUpIndicator()
        {
            AppCompatDelegateImpl appcompatdelegateimpl = AppCompatDelegateImpl.this;
            Object obj = appcompatdelegateimpl.getSupportActionBar();
            Object obj1;
            if (obj != null)
            {
                obj = ((ActionBar) (obj)).getThemedContext();
            } else
            {
                obj = null;
            }
            obj1 = obj;
            if (obj == null)
            {
                obj1 = appcompatdelegateimpl.mContext;
            }
            obj = new TintTypedArray(((Context) (obj1)), ((Context) (obj1)).obtainStyledAttributes(null, new int[] {
                0x7f01009e
            }));
            obj1 = ((TintTypedArray) (obj)).getDrawable(0);
            ((TintTypedArray) (obj)).mWrapped.recycle();
            return ((Drawable) (obj1));
        }

        public final boolean isNavigationVisible()
        {
            ActionBar actionbar = getSupportActionBar();
            return actionbar != null && (actionbar.getDisplayOptions() & 4) != 0;
        }

        public final void setActionBarDescription(int i)
        {
            ActionBar actionbar = getSupportActionBar();
            if (actionbar != null)
            {
                actionbar.setHomeActionContentDescription(i);
            }
        }

        public final void setActionBarUpIndicator(Drawable drawable, int i)
        {
            ActionBar actionbar = getSupportActionBar();
            if (actionbar != null)
            {
                actionbar.setHomeAsUpIndicator(drawable);
                actionbar.setHomeActionContentDescription(i);
            }
        }

        ActionBarDrawableToggleImpl()
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
        }
    }


    private class ActionMenuPresenterCallback
        implements android.support.v7.view.menu.MenuPresenter.Callback
    {

        private final AppCompatDelegateImpl this$0;

        public final void onCloseMenu(MenuBuilder menubuilder, boolean flag)
        {
            checkCloseActionMenu(menubuilder);
        }

        public final boolean onOpenSubMenu(MenuBuilder menubuilder)
        {
            android.view.Window.Callback callback = mWindow.getCallback();
            if (callback != null)
            {
                callback.onMenuOpened(108, menubuilder);
            }
            return true;
        }

        ActionMenuPresenterCallback()
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
        }
    }


    private class _cls6
        implements Runnable
    {

        public final AppCompatDelegateImpl this$0;

        public final void run()
        {
            mActionModePopup.showAtLocation(mActionModeView, 55, 0, 0);
            endOnGoingFadeAnimation();
            AppCompatDelegateImpl appcompatdelegateimpl = AppCompatDelegateImpl.this;
            boolean flag;
            if (appcompatdelegateimpl.mSubDecorInstalled && appcompatdelegateimpl.mSubDecor != null && ViewCompat.isLaidOut(appcompatdelegateimpl.mSubDecor))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                mActionModeView.setAlpha(0.0F);
                AppCompatDelegateImpl appcompatdelegateimpl1 = AppCompatDelegateImpl.this;
                ViewPropertyAnimatorCompat viewpropertyanimatorcompat = ViewCompat.animate(mActionModeView);
                View view = (View)viewpropertyanimatorcompat.mView.get();
                if (view != null)
                {
                    view.animate().alpha(1.0F);
                }
                appcompatdelegateimpl1.mFadeAnim = viewpropertyanimatorcompat;
                class _cls1 extends ViewPropertyAnimatorListenerAdapter
                {

                    private final _cls6 this$1;

                    public final void onAnimationEnd(View view1)
                    {
                        mActionModeView.setAlpha(1.0F);
                        mFadeAnim.setListener(null);
                        mFadeAnim = null;
                    }

                    public final void onAnimationStart(View view1)
                    {
                        mActionModeView.setVisibility(0);
                    }

                _cls1()
                {
                    this$1 = _cls6.this;
                    super();
                }
                }

                mFadeAnim.setListener(new _cls1());
                return;
            } else
            {
                mActionModeView.setAlpha(1.0F);
                mActionModeView.setVisibility(0);
                return;
            }
        }

        _cls6()
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
        }
    }


    private class _cls7 extends ViewPropertyAnimatorListenerAdapter
    {

        private final AppCompatDelegateImpl this$0;

        public final void onAnimationEnd(View view)
        {
            mActionModeView.setAlpha(1.0F);
            mFadeAnim.setListener(null);
            mFadeAnim = null;
        }

        public final void onAnimationStart(View view)
        {
            mActionModeView.setVisibility(0);
            mActionModeView.sendAccessibilityEvent(32);
            if (mActionModeView.getParent() instanceof View)
            {
                ViewCompat.requestApplyInsets((View)mActionModeView.getParent());
            }
        }

        _cls7()
        {
            this$0 = AppCompatDelegateImpl.this;
            super();
        }
    }

}
