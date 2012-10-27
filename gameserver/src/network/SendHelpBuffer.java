package network;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-28
 * Time: 下午5:04
 * To change this template use File | Settings | File Templates.
 */
public class SendHelpBuffer
{
	private ChannelBuffer buffer = null;
	private int needBytes = 0;
	public SendHelpBuffer()
	{}

	public void setBuffer(ChannelBuffer buffer)
	{
		this.buffer = buffer;
	}

	public int getNeedBytes()
	{
		return needBytes;
	}

	public void reset()
	{
		needBytes = 0;
		buffer = null;
	}

	final private static int longlen = Long.SIZE / Byte.SIZE;
	public void writeLong(long value)
	{
		if(null != buffer)
		{
			buffer.writeLong(value);
		}
		needBytes += longlen;
	}

	public void writeString(String value)
	{
		final byte[] data = value.getBytes();
		writeBytes(data);
	}

	final private static int intlen = Integer.SIZE / Byte.SIZE;
	public void writeInt(int value)
	{
		if(null != buffer)
		{
			buffer.writeInt(value);
		}
		needBytes += intlen;
	}

	final private static int shortlen = Short.SIZE / Byte.SIZE;
	public void writeShort(short value)
	{
		if(null != buffer)
		{
			buffer.writeShort(value);
		}
		needBytes += shortlen;
	}

	final private static int charlen = Character.SIZE / Byte.SIZE;
	public void writeChar(char value)
	{
		if(null != buffer)
		{
			buffer.writeChar(value);
		}
		needBytes += charlen;
	}

	public void writeBytes(byte[] value)
	{
		if(null != buffer)
		{
			buffer.writeBytes(value);
		}
		needBytes += value.length;
	}

	final private static int bytelen = Byte.SIZE / Byte.SIZE;
	public void writeByte(byte value)
	{
		if(null != buffer)
		{
			buffer.writeByte(value);
		}
		needBytes += bytelen;
	}

	final private static int floatlen = Float.SIZE / Byte.SIZE;
	public void writeFloat(float value)
	{
		if(null != buffer)
		{
			buffer.writeFloat(value);
		}
		needBytes += floatlen;
	}

	final private static int doublelen = Double.SIZE / Byte.SIZE;
	public void writeFloat(double value)
	{
		if(null != buffer)
		{
			buffer.writeDouble(value);
		}
		needBytes += doublelen;
	}
}
