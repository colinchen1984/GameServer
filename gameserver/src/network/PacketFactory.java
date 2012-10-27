package network;

import org.apache.commons.collections.FastHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-26
 * Time: 下午11:04
 * To change this template use File | Settings | File Templates.
 */

public class PacketFactory
{
	final FastHashMap packetFactory = new FastHashMap();

	{
		packetFactory.setFast(true);
	}

	public Packet getPacketByID(short packetid)
	{
		final Packet packet = (Packet) packetFactory.get(packetid);
		Packet result = null != packet ? (Packet) packet.getPacket() : null;
		return result;
	}

	public void regiestePacket(Packet packet)
	{
		packetFactory.put(packet.getPacketID(), packet);
	}
}
