package player;

import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-12-5
 * Time: 下午11:46
 */
final public class Player{

	private Channel channel;


	public static Player getPlayer(Channel channel){
		Player result = new Player();
		result.setChannel(channel);
		return result;
	}

	public void setChannel(Channel channel){
		this.channel = channel;
	}

	public Channel getChannel(){
		return channel;
	}
}
