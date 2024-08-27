package net.nmoncho.helenus.examples.hotels.models

import java.util.UUID
import net.nmoncho.helenus.api.ColumnNamingScheme
import net.nmoncho.helenus.api.SnakeCase
import net.nmoncho.helenus.api.RowMapper

final case class Guest(
    id: java.util.UUID,
    firstName: String,
    lastName: String,
    title: Option[String],
    emails: Set[String],
    phoneNumbers: List[String],
    addresses: Map[String, Address],
    confirmationNumber: String
)

object Guest {
  given ColumnNamingScheme = SnakeCase

  given RowMapper[Guest] =
    RowMapper.deriveRenamed[Guest](_.id -> "guest_id", _.confirmationNumber -> "confirm_number")
}
