package network;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-26
 * Time: 下午10:56
 * To change this template use File | Settings | File Templates.
 */
public interface Packet extends Cloneable
{
	public String getPacketName();

	public Packet getPacket();

	public short getPacketID();
}
