package com.sacidpak.clients.depot;

public record DepotResponse(
        Long id,
        String name,
        String type,
        String city
) {
}
