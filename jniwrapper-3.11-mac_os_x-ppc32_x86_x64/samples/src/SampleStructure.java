/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class represents a structure with fields of the most frequently used types.
 * On the native side it is declared as:
 * <br>struct SampleStructure
 * <br>{
 * <br> &nbsp &nbsp bool b;
 * <br> &nbsp &nbsp short s;
 * <br> &nbsp &nbsp int i;
 * <br> &nbsp &nbsp long l;
 * <br> &nbsp &nbsp char c;
 * <br> &nbsp &nbsp wchar_t w;
 * <br> &nbsp &nbsp float f;
 * <br> &nbsp &nbsp double d;
 * <br> &nbsp &nbsp long double ld;
 * <br>};
 *
 * @author Alexey Razoryonov
 */
public class SampleStructure extends Structure
{
    private static final short ALIGNMENT = 8;

    private Bool _boolField = new Bool(true);
    private ShortInt _shortIntField = new ShortInt();
    private Int _intField = new Int();
    private LongInt _longIntField = new LongInt();
    private Char _charField = new Char();
    private WideChar _wideCharField = new WideChar();
    private SingleFloat _floatField = new SingleFloat();
    private DoubleFloat _doubleField = new DoubleFloat();
    private LongDouble _longDoubleField = new LongDouble();

    public SampleStructure()
    {
        init(new Parameter[]
        {
            _boolField,
            _shortIntField,
            _intField,
            _longIntField,
            _charField,
            _wideCharField,
            _floatField,
            _doubleField,
            _longDoubleField
        }, ALIGNMENT);
    }

    public SampleStructure(SampleStructure that)
    {
        this();
        initFrom(that);
    }

    public boolean getBoolField()
    {
        return _boolField.getValue();
    }

    public void setBoolField(boolean boolField)
    {
        _boolField.setValue(boolField);
    }

    public long getShortIntField()
    {
        return _shortIntField.getValue();
    }

    public void setShortIntField(long shortIntField)
    {
        _shortIntField.setValue(shortIntField);

    }

    public long getIntField()
    {
        return _intField.getValue();
    }

    public void setIntField(long intField)
    {
        _intField.setValue(intField);
    }

    public long getLongIntField()
    {
        return _longIntField.getValue();
    }

    public void setLongIntField(long longIntField)
    {
        _longIntField.setValue(longIntField);
    }

    public char getCharField()
    {
        return _charField.getValue();
    }

    public void setCharField(char charField)
    {
        _charField.setValue(charField);
    }

    public char getWideCharField()
    {
        return _wideCharField.getValue();
    }

    public void setWideCharField(char wideCharField)
    {
        _wideCharField.setValue(wideCharField);
    }

    public double getFloatField()
    {
        return _floatField.getValue();
    }

    public void setFloatField(double floatField)
    {
        _floatField.setValue(floatField);
    }

    public double getDoubleField()
    {
        return _doubleField.getValue();
    }

    public void setDoubleField(double doubleField)
    {
        _doubleField.setValue(doubleField);
    }

    public double getLongDoubleField()
    {
        return _longDoubleField.getValue();
    }

    public void setLongDoubleField(double longDoubleField)
    {
        _longDoubleField.setValue(longDoubleField);
    }

    public Object clone()
    {
        return new SampleStructure(this);
    }
}