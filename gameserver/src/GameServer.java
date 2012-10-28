import network.ClientChannelPipelineFactory;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-26
 * Time: 下午9:52
 * To change this template use File | Settings | File Templates.
 */
public class GameServer
{
	public static void main(String[] args) throws Exception
	{

		//读取各种配置
		//初始化资源
		//连接login

		//初始化网络
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory());

		// Set up the pipeline factory.
		bootstrap.setPipelineFactory(new ClientChannelPipelineFactory());
		bootstrap.bind(new InetSocketAddress(11111));
		return;
	}
}
