package com.pcbwx.pcbmis.component;

public class ServiceRouter {
//	private static Logger logger = Logger.getLogger(ServiceRouter.class);

	private static CacheService cacheService;

	public void init() {

	}

	public static CacheService getCacheService() {
		return cacheService;
	}
	public static void setCacheService(CacheService cacheService) {
		ServiceRouter.cacheService = cacheService;
	}
}
