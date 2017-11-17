package com.openoneapp.protocol;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class EmptyElementConverter implements Converter<String>
{
    @Override
    public String read(InputNode node) throws Exception
    {

    	return "";
    }

    @Override
    public void write(OutputNode node, String value) throws Exception
    {
        /* Simple implementation: do nothing here ;-) */
    }
}