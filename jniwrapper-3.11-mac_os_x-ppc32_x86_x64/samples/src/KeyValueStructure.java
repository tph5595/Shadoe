/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class represents a structure with pointer fields.
 * On the native side it is declared as:
 * <br>struct KeyValueStructure
 * <br>{
 * <br> &nbsp &nbsp int* key;
 * <br> &nbsp &nbsp char* value;
 * <br>};
 *
 * @author Alexey Razoryonov
 */
public class KeyValueStructure extends Structure
{
    private static final short ALIGNMENT = 8;

    private Int _intField = new Int();
    private AnsiString _stringField = new AnsiString();

    public KeyValueStructure()
    {
        init(new Parameter[]
        {
            new Pointer(_intField),
            new Pointer(_stringField)
        }, ALIGNMENT);
    }

    public KeyValueStructure(int intField, String stringField)
    {
        this();
        setIntField(intField);
        setStringField(stringField);
    }

    public KeyValueStructure(KeyValueStructure that)
    {
        this();
        initFrom(that);
    }

    public long getIntField()
    {
        return _intField.getValue();
    }

    public void setIntField(long intField)
    {
        _intField.setValue(intField);
    }

    public String getStringField()
    {
        return _stringField.getValue();
    }

    public void setStringField(String stringField)
    {
        _stringField.setValue(stringField);
    }

    public Object clone()
    {
        return new KeyValueStructure(this);
    }
}