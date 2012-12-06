package pool;

import org.apache.commons.pool.PoolableObjectFactory;

import java.security.InvalidParameterException;

/**
 * Created with IntelliJ IDEA.
 * User: chenlin
 * Date: 12-12-6
 * Time: 下午11:06
 * To change this template use File | Settings | File Templates.
 */
public abstract class PoolableFactory implements PoolableObjectFactory{

	@Override
	public void destroyObject(Object obj) throws Exception{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public boolean validateObject(Object obj){
		return true;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void activateObject(Object obj) throws Exception{
		if(!(obj instanceof Poolable)){
			throw new IllegalArgumentException("object " + obj.toString() + "isn't implement Poolable");
		}
		Poolable o = (Poolable) obj;
		o.willGetFromPool();
	}

	@Override
	public void passivateObject(Object obj) throws Exception{
		if(!(obj instanceof Poolable)){
			throw new IllegalArgumentException("object " + obj.toString() + "isn't implement Poolable");
		}
		Poolable o = (Poolable) obj;
		o.willReturnToPool();
	}
}
