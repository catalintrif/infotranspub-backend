package ro.gov.ithub.entity.util;

import lombok.Getter;

public enum RouteType {

    TRAM_STREETCAR_LIGHTRAIL(0),
    SUBWAY_METRO(1),
    RAIL(2),
    BUS(3),
    FERRY(4),
    CABLE_CAR(5),
    GONDOLA_SUSPENDEDCABLECAR(6),
    FUNICULAR(7);

    @Getter
    private int ordinal;

    RouteType(int ord) {
        this.ordinal = ord;
    }
}
