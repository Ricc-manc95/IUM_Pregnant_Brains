// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelStore;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.content.Loader;
import android.support.v4.util.ContainerHelpers;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.SparseArrayCompat;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.HashMap;

// Referenced classes of package android.support.v4.app:
//            LoaderManager

public final class LoaderManagerImpl extends LoaderManager
{
    static class LoaderViewModel extends ViewModel
    {

        private static final android.arch.lifecycle.ViewModelProvider.Factory FACTORY = new _cls1();
        public boolean mCreatingLoader;
        public SparseArrayCompat mLoaders;

        static LoaderViewModel getInstance(ViewModelStore viewmodelstore)
        {
            ViewModelProvider viewmodelprovider = new ViewModelProvider(viewmodelstore, FACTORY);
            viewmodelstore = android/support/v4/app/LoaderManagerImpl$LoaderViewModel.getCanonicalName();
            if (viewmodelstore == null)
            {
                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
            }
            String s = (new StringBuilder("android.arch.lifecycle.ViewModelProvider.DefaultKey:")).append(viewmodelstore).toString();
            ViewModel viewmodel = (ViewModel)viewmodelprovider.mViewModelStore.mMap.get(s);
            viewmodelstore = viewmodel;
            if (!android/support/v4/app/LoaderManagerImpl$LoaderViewModel.isInstance(viewmodel))
            {
                viewmodelstore = viewmodelprovider.mFactory.create$5166KOBMC4NMOOBECSNK6R31EDPJMAACC5N68SJFD5I2UOBICDK2UR39CPIM6UB3DHIIULJ9CLRKQRR4CLM3M___0();
                ViewModel viewmodel1 = (ViewModel)viewmodelprovider.mViewModelStore.mMap.put(s, viewmodelstore);
                if (viewmodel1 != null)
                {
                    viewmodel1.onCleared();
                }
            }
            return (LoaderViewModel)viewmodelstore;
        }

        protected final void onCleared()
        {
            super.onCleared();
            SparseArrayCompat sparsearraycompat = mLoaders;
            if (sparsearraycompat.mGarbage)
            {
                sparsearraycompat.gc();
            }
            int k = sparsearraycompat.mSize;
            for (int i = 0; i < k; i++)
            {
                sparsearraycompat = mLoaders;
                if (sparsearraycompat.mGarbage)
                {
                    sparsearraycompat.gc();
                }
                ((LoaderInfo)sparsearraycompat.mValues[i]).destroy(true);
            }

            sparsearraycompat = mLoaders;
            k = sparsearraycompat.mSize;
            Object aobj[] = sparsearraycompat.mValues;
            for (int j = 0; j < k; j++)
            {
                aobj[j] = null;
            }

            sparsearraycompat.mSize = 0;
            sparsearraycompat.mGarbage = false;
        }


        LoaderViewModel()
        {
            mLoaders = new SparseArrayCompat();
            mCreatingLoader = false;
        }

