package net.nmoncho.helenus.examples.hotels.models

import net.nmoncho.helenus.api.RowMapper

final case class PointOfInterest(name: String, description: String) derives RowMapper
