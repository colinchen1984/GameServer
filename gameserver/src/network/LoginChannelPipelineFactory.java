package network;

import network.l2gpacket.L2GPacketFactory;
import network.l2gpacket.LoginPacketHandler;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.DefaultSocketChannelConfig;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import thread.PacketProcessThread;

import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-10-27
 * Time: 下午4:14
 * To change this template use File | Settings | File Templates.
 */
public class LoginChannelPipelineFactory implements ChannelPipelineFactory{
	//为网络包的执行添加线程池
	final static OrderedMemoryAwareThreadPoolExecutor upventExecutor =
			new OrderedMemoryAwareThreadPoolExecutor(
					5, 1000000, 10000000, 100,
					TimeUnit.MILLISECONDS);

	{
		upventExecutor.setThreadFactory(new PacketProcessThread.PacketProcessThreadFactory(L2GPacketFactory.class));
	}

	private class SocketSetConfig extends SimpleChannelUpstreamHandler{
		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception{
			DefaultSocketChannelConfig config = (DefaultSocketChannelConfig) e.getChannel().getConfig();
			if(null != config){
				//将buffer设为小字节序
				config.setBufferFactory(HeapChannelBufferFactory.getInstance(ByteOrder.LITTLE_ENDIAN));
				//设置buffer大小
				config.setReceiveBufferSize(1024 << 3);
				config.setSendBufferSize(1024 << 3);
			}else{
				e.getChannel().close();
			}
		}
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception{
		//添加确认包长度Pipe
		ChannelPipeline p = Channels.pipeline();
		p.addLast("SocketConfig", new SocketSetConfig());
		LengthFieldBasedFrameDecoder frameDecoder = new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 2);
		p.addLast("Decoder", frameDecoder);
		//默認不通過該pool來處理下行數據，而是直接在上行數據的的處理函數中直接發送下行數據
		//如果確實需要線程吃來處理下行數據，可以創建新的線程池
		//但是不推薦在上行數據處理池內同時處理下行數據e
		//因爲該池是order的池，同時處理上下行數據，會導致上行包的處理杯下行包影響
		p.addLast("Excutor", new ExecutionHandler(upventExecutor));
		p.addLast("Packet Handler", new LoginPacketHandler());
		return p;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
