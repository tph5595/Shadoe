/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.Function;
import com.jniwrapper.Parameter;
import com.jniwrapper.Pointer;

/**
 * This class demonstrates the {@link com.jniwrapper.Union} class functionality.
 * It calls the copyUnionStructure native function, which copies one {@link UnionStructure} to another one.
 *
 * @author Alexey Razoryonov
 */
public class UnionSample extends BasicJNIWrapperSample
{
    private static void printStructure(UnionStructure unionStructure)
    {
        StringBuffer result = new StringBuffer("UnionStructure [");
        result.append("isSymbol = " + unionStructure.isSymbol() + ", ");
        if (unionStructure.isSymbol())
        {
            result.append("symbol = " + unionStructure.getSymbol() + "]");
        }
        else
        {
            result.append("code = " + unionStructure.getCode() + "]");
        }
        System.out.println(result);
    }

    public static void main(String[] args)
    {
        Function copyUnionStructure = SAMPLE_LIB.getFunction("copyUnionStructure");

        UnionStructure structure = new UnionStructure();
        UnionStructure resultStructure = new UnionStructure();

        structure.setIsSymbol(true);
        structure.setSymbol('g');

        copyUnionStructure.invoke(null, new Parameter[]
        {
            new Pointer(structure),
            new Pointer(resultStructure)
        });

        printStructure(resultStructure);

        structure.setIsSymbol(false);
        structure.setCode(9);

        copyUnionStructure.invoke(null, new Parameter[]
        {
            new Pointer(structure),
            new Pointer(resultStructure)
        });

        printStructure(resultStructure);
    }
}