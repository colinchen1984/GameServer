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

public class PacketFactory{
	final Map<Integer, Packet> packetFactory = new HashMap<Integer, Packet>(128);
    final PacketIOHelper sendHelper = new PacketIOHelper();
	boolean useCachePacket = false;

	public PacketFactory(boolean useCachePacket){
		this.useCachePacket = useCachePacket;
	}

	//單獨爲每一個線程創建一個專屬與該線程的packet map
	//在每個線程處理網絡包時，同樣的ID的網絡包在一個線程內
	//的任意時刻指可能出現一次，所以可以通過cachedPacketFactory
	//來避免不斷的創建新的Packet object
	public Packet getPacketByID(short packetid){
		final Packet packet = packetFactory.get((int)packetid);
		final Packet result;
		if(null == packet || useCachePacket){
			result = packet;
		}else{
			result = packet.getPacket();
		}
		return result;

	}

	public void regiestePacket(Packet packet) throws RuntimeException{
		Integer packetID = (int)packet.getPacketID();
		Packet old = packetFactory.get(packetID);
		if(null != old){
			throw new RuntimeException("packet id " + packetID + " is registed with packet " + old.getClass().getName());
		}
		packetFactory.put(packetID, packet);
	}

    protected PacketIOHelper getSendHelper() {
        return sendHelper;
    }
}
