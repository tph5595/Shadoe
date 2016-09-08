/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.Int;

/**
 * This class demonstrates the {@link com.jniwrapper.BitField} class functionality.
 * It demonstrates the structure {@link BitStructure}.
 *
 * @author Vadim Steshenko
 */
public class BitFieldSample extends BasicJNIWrapperSample
{
    public static void main(String[] args)
    {
        BitStructure bitStructure = new BitStructure();
        bitStructure.setB1(1);
        bitStructure.setB2(2);
        bitStructure.setB3(3);
        bitStructure.setB4(1);
        bitStructure.setB5(93);
        Int result = new Int();
        SAMPLE_LIB.getFunction("calculateSunFromBitStructure").invoke(result, bitStructure);
        System.out.println("The result value is " + result);
    }
}