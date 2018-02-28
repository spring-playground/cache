package com.spring.cache.repository;

import com.spring.cache.domain.Country;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@CacheConfig(cacheNames = "countries")
public class CountryRepository {

	@Cacheable
	public Country findByCode(String code) {
		System.out.println("---> Loading country with code '" + code + "'");
		return new Country(code);
	}

}
