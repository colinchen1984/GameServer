package network.l2gpacket;

import network.Packet;
import network.PacketFactory;
import network.ReceivedPacketHanlder;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-10-27
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class LoginPacketHandler extends ReceivedPacketHanlder{
	private static PacketFactory packetFactory = new L2GPacketFactory(false);

	@Override
	protected Packet getPacketByID(short id){
		return packetFactory.getPacketByID(id);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception{
		//处理客户端断开逻辑

		e.getChannel().close();
	}
}
