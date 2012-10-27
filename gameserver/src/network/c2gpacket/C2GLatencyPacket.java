package network.c2gpacket;

import network.PacketID;
import network.ReceivedPacket;
import network.SendPacket;
import network.g2cpacket.G2CLatencyPacket;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-28
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
public class C2GLatencyPacket extends ReceivedPacket
{
	@Override
	public short getPacketID()
	{
		return PacketID.C2GLatencyPacket;
	}

	private long sendTime;

	@Override
	public void readFromBuffer(ChannelBuffer buff)
	{
		sendTime = buff.readLong();
	}

	@Override
	public void run()
	{
		Channel channel = getChannel();
		SendPacket packet = new G2CLatencyPacket(sendTime);
		packet.send(channel);
	}

}
