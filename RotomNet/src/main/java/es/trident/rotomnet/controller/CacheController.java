package es.trident.rotomnet.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.spring.cache.HazelcastCacheManager;

@RestController
public class CacheController {
	
	@Autowired
	private CacheManager cacheManager;
	
	@GetMapping("/cache/cards")
	public Map<Object, Object> getCardsCacheContent(){
		HazelcastCacheManager manager = (HazelcastCacheManager) cacheManager;
		return manager.getHazelcastInstance().getMap("cards");
	}
	
	@GetMapping("/cache/teams")
	public Map<Object, Object> getTeamsCacheContent(){
		HazelcastCacheManager manager = (HazelcastCacheManager) cacheManager;
		return manager.getHazelcastInstance().getMap("teams");
	}
}
