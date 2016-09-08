/*
 * Copyright (c) 2000-2009 TeamDev Ltd. All rights reserved.
 * TeamDev PROPRIETARY and CONFIDENTIAL.
 * Use is subject to license terms.
 */
import com.jniwrapper.Int;
import com.jniwrapper.Parameter;
import com.jniwrapper.Pointer;
import com.jniwrapper.Structure;

/**
 * This class represents a linked structure, i.e. structure that is designed for creating dynamic linked lists.
 * On the native side it is declared as:
 * <br>struct LinkedStructure
 * <br>{
 * <br> &nbsp &nbsp int content;
 * <br> &nbsp &nbsp LinkedStructure* next;
 * <br>};
 *
 * @author Alexey Razoryonov
 */
public class LinkedStructure extends Structure
{
    private static final short ALIGNMENT = 8;

    private Int _content = new Int();
    private Pointer _next = new Pointer(LinkedStructure.class);

    public LinkedStructure()
    {
        init(new Parameter[]
        {
            _content,
            _next
        }, ALIGNMENT);
    }

    public LinkedStructure(LinkedStructure that)
    {
        this();
        initFrom(that);
    }

    public int getContent()
    {
        return (int) _content.getValue();
    }

    public void setContent(int content)
    {
        _content.setValue(content);
    }

    public LinkedStructure getNext()
    {
        if (!_next.isNull())
        {
            return (LinkedStructure) _next.getReferencedObject();
        }
        return null;
    }

    public void setNext(LinkedStructure next)
    {
        _next.setReferencedObject(next, next == null);
    }

    public boolean hasNext()
    {
        return !_next.isNull();
    }

    public Object clone()
    {
        return new LinkedStructure(this);
    }
}