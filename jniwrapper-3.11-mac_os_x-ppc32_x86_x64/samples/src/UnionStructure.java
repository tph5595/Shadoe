/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.*;

/**
 * This class represents a structure that consists of a union and a flag.
 * The union of UnionStructure contains either symbol or code, depending on the isSymbol flag value.
 * If isSymbol is true, union contains a symbol.
 * On the native side it is declared as:
 * <br>struct UnionStructure<br>
 * {<br>
 * &nbsp &nbsp bool isChar;<br>
 * &nbsp &nbsp union<br>
 * &nbsp &nbsp {<br>
 * &nbsp &nbsp  &nbsp &nbsp wchar_t symbol;<br>
 * &nbsp &nbsp  &nbsp &nbsp int code;<br>
 * };};
 *
 * @author Alexey Razoryonov
 */
public class UnionStructure extends Structure
{
    private static final short ALIGNMENT = 8;

    private IntBool _isSymbol = new IntBool();
    private Int _code = new Int();
    private WideChar _symbol = new WideChar();
    private Union _union = new Union(new Parameter[]{_symbol, _code});

    public UnionStructure()
    {
        init(new Parameter[]
        {
            _isSymbol,
            _union
        }, ALIGNMENT);
    }

    public UnionStructure(UnionStructure that)
    {
        this();
        initFrom(that);
    }

    public boolean isSymbol()
    {
        return _isSymbol.getBooleanValue();
    }

    public void setIsSymbol(boolean flag)
    {
        _isSymbol.setBooleanValue(flag);
    }

    public int getCode()
    {
        _union.setActiveMember(_code);
        return (int) _code.getValue();
    }

    public void setCode(int code)
    {
        _union.setActiveMember(_code);
        _code.setValue(code);
    }

    public char getSymbol()
    {
        _union.setActiveMember(_symbol);
        return _symbol.getValue();
    }

    public Object clone()
    {
        return new UnionStructure(this);
    }

    public void setSymbol(char symbol)
    {
        _union.setActiveMember(_symbol);
        _symbol.setValue(symbol);
    }
}