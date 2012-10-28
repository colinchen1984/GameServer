package network.c2gpacket;

import network.PacketFactory;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-10-27
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public class C2GPacketFactory extends PacketFactory
{
	{
		this.regiestePacket(new C2GLatencyPacket());
	}

	public C2GPacketFactory(boolean useCachePacket)
	{
		super(useCachePacket);
	}
}
