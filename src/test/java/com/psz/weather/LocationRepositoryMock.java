package com.psz.weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationRepositoryMock implements LocationRepository {

    private List<Location> locations = new ArrayList<>();

    @Override
    public Location save(Location location) {
        location.setId(1);
        locations.add(location);
        return location;
    }

    @Override
    public List<Location> getLocations() {
        return Collections.emptyList();
    }
}
