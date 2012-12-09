package network;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-28
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public abstract class SendPacket implements Packet, Cloneable{
    private final PacketIOHelper sendHelper;
    private final String packetName = this.getClass().getName();

    protected SendPacket(PacketIOHelper sendHelper) {
        this.sendHelper = sendHelper;
    }

    @Override
    public String getPacketName(){
        return packetName;
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

	public void send(Channel channel){
        sendHelper.reset();;
		writeData2Buffer(sendHelper);
		int needBytes = sendHelper.getNeedBytes() + 4;
        sendHelper.reset();
		ChannelBuffer buffer = channel.getConfig().getBufferFactory().getBuffer(needBytes);
		buffer.writeShort(needBytes - 2);
		buffer.writeShort(getPacketID());
        sendHelper.setBuffer(buffer);
		writeData2Buffer(sendHelper);
		channel.write(buffer);
	}

	protected abstract void writeData2Buffer(PacketIOHelper buffer);
}
