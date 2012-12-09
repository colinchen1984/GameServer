package network.cgpacket;

import network.PacketFactory;
import network.PacketID;
import network.ReceivedPacket;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-28
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
public class C2GLatencyPacket extends ReceivedPacket{
    public C2GLatencyPacket(PacketFactory packetFactory) {
        super(packetFactory);
    }

    @Override
    public short getPacketID(){
		return PacketID.C2GLatencyPacket;
	}

	private long sendTime;

	@Override
	public void readFromBuffer(ChannelBuffer buff){
		sendTime = buff.readLong();
	}

	@Override
	public void run(){
		Channel channel = getPlayer().getChannel();
        G2CLatencyPacket packet = (G2CLatencyPacket) getPacketFactory().getPacketByID(PacketID.G2CLatencyPacket);
        packet.setSendtime(sendTime);
        packet.send(channel);

	}

}
