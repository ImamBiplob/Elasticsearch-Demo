package com.imambiplob.elasticsearchdemo.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public Config configCache() {
        return new Config()
                .setInstanceName("hazel-instance")
                .addMapConfig(new MapConfig()
                        .setName("employee-cache")
                        .setTimeToLiveSeconds(3000)
                        .setEvictionConfig(new EvictionConfig()
                                .setSize(200)
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                                .setEvictionPolicy(EvictionPolicy.LRU)
                        )
                );
    }
}
