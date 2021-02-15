package com.acarrillo.touche.domain.repositories;

public interface Repository {
    enum CachePolicy{ NEVER, ALWAYS }
    void setCachePolicy(CachePolicy cachePolicy);
}
