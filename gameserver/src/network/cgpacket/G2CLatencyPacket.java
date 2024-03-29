package network.cgpacket;

import network.PacketID;
import network.PacketIOHelper;
import network.SendPacket;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-28
 * Time: 下午5:25
 * To change this template use File | Settings | File Templates.
 */
public class G2CLatencyPacket extends SendPacket{

    private long sendtime = 0;

    public void setSendtime(long sendtime) {
        this.sendtime = sendtime;
    }

	public G2CLatencyPacket(PacketIOHelper sendHelper){
        super(sendHelper);
    }


	@Override
	public short getPacketID(){
		return PacketID.G2CLatencyPacket;
	}

	@Override
	protected void writeData2Buffer(PacketIOHelper buffer){
		buffer.writeString("chenlintest");
		buffer.writeLong(sendtime);
		buffer.writeLong(0);
	}

}
