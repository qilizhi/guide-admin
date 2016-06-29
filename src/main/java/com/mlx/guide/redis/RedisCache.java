package com.mlx.guide.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCache implements Cache {
	public  static final long DEFAULT_EXPIRED_TIME=86400;

	private RedisTemplate<String, Object> redisTemplate;
	private String name;
    private long expireTime=DEFAULT_EXPIRED_TIME;	

	
     

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		// TODO Auto-generated method stub
		return this.redisTemplate;
	}

	@Override
	public ValueWrapper get(Object key) {
		// TODO Auto-generated method stub
		final String keyf = String.valueOf(key);
		Object object = null;
		object = redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {

				byte[] key = keyf.getBytes();
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				return toObject(value);

			}
		});
		return (object != null ? new SimpleValueWrapper(object) : null);
	}

	@Override
	public void put(Object key, Object value) {
		// TODO Auto-generated method stub
		final String keyf = String.valueOf(key);
		final Object valuef = value;
			redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] valueb = toByteArray(valuef);
				connection.set(keyb, valueb);
				if (expireTime > 0) {
					connection.expire(keyb, expireTime);
				}
				return 1L;
			}
		});
	}

	/**
	 * 描述 : <Object转byte[]>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	private byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 描述 : <byte[]转Object>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param bytes
	 * @return
	 */
	private Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	@Override
	public void evict(Object key) {
		// TODO Auto-generated method stub
		final String keyf = String.valueOf(key);
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(keyf.getBytes());
			}
		});
	}

	
	public boolean delCacheByKey(Object key){
		final String keyf = String.valueOf(key);
		redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(keyf.getBytes());
			}
		});
		
		return true;		
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Class<T> type) {

		return (T) this.get(key).get();
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		ValueWrapper vw = this.get(key);
		if (vw == null) {
			this.put(key, value);
			vw = this.get(key);
		}
		;
		return vw;
	}

	/**
	 * 设置key的生命期失效时间 （经过一段时间）
	 * 
	 * @param key
	 * @param timeout
	 * @param unit
	 *            {@link TimeUnit}
	 */
	public void expire(String key, long timeout, TimeUnit unit) {
		redisTemplate.expire(key, timeout, unit);
	}

	/**
	 * 
	 * 设置key 的失效时间点（某一时刻）
	 * 
	 * @param key
	 * @param date
	 */
	public void expireAt(String key, Date date) {
		redisTemplate.expireAt(key, date);

	}

	/**
	 * 以秒为单 位设置失效时间
	 * 
	 * @param key
	 * @param seconds
	 */
	public void expireBySecond(String key, long seconds) {
		redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}

	public boolean setToCacheWithTime(String key, Object obj, long seconds) {
		this.put(key, obj);
		expireBySecond(key, seconds);
		return true;
	}

	/**
	 * 
	 * 自定义生成key 的方法， 类名+:+方法名+:+参数
	 * 
	 * @return
	 */
	@Bean(name="keyGenerator")
	public KeyGenerator customKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(":");
				sb.append(method.getName());
				sb.append(":");
				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return null;
	}


}
