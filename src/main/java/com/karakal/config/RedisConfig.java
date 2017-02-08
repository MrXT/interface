package com.karakal.config;
import com.karakal.commons.redis.KarakalShardedJedis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by GhostMan on 2016/9/6.
 */
@Configuration
public class RedisConfig {
    @Value("${redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${redis.pool.maxTotal}")
    private int maxTotal;
    @Value("${redis.pool.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${redis.pool.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${redis.cluster.nodes}")
    private String nodes;
    @Value("${redis.masterHost}")
    private String masterHost;
    @Value("${redis.masterPort}")
    private int masterPort;
    @Value("${redis.commandTimeout}")
    private int commandTimeout;
    @Value("${redis.pwd}")
    private String pwd;
    
    
    @Bean(name="jedisPool")
    public JedisPool jedisPool(){
        //配置线程池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return new JedisPool(jedisPoolConfig, masterHost,masterPort,maxWaitMillis,pwd);
    }
    @Bean(name="jedisCluster")
    public JedisCommands jedisCommands(){
        //配置线程池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        //配置
        if(StringUtils.isNotEmpty(nodes)){//集群配置
            String[] serverArray = nodes.split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
            Set<HostAndPort> nodes = new HashSet<>();
            for (String ipPort : serverArray) {
                String[] ipPortPair = ipPort.split(":");
                nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
            }
            return new JedisCluster(nodes,commandTimeout,jedisPoolConfig);
        }else{
            JedisShardInfo jedisShardInfo = new JedisShardInfo(masterHost,masterPort);
            jedisShardInfo.setPassword(pwd);
            ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, Arrays.asList(jedisShardInfo));
            KarakalShardedJedis jedisCluster = new KarakalShardedJedis();
            jedisCluster.setShardedJedisPool(shardedJedisPool);
            return jedisCluster;
        }
    }
}