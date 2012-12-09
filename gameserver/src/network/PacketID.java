package network;

/**
 * Created with IntelliJ IDEA.
 * User: colin
 * Date: 12-9-28
 * Time: 上午11:52
 * To change this template use File | Settings | File Templates.
 */
public class PacketID{
    //使用了虛擬機選項-XX:AutoBoxCacheMax=1024將Integer 的緩存設置爲1024
    //從而在box的時候不用產生新的Integer對象，從而提高效率
    //因此，在分配PacketID的時候，儘量將ID控制在1024以內
	public static final short C2GLatencyPacket = 0;
	public static final short G2CLatencyPacket = 1;


	public static final short split = 512;
	public static final short G2LTestPacket = 512;
}
