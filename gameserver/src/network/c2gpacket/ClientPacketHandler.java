package network.c2gpacket;

import network.Packet;
import thread.PacketProcessThread;
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
	@Override
	protected Packet getPacketByID(short id)
	{
		PacketProcessThread thread = (PacketProcessThread) Thread.currentThread();
		return thread.getPacketFactory().getPacketByID(id);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception
	{
		//处理客户端断开逻辑

		e.getChannel().close();
	}

}
