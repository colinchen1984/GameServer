package network;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-28
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public abstract class SendPacket implements Packet, Cloneable
{
	public Object getPacket()
	{
		Object result = null;
		try
		{
			result = super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	public void send(Channel channel)
	{
		SendHelpBuffer helpBuffer = new SendHelpBuffer();
		writeData2Buffer(helpBuffer);
		int needBytes = helpBuffer.getNeedBytes() + 4;
		helpBuffer.reset();
		ChannelBuffer buffer = channel.getConfig().getBufferFactory().getBuffer(needBytes);
		buffer.writeShort(needBytes - 2);
		buffer.writeShort(getPacketID());
		helpBuffer.setBuffer(buffer);
		writeData2Buffer(helpBuffer);
		channel.write(buffer);
	}

	protected abstract void writeData2Buffer(SendHelpBuffer buffer);
}
