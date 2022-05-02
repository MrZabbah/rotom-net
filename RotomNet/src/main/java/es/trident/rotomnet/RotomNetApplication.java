package es.trident.rotomnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@SpringBootApplication
@EnableHazelcastHttpSession
@EnableCaching
public class RotomNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(RotomNetApplication.class, args);
	}
	
	@Bean 
	public Config config() {
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();		
		joinConfig.getMulticastConfig().setEnabled(true);
		
		//MapConfigs
		MapConfig cardMapConfig = new MapConfig()
				.setName("cards")
				.setTimeToLiveSeconds(800);
		MapConfig teamMapConfig = new MapConfig()
				.setName("teams")
				.setTimeToLiveSeconds(800);
		
		config.addMapConfig(cardMapConfig);
		config.addMapConfig(teamMapConfig);
		
		return config;
	}
	
	@Bean
	public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
		return new HazelcastCacheManager(hazelcastInstance);
	}
	
	@Bean
	public HazelcastInstance hazelcastInstance(Config config){
		return Hazelcast.newHazelcastInstance(config);
	}
	
	/*
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("cards", "teams");
	}
	*/
	
}
