package network;

import org.jboss.netty.buffer.ChannelBuffer;
import player.Player;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-26
 * Time: 下午10:58
 * To change this template use File | Settings | File Templates.
 */
public abstract class ReceivedPacket implements Packet{
	private Player player;
	private final String packetName = this.getClass().getName();
    private final PacketFactory packetFactory;

    public PacketFactory getPacketFactory() {
        return packetFactory;
    }

    protected ReceivedPacket(PacketFactory packetFactory) {
        this.packetFactory = packetFactory;
    }

    public Packet getPacket(){
		Packet result = null;
		try{
			result = (Packet) super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public String getPacketName(){
		return packetName;
	}

	public abstract void readFromBuffer(ChannelBuffer buff);

	public abstract void run();

	public void setPlayer(Player player){
		this.player = player;
	}

	protected Player getPlayer(){
		return player;
	}
}
