/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.DefaultLibraryLoader;
import com.jniwrapper.Library;
import com.jniwrapper.Function;
import com.jniwrapper.PlatformContext;

/**
 * This class loads up the native DLL.
 *
 * @author Alexey Razoryonov
 */
public class BasicJNIWrapperSample
{
    protected static Library SAMPLE_LIB;
    static
    {
        String libName = "JNIWrapper";
        if (PlatformContext.isLinux() && PlatformContext.isPPC())
        {
            libName += "_ppc";
        }
        else if (PlatformContext.isSunOS())
        {
            libName += "_sunos";
        }
        if (PlatformContext.isSparc())
        {
            libName += "_sparc";
        }
        if (PlatformContext.isSparc64())
        {
            libName += "_sparc64";
        }
        if (!PlatformContext.isMacOS() && (PlatformContext.isX64() || PlatformContext.isPPC64()))
        {
            libName += "64";
        }
        SAMPLE_LIB = new Library(libName + "SampleDLL",
                Function.STDCALL_CALLING_CONVENTION);
    }

    static
    {
        DefaultLibraryLoader.getInstance().addPath("../samples/bin");
        SAMPLE_LIB.load();
    }
}