        private class LoaderInfo extends MutableLiveData
            implements android.support.v4.content.Loader.OnLoadCompleteListener
        {

            public final Bundle mArgs;
            public final int mId;
            private LifecycleOwner mLifecycleOwner;
            public final Loader mLoader;
            public LoaderObserver mObserver;
            private Loader mPriorLoader;

            final Loader destroy(boolean flag)
            {
                mLoader.onCancelLoad();
                mLoader.mAbandoned = true;
                LoaderObserver loaderobserver = mObserver;
                if (loaderobserver != null)
                {
                    removeObserver(loaderobserver);
                    if (flag && loaderobserver.mDeliveredData)
                    {
                        loaderobserver.mCallback.onLoaderReset$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5THMURJKCLN78BQCDTGM8PBI7CKLC___0();
                    }
                }
                Loader loader1 = mLoader;
                if (loader1.mListener == null)
                {
                    throw new IllegalStateException("No listener register");
                }
                if (loader1.mListener != this)
                {
                    throw new IllegalArgumentException("Attempting to unregister the wrong listener");
                }
                loader1.mListener = null;
                if (loaderobserver != null && !loaderobserver.mDeliveredData || flag)
                {
                    Loader loader = mLoader;
                    loader.onReset();
                    loader.mReset = true;
                    loader.mStarted = false;
                    loader.mAbandoned = false;
                    loader.mContentChanged = false;
                    loader.mProcessingChange = false;
                    return mPriorLoader;
                } else
                {
                    return mLoader;
                }
            }

            final void markForRedelivery()
            {
                LifecycleOwner lifecycleowner = mLifecycleOwner;
                LoaderObserver loaderobserver = mObserver;
                if (lifecycleowner != null && loaderobserver != null)
                {
                    super.removeObserver(loaderobserver);
                    observe(lifecycleowner, loaderobserver);
                }
            }

            protected final void onActive()
            {
                Loader loader = mLoader;
                loader.mStarted = true;
                loader.mReset = false;
                loader.mAbandoned = false;
                loader.onStartLoading();
            }

            protected final void onInactive()
            {
                Loader loader = mLoader;
                loader.mStarted = false;
                loader.onStopLoading();
            }

            public final void onLoadComplete$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5THMURJKCLN78BQCDTGM8PBI7D66KOBMC4NMOOBECSNKUOJACLHN8EP9AO______0(Object obj)
            {
                if (Looper.myLooper() == Looper.getMainLooper())
                {
                    setValue(obj);
                    return;
                } else
                {
                    postValue(obj);
                    return;
                }
            }

            public final void removeObserver(Observer observer)
            {
                super.removeObserver(observer);
                mLifecycleOwner = null;
                mObserver = null;
            }

            final Loader setCallback(LifecycleOwner lifecycleowner, LoaderManager.LoaderCallbacks loadercallbacks)
            {
                loadercallbacks = new LoaderObserver(mLoader, loadercallbacks);
                observe(lifecycleowner, loadercallbacks);
                if (mObserver != null)
                {
                    removeObserver(mObserver);
                }
                mLifecycleOwner = lifecycleowner;
                mObserver = loadercallbacks;
                return mLoader;
            }

            public final void setValue(Object obj)
            {
                super.setValue(obj);
                if (mPriorLoader != null)
                {
                    obj = mPriorLoader;
                    ((Loader) (obj)).onReset();
                    obj.mReset = true;
                    obj.mStarted = false;
                    obj.mAbandoned = false;
                    obj.mContentChanged = false;
                    obj.mProcessingChange = false;
                    mPriorLoader = null;
                }
            }

            public final String toString()
            {
                StringBuilder stringbuilder = new StringBuilder(64);
                stringbuilder.append("LoaderInfo{");
                stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
                stringbuilder.append(" #");
                stringbuilder.append(mId);
                stringbuilder.append(" : ");
                DebugUtils.buildShortClassTag(mLoader, stringbuilder);
                stringbuilder.append("}}");
                return stringbuilder.toString();
            }

            LoaderInfo(int i, Bundle bundle, Loader loader, Loader loader1)
            {
                mId = i;
                mArgs = bundle;
                mLoader = loader;
                mPriorLoader = loader1;
                bundle = mLoader;
                if (((Loader) (bundle)).mListener != null)
                {
                    throw new IllegalStateException("There is already a listener registered");
                } else
                {
                    bundle.mListener = this;
                    bundle.mId = i;
                    return;
                }
            }

            private class LoaderObserver
                implements Observer
            {

                public final LoaderManager.LoaderCallbacks mCallback;
                public boolean mDeliveredData;
                private final Loader mLoader;

                public final void onChanged(Object obj)
                {
                    mCallback.onLoadFinished(mLoader, obj);
                    mDeliveredData = true;
                }

                public final String toString()
                {
                    return mCallback.toString();
                }

                LoaderObserver(Loader loader, LoaderManager.LoaderCallbacks loadercallbacks)
                {
                    mDeliveredData = false;
                    mLoader = loader;
                    mCallback = loadercallbacks;
                }
            }

        }


        class _cls1
            implements android.arch.lifecycle.ViewModelProvider.Factory
        {

            public final ViewModel create$5166KOBMC4NMOOBECSNK6R31EDPJMAACC5N68SJFD5I2UOBICDK2UR39CPIM6UB3DHIIULJ9CLRKQRR4CLM3M___0()
            {
                return new LoaderViewModel();
            }

                _cls1()
                {
                }
        }

    }


    public static boolean DEBUG = false;
    private final LifecycleOwner mLifecycleOwner;
    private final LoaderViewModel mLoaderViewModel;

