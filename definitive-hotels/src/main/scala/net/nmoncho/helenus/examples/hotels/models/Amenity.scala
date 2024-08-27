package net.nmoncho.helenus.examples.hotels.models

import net.nmoncho.helenus.api.RowMapper

final case class Amenity(name: String, description: String) derives RowMapper
