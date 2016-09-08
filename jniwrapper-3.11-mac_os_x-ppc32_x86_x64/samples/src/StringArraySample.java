/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class demonstrates the {@link com.jniwrapper.StringArray} class functionality, which allows us to work with
 * zero-terminated string array. Elements of this array are separated with '\0' and it ends with another '\0' symbol.
 *
 * @author Alexey Razoryonov
 */
public class StringArraySample extends BasicJNIWrapperSample
{
    private static final String[] STRINGS = {"Short string", "Longer string", " ", "Very long string", "  string"};

    /**
     * This method calls the findLongest native function,
     * which finds the longest string in the zero-terminated string array.
     */
    public static void unicodeSample()
    {
        Function findLongest = SAMPLE_LIB.getFunction("findLongest");

        System.out.println("These are the elements of the string array:");

        StringArray array = new StringArray(STRINGS);
        for (int i = 0; i < array.getValue().length; i++)
        {
            System.out.print(" \"" + array.getValue()[i] + "\"");
            if (i != (array.getValue().length - 1))
            {
                System.out.print(",");
            }
        }

        WideString result = new WideString();
        findLongest.invoke(result, new Pointer(array));

        System.out.println("\nThe longest string is \"" + result + "\"");
        System.out.println();
    }

    /**
     * The array of Unicode strings is converted to the array of ANSI strings
     * using the <code>convert</code> native function.
     */
    public static void ansiSample()
    {
        Function convert = SAMPLE_LIB.getFunction("convert");
        System.out.println("These are the elements of the unicode  string array:");

        StringArray array = new StringArray(STRINGS);
        for (int i = 0; i < array.getValue().length; i++)
        {
            System.out.print(" \"" + array.getValue()[i] + "\"");
            if (i != (array.getValue().length - 1))
            {
                System.out.print(",");
            }
        }

        StringArray result = new StringArray(false);
        convert.invoke(null, new Pointer(array), new Pointer(result));

        System.out.println("\nThese are the elements of the converted  string array:");
        for (int i = 0; i < result.getValue().length; i++)
        {
            System.out.print(" \"" + result.getValue()[i] + "\"");
            if (i != (result.getValue().length - 1))
            {
                System.out.print(",");
            }
        }
    }

    public static void main(String[] args)
    {
        unicodeSample();
        ansiSample();
    }
}