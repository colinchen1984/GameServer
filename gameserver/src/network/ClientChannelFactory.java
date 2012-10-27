package network;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.ServerSocketChannel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-27
 * Time: 上午9:54
 * To change this template use File | Settings | File Templates.
 */
public class ClientChannelFactory extends NioServerSocketChannelFactory
{
	public ClientChannelFactory( Executor bossExecutor, Executor workerExecutor)
	{
		super(bossExecutor, workerExecutor);
	}

	public ClientChannelFactory()
	{
		this(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
	}

	@Override
	public ServerSocketChannel newChannel(ChannelPipeline pipeline)
	{
		ServerSocketChannel channel = super.newChannel(pipeline);
		channel.getFactory();
		return channel;
	}

}
