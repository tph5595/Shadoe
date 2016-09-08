/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.Function;
import com.jniwrapper.Parameter;
import com.jniwrapper.Pointer;

/**
 * This class demonstrates the {@link com.jniwrapper.Structure} class functionality. It demonstrates two different
 * structures: {@link SampleStructure} and {@link KeyValueStructure}.
 *
 * @author Alexey Razoryonov
 */
public class StructureSample extends BasicJNIWrapperSample
{
    private static void printStructure(SampleStructure structure)
    {
        System.out.println("boolField = " + structure.getBoolField());
        System.out.println("shortIntField = " + structure.getShortIntField());
        System.out.println("intField = " + structure.getIntField());
        System.out.println("longIntField = " + structure.getLongIntField());
        System.out.println("charField = " + structure.getCharField());
        System.out.println("wideCharField = " + structure.getWideCharField());
        System.out.println("floatField = " + structure.getFloatField());
        System.out.println("doubleField = " + structure.getDoubleField());
        System.out.println("longDoubleField = " + structure.getLongDoubleField());
    }

    /**
     * This method calls the copySampleStructure native function, which copies one {@link SampleStructure} to another.
     */
    public static void structureSample()
    {
        Function copySampleStructure = SAMPLE_LIB.getFunction("copySampleStructure");
        SampleStructure structure = new SampleStructure();
        SampleStructure resultStructure = new SampleStructure();

        structure.setBoolField(true);
        structure.setCharField('s');
        structure.setWideCharField('a');
        structure.setDoubleField(11.0);
        structure.setFloatField(12.0);
        structure.setLongDoubleField(13.0);
        structure.setIntField(6);
        structure.setShortIntField(8);
        structure.setLongIntField(7);

        System.out.println("Source structure:");
        printStructure(structure);

        copySampleStructure.invoke(null, new Parameter[]
        {
            new Pointer(structure),
            new Pointer(resultStructure)
        });

        System.out.println("Result structure after copySampleStructure function invoke:");
        printStructure(resultStructure);
    }

    /**
     * This method calls the fillKeyValueStructure native function, which sets the key field to "365"
     * and the value field to "days of the year" in the {@link KeyValueStructure}.
     */
    public static void pointerStructureSample()
    {
        Function yearPointerStructure = SAMPLE_LIB.getFunction("fillKeyValueStructure");
        KeyValueStructure struct = new KeyValueStructure();

        yearPointerStructure.invoke(null, new Pointer(struct));
        System.out.println("KeyValueStructure after the fillKeyValueStructure function invocation:");
        System.out.println(struct.getIntField() + struct.getStringField());
    }


    public static void main(String[] args)
    {
        structureSample();
        pointerStructureSample();
    }
}