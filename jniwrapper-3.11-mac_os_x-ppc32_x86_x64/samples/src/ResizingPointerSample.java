/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class demonstrates the {@link com.jniwrapper.ResizingPointer} class functionality.
 * It calls the native function, which removes duplicated symbols from the array, which was spesified as its
 * parameter. The function changes both specified parameters.
 *
 * @author Alexey Razoryonov
 */
public class ResizingPointerSample extends BasicJNIWrapperSample
{
    public static void main(String[] args)
    {
        Function removeDuplicates = SAMPLE_LIB.getFunction("removeDuplicates");
        PrimitiveArray myArray = new PrimitiveArray(new Parameter[]
        {
            new DoubleFloat(-1000),
            new DoubleFloat(5),
            new DoubleFloat(7),
            new DoubleFloat(-100),
            new DoubleFloat(9),
            new DoubleFloat(7),
            new DoubleFloat(7),
            new DoubleFloat(7),
            new DoubleFloat(7),
            new DoubleFloat(-100)
        });

        System.out.println("The source array:");
        for (int i = 0; i < myArray.getElementCount(); i++)
        {
            System.out.print(" " + myArray.getElement(i) + " ");
        }

        ResizingPointer result = new ResizingPointer(myArray);
        Int length = new Int(myArray.getElementCount());
        removeDuplicates.invoke(null, new Parameter[]
        {
            result,
            new Pointer(length)
        });

        result.readArray((int) length.getValue());

        System.out.println("\nThe same array without duplicated elements:");
        for (int i = 0; i < myArray.getElementCount(); i++)
        {
            System.out.print(" " + myArray.getElement(i) + " ");
        }
    }
}