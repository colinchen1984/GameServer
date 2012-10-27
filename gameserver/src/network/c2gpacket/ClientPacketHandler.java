package network.c2gpacket;

import network.Packet;
import network.ReceivedPacketHanlder;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-10-27
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class ClientPacketHandler extends ReceivedPacketHanlder
{
	private static C2GPacketFactory packetFactory = new C2GPacketFactory();

	@Override
	protected Packet getPacketByID(short id)
	{
		return packetFactory.getPacketByID(id);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception
	{
		//处理客户端断开逻辑

		e.getChannel().close();
	}

}
