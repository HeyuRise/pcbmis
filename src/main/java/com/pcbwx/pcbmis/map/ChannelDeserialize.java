package com.pcbwx.pcbmis.map;

public abstract class ChannelDeserialize<DST> {
	public abstract DST deserialize(Object src) throws Exception;
	
	public abstract static class None extends ChannelDeserialize<Object> { }
}
