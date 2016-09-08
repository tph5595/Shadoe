/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class demonstrates the {@link Callback} class functionality. The new array is created and
 * filled  in callback on the Java side. The callback is passed to the native function createArray.
 *
 * @author Vadim Steshenko
 */
public class CallbackWithArraySample extends BasicJNIWrapperSample
{
    /**
     * Create a new array of int and fill with vales from user defined diapason.
     */
    private static class CreateArrayCallback extends Callback
    {
        private Int _from = new Int();
        private Int _to = new Int();
        private Int _count = new Int();
        private PrimitiveArray _array = new PrimitiveArray(Int.class, 0);
        private Pointer.OutOnly _arrayPtr = new Pointer.OutOnly(_array);

        private Pointer.Void _returnValue = new Pointer.Void();

        public CreateArrayCallback()
        {
            init(new Parameter[]{
                    _from,
                    _to,
                    new Pointer(_count),
                    new Pointer.OutOnly(_arrayPtr)
            }, _returnValue);
        }

        public void callback()
        {
            long from = _from.getValue();
            long to = _to.getValue();
            int size = (int) Math.abs(to - from);
            _count.setValue(size);
            _array.setElementCount(size);
            for (int i = 0; i < size; i++)
            {
                long val = (from < to) ? from + i : from - i;
                _array.setElement(i, new Int(val));
            }
        }
    }

    public static void main(String[] args)
    {
        Function createArray = SAMPLE_LIB.getFunction("createArray");
        CreateArrayCallback myCallback = new CreateArrayCallback();
        Int count = new Int();
        PrimitiveArray myArray = new PrimitiveArray(Int.class, 0);
        ExternalArrayPointer myArrayPtr = new ExternalArrayPointer(myArray);
        createArray.invoke(myArrayPtr, new Parameter[]{
                new Int(10),
                new Int(20),
                new Pointer(count),
                myCallback
        });
        long size = count.getValue();
        System.out.println("Elements:");
        myArrayPtr.readArray((int) size);
        for (int i = 0; i < size; i++)
        {
            System.out.println("element[" + i + "] = " + myArray.getElement(i));
        }
        myCallback.dispose();
    }
}