    public LoaderManagerImpl(LifecycleOwner lifecycleowner, ViewModelStore viewmodelstore)
    {
        mLifecycleOwner = lifecycleowner;
        mLoaderViewModel = LoaderViewModel.getInstance(viewmodelstore);
    }

    private final Loader createAndInstallLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loadercallbacks, Loader loader)
    {
        Loader loader1;
        mLoaderViewModel.mCreatingLoader = true;
        loader1 = loadercallbacks.onCreateLoader$514KOOBECHP6UQB45TNN6BQ2ELN68R357CKKOOBECHP6UQB45TPNAS3GDTP78BRM6GNM6RREEHIMST1F9HNM2P35E8TG____0(bundle);
        if (loader1.getClass().isMemberClass() && !Modifier.isStatic(loader1.getClass().getModifiers()))
        {
            throw new IllegalArgumentException((new StringBuilder("Object returned from onCreateLoader must not be a non-static inner member class: ")).append(loader1).toString());
        }
        break MISSING_BLOCK_LABEL_78;
        bundle;
        mLoaderViewModel.mCreatingLoader = false;
        throw bundle;
        bundle = new LoaderInfo(i, bundle, loader1, loader);
        mLoaderViewModel.mLoaders.put(i, bundle);
        mLoaderViewModel.mCreatingLoader = false;
        return bundle.setCallback(mLifecycleOwner, loadercallbacks);
    }

    public final void destroyLoader(int i)
    {
        if (mLoaderViewModel.mCreatingLoader)
        {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper())
        {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        Object obj = mLoaderViewModel.mLoaders;
        i = ContainerHelpers.binarySearch(((SparseArrayCompat) (obj)).mKeys, ((SparseArrayCompat) (obj)).mSize, 54321);
        if (i < 0 || ((SparseArrayCompat) (obj)).mValues[i] == SparseArrayCompat.DELETED)
        {
            obj = null;
        } else
        {
            obj = ((SparseArrayCompat) (obj)).mValues[i];
        }
        obj = (LoaderInfo)obj;
        if (obj != null)
        {
            ((LoaderInfo) (obj)).destroy(true);
            obj = mLoaderViewModel.mLoaders;
            i = ContainerHelpers.binarySearch(((SparseArrayCompat) (obj)).mKeys, ((SparseArrayCompat) (obj)).mSize, 54321);
            if (i >= 0 && ((SparseArrayCompat) (obj)).mValues[i] != SparseArrayCompat.DELETED)
            {
                ((SparseArrayCompat) (obj)).mValues[i] = SparseArrayCompat.DELETED;
                obj.mGarbage = true;
            }
        }
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        LoaderViewModel loaderviewmodel = mLoaderViewModel;
        SparseArrayCompat sparsearraycompat = loaderviewmodel.mLoaders;
        if (sparsearraycompat.mGarbage)
        {
            sparsearraycompat.gc();
        }
        if (sparsearraycompat.mSize > 0)
        {
            printwriter.print(s);
            printwriter.println("Loaders:");
            String s1 = (new StringBuilder()).append(s).append("    ").toString();
            int i = 0;
            do
            {
                Object obj = loaderviewmodel.mLoaders;
                if (((SparseArrayCompat) (obj)).mGarbage)
                {
                    ((SparseArrayCompat) (obj)).gc();
                }
                if (i >= ((SparseArrayCompat) (obj)).mSize)
                {
                    break;
                }
                obj = loaderviewmodel.mLoaders;
                if (((SparseArrayCompat) (obj)).mGarbage)
                {
                    ((SparseArrayCompat) (obj)).gc();
                }
                LoaderInfo loaderinfo = (LoaderInfo)((SparseArrayCompat) (obj)).mValues[i];
                printwriter.print(s);
                printwriter.print("  #");
                obj = loaderviewmodel.mLoaders;
                if (((SparseArrayCompat) (obj)).mGarbage)
                {
                    ((SparseArrayCompat) (obj)).gc();
                }
                printwriter.print(((SparseArrayCompat) (obj)).mKeys[i]);
                printwriter.print(": ");
                printwriter.println(loaderinfo.toString());
                printwriter.print(s1);
                printwriter.print("mId=");
                printwriter.print(loaderinfo.mId);
                printwriter.print(" mArgs=");
                printwriter.println(loaderinfo.mArgs);
                printwriter.print(s1);
                printwriter.print("mLoader=");
                printwriter.println(loaderinfo.mLoader);
                loaderinfo.mLoader.dump((new StringBuilder()).append(s1).append("  ").toString(), filedescriptor, printwriter, as);
                if (loaderinfo.mObserver != null)
                {
                    printwriter.print(s1);
                    printwriter.print("mCallbacks=");
                    printwriter.println(loaderinfo.mObserver);
                    obj = loaderinfo.mObserver;
                    printwriter.print((new StringBuilder()).append(s1).append("  ").toString());
                    printwriter.print("mDeliveredData=");
                    printwriter.println(((LoaderObserver) (obj)).mDeliveredData);
                }
                printwriter.print(s1);
                printwriter.print("mData=");
                obj = ((LiveData) (loaderinfo)).mData;
                StringBuilder stringbuilder;
                boolean flag;
                if (obj == LiveData.NOT_SET)
                {
                    obj = null;
                }
                stringbuilder = new StringBuilder(64);
                DebugUtils.buildShortClassTag(obj, stringbuilder);
                stringbuilder.append("}");
                printwriter.println(stringbuilder.toString());
                printwriter.print(s1);
                printwriter.print("mStarted=");
                if (((LiveData) (loaderinfo)).mActiveCount > 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                printwriter.println(flag);
                i++;
            } while (true);
        }
    }

    public final Loader initLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loadercallbacks)
    {
        if (mLoaderViewModel.mCreatingLoader)
        {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper())
        {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        Object obj = mLoaderViewModel.mLoaders;
        int j = ContainerHelpers.binarySearch(((SparseArrayCompat) (obj)).mKeys, ((SparseArrayCompat) (obj)).mSize, i);
        if (j < 0 || ((SparseArrayCompat) (obj)).mValues[j] == SparseArrayCompat.DELETED)
        {
            obj = null;
        } else
        {
            obj = ((SparseArrayCompat) (obj)).mValues[j];
        }
        obj = (LoaderInfo)obj;
        if (obj == null)
        {
            return createAndInstallLoader(i, bundle, loadercallbacks, null);
        } else
        {
            return ((LoaderInfo) (obj)).setCallback(mLifecycleOwner, loadercallbacks);
        }
    }

    public final void markForRedelivery()
    {
        LoaderViewModel loaderviewmodel = mLoaderViewModel;
        SparseArrayCompat sparsearraycompat = loaderviewmodel.mLoaders;
        if (sparsearraycompat.mGarbage)
        {
            sparsearraycompat.gc();
        }
        int j = sparsearraycompat.mSize;
        for (int i = 0; i < j; i++)
        {
            SparseArrayCompat sparsearraycompat1 = loaderviewmodel.mLoaders;
            if (sparsearraycompat1.mGarbage)
            {
                sparsearraycompat1.gc();
            }
            ((LoaderInfo)sparsearraycompat1.mValues[i]).markForRedelivery();
        }

    }

    public final Loader restartLoader(int i, Bundle bundle, LoaderManager.LoaderCallbacks loadercallbacks)
    {
        Object obj1 = null;
        if (mLoaderViewModel.mCreatingLoader)
        {
            throw new IllegalStateException("Called while creating a loader");
        }
        if (Looper.getMainLooper() != Looper.myLooper())
        {
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
        Object obj = mLoaderViewModel.mLoaders;
        i = ContainerHelpers.binarySearch(((SparseArrayCompat) (obj)).mKeys, ((SparseArrayCompat) (obj)).mSize, 0);
        LoaderInfo loaderinfo;
        if (i < 0 || ((SparseArrayCompat) (obj)).mValues[i] == SparseArrayCompat.DELETED)
        {
            obj = null;
        } else
        {
            obj = ((SparseArrayCompat) (obj)).mValues[i];
        }
        loaderinfo = (LoaderInfo)obj;
        obj = obj1;
        if (loaderinfo != null)
        {
            obj = loaderinfo.destroy(false);
        }
        return createAndInstallLoader(0, bundle, loadercallbacks, ((Loader) (obj)));
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("LoaderManager{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" in ");
        DebugUtils.buildShortClassTag(mLifecycleOwner, stringbuilder);
        stringbuilder.append("}}");
        return stringbuilder.toString();
    }

}
