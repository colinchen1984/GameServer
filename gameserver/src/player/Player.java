package player;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.jboss.netty.channel.Channel;
import pool.Poolable;
import pool.PoolableFactory;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-12-5
 * Time: 下午11:46
 */
final public class Player implements Poolable{

	static GenericObjectPool playerObjectPool = null;
	static {
		playerObjectPool = new GenericObjectPool<Object>(new PoolableFactory(){
			@Override
			public Object makeObject() throws Exception{
				return new Player();
			}
		},
				10, GenericObjectPool.WHEN_EXHAUSTED_GROW, 10);
	}
	private Channel channel;


	public static Player getPlayer(Channel channel){
		Player result = null;
		try{
			result = (Player) playerObjectPool.borrowObject();
			result.setChannel(channel);
		}catch(Exception e){
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return result;
	}

	public static void returnPlayer(Player player){
		try{
			playerObjectPool.returnObject(player);
		}catch(Exception e){
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	public Player(){

	}

	public void setChannel(Channel channel){
		this.channel = channel;
	}

	public Channel getChannel(){
		return channel;
	}

	@Override
	public boolean willGetFromPool(){
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean willReturnToPool(){
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
