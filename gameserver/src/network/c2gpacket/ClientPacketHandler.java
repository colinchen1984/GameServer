package network.c2gpacket;

import network.Packet;
import network.ReceivedPacketHanlder;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import player.Player;
import thread.PacketProcessThread;

import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-10-27
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class ClientPacketHandler extends ReceivedPacketHanlder{

	private void onConnectionClosed(Channel channel){
		Player player = (Player) channel.getAttachment();
		Player.returnPlayer(player);
		channel.setAttachment(null);
		channel.close();
	}

	@Override
	protected Packet getPacketByID(short id){
		PacketProcessThread thread = (PacketProcessThread) Thread.currentThread();
		return thread.getPacketFactory().getPacketByID(id);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception{
		//处理客户端断开逻辑
		onConnectionClosed(e.getChannel());
	}

	/**
	 * Invoked when a {@link org.jboss.netty.channel.Channel} was closed and all its related resources
	 * were released.
	 */
	public void channelClosed(
			ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		onConnectionClosed(e.getChannel());
	}

}
