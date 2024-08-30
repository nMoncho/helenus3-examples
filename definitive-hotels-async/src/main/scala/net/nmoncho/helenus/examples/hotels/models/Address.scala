package net.nmoncho.helenus.examples.hotels.models

import net.nmoncho.helenus.api.ColumnNamingScheme
import net.nmoncho.helenus.api.SnakeCase
import net.nmoncho.helenus.api.`type`.codec.Codec
import net.nmoncho.helenus.api.`type`.codec.UDTCodec

given ColumnNamingScheme = SnakeCase // `stateOrProvince` is actually `state_or_province`

final case class Address(
    street: String,
    city: String,
    stateOrProvince: String,
    postalCode: String,
    country: String
) derives UDTCodec
