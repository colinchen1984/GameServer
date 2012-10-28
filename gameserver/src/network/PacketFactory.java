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
	boolean useCachePacket = false;

	public PacketFactory(boolean useCachePacket)
	{
		this.useCachePacket = useCachePacket;
	}

	public Packet getPacketByID(short packetid)
	{
		final Packet packet = packetFactory.get(packetid);
		if(null == packet)
		{
			return packet;
		}
		return useCachePacket ? packet : packet.getPacket();

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
