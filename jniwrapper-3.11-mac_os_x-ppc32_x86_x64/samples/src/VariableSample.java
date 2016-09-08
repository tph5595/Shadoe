/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.AnsiString;

/**
 * This class demonstrates the {@link com.jniwrapper.Library} class functionality.
 * It gets a global variable from the dll.
 *
 * @author Alexey Razoryonov
 */
public class VariableSample extends BasicJNIWrapperSample
{
    public static void main(String[] args)
    {
        AnsiString result = new AnsiString();
        SAMPLE_LIB.getVariable("_data", result);
        System.out.println("The _data variable value is " + result.getValue());
    }
}