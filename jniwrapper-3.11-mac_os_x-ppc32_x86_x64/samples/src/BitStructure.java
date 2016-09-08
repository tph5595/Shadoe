/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.Structure;
import com.jniwrapper.BitField;
import com.jniwrapper.Parameter;

/**
 * This class represents a bit structure.
 * <br>struct BitStructure
 * <br>{
 * <br> &nbsp &nbsp unsigned b1 : 1;
 * <br> &nbsp &nbsp unsigned b2 : 2;
 * <br> &nbsp &nbsp unsigned b3 : 3;
 * <br> &nbsp &nbsp unsigned b4 : 2;
 * <br> &nbsp &nbsp unsigned b5 : 10;
 * <br>};
 *
 * @author Vadim Steshenko
 */
public class BitStructure extends Structure
{
    private BitField _b1 = new BitField(1);
    private BitField _b2 = new BitField(2);
    private BitField _b3 = new BitField(3);
    private BitField _b4 = new BitField(2);
    private BitField _b5 = new BitField(10);

    BitStructure()
    {
        init(new Parameter[]{_b1, _b2, _b3, _b4, _b5});
    }

    public long getB1()
    {
        return _b1.getValue();
    }

    public void setB1(long value)
    {
        _b1.setValue(value);
    }

    public long getB2()
    {
        return _b2.getValue();
    }

    public void setB2(long value)
    {
        _b2.setValue(value);
    }

    public long getB3()
    {
        return _b3.getValue();
    }

    public void setB3(long value)
    {
        _b3.setValue(value);
    }

    public long getB4()
    {
        return _b4.getValue();
    }

    public void setB4(long value)
    {
        _b4.setValue(value);
    }

    public long getB5()
    {
        return _b5.getValue();
    }

    public void setB5(long value)
    {
        _b5.setValue(value);
    }

    public Object clone()
    {
        BitStructure result = new BitStructure();
        result.initFrom(this);
        return result;
    }
}