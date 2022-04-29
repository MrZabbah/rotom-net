package es.trident.rotomnet;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

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
		
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("127.0.0.1"));
		//joinConfig.getMulticastConfig().setEnabled(true);
		return config;
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("cards", "teams");
	}
}
