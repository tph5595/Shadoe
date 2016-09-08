/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.Function;
import com.jniwrapper.Pointer;

/**
 * This is the sample of the {@link LinkedStructure} class usage.
 *
 * @author Alexey Razoryonov
 */
public class LinkedStructureSample extends BasicJNIWrapperSample
{
    /**
     * It calls the fillLinkedStructures native function, which fills the content of 10 {@link LinkedStructure}s
     * with numbers from 1 to 10 and links each of these structures to the next one.
     */
    public static void main(String args[])
    {
        Function fillLinkedStructure = SAMPLE_LIB.getFunction("fillLinkedStructures");

        LinkedStructure structure = new LinkedStructure();

        fillLinkedStructure.invoke(null, new Pointer(structure));
        do
        {
            System.out.println("LinkedStructure.content = " + structure.getContent());
            structure = structure.getNext();
        } while ((structure.hasNext()));
        System.out.println("LinkedStructure.content = " + structure.getContent());
    }
}