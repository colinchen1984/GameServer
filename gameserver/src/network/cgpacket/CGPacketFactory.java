package network.cgpacket;

import network.PacketFactory;
import network.PacketIOHelper;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-10-27
 * Time: 下午4:20
 * To change this template use File | Settings | File Templates.
 */
public class CGPacketFactory extends PacketFactory{
	{
        //client send 2 gameserver packet
		this.regiestePacket(new C2GLatencyPacket(this));

        //------------------------------------------------
        //gameserver send 2 client packet
        PacketIOHelper sendHelper = getSendHelper();
		this.regiestePacket(new G2CLatencyPacket(sendHelper));
	}

	public CGPacketFactory(){
		this(true);
	}

	public CGPacketFactory(boolean useCachePacket){
		super(useCachePacket);
	}
}
