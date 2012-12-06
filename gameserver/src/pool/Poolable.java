package pool;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-12-6
 * Time: 下午11:04
 * To change this template use File | Settings | File Templates.
 */
public interface Poolable{

	//該對象將被從池中取出
	public boolean willGetFromPool();

	//該對象將被還給池
	public boolean willReturnToPool();
}
