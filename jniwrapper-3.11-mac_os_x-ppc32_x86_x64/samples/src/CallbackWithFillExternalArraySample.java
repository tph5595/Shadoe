/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class demonstrates the {@link com.jniwrapper.Callback} class functionality. The external array
 * is filled in callback on the Java side.
 *
 * @author Vadim Steshenko
 */
public class CallbackWithFillExternalArraySample extends BasicJNIWrapperSample
{
    /**
     * Fill an extern array with vales from user defined diapason.
     */
    private static class FillArrayCallback extends Callback
    {
        private Int _from = new Int();
        private Int _count = new Int();
        private Pointer.Void _arrayPtr = new Pointer.Void();

        private Pointer.Void _returnValue = new Pointer.Void();

        public FillArrayCallback()
        {
            init(new Parameter[]{
                    _from,
                    _count,
                    _arrayPtr
            }, _returnValue);
        }

        public void callback()
        {
            long from = _from.getValue();
            int size = (int) _count.getValue();
            PrimitiveArray array = new PrimitiveArray(Int.class, size);
            Pointer arrayPtr = new Pointer(array);
            // make arrayPtr to refer to the same address that arrayPtr does
            _arrayPtr.castTo(arrayPtr);
            // write elements to that external array 
            for (int i = 0; i < size; i++, from++)
            {
                array.setElement(i, new Int(from));
            }
        }
    }

    public static void main(String[] args)
    {
        Function fillArray = SAMPLE_LIB.getFunction("fillArray");
        FillArrayCallback myCallback = new FillArrayCallback();
        Bool result = new Bool();
        fillArray.invoke(result, new Parameter[]{
                new Int(1),
                new Int(10),
                myCallback
        });
        System.out.println("result = " + result);
        myCallback.dispose();
    }
}