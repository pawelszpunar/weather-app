package com.psz.weather;

import java.util.List;
import java.util.Optional;

public interface LocationRepository {
    Location save(Location location);
    List<Location> getLocations();
    Optional<Location> findById(Integer id);
}

