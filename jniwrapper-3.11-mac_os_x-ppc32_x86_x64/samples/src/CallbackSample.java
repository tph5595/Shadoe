/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.Callback;
import com.jniwrapper.Function;
import com.jniwrapper.Int;
import com.jniwrapper.Parameter;

/**
 * This class demonstrates the {@link Callback} class functionality. The sum of all the natural numbers from 1 to
 * the number parameter value is calculated in callback on the Java side. The callback is passed to the native
 * function calculateSum.
 *
 * @author Alexey Razoryonov
 */
public class CallbackSample extends BasicJNIWrapperSample
{
    /**
     * Returns the sum of all the numbers from 0 to the arg value, including the last one.
     */
    private static class SumCalculator extends Callback
    {
        private Int _arg = new Int();
        private Int _result = new Int();

        public SumCalculator()
        {
            init(new Parameter[]{_arg}, _result);
        }

        public void callback()
        {
            int number = (int) _arg.getValue();
            int sum = 0;

            for (int i = 0; i <= number; i++)
            {
                sum += i;
            }
            _result.setValue(sum);
        }
    }

    public static void main(String[] args)
    {
        Function calculateSum = SAMPLE_LIB.getFunction("calculateSum");
        SumCalculator myCallBack = new SumCalculator();
        int number = 4;
        Int sum = new Int();
        calculateSum.invoke(sum, new Parameter[]
        {
            myCallBack,
            new Int(number)
        });
        System.out.println("The sum of all the numbers from 1 to " + number + " is " + sum.getValue());
        myCallBack.dispose();
    }
}