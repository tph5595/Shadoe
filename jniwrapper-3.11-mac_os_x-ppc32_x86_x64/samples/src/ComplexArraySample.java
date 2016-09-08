/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class demonstrates the {@link ComplexArray} class functionality. The findValueByKey native function
 * returns the value that corresponds to the specified key from the array of {@link KeyValueStructure}.
 *
 * @author Alexey Razoryonov
 */
public class ComplexArraySample extends BasicJNIWrapperSample
{
    public static void main(String[] args)
    {
        Function findByKey = SAMPLE_LIB.getFunction("findValueByKey");
        ComplexArray array = new ComplexArray(new Parameter[]
        {
            new KeyValueStructure(1, "The first value"),
            new KeyValueStructure(2, "The second value"),
            new KeyValueStructure(3, "The third value")
        });
        final int key = 3;
        AnsiString result = new AnsiString();
        findByKey.invoke(new Pointer(result),
                new Parameter[]
                {
                    new Pointer(array),
                    new Int(array.getElementCount()),
                    new Int(key)
                });
        System.out.println("The ComplexArray of structures, containing key-value pairs:");
        for (int i = 0; i < array.getElementCount(); i++)
        {
            KeyValueStructure current = (KeyValueStructure) array.getElement(i);
            System.out.println(current.getIntField() + " = " + current.getStringField());
        }
        System.out.println("\nThe value of the key " + key + " is \"" + result.getValue() + "\"");
    }
}