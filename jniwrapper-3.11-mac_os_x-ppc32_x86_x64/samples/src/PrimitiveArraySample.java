/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class demonstrates the {@link PrimitiveArray} class functionality. It calls the findMinimum native function,
 * which finds the minimal element of an array of doubles.
 *
 * @author Alexey Razoryonov
 */
public class PrimitiveArraySample extends BasicJNIWrapperSample
{
    public static void main(String[] args)
    {
        Function findMinimum = SAMPLE_LIB.getFunction("findMinimum");
        PrimitiveArray array = new PrimitiveArray(new Parameter[]
        {
            new DoubleFloat(1000),
            new DoubleFloat(5),
            new DoubleFloat(7),
            new DoubleFloat(-100),
            new DoubleFloat(3),
            new DoubleFloat(9),
            new DoubleFloat(8),
            new DoubleFloat(7),
            new DoubleFloat(4),
            new DoubleFloat(4)
        });
        DoubleFloat res = new DoubleFloat();

        findMinimum.invoke(res,
                new Pointer(array),
                new Int(array.getElementCount()));

        System.out.print("PrimitiveArray  :");
        for (int i = 0; i < array.getElementCount(); i++)
        {
            System.out.print(" " + array.getElement(i) + " ");
        }
        System.out.println("\nThe minimal element is " + res.getValue());
    }
}