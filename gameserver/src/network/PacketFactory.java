package network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-26
 * Time: 下午11:04
 * To change this template use File | Settings | File Templates.
 */

public class PacketFactory
{
	final Map<Short, Packet> packetFactory = new HashMap<Short, Packet>(128);
	Map<Thread, Map<Short, Packet>> cachedPacketFactory = null;

	public PacketFactory(boolean useCachePacket)
	{
		if(useCachePacket)
		{
			cachedPacketFactory = new HashMap<Thread, Map<Short, Packet>>(8);
		}
	}

	//單獨爲每一個線程創建一個專屬與該線程的packet map
	//在每個線程處理網絡包時，同樣的ID的網絡包在一個線程內
	//的任意時刻指可能出現一次，所以可以通過cachedPacketFactory
	//來避免不斷的創建新的Packet object
	public Packet getPacketByID(short packetid)
	{
		final Packet packet = packetFactory.get(packetid);
		if(null == packet)
		{
			return packet;
		}

		if(null == cachedPacketFactory)
		{
			return packet.getPacket();
		}

		final Thread thread = Thread.currentThread();
		Map<Short, Packet> packetMap = cachedPacketFactory.get(thread);
		Packet result;
		if(null == packetMap)
		{
			packetMap = new HashMap<Short, Packet>(128);
			synchronized (cachedPacketFactory)
			{
				cachedPacketFactory.put(thread, packetMap);
			}
			result = packet.getPacket();
			packetMap.put(packetid, result);
			return result;
		}
		result = packetMap.get(packetid);
		if(null == result)
		{
			result = packet.getPacket();
			packetMap.put(packetid, result);
		}
		return result;

	}

	public void regiestePacket(Packet packet) throws RuntimeException
	{
		Short packetID = packet.getPacketID();
		Packet old = packetFactory.get(packetID);
		if(null != old)
		{
			throw new RuntimeException("packet id " + packetID + " is registed with packet " + old.getClass().getName());
		}
		packetFactory.put(packetID, packet);
	}
}
