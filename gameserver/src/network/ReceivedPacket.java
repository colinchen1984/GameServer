package network;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-26
 * Time: 下午10:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class ReceivedPacket implements Packet, Cloneable
{
	private Channel channel;

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

	public abstract void readFromBuffer(ChannelBuffer buff);

	public abstract void run();

	public void setChannel(Channel channel)
	{
		this.channel = channel;
	}

	protected Channel getChannel()
	{
		return channel;
	}
}
