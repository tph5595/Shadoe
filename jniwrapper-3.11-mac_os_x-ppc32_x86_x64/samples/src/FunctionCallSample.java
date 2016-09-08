/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.AnsiString;
import com.jniwrapper.Function;
import com.jniwrapper.Int;
import com.jniwrapper.Pointer;

/**
 * This class demonstrates how to invoke native functions from dynamic-link libraries (DLL) using
 * the {@link Function} class.
 *
 * @author Alexey Razoryonov
 */
public class FunctionCallSample extends BasicJNIWrapperSample
{
    /**
     * This method invokes the sumStdCall native function using the STDCALL calling convention.
     */
    public static void returnValueSample()
    {
        int a = 10, b = 100;
        Function add = SAMPLE_LIB.getFunction("sumStdCall");
        Int result = new Int();
        add.invoke(result, new Int(a), new Int(b));
        System.out.println("The sum of a = " + a + " and b = " + b + " is " + result.getValue());
    }

    /**
     * This method invokes the printStringCdeclCall native function using the CDECL calling convention.
     * printStringCdeclCall prints the specified string to the standard output stream on the native side.
     */
    public static void noReturnValueStdCallSample()
    {
        String str = "This string was put to the out stream in printStringCdeclCall native function.";
        Function putString = SAMPLE_LIB.getFunction("printStringCdeclCall");
        putString.setCallingConvention(Function.CDECL_CALLING_CONVENTION);
        putString.invoke(null, new Pointer(new AnsiString(str)));
    }

    public static void main(String[] args)
    {
        returnValueSample();
        noReturnValueStdCallSample();
    }
}