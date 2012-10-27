package network;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-26
 * Time: 下午10:47
 * To change this template use File | Settings | File Templates.
 */
public abstract class ReceivedPacketHanlder extends SimpleChannelUpstreamHandler
{
	private static Log log = LogFactory.getLog(ReceivedPacketHanlder.class);
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception
	{
		Object message = e.getMessage();
		if(!(message instanceof ChannelBuffer))
		{
			throw new RuntimeException("PacketHandler can't handle meaage which type is" + message.getClass().getName());
		}
		log.info("Receive packet from " + e.getChannel().getAttachment());
		ChannelBuffer buffer = (ChannelBuffer)message;
		short packetDataLength = buffer.readShort();
		short packetID = buffer.readShort();
		ReceivedPacket packet = (ReceivedPacket) getPacketByID(packetID);
		if(null == packet)
		{
			log.warn("Unkown packet with id " + packetID + "and data length is " + packetDataLength);
			return;
		}
		packet.setChannel(e.getChannel());
		packet.readFromBuffer(buffer);
		packet.run();
	}

	protected abstract Packet getPacketByID(short id);
}
