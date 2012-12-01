package network;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-12-1
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
public class PacketProcessThread extends Thread {
	private PacketFactory packetFactory = null;
	public PacketProcessThread(PacketFactory factory, Runnable runnable){
		super(runnable);
		this.packetFactory = factory;
	}

	public PacketFactory getPacketFactory(){
		return packetFactory;
	}

	static public class PacketProcessThreadFactory implements ThreadFactory{
		private Class factoryType = null;
		static AtomicInteger count = new AtomicInteger(0);
		public PacketProcessThreadFactory(Class factoryType){
			this.factoryType = factoryType;
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread result = null;
			try {
				result = new PacketProcessThread((PacketFactory)factoryType.newInstance(), r);
				result.setName("thread " + factoryType.getName() + count.addAndGet(1));
			} catch(InstantiationException e) {
				e.printStackTrace();
			} catch(IllegalAccessException e) {
				e.printStackTrace(); 
			}
			return result;
		}
	}
}
