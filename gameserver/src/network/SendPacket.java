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
	public Packet getPacket()
	{
		Packet result = null;
		try
		{
			result = (Packet) super.clone();
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
		PacketIOHelper helper = new PacketIOHelper();
		writeData2Buffer(helper);
		int needBytes = helper.getNeedBytes() + 4;
		helper.reset();
		ChannelBuffer buffer = channel.getConfig().getBufferFactory().getBuffer(needBytes);
		buffer.writeShort(needBytes - 2);
		buffer.writeShort(getPacketID());
		helper.setBuffer(buffer);
		writeData2Buffer(helper);
		channel.write(buffer);
	}

	protected abstract void writeData2Buffer(PacketIOHelper buffer);
}
